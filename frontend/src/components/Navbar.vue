<template>
    <div class="background">
    <b-navbar toggleable="lg" type="dark" variant="dark">
                <b-navbar-brand>
                    <router-link to="/" class="routerlink">Nistagram</router-link>
                </b-navbar-brand>
                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                <b-collapse id="nav-collapse" is-nav>
                    <b-navbar-nav>
                        <b-nav-item> 
                            <router-link v-if="!isUserLogged" to="/loginPage" class="routerlink">Profile</router-link>
                            <router-link v-else to="/" class="routerlink">{{userFullname}}</router-link>
                        </b-nav-item>
                    </b-navbar-nav>
                     <b-navbar-nav>
                        <b-nav-item> 
                            <router-link v-if="!isUserLogged" to="/new-post" class="routerlink">New post</router-link>
                        </b-nav-item>
                    </b-navbar-nav>
                    <b-navbar-nav class="ml-auto">
                        <b-nav-form>
                            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                            <router-link to="/" class="routerlink">
                                <button type="button" class="btn btn-primary">Search</button>
                            </router-link>
                        </b-nav-form>
                        <b-nav-item>
                            <router-link to="/loginPage" v-if="!isUserLogged"  class="routerlink">
                                <b-button style="background-color:green">Login</b-button>
                            </router-link>
                            <b-button v-else style="background-color:red" v-on:click="logout">Logout</b-button>
                        </b-nav-item>
                    </b-navbar-nav>
                </b-collapse>
     </b-navbar>     
    </div>
</template>

<script>
export default {
    name: "Navbar",
    computed: {
        user(){
            return this.$store.state.user
        },
        isUserLogged() {
            return this.$store.state.isUserLogged
        },
        userFullname() {
            return this.$store.getters.getFullName
        }
    },
    methods: {
        logout() {
            var user = {id:null};
            this.$store.commit('updateUser', {user});
            this.$router.push({name: 'Home'})
        }
    }
}
</script>

<style scoped>
.routerlink {
  display: block;
  color: rgb(255, 255, 255);
}
</style>