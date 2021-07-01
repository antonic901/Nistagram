<template>
    <div class="container-1">
        <Navbar/>
        <div class="container-6">
            <div class="container-4">
                <div v-for="collection in collections" :key="collection.id" class="container-3" style="width:500px;" v-on:click="openCollection(collection)">
                    <b-card v-if="collection.posts.length != 0" style="margin:20px;" :img-src="collection.posts[0].imagesAndVideos[0]" img-top>
                        <b-card-text style="text-align:center;">
                            {{collection.name}}
                        </b-card-text>
                    </b-card>
                </div>
            </div>
            <div v-if="show">
                <div class="container-7" v-for="post in posts" :key="post.id" v-on:click="openPost(post)">
                    <label style="color:white;"><b>@{{post.user.username}}</b></label>
                    <div class="container-2" style="height:292px;">
                        <div class="container-5" v-for="image in post.imagesAndVideos" :key="image.id">
                            <b-img-lazy class="item-1" rounded :src="image"></b-img-lazy>
                        </div>
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
    name: 'Collections',
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
            collections: [],
            posts: [],
            show: false
        }
    },
    methods: {
        async openCollection(collection) {
            this.posts = collection.posts
            this.posts.forEach(post => {
                if(post != []) {
                    axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + post.user.id)
                        .then(r => {
                            post.user = JSON.parse(JSON.stringify(r.data))
                        })
                }
            })
            this.show = true;
        },
        openPost(post) {
            console.log(post)
        }
    },
    created() {
        axios.get(this.$store.getters.getPostAPI + "/api/user/get-collections/" + this.User.id)
            .then(r => {
                 this.collections = JSON.parse(JSON.stringify(r.data))
            })
    }
}
</script>

<style scoped>

    .container-1 {
        display: flex;
        flex-direction: column;
        background-color: #3498db;
        min-width: 100%;
        min-height: 100%;
        position:absolute;
    }

    .container-2 {
        display: flex;
        flex-direction: row;
        /* flex-wrap: wrap; */
    }

    .container-7 {
        display: flex;
        flex-direction: column;
        margin: 20px;
    }

    .container-3 {
        transition: 0.2s all ease-in-out;
    }

    .container-3:hover {
        margin-left: -10px;
    }

    .container-4 {
        display: flex;
        flex-direction: column;
    }

    .container-5 {
        transition: 0.2s all ease-in-out;
    }

    .container-5:hover {
        margin-top: -10px;
    }

    .container-6 {
        display: flex;
        flex-direction: row;
    }

   .item-1 {
        width: 292px;
        height: 292px;
        flex: 1 0 21%;
        margin-right: 10px;
    }

</style>