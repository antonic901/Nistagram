<template>
  <div>
    <b-card v-for="post in posts" :key="post.id" no-body style="flex-direction: column; max-width: 50rem; margin-top: 50px; border-radius: 10px;
        box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);">
          <template #header>
            <b-row no-gutters>
                <b-col md="11">
                        <h4 class="mb-0">@{{post.user.username}}</h4>
                </b-col>
                <b-col md="1" style="text-align:right">
                 <b-icon v-if="isUserLogged" icon="exclamation-triangle" scale="1.25" v-b-modal.modalReport v-on:click="setType(post)" v-b-tooltip.hover.right.v-danger="'Report content'" variant="danger" ></b-icon>
                 
                </b-col>
              </b-row>
          </template>

          <b-card-body v-if="post.location != null" style="padding-top: 10px; padding-bottom: 0px;">
            <b-card-sub-title class="mb-2">{{post.location.display_name}}</b-card-sub-title>
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
                <b-col v-if="isUserLogged" md="1" style="text-align:right">
                    <b-icon icon="bookmark" scale="1.5" v-b-modal.modalFavorites v-b-tooltip.hover.right="'Add to favourites'" v-on:click="setType(post)"></b-icon>  
                </b-col>
            </b-row>
            <b-row v-if="isUserLogged" no-gutters>
              <b-icon v-on:click="addLikeDislike(true, post)" icon="hand-thumbs-up" scale="1.5" v-b-tooltip.hover.left.v-success="'I like this!'" variant="success" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;" ></b-icon>
              <label>{{countLikes(post)}}</label>
              <b-icon v-on:click="addLikeDislike(false, post)" icon="hand-thumbs-down" scale="1.5" v-b-tooltip.hover.right.v-danger="'Dislike'" variant="danger" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;"></b-icon>
              <label>{{countDislikes(post)}}</label>
            </b-row>
            <b-row v-else no-gutters>
              <b-icon icon="hand-thumbs-up" scale="1.5" v-b-tooltip.hover.left.v-success="'I like this!'" variant="success" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;" ></b-icon>
              <label>{{countLikes(post)}}</label>
              <b-icon icon="hand-thumbs-down" scale="1.5" v-b-tooltip.hover.right.v-danger="'Dislike'" variant="danger" style="margin-top: 10px; margin-bottom: 0px; margin-left: 10px; margin-right: 10px;"></b-icon>
              <label>{{countDislikes(post)}}</label>
            </b-row>
          </b-card-body>

          <b-list-group flush>
            <b-list-group-item v-for="comment in post.comments" :key="comment.id" b-list-group-item>
              <p><b style="font-size:16px;">@{{comment.user.username}}</b> {{comment.content}}</p>
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
      <div>
        <Report/>
      </div>
  </div>
</template>

<script>
import Report from '../components/Report.vue'

import axios from 'axios'

export default {
    components: { 
      Report,
        Report
    },
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
        }
    },
    data() {
      return {
        text:'',
        description: '',
        hashTags: [],
        profileTags: [],
        type: 'collection',
        posts: []
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
        await axios.post(this.$store.getters.getPostAPI + "/api/tag/create-tag", tag)

        var comment = {
            userId: this.user.id,
            postId: post.id,
            content: this.description,
            hashTags: this.hashTags,
            profileTags: this.profileTags
        }

        await axios.post(this.$store.getters.getPostAPI + "/api/comment/add-new-comment", comment)
          .then(r => {
            var newComment = JSON.parse(JSON.stringify(r.data))
            if(newComment.id == null) {
              alert(newComment.content)
            }
            else {
              axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + this.user.id)
                .then(r => {
                  newComment.user = JSON.parse(JSON.stringify(r.data))
                  post.comments.push(newComment)
                })
              this.text = ''
              // this.description = ''
              this.hashTags = []
              this.profileTags = []
            }

          })

      },
      findHashtags(searchText) {
            // var regexp = /\B\#\w\w+\b/g
            var regexp = /\#\w+\b/g
            var tags = searchText.match(regexp);
            if(tags == null ) this.hashTags = []
            else this.hashTags = tags
            
            var regexp = /\@\w+\b/g
            tags = searchText.match(regexp);
            if(tags == null) this.profileTags = [] 
            else this.profileTags = tags;

            this.description = this.text;

            try {
                this.hashTags.forEach(hashTag => {
                  this.description = this.description.replace(hashTag, "");
                })
                this.profileTags.forEach(hashTag => {
                  this.description = this.description.replace(hashTag, "");
                })
            } catch (error) {

            }

            this.description = this.description.trim()
        },
        setType(post) {
          var type = 'collection'
          this.$store.dispatch('updateType', type)
          this.$store.dispatch('updateEntity', post)
        },
        addLikeDislike(isLike, post) {
          var add = {
            userId: this.user.id,
            postId: post.id,
            like: isLike
          }

          axios.post(this.$store.getters.getPostAPI + "/api/post/add-like-dislike", add)
            .then(r => {
                var response = JSON.parse(JSON.stringify(r.data))
                response.forEach(likedislike => {
                  axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + likedislike.user.id)
                    .then(r => {
                      likedislike.user = JSON.parse(JSON.stringify(r.data))
                    })
                })
                post.likesDislikes = response
            })
        },
        countLikes(post) {
          var counter = 0
          post.likesDislikes.forEach(l => {
              if(l.like) counter = counter + 1
          })
          return counter
        },
        countDislikes(post) {
          var counter = 0
          post.likesDislikes.forEach(l => {
              if(!l.like) counter = counter + 1
          })
          return counter
        }
    },
    mounted() {
      var id;
      if(this.user.id == null) id = -1;
      else id = this.user.id; 
      axios.get(this.$store.getters.getPostAPI + "/api/post/get-posts-for-feed/" + id)
        .then(r => {
          this.posts = JSON.parse(JSON.stringify(r.data))
          this.posts.forEach(post => {
            axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + post.user.id)
              .then(r => {
                  post.user = JSON.parse(JSON.stringify(r.data))
              })
            post.comments.forEach(comment => {
              axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + comment.user.id)
                .then(r => {
                    comment.user = JSON.parse(JSON.stringify(r.data))
                })
            })
            post.likesDislikes.forEach(comment => {
              axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + comment.user.id)
                .then(r => {
                    comment.user = JSON.parse(JSON.stringify(r.data))
                })
            })
          })
        })
  }
}
</script>
