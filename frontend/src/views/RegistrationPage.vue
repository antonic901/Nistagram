<template>
    <div class = "background">
        <Navbar/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form onsubmit="event.preventDefault()" class="box">
                            <div class = "cls">
                                <h1 class = "title" style="color:#4d4d4d ">Sign up with us</h1>
                            </div>
                            <b-form-input type="text" v-model="user.email" placeholder="enter email" style="font-style:italic" v-on:input="checkIsEmailValid" required/>
                            <b-form v-if="!emailValid" style="color:red">
                                {{this.emailMessage}}
                            </b-form >
                            <b-form v-else style="color:green">
                                Looks Good.
                            </b-form>
                            <b-form-input type="text" v-model="user.username" placeholder="enter username" style="font-style:italic" v-on:input="checkIsUsernameValid" required/>
                            <b-form v-if="!usernameValid" style="color:red">
                                {{this.usernameMessage}}
                            </b-form >
                            <b-form v-else style="color:green">
                                Looks Good.
                            </b-form>
                            <b-form-input type="password" v-model="user.password" placeholder="enter password" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" :state= validation trim required/> 
                              <b-form-invalid-feedback id="input-live-feedback" style="font-style:italic">
                                  Enter at least 3 characters. 
                              </b-form-invalid-feedback>
                            <b-form-input type="password" v-model="user.confirmPassword" placeholder="confirm password" style="font-style:italic" required/> 
                            <b-form-input type="text" v-model="user.name" placeholder="enter name" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.surname" placeholder="enter surname" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.phoneNumber" placeholder="enter phone number" style="font-style:italic" required/>
                            <b-form-group label="Select GENDER:">
                              <b-form-radio-group v-model="user.gender" :aria-describedby="ariaDescribedby" aria-controls="ex-disabled-readonly">
                                <b-form-radio value="MALE">MALE</b-form-radio>
                                <b-form-radio value="FEMALE">FEMALE</b-form-radio>
                              </b-form-radio-group>
                            </b-form-group>
                            <b-form-group label="Select USERTYPE:">
                              <b-form-radio-group v-model="user.userType" :aria-describedby="ariaDescribedby" aria-controls="ex-disabled-readonly">
                                <b-form-radio value="USER">USER</b-form-radio>
                                <b-form-radio value="ADMIN">ADMIN</b-form-radio>
                              </b-form-radio-group>
                            </b-form-group>
                            <b-form-datepicker id="example-datepicker" v-model="user.birthdayDate" class="mb-2" placeholder="enter date of your birth"></b-form-datepicker>
                            <b-form-input type="text" v-model="user.website" placeholder="enter website" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.biography" placeholder="enter biography" style="font-style:italic" required/>
                            <b-form v-if="!formValid" style="color:red">
                                {{this.formMessage}}
                            </b-form >
                            <br>
                            <b-button v-on:click="register">Register</b-button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import Navbar from '../components/Navbar.vue'

