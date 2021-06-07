<template>
 <div class = "background">
    <Navbar/>
    <div class="container">
    <div class="main-body"> 
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="../assets/user.jpg" alt="Admin" class="rounded-circle" width="150">
                    <div class="mt-3">
                      <h4>John Doe</h4>
                      <p class="text-secondary mb-1">Full Stack Developer</p>
                      <p class="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                      <button class="btn btn-primary">Follow</button>
                      <button class="btn btn-outline-primary">Message</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Username</h6>
                    </div>
                    <div class="col-sm-9 text-secondary" >
                      kenneth123
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Name</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Kenneth 
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Surname</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Valdez
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fip@jukmuh.al
                    </div>
                  </div>
                  <hr>
                   <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Birtday</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fip@jukmuh.al
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Phone</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      (239) 816-9029
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Gender</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      Male
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Website</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fff
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Biography</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      fff
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-12">
                      <button class="btn btn-primary">Edit</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
 
        </div>
        
    </div>
    <div>
        <div class="container2">
  <div class="card__container">
    <div class="card2">
      <div class="card__content">
         <img src="../assets/picture1.jpg" width="100%">
        <button class="card__button">Open</button>
      </div>
    </div>
    <div class="card2">
      <div class="card__content">
         <img src="../assets/picture2.jpg" width="100%">
        <button class="card__button">Open</button>
      </div>
    </div>
    <div class="card2">
      <div class="card__content">
         <img src="../assets/picture1.jpg" width="100%">
        <button class="card__button">Open</button>
      </div>
    </div>
     <div class="card2">
      <div class="card__content">
         <img src="../assets/picture1.jpg" width="100%">
        <button class="card__button">Open</button>
      </div>
    </div>
     <div class="card2">
      <div class="card__content">
         <img src="../assets/picture2.jpg" width="100%">
        <button class="card__button">Open</button>
      </div>
    </div>
     <div class="card2">
      <div class="card__content">
         <img src="../assets/picture2.jpg" width="100%">
        <button class="card__button">Open</button>
      </div>
    </div>
  </div>
</div>
</div>
 </div>

</template>

<style>
 .background {
  background-color: #3498db;
  background-repeat: repeat-y;
  position:absolute;
  top: 0; 
  left: 0; 
  min-width: 100%;
  min-height: 100%;

} 
.body{
    margin-top:20px;
    color: #1a202c;
    text-align: left;
    background-color: #e2e8f0;    
}
.main-body {
    padding: 15px;
}

.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid rgba(0,0,0,.125);
    border-radius: .25rem;
    margin-top: 90px;
}

.card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1rem;
}

.gutters-sm {
    margin-right: -8px;
    margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
    padding-right: 8px;
    padding-left: 8px;
}
.mb-3, .my-3 {
    margin-bottom: 1rem!important;
}

.bg-gray-300 {
    background-color: #e2e8f0;
}
.h-100 {
    height: 100%!important;
}
.shadow-none {
    box-shadow: none!important;
}
@import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;0,700;1,400&display=swap");
*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Roboto", sans-serif;
}

.container2 {
  position:absolute;
  width: 100%;
  height: 100vh;
  background-color: #3498db;
  margin-top: 10px;
}

.card2 {
  width: 60%;
  height: 100%;
  max-height: 1000px;
  padding: auto;
  text-align: center;
  margin-top: 10px;
  border-radius: 10px;
 
}
.card__container {
  display: grid;
  width: 100%;
  height: 100%;
  grid-template-columns: 1fr 1fr 1fr;
  align-items: center;
  justify-items: center;
  color: white;
}
.card__content {
  width: 90%;
  height: 95%;
  background: #191a19;
  margin: 10px auto;
  border-radius: 5px;
  padding: 20px;
  cursor: pointer;
  transition: 0.3s all ease-in-out;
}
.card__content:hover {
  margin-top: -10px;
}
.card__header {
  text-transform: uppercase;
  font-size: 20px;
  margin: 40px auto;
}
.card__button {
  padding: 10px;
  border-radius: 50px;
  background: #1f75c4;
  color: white;
  font-weight: bold;
  cursor: pointer;
  border: none;
  margin: 50px auto;
}
</style>

<script>
import Navbar from '../components/Navbar.vue'

export default {
  name: "Profile",
  components: {
      Navbar
  }
  
}
</script>
