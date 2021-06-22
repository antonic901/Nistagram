<template>
    <div class="container-1">
        <Navbar/>
        <div class="container-6">
            <div class="container-4">
                <div class="container-3" style="width:500px;" v-on:click="openCollection('ja')">
                    <b-card style="margin:20px;" :img-src="'https://placekitten.com/1000/300'" img-top>
                        <b-card-text style="text-align:center;">
                            Some quick example text to build on the card and make up the bulk of the card's content.
                        </b-card-text>
                    </b-card>
                </div>
            </div>
            <div class="container-2">
                <div class="container-5" style="height:292px;">
                    <b-img-lazy class="item-1" rounded :src="'https://picsum.photos/600/300'"></b-img-lazy>
                </div>
            </div>
        </div>
    </div>
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
        margin: 10px;
        flex: 1 0 21%;
    }

</style>