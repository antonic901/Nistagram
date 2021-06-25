<template>
    <div class="container-1">
        <div class="container-4">
            <div class="container-1">
                <div class="container-3">
                    <b-form-input type="text" v-model="user.username" style="border-radius:20px; margin:5px; text-align:center; font-weight:bold;" placeholder="username" trim ></b-form-input>
                    <b-form-input type="password" v-model="oldPassword" style="border-radius:20px; margin:5px; text-align:center;" placeholder="enter old password" trim></b-form-input>
                    <b-form-input type="password" v-model="newPassword" style="border-radius:20px; margin:5px; text-align:center;" placeholder="enter new password" trim></b-form-input>
                    <b-form-input type="password" v-model="confirmPassword" style="border-radius:20px; margin:5px; text-align:center;" placeholder="confirm new password" trim></b-form-input>
                    <b-form-input type="text" v-model="user.email" class="item-4" style="border-radius:20px; margin:5px; text-align:center;font-weight:bold;" placeholder="email" trim></b-form-input>
                </div>

                <div class="container-3">
                    <b-form-input type="text" v-model="user.website" style="border-radius:20px; margin:5px; text-align:center;font-weight:bold;" placeholder="website" trim></b-form-input>
                    <b-form-input type="text" v-model="user.biography" style="border-radius:20px; margin:5px; text-align:center;font-weight:bold;" placeholder="biography" trim></b-form-input>
                    <b-form-input type="text" v-model="user.name" style="border-radius:20px; margin:5px; text-align:center;font-weight:bold;" placeholder="name" trim></b-form-input>
                    <b-form-input type="text" v-model="user.surname" style="border-radius:20px; margin:5px; text-align:center;font-weight:bold;" placeholder="surname" trim></b-form-input>
                    <b-form-input type="text" v-model="user.phoneNumber" style="border-radius:20px; margin:5px; text-align:center;font-weight:bold;" placeholder="phone" trim></b-form-input>
                </div>
                <div class="container-3">
                    <b-form-checkbox
                        v-model="user.private"
                        :value="true"
                        :unchecked-value="false"
                        style="color:white;margin:10px;font-size:16px;"
                    >Private profile</b-form-checkbox>
                    <b-form-checkbox
                        v-model="user.taggable"
                        :value="true"
                        :unchecked-value="false"
                        style="color:white;margin:10px;font-size:16px;"
                    >Can other user tag you</b-form-checkbox>
                    <b-form-checkbox
                        v-model="user.receiveMessage"
                        :value="true"
                        :unchecked-value="false"
                        style="color:white;margin:10px;font-size:16px;"
                    >Receive messages only from friends</b-form-checkbox>
                    <b-button v-if="this.verifyStatus == 'Verification is in progress' || this.verifyStatus == 'Profile is verified'" class="item-4" style="margin:5px;border-radius:20px;">{{verifyStatus}}</b-button>
                    <b-button v-else v-b-modal.modalVerifyProfile class="item-4" style="margin:5px;border-radius:20px;">{{verifyStatus}}</b-button>

                </div>
            </div>
            <label style="color:red;text-align:center;"><b>{{message}}</b></label>
            <b-button class="item-4" style="border-radius:20px; margin:5px; text-align:center; width:150px" v-on:click="clickSave">Save</b-button>
        </div>
        <div>
            <VerifyProfile/>
        </div>
    </div>     
</template>

<script>

import axios from 'axios'
import VerifyProfile from '../components/VerifyProfile.vue'

