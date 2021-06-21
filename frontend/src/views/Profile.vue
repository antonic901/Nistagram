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
            <button class="btn btn-primary" style="margin:5px; margin-right:20px; border-radius:20px;">Follow</button>
            <button class="btn btn-outline-primary" style="margin:5px; margin-right:20px; margin-bottom:20px; border-radius:20px;" v-on:click="clickEdit">Edit</button>
          </div>
      </div>
      <div v-if="showEdit" style="margin:15px">
          <EditProfile v-bind:showEdit="showEdit"/>
      </div>
    </div>
     <div class="container-1">
        <div class="container-2" v-for="post in this.Posts" :key="post.id" v-on:click="change(post.id)">
          <b-img-lazy class="item-2" rounded="circle" :src="post.imagesAndVideos[0]"></b-img-lazy>
        </div>
    </div>
    <div class="container-1">
        <div class="container-2" v-for="post in this.Posts" :key="post.id" v-on:click="change(post.id)">
          <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
        </div>
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
      showEdit: false
    }
  },
  methods: {
    change(postId) {
      alert("TODO Nemanja" + postId)
    },
    clickEdit() {
      if(this.showEdit) this.showEdit = false
      else this.showEdit = true
    }
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
    justify-content:space-between;
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

  .item-1 {
    width: 292px;
    height: 292px;
    margin: 10px;
    border-radius: 15px;
  }

  .item-2 {
    width: 84px;
    height: 84px;
    margin: 10px;
  }

  .item-3 {
    align-self: center;
    margin: 20px;
  }

  .card {
    background: white;
    margin: 15px;
    border-radius: 20px;
}
</style>
