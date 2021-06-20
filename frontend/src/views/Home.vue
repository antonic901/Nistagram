<template>
  <div class="background">  
    <Navbar/>
    <Post/>
  </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'
import Post from '../components/Post.vue'

export default {
  name: "Home",
  components: {
    Navbar,
    Post
  }
};
</script>

<style>
 .background {
    background-color: #3498db;
  }
</style> 