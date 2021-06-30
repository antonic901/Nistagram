<template>
  <div >
    <router-view>

    </router-view>
    <!-- <Test/> -->
  </div>
</template>

<script>

import Test from './views/Test.vue'

export default {
  name: 'App',
  components: {
    Test
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
