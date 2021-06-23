<template>
    <div>
        <b-modal id="modalFavorites" :hide-footer="true" :title="'Add to' + ' ' + this.type" style="height: 500px;">
            <b-row no-gutters>
                <b-col sm="8">
                    <b-form-input type="text" v-model="name" placeholder="Type name" style="font-style:italic; width: 300px;"/>
                </b-col>
                <b-col sm="4">
                    <b-button variant="primary" style="margine-top: 10px; margine-bottom: 10px;" v-on:click="create">Create {{this.type}}</b-button>
                </b-col>
            </b-row>
            <b-row no-gutters style="text-align:center;">
                <b-col style="margin:5px;">
                    <b-dropdown :text="'Select' + ' ' + this.type"> 
                        <b-dropdown-item v-for="collection in collections" :key="collection.id" v-on:click="selectCollection(collection)">{{collection.name}}</b-dropdown-item>
                    </b-dropdown>
                    <label style="margin:5px;" v-if="selectedCollection != null">{{selectedCollection.name}}</label>
                </b-col>
            </b-row>
            <b-row>
                <div class="container">
                    <b-button v-if="this.type == 'collection'" variant="primary" style="margine-top: 10px; margine-bottom: 10px;" v-on:click="add">Add post<p></p></b-button>
                    <b-button v-else variant="primary" style="margine-top: 10px; margine-bottom: 10px;" v-on:click="add">Add story<p></p></b-button>
                </div>
            </b-row>

        </b-modal>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: 'Favorites',
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        type() {
            return this.$store.getters.getType
        },
        entity() {
            return this.$store.getters.getEntity
        }
    },
    data() {
        return {
            collections: [],
            name: '',
            selectedCollection: null
        }
    },
    methods: {
        create() {
            if(this.name == '') {
                alert("Invalid name")
                return
            }
            var create = {
                userId: this.User.id,
                name: this.name
            }
            if(this.type == 'collection') {
                axios.post("http://localhost:8082/api/user/create-collection", create)
                    .then(r => {
                        this.collections = JSON.parse(JSON.stringify(r.data));
                        this.name = ''
                        alert("Succesfully created!")
                    })
            }
            else {
                axios.post("http://localhost:8083/api/user/create-highlight", create)
                    .then(r => {
                        this.collections = JSON.parse(JSON.stringify(r.data));
                        this.name = ''
                        alert("Succesfully created!")
                    })
            }
        },
        selectCollection(collection) {
            this.selectedCollection = collection;
        },
        add() {
            if(this.selectedCollection == null) {
                if(this.type == 'collection') {
                    alert("Please select collection!")
                }
                else {
                    alert("Please select highlight!")
                }
                
                return
            }
            var add = {
                collectionId: this.selectedCollection.id,
                postId: this.entity.id
            }
            if(this.type == 'collection') {
                axios.post("http://localhost:8082/api/collection/add-to-collection", add)
                    .then(r => {
                        alert("Succesfully added to selected collection!")
                    }) 
            }
            else {
                axios.post("http://localhost:8083/api/highlight/add-to-highlight", add)
                    .then(r => {
                        alert("Succesfully added to selected highlight!")
                    }) 
            }
        }
    },
    mounted() {
        this.$root.$on('bv::modal::show', (bvEvent, modalFavorites) => {
            if(this.User.id == null) return
            if(this.type == 'collection') {
                axios.get("http://localhost:8082/api/user/get-collections/" + this.User.id)
                    .then(r => {
                        this.collections = JSON.parse(JSON.stringify(r.data))
                })
            } 
            else {
                axios.get("http://localhost:8083/api/user/get-highlights/" + this.User.id)
                    .then(r => {
                        this.collections = JSON.parse(JSON.stringify(r.data))
                    })
            }
        })
    }
}
</script>

<style scoped>
    .container {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
</style>