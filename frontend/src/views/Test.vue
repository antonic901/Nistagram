<template>
    
</template>

<script>

import Navbar from '../components/Navbar.vue'
import axios from 'axios'

export default {
    name: 'AllUsers',
    components: {
        Navbar
    },
    computed: {
        Users() {
            return this.$store.getters.getUsers
        },
        Posts() {
            return this.$store.getters.getPosts
        }
    },
    data() {
        return {
            
        }
    },
    methods: {
        openCollection(collection) {
            alert("Hello")
        }
    }
}
</script>

<style scoped>

</style>