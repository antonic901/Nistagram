import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      id: "",
      name: "",
      surname: "",
      email: "",
      phoneNumber: "",
      gender: null,
      birthdayDate: null,
      website: "",
      biography: "",
      username: "",
      password: "",
      confirmPassword: ""
    },
    isUserLogged: false,
    userFullname: ""
  },
  mutations: {
    // syncrous
  },
  actions: {
    // asyncronous
    setUser(state, payload) {
      
    }
  },
  modules: {
    getUser(state) {
      return state.user;
    }
  }
})
