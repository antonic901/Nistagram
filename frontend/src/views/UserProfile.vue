<template>
 <div class="container-0">
    <Navbar/>
    <div class="container-3">
        <div class="container-5">
            <div class="card container-3">
                <b-img class="item-4" :src="require('../static/picture.jpg')" style="width:250px; margin-bottom:10px;border-radius:20px;"></b-img>
                <div class="container-4">
                    <h4 class="item-3">{{User.name}} {{User.surname}}</h4>
                    <p class="item-3 text-secondary mb-1" style="max-width:200px;">{{User.biography}}</p>
                    <b-link class="item-3" style="text-decoration: underline;" :href="User.website">Website</b-link>
                    <div class="container-3">
                        <button class="btn btn-primary" style="margin:5px; margin-right:20px; border-radius:20px; width:100px;" v-on:click="follow">{{this.message1}}</button>
                        <button class="btn btn-primary" style="margin:5px; margin-right:20px; border-radius:20px;width:200px;" v-on:click="block">{{this.message2}}</button>
                    </div>
                    <div class="container-3">
                        <button class="btn btn-primary" style="margin:5px; margin-right:20px; border-radius:20px;width:100px;" v-on:click="mute">{{this.message3}}</button>
                        <button class="btn btn-primary" style="margin:5px; margin-right:20px; border-radius:20px;width:200px;" v-on:click="closedFriend">{{this.message4}}</button>
                    </div>
                    
                </div>
            </div>
            <div v-if="show" class="container-5">
                <label class="item-3" style="margin-left:20px;color:white;"><b>This Account is Private</b></label>
                <label class="item-3" style="margin-left:20px;color:white;"><b>Follow to see their photos and videos.</b></label>
            </div>
        </div>
      <div v-if="showEdit" style="margin:15px">
          <EditProfile v-bind:showEdit="showEdit"/>
      </div>
    </div>
    <div v-if="!show" class="container-1" style="height:84px;margin-bottom:40px;">
      <div class="container-6" v-for="highLight in this.highLights" :key="highLight.id" v-b-modal.modalHighLight v-on:click="changeStory(highLight)">
        <b-img-lazy class="item-2" rounded="circle" :src="highLight.stories[0].imagesAndVideos[0]"></b-img-lazy>
        <label class="item-5" style="color:white;text-aling:center;"><b>{{highLight.name}}</b></label>
      </div>
    </div>
    <div v-if="this.highLight != null">
      <b-modal id="modalHighLight" size="lg" :hide-footer="true" :title="this.highLight.name">
        <b-card no-body class="overflow-hidden" style="max-width: auto; max-height: auto; margin-top: 0px;">
          <b-row no-gutters>
            <b-col md="12">
              <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <b-card-img :src="highLight.stories[i].imagesAndVideos[j]" alt="Image" class="rounded-0"></b-card-img>
                </div>
                <a class="carousel-control-prev" role="button" v-on:click="previusHighlightStory" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" role="button" v-on:click="nextHighlightStory" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
                </a>
              </div>
            </b-col>
          </b-row>
        </b-card>
      </b-modal>
    </div>
    <div v-if="!show" class="container-1">
        <div class="container-2" v-for="post in this.Posts" :key="post.id" v-b-modal.modalAdditionalPost v-on:click="change(post)">
          <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
        </div>
    </div>
    <div>
      <PostModal/>
    </div>
  </div>

</template>

<script>

import axios from 'axios'
import Navbar from '../components/Navbar.vue'
import EditProfile from '../components/EditProfile.vue'
import PostModal from '../components/PostModal.vue'

