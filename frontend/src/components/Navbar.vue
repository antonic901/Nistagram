<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <router-link to="/" class="nav-link" style="color:#3498db;">Ništagram</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="!isUserLogged" to="/loginPage" class="nav-link">Profile</router-link>
                        <router-link v-else to="/profile" class="nav-link">{{userFullname}}</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/new-post" class="nav-link">New post</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/new-story" class="nav-link">New story</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/allUsers" class="nav-link">Blocked, muted, followers...</router-link>
                    </li>
                </ul>
            </div>
            <div class="mx-auto order-0">
                <b-form-input v-model="searchInput" style="text-align:center;width:300px;border-radius:10px;" size="sm" class="mr-sm-2" placeholder="Search" @keyup.enter="search"></b-form-input>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/allStories" class="nav-link">All Stories</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/LikedAndDisliked" class="nav-link">All liked and disliked posts</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/collections" class="nav-link">Collections</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/loginPage" v-if="!isUserLogged">
                            <b-button variant="success">Login</b-button>
                        </router-link>
                        <router-link to="/" v-else>
                            <b-button variant="danger" style="border-radius:10px;" v-on:click="logout">Logout</b-button>
                        </router-link>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: "Navbar",
    computed: {
        isUserLogged() {
            return this.$store.getters.isUserLogged
        },
        userFullname() {
            return this.$store.getters.getFullName
        },
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            searchInput: ''
        }
    },
    methods: {
        logout() {
            var user = {id:null};
            this.$store.dispatch('updateUser', {user});
            this.$router.push({name: 'Home'})
        },
        search() {
            var number = (this.searchInput.match(new RegExp("@", "g")) || []).length
            if(number > 1) {
                this.searchInput = 'Invalid search'
                return
            }

            number = (this.searchInput.match(new RegExp("#", "g")) || []).length
            if(number > 1) {
                this.searchInput = 'Invalid search'
                return
            }

            if(this.searchInput.includes("@") && this.searchInput.includes("#")) {
                this.searchInput = 'Invalid search'
                return
            }

            var search;

            if(this.searchInput.includes("@")) {
                search = {
                userId: this.User.id,
                input: this.searchInput.replace("@", "")
                }
                axios.post("http://localhost:8081/api/userprofile/search-by-username", search)
                    .then(r => {
                        var users = JSON.parse(JSON.stringify(r.data))
                        this.$store.dispatch('updateUsers', users)
                    })
            }
            else if(this.searchInput.includes("#")) {
                search = {
                    userId: this.User.id,
                    input: this.searchInput.replace("#", "")
                }
                axios.post("http://localhost:8082/api/post/search-by-hashtag", search)
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
            else {
                
                search = {
                    userId: this.User.id,
                    input: this.searchInput
                }
                axios.post("http://localhost:8082/api/post/search-by-location", search)
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

            this.$store.dispatch('updateSearchType', this.searchInput)
            this.$router.push({name: 'Search'})
        }
    }
}
</script>

<style scoped>

.navbar-brand {
    font-size: 25px;
}

.nav-link:hover {
    /* text-decoration: underline; */
    font-weight: bold;
}

.routerlink {
  display: block;
  color: rgb(255, 255, 255);
}
</style>