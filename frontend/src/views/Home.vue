<template>
  <body>
    <div>  
      <Navbar/>
      <Post/>
    </div>
  </body>
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