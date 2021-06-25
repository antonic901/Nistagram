<template>
    <div class=" background container-column">
        <Navbar/>
        <div class="table-responsive">
            <b-table
                class="table-light" 
                style="margin:20px;text-align:center;"
                head-variant="dark"
                selectable sticky-header="100%"
                select-mode="single"
                striped
                hover
                :items="followRequestes"
                :fields="fields"
                :filter="filter"
                :filter-included-fields="filterOn">
                <template v-if="this.selected!='followers'" #cell(accept)="row">
                    <b-button size="sm" @click="buttonClickAllow(row.item)" class="mr-2">Accept</b-button>
                </template>
                <template v-if="this.selected!='followers'" #cell(reject)="row">
                    <b-button size="sm" @click="buttonClickReject(row.item)" class="mr-2">Reject</b-button>
                </template>
            </b-table>
        </div>
    </div>
</template>

<script>

import Navbar from '../components/Navbar.vue'
import axios from 'axios'

export default {
    name: 'FollowRequest',
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
            followRequestes: [],
            fields: [
                {key: 'userWhoRequested.username', sortable: true, label: 'Username'},
                {key: 'userWhoRequested.name', sortable: true, label: 'Name'}, 
                {key: 'userWhoRequested.surname', sortable:true, label: 'Surname'}, 
                {key: 'userWhoRequested.email', sortable: true, label: 'Email'}, 
                {key: 'userWhoRequested.gender', sortable: true, label: 'Gender'},
                {key: 'accept'},
                {key: 'reject'}
            ],
            filter: null,
            filterOn: []
        }
    },
    methods: {
        buttonClickAllow(followRequest) {
            var approve = {
                requestId: followRequest.id,
                approved: true
            }
            axios.post("http://localhost:8081/api/followrequest/approve-request", approve)
                .then(r => {
                    axios.get("http://localhost:8081/api/userprofile/get-follow-requests/" + this.User.id)
                        .then(r => {
                            this.followRequestes = JSON.parse(JSON.stringify(r.data))
                        })
                })
        },
        buttonClickReject(followRequest) {
            var approve = {
                requestId: followRequest.id,
                approved: false
            }
            axios.post("http://localhost:8081/api/followrequest/approve-request", approve)
                .then(r => {
                    axios.get("http://localhost:8081/api/userprofile/get-follow-requests/" + this.User.id)
                        .then(r => {
                            this.followRequestes = JSON.parse(JSON.stringify(r.data))
                        })
                })
        }
    },
    mounted() {
        axios.get("http://localhost:8081/api/userprofile/get-follow-requests/" + this.User.id)
            .then(r => {
                this.followRequestes = JSON.parse(JSON.stringify(r.data))
            })
    }
}
</script>

<style scoped>
    .background {
        background-color: #3498db;
        min-width: 100%;
        min-height: 100%;
        position: absolute;
    }
    .container-row {
        display: flex;
        flex-direction: row;
        
    }
    .container-column {
        display: flex;
        flex-direction: column;
        
    }
</style>