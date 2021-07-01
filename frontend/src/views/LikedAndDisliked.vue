<template>
    <div class="container-1">
        <Navbar/>
        <div class="container-2">
            <b-form-group label ="Select which posts you want to show:" style="color:white;font-size:20px;text-align:center;">
                <b-form-radio-group
                    id="btn-radios-2"
                    v-model="selected"
                    :options="options"
                    button-variant="primary"
                    size="lg"
                    name="radio-btn-outline"
                    buttons
                ></b-form-radio-group>
            </b-form-group>
        </div>
        <div v-if="selected == 'liked'">
            <div class="container-1">
                <div class="container-2" v-for="post in this.likedPosts" :key="post.id" v-b-modal.modalAdditionalPost v-on:click="change(post)">
                    <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
                </div>
            </div>
        </div>
        <div v-else>
            <div class="container-1">
                <div class="container-2" v-for="post in this.dislikedPosts" :key="post.id" v-b-modal.modalAdditionalPost v-on:click="change(post)">
                    <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
                </div>
            </div>
        </div>
        <div>
            <PostModal/>
        </div>
    </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'
import PostModal from '../components/PostModal.vue'

import axios from 'axios'

export default {
    name: 'LikedAndDisliked',
    components: {
        Navbar,
        PostModal
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            selected: '',
            options: [
                { text: 'Liked', value: 'liked' },
                { text: 'Disliked', value: 'disliked' }
            ],
            likedPosts: [],
            dislikedPosts: []
        }
    },
    methods: {
        change(post) {
            this.$store.dispatch('updateEntity', post)
        }
    },
    mounted() {
        axios.get(this.$store.getters.getPostAPI + "/api/post/get-liked-posts/" + this.User.id)
            .then(r => {
                var posts = JSON.parse(JSON.stringify(r.data))
                posts.forEach(post => {
                    axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + post.user.id)
                        .then(r => {
                            post.user = JSON.parse(JSON.stringify(r.data))
                        })
                    post.comments.forEach(comment => {
                       axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + comment.user.id)
                            .then(r => {
                                comment.user = JSON.parse(JSON.stringify(r.data))
                            }) 
                    })
                });
                this.likedPosts = posts;
            })
        axios.get(this.$store.getters.getPostAPI + "/api/post/get-disliked-posts/" + this.User.id)
            .then(r => {
                var posts = JSON.parse(JSON.stringify(r.data))
                posts.forEach(post => {
                    axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + post.user.id)
                        .then(r => {
                            post.user = JSON.parse(JSON.stringify(r.data))
                        })
                    post.comments.forEach(comment => {
                       axios.get(this.$store.getters.getUserAPI + "/api/userprofile/get-by-id/" + comment.user.id)
                            .then(r => {
                                comment.user = JSON.parse(JSON.stringify(r.data))
                            }) 
                    })
                });
                this.dislikedPosts = posts;
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
        flex-wrap: wrap;
        justify-content: center;
    }

    .container-3 {
        transition: 0.2s all ease-in-out;
    }

    .container-3:hover {
        margin-top: -10px;
    }

    .container-4 {
        display: flex;
        flex-direction: column;
    }

    .item-1 {
        width: 292px;
        height: 292px;
        margin: 10px;
        border-radius: 15px;
    }

</style>