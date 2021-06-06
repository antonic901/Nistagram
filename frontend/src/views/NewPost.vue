<template>
    <div class = "background">
    <Navbar/>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form onsubmit="event.preventDefault()" class="box">
                            <div class = "cls">
                                <h1 class = "title" style="color:#4d4d4d ">New post</h1>
                            </div>
                            <div >
                                <div style="font-style:italic" required>
                                    <b-form-file v-model="file2" class="mt-3 cl1" plain></b-form-file>
                                </div>
                            </div>
                            <input type="text" placeholder="enter description" style="font-style:italic"> 
                            <input type="text" placeholder="enter tags" style="font-style:italic"> 
                            <input type="text" placeholder="enter location" style="font-style:italic">
                            <br/>
                            <input type="submit"  style="color: white" name="" value="Create">
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
  name: "NewPost",
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
  
    onSubmit(event) {
      event.preventDefault();
    },
    onReset(event) {
      event.preventDefault();
      console.log("reset");
    },
  computed: {
      
  }}
</script>

<style scoped>
.cl1 {
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 450px;
    border-radius: 24px;
    transition: 0.25s
}
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
    width: 550px;
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

.box input[type="text"]{
    border: 0;
    background: none;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 450px;
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