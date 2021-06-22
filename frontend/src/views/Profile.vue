<template>
 <div class="container-0">
    <Navbar/>
    <div class="container-3">
      <div class="card container-3">
        <b-img class="item-4" :src="require('../static/picture.jpg')" style="width:250px; margin-bottom:10px;border-radius:20px;"></b-img>
          <div class="container-4">
            <h4 class="item-3">{{User.name}} {{User.surname}}</h4>
            <p class="item-3 text-secondary mb-1" style="max-width:200px;">{{User.biography}}</p>
            <b-link class="item-3" style="text-decoration: underline;" :href="User.website">Website</b-link>
            <button class="btn btn-primary" style="margin:5px; margin-right:20px; margin-bottom:20px; border-radius:20px;" v-b-modal.modal-scrollable>New story</button>
            <b-modal id="modal-scrollable" :hide-footer="true" size="lg" scrollable title="New story">
              <div class="cardStory">
                <form onsubmit="event.preventDefault()" class="box">
                  <div class = "cls">
                    <h1 class = "title">New story</h1>
                  </div>
                  <div style="font-style:italic" required class="app">
                      <input type="file" @change="onFileSelected" multiple>
                       <img style="margin:10px" class="image" v-for="u in url" :key="u.blob" :src="u" />
                  </div>
                  <b-button variant="light" >Create</b-button>
                </form>
              </div>
            </b-modal>
            <button class="btn btn-outline-primary" style="margin:5px; margin-right:20px; margin-bottom:20px; border-radius:20px;" v-on:click="clickEdit">Edit</button>
          </div>

      </div>
      <div v-if="showEdit" style="margin:15px">
          <EditProfile v-bind:showEdit="showEdit"/>
      </div>
    </div>
     <div class="container-1" style="height:84px;margin-bottom:40px;">
        <div class="container-5" v-for="highLight in this.highLights" :key="highLight.id" v-on:click="changeStory(highLight)">
          <b-img-lazy class="item-2" rounded="circle" :src="highLight.stories[0].imagesAndVideos[0]"></b-img-lazy>
          <label class="item-5" style="color:white;text-aling:center;"><b>{{highLight.name}}</b></label>
        </div>
    </div>
    <div class="container-1">
        <div class="container-2" v-for="post in this.Posts" :key="post.id" v-b-modal.modal-xl v-on:click="change(post)">
          <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
        </div>
    </div>
    <div v-if="this.post != null">
      <b-modal id="modal-xl" size="xl" :hide-footer="true" :title="'@' + this.User.username">

        <b-card no-body class="overflow-hidden" style="max-width: 1100px; max-height: 550px; margin-top: 0px;">
          <b-row no-gutters>
            <b-col md="8">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                      <div class="carousel-inner">
                          <b-card-img :src="this.post.imagesAndVideos[i]" alt="Image" class="rounded-0"></b-card-img>
                      </div>
                      <a class="carousel-control-prev" role="button" v-on:click="previus" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                      </a>
                      <a class="carousel-control-next" role="button" v-on:click="next" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                      </a>
                    </div>
            </b-col>
            <b-col md="4">
		          <b-card-body>
                  <b-card-text>
                    <p>{{this.post.caption.description}}</p>
                    <p v-for="t in this.post.caption.tags" :key="t.id">{{t.name}}</p>
                  </b-card-text>
                 </b-card-body>

            </b-col>
          </b-row>
        </b-card>
      </b-modal>
    </div>
  </div>

</template>

<script>

import axios from 'axios'
import Navbar from '../components/Navbar.vue'
import EditProfile from '../components/EditProfile.vue'

export default {
  name: "Profile",
  components: {
      Navbar,
      EditProfile
  },
  computed: {
    User() {
      return this.$store.getters.getUser;
    },
    Posts() {
      return this.$store.getters.getPosts;
    }
  },
  data() {
    return {
      showEdit: false,
      post: null,
      highLight: null,
      url: [],
      i: 0,
      highLights: []
    }
  },
  methods: {
    change(post) {
      this.post = post
    },
    clickEdit() {
      if(this.showEdit) this.showEdit = false
      else this.showEdit = true
    },
    changeStory(highLight) {
      this.highLight = highLight
    },
    previus() {
      var i = this.i - 1
      if(i < 0) {
        this.i = this.post.imagesAndVideos.length-1
        return
      }
      this.i = i
    },
    next() {
      var i = this.i + 1
      if(i >= this.post.imagesAndVideos.length) {
        this.i = 0
        return
      }
      this.i = i
    },
    onFileSelected(event) {
            this.url = []
            this.selectedFiles = event.target.files
            this.selectedFiles.forEach(selectedFile => {
                this.url.push(URL.createObjectURL(selectedFile));
            })
    },
  },
  created() {
    axios.get("http://localhost:8082/api/user/get-posts-for-user/" + this.User.id)
      .then(r => {
         var posts = JSON.parse(JSON.stringify(r.data))
         this.$store.dispatch('updatePosts', posts)
      })
    
    axios.get("http://localhost:8083/api/user/get-highlights/" + this.User.id)
      .then(r => {
        var response = JSON.parse(JSON.stringify(r.data))
        this.highLights = response;
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
    flex-flow: column;
    margin-left: 20px;
    transition: 0.2s all ease-in-out;
  }

  .container-5:hover {
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

  .item-4 {
    align-self: center;
    /* margin: 20px; */
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

.cardStory {
    margin-bottom: 20px;
    border: none;
   
}

.box {
    width: 550px;
    padding: 40px;
    position:relative;
    background-color: #3498db;
    box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
    text-align: center;
    transition: 0.25s;
    margin-top: 20px;
    margin-left: 75px;
    margin-right: 75px;
    border-radius: 20px; 
}
.cls{
  margin-bottom: 40px;
}
.title {
  font-family: arial;
  font-size: 30px;
  color: white;
}
.app {
  padding: 20px;
}
.image{
    max-width: 420px;
    max-height: 500px;
}
</style>
