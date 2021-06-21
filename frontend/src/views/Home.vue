<template>
  <div class="background">  
    <Navbar/>
    <div style="max-width: 50rem;  margin: auto;">
      <Post/>
      <Post/>
    </div>
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