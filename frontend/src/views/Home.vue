<template>
  <div class="background">  
    <Navbar/>
    <div>
      <Story/>
    </div>
    <div style="max-width: 50rem;  margin: auto;">
      <Post/>
    </div>
    <div>
      <Favorites/>
    </div>
  </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'
import Post from '../components/Post.vue'
import Story from '../components/Story.vue'
import Favorites from '../components/Favorites.vue'
import axios from 'axios'

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
    Favorites
  },
  mounted() {
    var id;
    if(this.User.id == null) id = -1;
    else id = this.User.id; 
    axios.get("http://localhost:8082/api/post/get-posts-for-feed/" + id)
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
        this.$store.dispatch('updatePosts', posts)
      })
  }
};
</script>

<style>
 .background {
    background-color: #3498db;
    min-width: 100%;
    min-height: 100%;
    position: absolute;
  }

  .container-1 {
    display: flex;
    flex-direction: column;
  }

  .container-story1 {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    background-color: whitesmoke;
    border-radius: 20px;
    box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);
    margin:10px;
    /* justify-content:space-between; */
  }
  .container-story2 {
    transition: 0.2s all ease-in-out;
  }

  .container-story2:hover {
    margin-top: -10px;
  }
  .item-story2 {
    width: 84px;
    height: 84px;
    margin: 10px;
  }
  .card {
    background: white;
    margin: 15px;
    border-radius: 20px;
}

.box {
    width: 550px;
    padding: 40px;
    position:relative;
    background-color: white;
    box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
    text-align: center;
    transition: 0.25s;
    margin-top: 20px;
    margin-left: 100px;
    border-radius: 20px; 
}
.cls{
  margin-bottom: 40px;
}
.title {
  font-family: fantasy;
  font-size: 30px;
  color: black;
}
.app {
  padding: 20px;
}
.image{
    max-width: 420px;
    max-height: 500px;
}
</style> 