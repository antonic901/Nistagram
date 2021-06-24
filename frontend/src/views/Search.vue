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
            <div class="container-3" v-for="post in this.Posts" :key="post.id" v-b-modal.modalAdditionalPost v-on:click="change(post)">
                <b-img-lazy class="item-1" :src="post.imagesAndVideos[0]"></b-img-lazy>
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

export default {
    name: 'Search',
    components: {
        Navbar,
        PostModal
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
    methods: {
        clickOpen(user) {
            this.$store.dispatch('updateUserProfile', user)
            this.$router.push({name: 'UserProfile'})
        },
        change(post) {
            this.$store.dispatch('updateEntity', post)
        }
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