export default {
  name: "RegistrationPage",
  components: {
      Navbar
  },
  data() {
    return {
      user: {
       name: "",
       surname: "",
       email: "",
       phoneNumber: "",
       gender: null,
       userType: null,
       birthdayDate: null,
       website: "",
       biography: "",
       username: "",
       password: "",
       confirmPassword: ""
      },
      show: false,
      passwordValid: false,
      usernameValid: false,
      usernameMessage: "",
      emailValid: false,
      emailMessage: "",
      formValid: false,
      formMessage: ""
    }
  },
  methods: {
    register() {
      if(this.user.name == "" || this.user.surname == "" || !this.emailValid || isNaN(this.user.phoneNumber) || this.user.gender == null ||
        this.user.birthdayDate == null || !this.passwordValid || !this.usernameValid || !this.passwordValid) {
          this.formValid = false;
          this.formMessage = "Please check again all input fields!";
          return;
      }
      this.formValid = true;
      if(this.user.userType == 'USER') {
        axios.post(this.$store.getters.getUserAPI + "/api/userprofile/register-user", this.user)
          .then(r => {
              if(r.data == "ok") {
                alert("Welcome to Nistagram! Please, now log in to get best experience.");
                this.$router.push({name: 'LoginPage'});
              }
              else {
                alert ("There was a problem trying to register you! Please try again in a few minutes!")
              }
          })
          .catch(r => {
            console.log(r);
          })
      }
      else {
        axios.post(this.$store.getters.getUserAPI + "/api/user/register-user", this.user)
          .then(r => {
              if(r.data == "ok") {
                alert("Welcome to Nistagram! Please, now log in to get best experience.");
                this.$router.push({name: 'LoginPage'});
              }
              else {
                alert ("There was a problem trying to register you! Please try again in a few minutes!")
              }
          })
          .catch(r => {
            console.log(r);
          })
      }

    },
    checkIsUsernameValid() {
      if(this.user.username.length >= 5 && this.user.username.length <= 15) {
        axios.get(this.$store.getters.getUserAPI + "/api/userprofile/check-username/" + this.user.username)
        .then(r => {
          if(r.data == "not_taken") {
            this.usernameValid = true;
          }
          else {
            this.usernameValid = false;
            this.usernameMessage = "This username is already taken.";
          }
        })
      }
      else {
        this.usernameMessage = "Your user ID must be 5-15 characters long.";
        this.usernameValid = false;
      }
    },
    checkIsEmailValid() {
      console.log(this.user.email);
      if(this.validEmail(this.user.email)) {
        axios.get(this.$store.getters.getUserAPI + "/api/userprofile/check-email/" + this.user.email)
        .then(r => {
          if(r.data == "not_taken") {
            this.emailValid = true;
          }
          else {
            this.emailValid = false;
            this.emailMessage = "This email is already taken.";
          }
        })
      }
      else {
        this.emailValid = false;
        this.emailMessage = "Email is not valid!";
      }
    },
    validEmail (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }
  },
  computed: {
      validation() {
        if(this.user.password.length > 3) {
          this.passwordValid = true;
        }
        else {
          this.passwordValid = false;
        }
        if(this.user.password != this.user.confirmPassword) {
          this.passwordValid = false;
        }
        else {
          this.passwordValid = true;
        }
        return this.user.password.length > 3 ? true : false
      }
  }}
</script>

<style scoped>
.cls{
  margin-bottom: 40px;
}
 .background {
  background-color: #3498db;
  position: absolute; 
  top: 0; 
  left: 0; 
  min-width: 100%;
  min-height: 100%;
} 
.title {
  font-family: fantasy;
  font-size: 30px;
  color: black;
}
.body {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
    background: linear-gradient(to right, #b92b27, #1565c0)

}

.card {
    margin-bottom: 20px;
    border: none;
   
}

.box {
    width: 450px;
    padding: 40px;
    position:absolute;
    top: 50%;
    left: 50%;
    background: #ffffff;
    box-shadow: 10px 4px 8px 0 rgba(0,0,0,0.2);
    text-align: center;
    transition: 0.25s;
    margin-top: 20px;
    border-radius: 20px; 
}

.box input[type="text"],
.box input[type="password"] {
    border: 0;
    background: none;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 250px;
    outline: none;
    color: rgb(0, 0, 0);
    border-radius: 24px;
    transition: 0.25s
}

.box h1 {
    color: white;
    text-transform: uppercase;
    font-weight: 500
}

.box input[type="text"]:focus,
.box input[type="password"]:focus {
    width: 300px;
    background-color:#ffffff;
}

.box input[type="submit"] {
    border: 0;
    background: none;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #7e7e7e;
    background-color: #7e7e7e;
    padding: 14px 40px;
    outline: none;
    color: rgb(0, 0, 0);
    border-radius: 24px;
    transition: 0.25s;
    cursor: pointer
}

.box input[type="submit"]:hover {
    background: #595959;
    border: 2px solid #595959;
}

.forgot {
    text-decoration: underline
}

</style>