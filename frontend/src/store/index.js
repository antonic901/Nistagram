import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  // this is like data:
  state: {
    user: {
      id: "",
      name: "",
      surname: "",
      email: "",
      phoneNumber: null,
      gender: null,
      birthdayDate: null,
      website: "",
      biography: "",
      username: "",
      password: ""
   },
   isUserLogged: false
  },
  //methods that return data (state)
  getters: {
    getFullName(state) {
      return state.user.name + " " + state.user.surname
    }
  },
  //methods for changing date (state)
  mutations: {
    updateUser(state, {user}) {
      state.user = user;
      if(user.id != null) {
        state.isUserLogged = true;
      }
      else {
        state.isUserLogged = false;
      }
    }
  }
})










// export default new Vuex.Store({
//   state: {
//     user: {
//       id: "",
//       name: "",
//       surname: "",
//       email: "",
//       phoneNumber: "",
//       gender: null,
//       birthdayDate: null,
//       website: "",
//       biography: "",
//       username: "",
//       password: "",
//       confirmPassword: ""
//     },
//     isUserLogged: false,
//     userFullname: ""
//   },
//   mutations: {
//     // syncrous
//   },
//   actions: {
//     // asyncronous
//     setUser(state, payload) {
      
//     }
//   },
//   modules: {
//     getUser(state) {
//       return state.user;
//     }
//   }
// })
