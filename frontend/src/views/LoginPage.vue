<template>
    <div class = "background">
    <Navbar/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form onsubmit="event.preventDefault()" class="box">
                            <div class = "cls">
                                <h1 class = "title" style="color:#4d4d4d ">Log in</h1>
                                <p class="text-muted"> Please enter your username and password!</p>
                                <p v-if="show" style="color:red;" >Your username or password isn't valid!</p>
                            </div>
                            <input type="text" v-model="Login.username" name="" placeholder="username" style="font-style:italic" required> 
                            <input type="password" v-model="Login.password" name="" placeholder="password" style="font-style:italic" required> 
                            <a class="forgot text-muted" href="#">Forgot password?</a>
                            <br/>
                            <br/>
                            <router-link to="/registrationPage" class="routerlink">Don't have an account? Create one!</router-link>
                            <input type="submit"  style="color: white" name="" value="Log in" href="#" v-on:click="login">
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
  name: "LoginPage",
  components: {
      Navbar
  },
  data() {
    return {
      show: false,
      error: "",
      Login: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    login() {
      if(this.Login.username == "" || this.Login.password == "") {
        this.show = true
        this.error = "Please fill all input fields!"
        return
      }
      axios.post("http://localhost:8081/api/user/login-user", this.Login)
        .then(r => {
            var user = JSON.parse(JSON.stringify(r.data))
            if(user.id != null) {
              this.show = false;
              if(user.userType == 'USER') {
                axios.post("http://localhost:8081/api/userprofile/login-user", this.Login)
                  .then(r => {
                      var user = JSON.parse(JSON.stringify(r.data))
                      this.$store.dispatch('updateUser', {user});
                      this.$router.push({name: 'Home'})
                  })
              }
              else {
                this.$store.dispatch('updateUser', {user});
                this.$router.push({name: 'HomeAdmin'})
              }
            }
            else {
              this.show = true;
            }
            
        })
    },
    onSubmit(event) {
      event.preventDefault();
    },
    onReset(event) {
      event.preventDefault();
      console.log("reset");
    }
  }
}
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

.routerlink {
  display: block;
  color:#7e7e7e;
}

</style>
<style>

</style>