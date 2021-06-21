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
                </ul>
            </div>
            <div class="mx-auto order-0">
                <b-form-input style="text-align:center;width:300px;border-radius:10px;" size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
            </div>
            <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link to="/loginPage" v-if="!isUserLogged" class="nav-link">
                            <b-button variant="success">Login</b-button>
                        </router-link>
                        <b-button v-else variant="danger" style="border-radius:10px;" v-on:click="logout">Logout</b-button>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>

export default {
    name: "Navbar",
    computed: {
        isUserLogged() {
            return this.$store.getters.isUserLogged
        },
        userFullname() {
            return this.$store.getters.getFullName
        }
    },
    methods: {
        logout() {
            var user = {id:null};
            this.$store.dispatch('updateUser', {user});
            this.$router.push({name: 'Home'})
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