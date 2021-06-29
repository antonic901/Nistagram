<template>
  <div id="map">
      <b-form-input type="text" trim v-bind="search" placeholder="enter some address"></b-form-input>
      <b-button v-on:click="places(search)">Search</b-button>
  <!--In the following div the HERE Map will render-->
    <div id="mapContainer" style="height:600px;width:100%" ref="map"></div>
  </div>
</template>

<script>

import axios from 'axios'

export default {
  name: "HereMap",
  props: {
    center: Object
    // center object { lat: 40.730610, lng: -73.935242 }
  },
  data() {
    return {
      platform: null,
      apikey: "93gPQk6xlnyMUowb8wblwYan3eQUvGAzXFpdbJUyCac",
      search: ''
      // You can get the API KEY from developer.here.com
    };
  },
  async mounted() {
    // Initialize the platform object:
    const platform = new window.H.service.Platform({
      apikey: this.apikey
    });
    this.platform = platform;
    this.initializeHereMap();
  },
  methods: {
    initializeHereMap() { // rendering map

      const mapContainer = this.$refs.map;
      const H = window.H;
      // Obtain the default map types from the platform object
      var maptypes = this.platform.createDefaultLayers();

      // Instantiate (and display) a map object:
      var map = new H.Map(mapContainer, maptypes.vector.normal.map, {
        zoom: 10,
        center: this.center
        // center object { lat: 40.730610, lng: -73.935242 }
      });

      addEventListener("resize", () => map.getViewPort().resize());

      // add behavior control
      new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

      // add UI
      H.ui.UI.createDefault(map, maptypes);
      // End rendering the initial map
    },
    places(query) {
        axios.get("https://places.api.here.com/places/v1/browse?at=${this.center.lat},${this.center.lng}&q=${query}&api_key=${this.apikey}")
            .then(r => r.json())
            .then(data => {
                console.log(data);
            })
    }
  }
};
</script>

<style scoped>
#map {
  width: 60vw;
  min-width: 360px;
  text-align: center;
  margin: 5% auto;
  background-color: #ccc;
}
</style>