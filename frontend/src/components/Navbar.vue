<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <router-link to="/" class="nav-link" style="color:#3498db;">Ništagram</router-link>
                    </li>
                    <!-- <li class="nav-item">
                        <router-link v-if="!isUserLogged" to="/loginPage" class="nav-link">Profile</router-link>
                        <router-link v-else to="/profile" class="nav-link">{{userFullname}}</router-link>
                    </li> -->
                </ul>
            </div>
            <div class="mx-auto order-0">
                <b-form-input v-model="searchInput" style="text-align:center;width:300px;border-radius:10px;" size="sm" class="mr-sm-2" placeholder="Search" @keyup.enter="search"></b-form-input>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link v-if="isUserLogged" to="/followRequest" class="nav-link">
                            <b-icon icon="person-plus"></b-icon>
                            Follow Request
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="isUserLogged && newNotification" to="/notifications" style="color:red;" class="nav-link">
                            <b-icon icon="bell-fill"></b-icon>
                            Notifications
                        </router-link>
                        <router-link v-if="isUserLogged && !newNotification" to="/notifications" class="nav-link">
                            <b-icon icon="bell"></b-icon>
                            Notifications
                        </router-link>
                    </li>
                    <li v-if="!isUserLogged" class="nav-item">
                        <router-link to="/loginPage" v-if="!isUserLogged">
                            <b-button variant="success">Login</b-button>
                        </router-link>
                    </li>
                    <li v-else class="nav-item">
                        <div>
                            <b-dropdown variant="primary" right>
                                <template #button-content>
                                    <b-icon icon="gear-fill" aria-hidden="true"></b-icon> Settings
                                </template>
                                <router-link to="/profile" style="text-decoration:none;">
                                    <b-dropdown-item-button>
                                        <b-icon icon="person-circle" aria-hidden="true"></b-icon>
                                        {{userFullname}} <span class="sr-only">(Click to unlock)</span>
                                    </b-dropdown-item-button>
                                </router-link>
                                <b-dropdown-divider></b-dropdown-divider>
                                <b-dropdown-group header="Choose options" class="small">
                                    <router-link to="/collections" style="text-decoration:none;">
                                        <b-dropdown-item-button >
                                            <b-icon icon="collection" aria-hidden="true"></b-icon>
                                            Collections <span class="sr-only">(Not selected)</span>
                                        </b-dropdown-item-button>
                                    </router-link>
                                    <router-link to="/allStories" style="text-decoration:none;">
                                        <b-dropdown-item-button>
                                        <b-icon icon="camera" aria-hidden="true"></b-icon>
                                        Stories <span class="sr-only">(Selected)</span>
                                        </b-dropdown-item-button>
                                    </router-link>
                                    <router-link to="/LikedAndDisliked" style="text-decoration:none;" >
                                        <b-dropdown-item-button>
                                        <b-icon icon="hand-thumbs-up" aria-hidden="true"></b-icon>
                                        Posts <span class="sr-only">(Not selected)</span>
                                        </b-dropdown-item-button>
                                    </router-link>
                                </b-dropdown-group>
                                <b-dropdown-divider></b-dropdown-divider>
                                <router-link to="/allUsers" style="text-decoration:none;">
                                    <b-dropdown-item-button>
                                        <b-icon icon="people" aria-hidden="true"></b-icon>
                                        Users status
                                    </b-dropdown-item-button>
                                </router-link>

                                <!-- <b-dropdown-item-button>Some other action</b-dropdown-item-button> -->
                                <b-dropdown-divider></b-dropdown-divider>
                                <router-link to="/" style="text-decoration:none;">
                                    <b-dropdown-item-button variant="danger" v-on:click="logout">
                                        <b-icon icon="arrow-left-circle" aria-hidden="true"></b-icon>
                                        Logout
                                    </b-dropdown-item-button>
                                </router-link>
                            </b-dropdown>
                        </div>
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
        },
        newNotification() {
            return this.$store.getters.getNewNotification
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
                axios.post(this.$store.getters.getUserAPI + "/api/userprofile/search-by-username", search)
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
                axios.post(this.$store.getters.getPostAPI + "/api/post/search-by-hashtag", search)
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
                        })
                        this.$store.dispatch('updatePosts', posts)
                    })
            }
            else {
                
                search = {
                    userId: this.User.id,
                    input: this.searchInput
                }
                axios.post(this.$store.getters.getPostAPI + "/api/post/search-by-location", search)
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