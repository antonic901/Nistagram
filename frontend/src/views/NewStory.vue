<template>
    <div class = "background">
    <Navbar/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form onsubmit="event.preventDefault()" class="box">
                            <div class = "cls">
                                <h1 class = "title" style="color:#4d4d4d ">New story</h1>
                            </div>
                            <div style="font-style:italic" required class="app">
                                <input type="file" @change="onFileSelected" multiple>
                                <img style="margin:10px" class="image" v-for="u in url" :key="u.blob" :src="u" />
                            </div>
                            <b-form-checkbox v-model="status" name="checkbox-1" :value="true" :unchecked-value="false" style="margin-bottom:20px;">
                                Is this story only for closed friends?
                            </b-form-checkbox>
                            <b-button style="color: white" v-on:click="upload" >Add new story</b-button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'
import axios from 'axios'

export default {
  name: "NewStory",
  components: {
    Navbar
  },
  computed: {
      User() {
          return this.$store.getters.getUser
      }
  },
  data() {
    return {
        url: [],
        status: false
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
        async upload() {
            var i = 1
            var date = (new Date()).getTime();
            var images = [];
            this.selectedFiles.forEach(selectedFile => {
                const fileToUpload = new FormData();
                images.push(this.User.username + '-story-' + date + '-image-' + i + ".jpg")
                fileToUpload.append('file', selectedFile, images[i-1])
                images[i-1] = "https://nistagramstorage.s3.eu-central-1.amazonaws.com/" + images[i-1]
                axios.post('http://localhost:8083/api/upload/upload-file', fileToUpload);

                i++
            })


            if(this.status) {
                console.log("prosao")
            }
            var newStory = {
                userId: this.User.id,
                forClosedFriends: this.status,
                imagesAndVideos: images
            }

            await axios.post("http://localhost:8083/api/story/add-new-story", newStory)
        }
    }
};
</script>

<style scoped>
.cl1 {
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 450px;
    border-radius: 24px;
    transition: 0.25s
}
.cls{
  margin-bottom: 40px;
}
 .background {
  background-color: #3498db;
  position:absolute; 
  top: 0; 
  left: 0; 
  min-width: 100%;
  min-height: 100%;
} 
.title {
  font-family: fantasy;
  font-size: 30px;
  color: black;
}
.body {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
    background: linear-gradient(to right, #b92b27, #1565c0)
}

.card {
    margin-bottom: 20px;
    border: none;
   
}

.box {
    width: 550px;
    padding: 40px;
    position:absolute;
    top: 50%;
    left: 50%;
    background: #ffffff;
    box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
    text-align: center;
    transition: 0.25s;
    margin-top: 20px;
    border-radius: 20px; 
}

.box input[type="text"]{
    border: 0;
    background: none;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 450px;
    outline: none;
    color: rgb(0, 0, 0);
    border-radius: 24px;
    transition: 0.25s
}

.box h1 {
    color: white;
    text-transform: uppercase;
    font-weight: 500
}

.box input[type="text"]:focus,
.box input[type="password"]:focus {
    width: 300px;
    background-color:#ffffff;
}

.box input[type="submit"] {
    border: 0;
    background: none;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #7e7e7e;
    background-color: #7e7e7e;
    padding: 14px 40px;
    outline: none;
    color: rgb(0, 0, 0);
    border-radius: 24px;
    transition: 0.25s;
    cursor: pointer
}

.box input[type="submit"]:hover {
    background: #595959;
    border: 2px solid #595959;
}

.forgot {
    text-decoration: underline
}

.routerlink {
  display: block;
  color:#7e7e7e;
}

body {
  background-color: #e2e2e2;
}

.app {
  padding: 20px;
}

.preview {
  display: flex;
  justify-content: center;
  align-items: center;
}

.preview img {
  max-width: 100%;
  max-height: 500px;
}
.image{
    max-width: 420px;
    max-height: 500px;
}

.textarea {
    height: 20vh;
    box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
    border-radius: 20px;
}

</style>