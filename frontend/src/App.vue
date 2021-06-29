<template>
  <div >
    <router-view>

    </router-view>
    <!-- <Test/> -->
    <!-- <div id="app">
      <HereMap  :center="center" />
    </div> -->
    <!-- <div>
      <Map/>
    </div> -->
  </div>
</template>

<script>

import Test from './views/Test.vue'
import HereMap from './components/HereMap.vue'
import Map from './components/Map.vue'

export default {
  name: 'App',
  components: {
    Test,
    HereMap,
    Map
  },
  data() {
    return {
        // we are this as prop to the HereMap component 
      center:{ 
        lat: 40.730610, 
        lng: -73.935242
      }
    }   
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
