<template>
  <div class="background">  
    <Navbar/>
    <div>
      <Story/>
    </div>
    <div v-if="isUserLogged" style="display: flex;justify-content:center">
      <div class="container-story3">
          <b-img :src="'https://picsum.photos/34/34'" style="margin:5px;" rounded="circle"></b-img>
          <b-form-textarea v-b-modal.modalNewPost type="text" placeholder="What's on your mind?" no-resize style="font-style:italic;width:300px;height:34px;margin:5px;border-radius:20px"/>
      </div>
    </div>
    <div style="max-width: 50rem;  margin: auto;">
      <Post/>
    </div>
    <div>
      <Favorites/>
    </div>
    <div>
      <NewPost/>
    </div>
  </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'
import Post from '../components/Post.vue'
import Story from '../components/Story.vue'
import Favorites from '../components/Favorites.vue'
import NewPost from '../views/NewPost.vue'

export default {
  name: "Home",
  computed: {
        isUserLogged() {
            return this.$store.getters.isUserLogged
        },
        User() {
            return this.$store.getters.getUser
        }
  },
  components: {
    Navbar,
    Post,
    Story,
    Favorites,
    NewPost
  }
};
</script>

<style scoped>
 .background {
    background-color: #3498db;
    min-width: 100%;
    min-height: 100%;
    position: absolute;
  }

  .container-story3 {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    background-color: whitesmoke;
    border-radius: 10px;
    box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);
    margin:10px;
    max-width: 500px;
    justify-content:center;
  }
</style> 