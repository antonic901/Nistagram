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
                            <b-form-input type="text" v-model="user.email" placeholder="enter email" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.username" placeholder="enter username" style="font-style:italic" required/>
                            <b-form-input type="password" v-model="user.password" placeholder="enter password" style="font-style:italic"  aria-describedby="input-live-help input-live-feedback" :state= validation trim required/> 
                              <b-form-invalid-feedback id="input-live-feedback" style="font-style:italic">
                                  Enter at least 7 characters. 
                              </b-form-invalid-feedback>
                            <b-form-input type="password" v-model="user.confirmPassword" placeholder="confirm password" style="font-style:italic" required/> 
                            <b-form-input type="text" v-model="user.name" placeholder="enter name" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.surname" placeholder="enter surname" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.phoneNumber" placeholder="enter phone number" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.gender" placeholder="enter gender" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.birthdayDate" placeholder="enter birthday date" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.website" placeholder="enter website" style="font-style:italic" required/>
                            <b-form-input type="text" v-model="user.biography" placeholder="enter biography" style="font-style:italic" required/>
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
import Home from './Home.vue'
import Navbar from '../components/Navbar.vue'

export default {
  name: "RegistrationPage",
  components: {
      Home,
      Navbar
  },
  data() {
    return {
      user: {
       name: "",
       surname: "",
       email: "",
       phoneNumber: "",
       gender: "",
       birthdayDate: "",
       website: "",
       biography: "",
       username: "",
       password: "",
       confirmPassword: ""
      },
  
      show: true,
    };
  },
  methods: {
    register() {
      if(this.user.password !== this.user.confirmPassword)
      {
        alert("Passwords don't match!");
        return;
      }
      axios.post("http://localhost:8081/api/userprofile/register-user", this.user)
        .then(r => {
          console.log(r);
        })
        .catch(r => {
          console.log(r);
        })
    }


  },
  computed: {
      validation() {
        return this.user.password.length > 7 ? true : false
      }
  }}
</script>

<style scoped>
.cls{
  margin-bottom: 40px;
}
 .background {
  background-color: #3498db;
  position: fixed; 
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
    margin-top: 100px;
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