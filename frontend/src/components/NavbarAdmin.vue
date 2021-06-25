<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <router-link to="/homeAdmin" class="nav-link" style="color:#3498db;">Ništagram</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="!isUserLogged" to="/loginPage" class="nav-link">Profile</router-link>
                        <router-link v-else to="/homeAdmin" class="nav-link">{{userFullname}}</router-link>
                    </li>
                </ul>
            </div>
            <div class="mx-auto order-0">
                <b-form-input v-model="searchInput" style="text-align:center;width:300px;border-radius:10px;" size="sm" class="mr-sm-2" placeholder="Search" @keyup.enter="search"></b-form-input>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ml-auto">
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
    name: "NavbarAdmin",
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
            alert("TODO")
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