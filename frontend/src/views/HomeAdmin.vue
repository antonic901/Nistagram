<template>
    <div class="background container-column">
        <NavbarAdmin/>
        <div class="table-responsive">
            <b-table
                class="table-light" 
                style="margin:20px;text-align:center;"
                head-variant="dark"
                selectable sticky-header="100%"
                select-mode="single"
                striped
                hover
                :items="verificationRequestes"
                :fields="fields"
                :filter="filter"
                :filter-included-fields="filterOn">
                <template #cell(viewimage)="row">
                    <b-button size="sm" @click="buttonClickViewImage(row.item)" v-b-modal.modalViewImage class="mr-2">View image</b-button>
                </template>
                <template #cell(accept)="row">
                    <b-button size="sm" @click="buttonClickAllow(row.item)" class="mr-2">Accept</b-button>
                </template>
                <template #cell(reject)="row">
                    <b-button size="sm" @click="buttonClickReject(row.item)" class="mr-2">Reject</b-button>
                </template>
            </b-table>
        </div>
        <div>
            <b-modal id="modalViewImage" :hide-footer="true" :hide-header="true">
                <b-card>
                    <div class="carousel-inner">
                        <b-img :src="image"/>
                    </div>
                </b-card>
            </b-modal>
        </div>
    </div>
</template>

<script>

import NavbarAdmin from '../components/NavbarAdmin.vue'
import axios from 'axios'

export default {
    name: 'HomeAdmin',
    components:{
        NavbarAdmin
    },
    data() {
        return {
            verificationRequestes: [],
            fields: [
                {key: 'user.username', sortable: true, label: 'Username'},
                {key: 'user.name', sortable: true, label: 'Name'}, 
                {key: 'user.surname', sortable:true, label: 'Surname'}, 
                {key: 'user.email', sortable: true, label: 'Email'}, 
                {key: 'user.gender', sortable: true, label: 'Gender'},
                {key: 'viewImage'},
                {key: 'accept'},
                {key: 'reject'}
            ],
            filter: null,
            filterOn: [],
            image: ''
        }
    },
    methods: {
        buttonClickAllow(verificationRequest) {
            var proceed = {
                id: verificationRequest.id,
                approved: true
            }
            axios.post("http://localhost:8084/api/verificationrequest/proceed-verification", proceed)
                .then(r => {
                    this.verificationRequestes = JSON.parse(JSON.stringify(r.data))
                })
        },
        buttonClickReject(verificationRequest) {
            var proceed = {
                id: verificationRequest.id,
                approved: true
            }
            axios.post("http://localhost:8084/api/verificationrequest/proceed-verification", proceed)
                .then(r => {
                    this.verificationRequestes = JSON.parse(JSON.stringify(r.data))
                })
        },
        buttonClickViewImage(verificationRequest) {
            this.image = verificationRequest.image
        }
    },
    mounted() {
        axios.get("http://localhost:8084/api/verificationrequest/get-all")
            .then(r => {
                var verificationRequestes = JSON.parse(JSON.stringify(r.data))
                verificationRequestes.forEach(element => {
                    axios.get("http://localhost:8081/api/userprofile/get-by-id/" + element.user.id)
                        .then(r => {
                            element.user = JSON.parse(JSON.stringify(r.data))
                        })
                });
                this.verificationRequestes = verificationRequestes
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

    .container-column {
        display: flex;
        flex-direction: row;
    }

    .container-column {
        display: flex;
        flex-direction: column;
    }
</style>