export default {
  name: "Profile",
  components: {
      Navbar,
      EditProfile,
      PostModal
  },
  computed: {
    User() {
      return this.$store.getters.getUserProfile;
    },
    LoggedUser() {
      return this.$store.getters.getUser
    }
  },
  data() {
    return {
      showEdit: false,
      Posts: [],
      highLights: [],
      highLight: null,
      i: 0,
      j: 0,
      message1: '',
      message2: '',
      message3: '',
      message4: '',
      show: false
    }
  },
  methods: {
    change(post) {
      this.$store.dispatch('updateEntity', post)
    },
    clickEdit() {
      if(this.showEdit) this.showEdit = false
      else this.showEdit = true
    },
    changeStory(highLight) {
      this.highLight = highLight
    },
    previusHighlightStory() {
        var i = this.i;
        var j = this.j;
        
        j = j - 1;

        if(i == 0 && j < 0) {
          i = this.highLight.stories.length - 1
          j = this.highLight.stories[i].imagesAndVideos.length - 1
        }

        if(j < 0) {
          i = i - 1
          j = this.highLight.stories[i].imagesAndVideos.length - 1
        }



        this.i = i;
        this.j = j;
    },
    nextHighlightStory() {
        var i = this.i;
        var j = this.j;

        j = j + 1;

        if(j >= this.highLight.stories[i].imagesAndVideos.length) {
          j = 0;
          i = i + 1;
        }
        
        if(i >= this.highLight.stories.length) {
          i = 0;
          j = 0;
        }

        this.i = i;
        this.j = j;
    },
    async follow() {
        if(this.LoggedUser.id == null) {
            this.$router.push({name: 'LoginPage'})
            return
        }

        if(this.message2 == 'Unblock') {
            alert("You can't follow user that is blocked!")
            return
        }

        var check = {
            userPostId: this.User.id,
            userViewId: this.LoggedUser.id
        }
        if(this.message1 == 'Follow') {
            await axios.post("http://localhost:8081/api/userprofile/follow", check)
            this.message1 = 'Unfollow'
            this.show = false
        }
        else {
            await axios.post("http://localhost:8081/api/userprofile/unfollow", check)
            this.message1 = 'Follow'
            this.message4 = 'Add to closed friends'
            this.show = this.User.private
        }
    },
    async block() {
        if(this.LoggedUser.id == null) {
            this.$router.push({name: 'LoginPage'})
            return
        }

        var check = {
            userPostId: this.User.id,
            userViewId: this.LoggedUser.id
        }
        if(this.message2 == 'Block') {
            await axios.post("http://localhost:8081/api/userprofile/block", check)
            this.message2 = 'Unblock'
            this.message1 = 'Follow'
            this.show = this.User.private
        }
        else {
            await axios.post("http://localhost:8081/api/userprofile/unblock", check)
            this.message2 = 'Block'
        }
    },
    async mute() {
        if(this.LoggedUser.id == null) {
            this.$router.push({name: 'LoginPage'})
            return
        }

        var check = {
            userPostId: this.User.id,
            userViewId: this.LoggedUser.id
        }
        if(this.message3 == 'Mute') {
            await axios.post("http://localhost:8081/api/userprofile/mute", check)
            this.message3 = 'Unmute'
        }
        else {
            await axios.post("http://localhost:8081/api/userprofile/unmute", check)
            this.message3 = 'Mute'
        }
    },
    async closedFriend() {
        if(this.LoggedUser.id == null) {
            this.$router.push({name: 'LoginPage'})
            return
        }

        if(this.message1 == 'Follow') {
            alert("You are not following this user!")
            return
        }

        var check = {
            userPostId: this.User.id,
            userViewId: this.LoggedUser.id
        }
        if(this.message4 == 'Add to closed friends') {
            await axios.post("http://localhost:8081/api/userprofile/add-to-closed-friends", check)
            this.message4 = 'Remove from closed friends'
        }
        else {
            await axios.post("http://localhost:8081/api/userprofile/remove-from-closed-friends", check)
            this.message4 = 'Add to closed friends'
        }
    }
  },
  mounted() {
    axios.get("http://localhost:8082/api/user/get-posts-for-user/" + this.User.id)
      .then(r => {
        var posts = JSON.parse(JSON.stringify(r.data))
        posts.forEach(post => {
          axios.get("http://localhost:8081/api/userprofile/get-by-id/" + post.user.id)
            .then(r => {
                post.user = JSON.parse(JSON.stringify(r.data))
            })
          post.comments.forEach(comment => {
            axios.get("http://localhost:8081/api/userprofile/get-by-id/" + comment.user.id)
              .then(r => {
                  comment.user = JSON.parse(JSON.stringify(r.data))
              })
          })
        })
        this.Posts = posts
      })
    
    axios.get("http://localhost:8083/api/user/get-highlights/" + this.User.id)
      .then(r => {
        var response = JSON.parse(JSON.stringify(r.data))
        this.highLights = response;
      })
    
    var check = {
        userPostId: this.User.id,
        userViewId: this.LoggedUser.id
    }

    this.show = this.User.private


    if(this.LoggedUser.id == null) {
        this.message1 = 'Follow'
        this.message2 = 'Block'
        this.message3 = 'Mute'
        this.message4 = 'Add to closed friends'
        return
    }
    
    axios.post("http://localhost:8081/api/userprofile/check-is-user-following", check)
        .then(r => {
            if(r.data == 'not_following') {
                this.message1 = 'Follow'
            }
            else {
                this.show = false
                this.message1 = 'Unfollow'
            }
        })

    axios.post("http://localhost:8081/api/userprofile/check-is-user-blocked", check)
        .then(r => {
            if(r.data == 'not_blocked') {
                this.message2 = 'Block'
            }
            else {
                this.message2 = 'Unblock'
            }
        })

    axios.post("http://localhost:8081/api/userprofile/check-is-user-muted", check)
        .then(r => {
            if(r.data == 'not_muted') {
                this.message3 = 'Mute'
            }
            else {
                this.message3 = 'Unmute'
            }
        })

    axios.post("http://localhost:8081/api/userprofile/check-is-user-closed-friend", check)
        .then(r => {
            if(r.data == 'not_closedfriend') {
                this.message4 = 'Add to closed friends'
            }
            else {
                this.message4 = 'Remove from closed friends'
            }
        })

    this.$root.$on('bv::modal::hidden', (bvEvent, modalPost) => {
      this.i = 0
      this.j = 0
    })

    this.$root.$on('bv::modal::hidden', (bvEvent, modalHighLight) => {
      this.i = 0
      this.j = 0
    })
  }  
}
</script>

<style scoped>

  .container-0 {
    display: flex;
    flex-direction: column;
    background-color: #3498db;
    min-width: 100%;
    min-height: 100%;
    position:absolute;
  }

  .container-1 {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    /* justify-content:space-between; */
  }

  .container-2 {
    transition: 0.2s all ease-in-out;
  }

  .container-2:hover {
    margin-top: -10px;
  }

  .container-3 {
    display: flex;
    flex-direction: row;
  }

  .container-4 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 20px;
  }

  .container-5 {
      display: flex;
      flex-direction: column;
  }

  .container-6 {
    display: flex;
    flex-flow: column;
    margin-left: 20px;
    transition: 0.2s all ease-in-out;
  }

  .container-6:hover {
    margin-top: -10px;
  }

  .item-1 {
    width: 292px;
    height: 292px;
    margin: 10px;
    border-radius: 15px;
  }

  .item-2 {
    width: 84px;
    height: 84px;
    margin-left: 10px;
    margin-right: 10px;
    margin-top: 10px;
    align-self: center;
  }

  .item-3 {
    align-self: center;
    margin: 20px;
  }

  .item-5 {
    align-self: center;
    text-align: center;
    /* margin: 20px; */
  }

  .card {
    background: white;
    margin: 15px;
    border-radius: 20px;
}
</style>
