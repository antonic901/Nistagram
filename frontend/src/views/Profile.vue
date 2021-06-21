<template>
 <div class = "background">
    <Navbar/>
    <div class="container">
      <div class="main-body"> 
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="../assets/user.jpg" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                      <h4>John Doe</h4>
                      <p class="text-secondary mb-1">Full Stack Developer</p>
                      <p class="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                      <button class="btn btn-primary">Follow</button>
                      <button class="btn btn-outline-primary">Message</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Username</h6>
                    </div>
                    <div class="col-sm-9 text-secondary" >
                      kenneth123
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Kenneth 
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Surname</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Valdez
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fip@jukmuh.al
                    </div>
                  </div>
                  <hr>
                   <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Birtday</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fip@jukmuh.al
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Phone</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      (239) 816-9029
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Gender</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Male
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Website</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fff
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Biography</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fff
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-12">
                      <button class="btn btn-primary">Edit</button>
                    </div>
                    <div class="col-sm-12">
                      <button class="btn btn-primary" v-b-modal.modal-scrollable>New story</button>
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
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
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

export default {
  name: "Profile",
  components: {
      Navbar
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
      i: 0,
      url: []
    }
  },
  methods: {
    change(post) {
      this.post = post
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
  }  
}
</script>

<style>
  .container-1 {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
  }

  .container-2 {
    transition: 0.2s all ease-in-out;
  }
  .container-2:hover {
    margin-top: -10px;
  }

  .item-1 {
    width: 292px;
    height: 292px;
    margin: 10px;
    border-radius: 15px;

  }

 .background {
  background-color: #3498db;
  background-repeat: repeat-y;
  position:absolute;
  top: 0; 
  left: 0; 
  min-width: 100%;
  min-height: 100%;

} 
.body{
    margin-top:20px;
    color: #1a202c;
    text-align: left;
    background-color: #e2e8f0;    
}
.main-body {
    padding: 15px;
}

.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid rgba(0,0,0,.125);
    border-radius: .25rem;
    margin-top: 90px;
}

.card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1rem;
}

.gutters-sm {
    margin-right: -8px;
    margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
    padding-right: 8px;
    padding-left: 8px;
}
.mb-3, .my-3 {
    margin-bottom: 1rem!important;
}

.bg-gray-300 {
    background-color: #e2e8f0;
}
.h-100 {
    height: 100%!important;
}
.shadow-none {
    box-shadow: none!important;
}
@import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,400&display=swap");
*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Roboto", sans-serif;
}

.container2 {
  position:absolute;
  width: 100%;
  height: 100vh;
  background-color: #3498db;
  margin-top: 10px;
}

.card2 {
  width: 60%;
  height: 100%;
  max-height: 1000px;
  padding: auto;
  text-align: center;
  margin-top: 10px;
  border-radius: 10px;
 
}
.card__container {
  display: grid;
  width: 100%;
  height: 100%;
  grid-template-columns: 1fr 1fr 1fr;
  align-items: center;
  justify-items: center;
  color: white;
}
.card__content {
  width: 90%;
  height: 95%;
  background: #191a19;
  margin: 10px auto;
  border-radius: 5px;
  padding: 20px;
  cursor: pointer;
  transition: 0.3s all ease-in-out;
}
.card__content:hover {
  margin-top: -10px;
}
.card__header {
  text-transform: uppercase;
  font-size: 20px;
  margin: 40px auto;
}
.card__button {
  padding: 10px;
  border-radius: 50px;
  background: #1f75c4;
  color: white;
  font-weight: bold;
  cursor: pointer;
  border: none;
  margin: 50px auto;
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
  font-family: fantasy;
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