export default {
    name: 'EditProfile',
    props: ['showEdit'],
    components: {
        VerifyProfile
    },
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        ShowEdit() {
            return this.showEdit
        },
        verifyStatus() {
            return this.$store.getters.getVerifyStatus
        }
    },
    data() {
        return {
            user: {
                id: null,
                name: "",
                surname: "",
                email: "",
                phoneNumber: null,
                gender: null,
                birthdayDate: null,
                website: "",
                biography: "",
                username: "",
                private: null,
                taggable: null,
                receiveMessage: null
            },
            oldPassword: '',
            newPassword: '',
            confirmPassword: '',
            message: ''
        }
    },
    methods: {
        async clickSave() {
            if(this.user.username != this.User.username) {
                var error = await axios.get("http://localhost:8081/api/userprofile/check-username/" + this.user.username)
                    .then(r => {
                        if(r.data == 'taken') {
                            this.message = "Username is already taken"
                            return 'error'
                        }
                        else {
                            this.message = ''
                            return ''
                        }
                    })
                
                if(error == 'error') return
            }

            if(this.user.email != this.User.email) {
                var error = await axios.get("http://localhost:8081/api/userprofile/check-email/" + this.user.email)
                    .then(r => {
                        if(r.data == 'taken') {
                            this.message = "Email is already taken"
                            return 'error'
                        }
                        else {
                            this.message = ''
                            return ''
                        }
                    })
                
                if(error == 'error') return
            }
            
            if(this.oldPassword != '') {

                if(this.oldPassword != this.User.password) {
                    this.message = "Old password is not valid"
                    return
                }

                else if(this.newPassword == '') {
                    this.message = "New password is not valid"
                    return
                }

                else if(this.newPassword != this.confirmPassword) {
                    this.message = "Passwords do not match"
                    return
                }

                else {
                    this.message = ''
                }

            }
            
            var user;
            if(this.oldPassword != '') {
                user = {
                    id: this.user.id,
                    name: this.user.name,
                    surname: this.user.surname,
                    email: this.user.email,
                    phoneNumber: this.user.phoneNumber,
                    gender: this.user.gender,
                    birthdayDate: this.user.birthdayDate,
                    website: this.user.website,
                    biography: this.user.biography,
                    username: this.user.username,
                    password: this.newPassword,
                    private: this.user.private,
                    taggable: this.user.taggable,
                    receiveMessage: this.user.receiveMessage
                }
            }
            else {
                    user = {
                    id: this.user.id,
                    name: this.user.name,
                    surname: this.user.surname,
                    email: this.user.email,
                    phoneNumber: this.user.phoneNumber,
                    gender: this.user.gender,
                    birthdayDate: this.user.birthdayDate,
                    website: this.user.website,
                    biography: this.user.biography,
                    username: this.user.username,
                    password: this.User.password,
                    private: this.user.private,
                    taggable: this.user.taggable,
                    receiveMessage: this.user.receiveMessage
                }
            }

            this.$store.dispatch('updateUser', {user})

            axios.post("http://localhost:8081/api/userprofile/update-user", user)

            alert('Your profile is successfuly updated!')

        },
        async verifyClick() {
            if(this.verifyStatus == 'Verification is in progress' || this.verifyStatus == 'Profile is verified') {
                return
            }
            // await axios.get("http://localhost:8084/api/verificationrequest/request-verification/" + this.user.id)
            // this.verifyStatus = 'Verification is in progress'
        }
    },
    created() {
        var user = this.$store.getters.getUser
        this.user.id = user.id
        this.user.name = user.name
        this.user.surname = user.surname
        this.user.email = user.email
        this.user.phoneNumber = user.phoneNumber
        this.user.gender = user.gender
        this.user.birthdayDate = user.birthdayDate
        this.user.website = user.website
        this.user.biography = user.biography
        this.user.username = user.username
        this.user.private = user.private
        this.user.taggable = user.taggable
        this.user.receiveMessage = user.receiveMessage
        axios.get("http://localhost:8084/api/verificationrequest/check-status-of-verification/" + this.user.id)
            .then(r => {
                if(r.data == 'not_sended') {
                    this.$store.dispatch('updateVerifyStatus', 'Verify profile')
                }
                else if(r.data == 'waiting') {
                    this.$store.dispatch('updateVerifyStatus', 'Verification is in progress')
                }
                else if(r.data == 'verified') {
                    this.$store.dispatch('updateVerifyStatus', 'Profile is verified')
                }
            })
    }
}
</script>

<style scoped>
.container-1 {
    display: flex;
    flex-direction: row;
}

.container-2 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 20px;
}

.container-3 {
    display: flex;
    flex-direction: column;
    margin: 5px;
    justify-content: space-between;
}

.container-4 {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.card {
    background: white;
    margin: 5px;
}

.item-1 {
    flex-grow: 1;
}

.item-2 {
    flex-grow: 1;
}

.item-3 {
    align-self: center;
    margin-right: 20px;
}

.item-4 {
    align-self:center;
}

.button {
    margin:10px;
}
</style>