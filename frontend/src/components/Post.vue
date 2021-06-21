<template>
  <b-card no-body style="flex-direction: column; max-width: 50rem; margin-top: 50px;">
      <template #header>
        <b-row no-gutters>
            <b-col md="11">
                    <h4 class="mb-0">@imenko_prezimenić</h4>
            </b-col>
            <b-col md="1" style="text-align:right">
                 <b-icon icon="exclamation-triangle" scale="1.25"></b-icon>
            </b-col>
          </b-row>
      </template>

      <b-card-body style="padding-top: 10px; padding-bottom: 0px;">
        <b-card-sub-title class="mb-2">Lokacija</b-card-sub-title>
      </b-card-body>

      <b-card-img src="https://placekitten.com/480/210" alt="Image"></b-card-img>

      <b-card-body>
        <b-row no-gutters>
            <b-col md="11">
                <b-card-text>
                  Some quick example text to build on the card title and make up the bulk of the card's content.
                </b-card-text>
            </b-col>
            <b-col md="1" style="text-align:right">
                 <b-icon icon="bookmark" scale="1.5" ></b-icon>
            </b-col>
        </b-row>
        <b-row no-gutters>
          <span class="likebtn-wrapper" data-theme="drop" data-ef_voting="push" data-white_label="true" data-identifier="LikeDislike" style="height: 15px; padding-top: 0px; padding-bottom: 0px;" ></span>
        </b-row>
        
      </b-card-body>

      <b-list-group flush>
        <b-list-group-item>Komentari</b-list-group-item>
      </b-list-group>

      <b-card-footer footer-text-variant="muted">3 days ago</b-card-footer>

    </b-card>
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