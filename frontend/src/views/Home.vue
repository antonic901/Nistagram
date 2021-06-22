<template>
  <div class="background">  
    <Navbar/>
    <div class="container-story1">
         <b-icon v-if="isUserLogged" @click="$root.$emit('bv::show::modal', 'modal-scrollable', $event.target)" icon="plus-circle" scale="5" v-b-tooltip.hover.top="'Add a new story'" style="margin-left: 50px; margin-right: 40px; margin-top: 45px;"></b-icon>
         <b-modal id="modal-scrollable" :hide-footer="true" size="lg" body-bg-variant="primary" scrollable title="New story">
            <div class="cardStory">
              <form onsubmit="event.preventDefault()" class="box">
                  <div class = "cls">
                      <h1 class = "title">New story</h1>
                  </div>
                  <div style="font-style:italic" required class="app">
                      <input type="file" @change="onFileSelected" multiple>
                      <img style="margin:10px" class="image" v-for="u in url" :key="u.blob" :src="u" />
                  </div>
                  <div style="margin-bottom: 20px;">
                    <b-form-checkbox v-model="checked" name="check-button" switch>
                      <b>Close friends only</b>
                    </b-form-checkbox>
                  </div>
                  <b-button variant="primary" >Create</b-button>
              </form>
            </div>
          </b-modal>
        <div class="container-story2" v-for="i in 10" :key="i">
          <b-img-lazy class="item-story2" rounded="circle" src="https://placekitten.com/480/210" alt="Image"></b-img-lazy>
        </div>
    </div>
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
  computed: {
        isUserLogged() {
            return this.$store.getters.isUserLogged
        }
  },
  components: {
    Navbar,
    Post
  },
  data() {
    return {
      url: [],
      checked: false
    }
  },
  methods: {
    onFileSelected(event) {
            this.url = []
            this.selectedFiles = event.target.files
            this.selectedFiles.forEach(selectedFile => {
                this.url.push(URL.createObjectURL(selectedFile));
            })
    },
  },
};
</script>

<style>
 .background {
    background-color: #3498db;
  }
  .container-story1 {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    background-color: whitesmoke;
    border-radius: 20px;
    box-shadow: 12px 12px 12px 0 rgba(0,0,0,0.2);
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