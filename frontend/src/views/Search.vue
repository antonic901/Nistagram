<template>
    <div class="container-1">
        <Navbar/>
        <div class="container-4" style="margin:10px;">
            <h4 class="item-3">Search result for:</h4>
            <p class="item-3 mb-1" style="max-width:200px; color:white; margin-left:5px">{{this.LookingFor}}</p>
        </div>
        <div v-if="this.SearchType" class="container-2">
            <div v-for="u in this.Users" :key="u.id" class="container-3">
                <b-card
                    :title="u.name + ' ' + u.surname"
                    img-src="https://picsum.photos/600/300"
                    img-alt="Image"
                    img-top
                    tag="article"
                    style="max-width: 20rem;margin:10px"
                    class="mb-2"
                >
                    <b-card-text>
                        {{u.biography}}
                    </b-card-text>

                    <b-button v-on:click="clickOpen(u)" variant="primary">Open user's profile</b-button>
                </b-card>
            </div>
        </div>
        <div v-else class="container-2">
            <div class="container-3" v-for="post in this.Posts" :key="post.id" v-b-modal.modalPost v-on:click="change(post)">
                <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
            </div>
        </div>
        <div v-if="this.post != null">
            <b-modal id="modalPost" size="xl" :hide-footer="true" :title="'@' + post.user.username">
                <b-card no-body class="overflow-hidden" style="max-width: auto; max-height: auto; margin-top: 0px;">
                    <b-row no-gutters>
                        <b-col md="8">
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <b-card-img :src="this.post.imagesAndVideos[i]" alt="Image" class="rounded-0"></b-card-img>
                            </div>
                            <a class="carousel-control-prev" role="button" v-on:click="previus" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" role="button" v-on:click="next" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                            </a>
                        </div>
                        </b-col>
                        <b-col md="4">
                            <b-card-body>
                            <b-card-text>
                            <p v-if="this.post.location != null" style="font-size:20px"><b>{{this.post.location.street}}, {{this.post.location.city}}, {{this.post.location.country}}</b></p>
                            <p>{{this.post.caption.description}}</p>
                            <p v-for="t in this.post.caption.tags" :key="t.id">{{t.name}}</p>
                            </b-card-text>
                            <b-card-text>
                            <p v-for="comment in post.comments" :key="comment.id" ><b style="font-size:14px;">@{{comment.user.username}}</b> {{comment.content}}</p>
                            </b-card-text>
                        </b-card-body>
                        </b-col>
                    </b-row>
                </b-card>
            </b-modal>
        </div>
    </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'

export default {
    name: 'Search',
    components: {
        Navbar
    },
    computed: {
        Users() {
            return this.$store.getters.getUsers
        },
        Posts() {
            return this.$store.getters.getPosts
        },
        SearchType() {
            return this.$store.getters.getSearchType
        },
        LookingFor() {
            return this.$store.getters.lookingFor
        }
    },
    data() {
        return {
            name: 'nikola',
            post: null,
            i: 0
        }
    },
    methods: {
        clickOpen(user) {
            this.$store.dispatch('updateUserProfile', user)
            this.$router.push({name: 'UserProfile'})
        },
        change(post) {
            this.post = post
        },
        clickEdit() {
            if(this.showEdit) this.showEdit = false
            else this.showEdit = true
        },
        changeStory(highLight) {
            this.highLight = highLight
        },
        previus() {
            var i = this.i - 1
            if(i < 0) {
                this.i = this.post.imagesAndVideos.length-1
                return
            }
            this.i = i
        },
        next() {
            var i = this.i + 1
            if(i >= this.post.imagesAndVideos.length) {
                this.i = 0
                return
            }
            this.i = i
        },
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