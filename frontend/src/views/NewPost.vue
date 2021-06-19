<template>
    <div class = "background">
    <Navbar/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form onsubmit="event.preventDefault()" class="box">
                            <div class = "cls">
                                <h1 class = "title" style="color:#4d4d4d ">New post</h1>
                            </div>
                            <div >
                                <div>
                                    <b-form-input v-model="enterLocation" type="text" v-on:input="searchLocation" placeholder="search in format: Country, City, Street" style="font-style:italic"></b-form-input>
                                    <b-table
                                        class="table-light" 
                                        selectable
                                        select-mode="single"
                                        sticky-header="60vh" 
                                        hover 
                                        :striped=true 
                                        head-variant="dark"  
                                        :items="locations" 
                                        :fields="fields"
                                        @row-clicked="locationSelect" 
                                        >
                                    </b-table>
                                </div>
                                <div style="font-style:italic" required class="app">
                                    <input type="file" @change="onFileSelected" multiple>
                                    <img style="margin:10px" class="image" v-for="u in url" :key="u.blob" :src="u" />
                                </div>
                            </div>
                            <b-form-textarea class="textarea" v-model="enterDescription" type="text" placeholder="enter description" style="font-style:italic"/> 
                            <br/>
                            <b-button style="color: white" @click="onUpload">Create</b-button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import axios from 'axios'
import Navbar from '../components/Navbar.vue'

export default {
  name: "NewPost",
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
        fields: ['country', 'city', 'street'],
        selectedFiles: [],
        show: false,
        url: [],
        error: '',
        enterLocation: '',
        enterDescription: '',
        locations: [],
        location: null,
        description: '',
        hashTags: []
    };
  },
    methods:{
        searchLocation() {
            if(this.enterLocation == '') {
                this.locations = []
                this.location = null 
                return
            }
            
            axios.get("http://localhost:8082/api/location/search-location/" + this.enterLocation)  
                .then(r => {
                    this.locations = JSON.parse(JSON.stringify(r.data))
                })  
        },
        locationSelect(location ,index) {
            this.location = location
        },
        onFileSelected(event) {
            this.url = []
            this.selectedFiles = event.target.files
            this.selectedFiles.forEach(selectedFile => {
                this.url.push(URL.createObjectURL(selectedFile));
            })
        },
        async onUpload() {
            this.findHashtags(this.enterDescription)

            var tag = {
                tags: this.hashTags
            }
            await axios.post("http://localhost:8082/api/tag/create-tag", tag)

            var i = 1
            var date = (new Date()).getTime();
            var images = [];
            this.selectedFiles.forEach(selectedFile => {
                const fileToUpload = new FormData();
                images.push(this.User.username + '/post-' + date + '-image-' + i + ".jpg")
                fileToUpload.append('file', selectedFile, images[i-1])

                axios.post('http://localhost:8082/api/upload/upload-file', fileToUpload);

                i++
            })
            var newPost;
            try {
                newPost = {
                userId: this.User.id,
                description: this.description,
                hashTags: this.hashTags,
                locationId: this.location.id,
                imagesAndVideos: images
                }
            } catch {
                newPost = {
                userId: this.User.id,
                description: this.description,
                hashTags: this.hashTags,
                locationId: null,
                imagesAndVideos: images
                }
            }

            await axios.post("http://localhost:8082/api/post/add-new-post", newPost)
                .then(r => {
                    console.log(r.data);
                })
        },
        findHashtags(searchText) {
            // var regexp = /\B\#\w\w+\b/g
            var regexp = /\#\w+\b/g
            this.hashTags = searchText.match(regexp);
            this.description = this.enterDescription;
            try {
                this.hashTags.forEach(hashTag => {
                this.description = this.description.replace(hashTag, "");
            })
            } catch (error) {

            }

            this.description = this.description.trim()
        },
        onSubmit(event) {
        event.preventDefault();
        },
        onReset(event) {
        event.preventDefault();
        console.log("reset");
        }
    }
}
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