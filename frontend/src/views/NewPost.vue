<template>
    <div>
    <!-- <Navbar/> -->
    <b-modal id="modalNewPost" :hide-footer="true" :hide-header="true">
        <div class = "d-block text-center" style="margin-bottom:20px;">
            <h1 class = "title" style="color:#4d4d4d ">New post</h1>
        </div>
        <div class="text-center">
            <div>
                <b-form-input type="text" @keyup="search" id="search-input" style="margin:5px;border-radius:20px;" placeholder="search locations" v-model="query"></b-form-input>
                <div v-if="results.length > 0" id="results">
                    <b-list-group style="max-width:300px;">
                        <b-list-group-item v-for="r in results" :key="r.place_id" class="d-flex align-items-center">
                            <p @click="select(r)" ><b>{{r.display_name}}</b></p>
                        </b-list-group-item>
                    </b-list-group>
                </div>
            </div>
            <div style="font-style:italic" required class="app">
                <input type="file" @change="onFileSelected" multiple>
                <img style="margin:10px" class="image" v-for="u in url" :key="u.blob" :src="u" />
            </div>
        </div>
        <b-form-textarea class="textarea" v-model="enterDescription" type="text" placeholder="enter description" style="font-style:italic"/>
        <div style="display:flex;justify-content:center;">
            <b-button class="mt-2" style="color: white;border-radius:10px; width:100px;" @click="onUpload">Create</b-button>
        </div>
    </b-modal>
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
        selectedFiles: [],
        url: [],
        error: '',
        enterDescription: '',
        description: '',
        hashTags: [],
        profileTags: [],
        timeout: null,
        results: [],
        selected: null,
        query: '',
    };
  },
    methods:{
        select: function(r) {
            this.selected = r;
            this.results = [];
            this.$emit('selected', r);
            console.log(this.selected)
        },
        search: function() {
            clearTimeout(this.timeout);
            this.results = [];
            let self = this;
            this.timeout = setTimeout(function() {
                axios.get('https://us1.locationiq.com/v1/search.php?key=pk.f79b28f4f091ea8662281203e0081f65&format=json&addressdetails=1&limit=5&q=' + self.query)
                    .then(r => {
                        self.results = r.data;
                    })
            }, 1000);
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
            await axios.post(this.$store.getters.getPostAPI + "/api/tag/create-tag", tag)

            var i = 1
            var date = (new Date()).getTime();
            var images = [];
            this.selectedFiles.forEach(selectedFile => {
                const fileToUpload = new FormData();
                images.push(this.User.username + '-post-' + date + '-image-' + i + ".jpg")
                fileToUpload.append('file', selectedFile, images[i-1])
                images[i-1] = "https://nistagramstorage.s3.eu-central-1.amazonaws.com/" + images[i-1]
                axios.post(this.$store.getters.getPostAPI + "/api/upload/upload-file", fileToUpload);

                i++
            })

            var newPost;
            if(this.selected != null) {
                newPost = {
                    location: {
                        address: this.selected.address,
                        display_name: this.selected.display_name
                    },
                    userId: this.User.id,
                    description: this.description,
                    hashTags: this.hashTags,
                    profileTags: this.profileTags,
                    imagesAndVideos: images
                }
            }
            else {
                newPost = {
                    location: null,
                    userId: this.User.id,
                    description: this.description,
                    hashTags: this.hashTags,
                    profileTags: this.profileTags,
                    imagesAndVideos: images
                }
            }

            await axios.post(this.$store.getters.getPostAPI + "/api/post/add-new-post", newPost)
                .then(r => {
                    alert(r.data);
                })
            

        },
        findHashtags(searchText) {
            // var regexp = /\B\#\w\w+\b/g
            var regexp = /\#\w+\b/g
            var tags = searchText.match(regexp);
            if(tags == null ) this.hashTags = []
            else this.hashTags = tags
            
            var regexp = /\@\w+\b/g
            tags = searchText.match(regexp);
            if(tags == null) this.profileTags = [] 
            else this.profileTags = tags;

            this.description = this.enterDescription;
            try {
                this.hashTags.forEach(hashTag => {
                    this.description = this.description.replace(hashTag, "");
                })
                this.profileTags.forEach(profileTag => {
                    this.description = this.description.replace(profileTag, "");
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