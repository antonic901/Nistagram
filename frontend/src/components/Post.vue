<template>
    <div style="width: 750px; height: 700px; margin: auto; border: 3px solid #73AD21;"> 
      <div class="card mb-3" style="flex-direction: column; padding-top: 25px; padding-bottom: 75px;">
        <h3 class="card-header">@imenko_prezimenić</h3>
        <div class="card-body">
          <h6 class="card-subtitle text-muted">Lokacija</h6>
        </div>
        <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="70%" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
          <rect width="100%" height="100%" fill="#868e96"></rect>
          <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
        </svg>
        <div class="card-body" style="height: 15px; padding-bottom: 0px;">
          <p class="card-text">Ovdje ide opis slike</p>
        </div>
        <div class="card-body">
          <span class="likebtn-wrapper" data-theme="drop" data-ef_voting="push" data-white_label="true" data-identifier="LikeDislike" style="height: 15px; padding-top: 0px; padding-bottom: 0px;" ></span>
        </div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item">Komentari</li>
        </ul>
        <div class="card-footer text-muted">
          2 days ago
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: "Post",
    computed: {
        user(){
            return this.$store.state.user
        },
        isUserLogged() {
            return this.$store.state.isUserLogged
        },
        userFullname() {
            return this.$store.getters.getFullName
        }
    },
}
</script>

<script>
(function(d,e,s){
    if(d.getElementById("likebtn_wjs"))return;
    a=d.createElement(e);
    m=d.getElementsByTagName(e)[0];
    a.async=1;
    a.id="likebtn_wjs";
    a.src=s;
    m.parentNode.insertBefore(a, m)})
    (document,"script","//w.likebtn.com/js/w/widget.js");
</script>