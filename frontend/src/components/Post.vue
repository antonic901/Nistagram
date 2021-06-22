<template>
  <div>
    <b-card v-for="post in Posts" :key="post.id" no-body style="flex-direction: column; max-width: 50rem; margin-top: 50px; border-radius: 10px;
        box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);">
          <template #header>
            <b-row no-gutters>
                <b-col md="11">
                        <h4 class="mb-0">@{{post.user.username}}</h4>
                </b-col>
                <b-col md="1" style="text-align:right">
                 <b-icon icon="exclamation-triangle" scale="1.25" v-b-modal.modal-tall v-b-tooltip.hover.right.v-danger="'Report content'" variant="danger" ></b-icon>
                 <b-modal id="modal-tall" :hide-footer="true" title="REPORT CONTENT">
                    <b-form-group label="Report for:" v-slot="{ ariaDescribedby }" style="margin-left: 120px;">
                          <b-form-radio-group
                            id="btn-radios-3"
                            v-model="selected"
                            :options="options"
                            :aria-describedby="ariaDescribedby"
                            button-variant="outline-danger"
                            name="radio-btn-stacked"
                            buttons
                            stacked
                          ></b-form-radio-group>
                    </b-form-group>
                    <b-button variant="danger" style="margin-left: 200px; margine-top: 10px; margine-bottom: 10px;">Report</b-button>
                  </b-modal>
                </b-col>
              </b-row>
          </template>

          <b-card-body v-if="post.location != null" style="padding-top: 10px; padding-bottom: 0px;">
            <b-card-sub-title class="mb-2">{{post.location.country}}, {{post.location.city}}, {{post.location.street}}</b-card-sub-title>
          </b-card-body>

          <b-card-img :src="post.imagesAndVideos[0]" alt="Image"></b-card-img>

          <b-card-body>
            <b-row no-gutters>
                <b-col md="11">
                    <b-card-text>
                      <p>{{post.caption.description}}</p>
                      <p v-for="tag in post.caption.tags" :key="tag.id">{{tag.name}}</p> 
                    </b-card-text>
                </b-col>
                <b-col md="1" style="text-align:right">
                    <b-icon icon="bookmark" scale="1.5" v-b-modal.modal-center v-b-tooltip.hover.right="'Add to favourites'"></b-icon>
                      <b-modal id="modal-center" :hide-footer="true" title="Add to collection" style="height: 500px;">
                        <b-row no-gutters>
                            <b-col sm="8">
                              <b-form-input type="text" v-model="user.email" placeholder="Collection name" style="font-style:italic; width: 300px;"/>
                            </b-col>
                            <b-col sm="4">
                              <b-button variant="primary" style="margine-top: 10px; margine-bottom: 10px;">Create collection</b-button>
                            </b-col>
                          </b-row>
                          <b-row no-gutters>
                            <b-col sm="4">
                              <b-card-text style="margin-top: 15px; font-size: 20px;">
                                Select collection:
                              </b-card-text>
                            </b-col>
                            <b-col sm="8">
                              <b-form-input list="my-list-id" style="margin: 10px;"></b-form-input>
                              <datalist id="my-list-id">
                                <option>Manual Option</option>
                                <!--<option v-for="size in sizes">{{ size }}</option>-->
                              </datalist>
                            </b-col>
                          </b-row>
                          <div style="height: 100px;" ></div>
                          <b-button variant="primary" style="margin-left: 180px; margine-top: 10px; margine-bottom: 10px;">Add post</b-button>
                        </b-modal>
                </b-col>
            </b-row>
            <b-row no-gutters>
              <b-icon icon="hand-thumbs-up" scale="1.5" v-b-tooltip.hover.left.v-success="'I like this!'" variant="success" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;" ></b-icon>
              <b-icon icon="hand-thumbs-down" scale="1.5" v-b-tooltip.hover.right.v-danger="'Dislike'" variant="danger" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;"></b-icon>
            </b-row>
          </b-card-body>

          <b-list-group flush>
            <b-list-group-item v-for="comment in post.comments" :key="comment.id" b-list-group-item>
              <p><b>Comment of user with id:{{comment.user.id}}</b></p>
              <p>{{comment.content}}</p>
              <p v-for="tag in comment.tags" :key="tag.id">{{tag.name}}</p>
            </b-list-group-item>
          </b-list-group>

          <div v-if="isUserLogged" v-on:keyup.enter="addComment(post)">
            <b-form-textarea
              id="textarea-state" 
              v-model="text" 
              :state="text.length <= 300"
              placeholder="Leave a comment..."
              rows="3"
              no-resize
              trim
            ></b-form-textarea>
          </div>

          <!-- <b-card-footer footer-text-variant="muted">3 days ago</b-card-footer> -->

      </b-card>
  </div>
</template>

<script>
import LoginPage from '../views/LoginPage.vue'
import axios from 'axios'

export default {
  components: { LoginPage },
    name: "Post",
    computed: {
        user(){
            return this.$store.getters.getUser
        },
        isUserLogged() {
            return this.$store.getters.isUserLogged
        },
        userFullname() {
            return this.$store.getters.getFullName
        },
        Posts() {
            return this.$store.getters.getPosts
        }
    },
    data() {
      return {
        selected: 'radio1',
        options: [
          { text: 'UNWANTED CONTENT', value: 'radio1' },
          { text: 'NUDETY OR SEXUAL ACTIVITY', value: 'radio2' },
          { text: 'HATE SYMBOL', value: 'radio3' },
          { text: 'VIOLENCE', value: 'radio4' },
          { text: 'ILLEGAL SALE', value: 'radio5' },
          { text: 'HARASSMENT', value: 'radio6' },
          { text: 'COPYRIGHT INFRIGEMENT', value: 'radio7' },
          { text: 'SUICIDE', value: 'radio8' },
          { text: 'SCAM', value: 'radio9' },
          { text: 'FALSE_INFORMATION', value: 'radio10' }
        ],
        text:'',
        description: '',
        hashTags: []
      }
    },
    methods: {
      async addComment(post) {
        if(this.text == '') {
          alert("Enter something")
          return
        }
        if(this.text.length > 300) {
          alert("Comment is too long")
          return
        }

        this.findHashtags(this.text)

        var tag = {
          tags: this.hashTags
        }
        await axios.post("http://localhost:8082/api/tag/create-tag", tag)

        var comment = {
            userId: this.user.id,
            postId: post.id,
            content: this.description,
            tags: this.hashTags
        }

        await axios.post("http://localhost:8082/api/comment/add-new-comment", comment)
          .then(r => {
            var newComment = JSON.parse(JSON.stringify(r.data))
            post.comments.push(newComment)
            this.text = ''
            this.description = ''
            this.hashTags = []
          })

      },
      findHashtags(searchText) {
            // var regexp = /\B\#\w\w+\b/g
            var regexp = /\#\w+\b/g
            var tags = searchText.match(regexp);
            if(tags == null ) this.hashTags = []
            else this.hashTags = tags
            this.description = this.text;
            try {
                this.hashTags.forEach(hashTag => {
                    this.description = this.description.replace(hashTag, "");
                })
            } catch (error) {

            }

            this.description = this.description.trim()
        },
        async getUserName(userId) {
            var user;
            await axios.get("http://localhost:8081/api/userprofile/get-by-id/" + userId)
                    .then(r => {
                        user = JSON.parse(JSON.stringify(r.data))  
                    })

            return user.username
        }
    }
}
</script>
