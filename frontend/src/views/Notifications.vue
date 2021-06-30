<template>
    <div class="background container-column">
        <Navbar/>
        <div v-for="notification in notifications" :key="notification.id" class="container-row" style="margin-top:20px;">
            <b-card img-src="https://picsum.photos/72/72" img-alt="Card image" img-left class="mb-3">
                <b-card-text>
                    <b>{{notification.description}}</b>
                </b-card-text>
            </b-card>
            <b-button style="height: 72px;" v-b-modal.modalAdditionalPost v-on:click="openPost(notification)">Open</b-button>
        </div>
        <div>
            <PostModal/>
        </div>
    </div>
</template>

<script>

import axios from 'axios'
import Navbar from '../components/Navbar.vue'
import PostModal from '../components/PostModal.vue'

export default {
    name: 'Notifications',
    components: {
        Navbar,
        PostModal
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        }
    },
    data() {
        return {
            notifications: []
        }
    },
    methods: {
        async openPost(notification) {
            this.$store.dispatch('updateEntity', notification.post)
            await axios.get(this.$store.getters.getPostAPI + "/api/notification/set-notification-status/" + notification.id)
            await axios.get(this.$store.getters.getPostAPI + "/api/notification/get-new-notifications/" + this.User.id)
                .then(r => {
                    var response = JSON.parse(JSON.stringify(r.data))
                    if(response.length == 0) {
                        this.$store.dispatch('updateNewNotification', false)
                    }
                    else this.$store.dispatch('updateNewNotification', true)
                })
        }
    },
    mounted() {
        if(this.User.id == null) return
        axios.get(this.$store.getters.getPostAPI + "/api/notification/get-notifications/" + this.User.id)
            .then(r => {
                var notifications = JSON.parse(JSON.stringify(r.data))

                notifications.forEach(notification => {
                    var post = notification.post
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

                this.notifications = notifications
            })
    }
}
</script>


<style scoped>

    .mb-3 {
        height: 72px;
    }

    .container-row {
        display: flex;
        flex-flow: row;
        justify-content: center;
    }

    .container-column {
        display: flex;
        flex-flow: column;
    }

     .background {
        background-color: #3498db;
        min-width: 100%;
        min-height: 100%;
        position: absolute;
    }
</style>