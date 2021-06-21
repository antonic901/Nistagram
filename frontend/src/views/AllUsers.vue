<template>
    <div class="container-1">
        <Navbar/>
        <div class="container-2">
            <b-form-group label ="Select which users you want to show:" style="color:white;font-size:20px;text-align:center;">
                <b-form-radio-group
                    id="btn-radios-2"
                    v-model="selected"
                    :options="options"
                    button-variant="primary"
                    size="lg"
                    name="radio-btn-outline"
                    buttons
                    @change="buttonPress"
                ></b-form-radio-group>
            </b-form-group>
        </div>
        <div class="table-responsive">
            <b-table
                class="table-light" 
                style="margin:20px;text-align:center;"
                head-variant="dark"
                selectable sticky-header="100%"
                select-mode="single"
                striped
                hover
                :items="users"
                :fields="fields"
                :filter="filter"
                :filter-included-fields="filterOn">
                <template v-if="this.selected!='followers'" #cell(button)="row">
                    <b-button size="sm" @click="buttonClick(row.item)" class="mr-2">{{buttonText}}</b-button>
                </template>
            </b-table>
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
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            selected: '',
            options: [
                { text: 'Followers', value: 'followers' },
                { text: 'Following', value: 'following' },
                { text: 'Closed friends', value: 'closedfriends' },
                { text: 'Muted users', value: 'mutedusers' },
                { text: 'Blocked users', value: 'blockedusers' }
            ],
            fields: [
                {key: 'username', sortable: true},
                {key: 'name', sortable: true}, 
                {key: 'surname', sortable:true}, 
                {key: 'email', sortable: true}, 
                {key: 'gender', sortable: true},
                {key: 'button'}
            ],
            users: [],
            filter: null,
            filterOn: [],
            buttonText: ''
        }
    },
    methods: {
        buttonPress() {
            if(this.selected == 'followers') {
                axios.get("http://localhost:8081/api/userprofile/get-followers/" + this.User.id)
                    .then(r => {
                        this.users = JSON.parse(JSON.stringify(r.data));
                    })
            }
            else if(this.selected == 'following') {
                axios.get("http://localhost:8081/api/userprofile/get-following/" + this.User.id)
                    .then(r => {
                        this.users = JSON.parse(JSON.stringify(r.data));
                        this.buttonText = 'Unfollow'
                    })
            }
            else if(this.selected == 'closedfriends') {
                axios.get("http://localhost:8081/api/userprofile/get-closed-friends/" + this.User.id)
                    .then(r => {
                        this.users = JSON.parse(JSON.stringify(r.data));
                        this.buttonText = 'Remove from closed friends'
                    })
            }
            else if(this.selected == 'mutedusers') {
                axios.get("http://localhost:8081/api/userprofile/get-muted-users/" + this.User.id)
                    .then(r => {
                        this.users = JSON.parse(JSON.stringify(r.data));
                        this.buttonText = 'Unmute'
                    })
            }
            else if(this.selected == 'blockedusers') {
                axios.get("http://localhost:8081/api/userprofile/get-blocked-users/" + this.User.id)
                    .then(r => {
                        this.users = JSON.parse(JSON.stringify(r.data));
                        this.buttonText = 'Unblock'
                    })
            }
        },
        buttonClick(selectedUser) {
            var check = {
                userPostId: selectedUser.id,
                userViewId: this.User.id
            }
            if(this.selected == 'following') {
                axios.post("http://localhost:8081/api/userprofile/unfollow", check)
                    .then(r => {
                        axios.get("http://localhost:8081/api/userprofile/get-following/" + this.User.id)
                            .then(r => {
                                this.users = JSON.parse(JSON.stringify(r.data));
                            })
                    })
            }
            else if(this.selected == 'closedfriends') {
                axios.post("http://localhost:8081/api/userprofile/remove-from-closed-friends", check)
                    .then(r => {
                        axios.get("http://localhost:8081/api/userprofile/get-closed-friends/" + this.User.id)
                            .then(r => {
                                this.users = JSON.parse(JSON.stringify(r.data));
                            })
                    })
            }
            else if(this.selected == 'mutedusers') {
                axios.post("http://localhost:8081/api/userprofile/unmute", check)
                    .then(r => {
                        axios.get("http://localhost:8081/api/userprofile/get-muted-users/" + this.User.id)
                            .then(r => {
                                this.users = JSON.parse(JSON.stringify(r.data));
                            })
                    })
            }
            else if(this.selected == 'blockedusers') {
                axios.post("http://localhost:8081/api/userprofile/unblock", check)
                    .then(r => {
                        axios.get("http://localhost:8081/api/userprofile/get-blocked-users/" + this.User.id)
                            .then(r => {
                                this.users = JSON.parse(JSON.stringify(r.data));
                            })
                    })
            }
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
        justify-content: center;
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