import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  //states (data) can be edided only through mutations
  // strict: true,
  // this is like data:
  state: {
    user: {
      id: null,
      name: "",
      surname: "",
      email: "",
      phoneNumber: null,
      gender: null,
      birthdayDate: null,
      website: "",
      biography: "",
      username: "",
      password: "",
      private: null
   },
   posts: [],
   users: [],
   searchType: '',
   userProfile: null,
   type: '',
   entity: null
  },
  //methods that return data (state)
  getters: {
    getUser(state) {
      return state.user
    },
    getFullName(state) {
      return state.user.name + " " + state.user.surname
    },
    isUserLogged(state) {
      if(state.user.id != null) {
        return true
      }
      else {
        return false
      }
    },
    getPosts(state) {
      return state.posts
    },
    getUsers(state) {
      return state.users
    },
    getSearchType(state) {
      var check = state.searchType.includes("@")
      return check
    },
    lookingFor(state) {
      return state.searchType
    },
    getUserProfile(state) {
      return state.userProfile
    },
    getType(state) {
      return state.type
    },
    getEntity(state) {
      return state.entity
    }
  },
  //methods for changing date (state)
  mutations: {
    updateUser(state, {user}) {
      state.user = user;
    },
    updatePosts(state, posts) {
      state.posts = posts;
    },
    updateUsers(state, users) {
      state.users = users;
    },
    updateSearchType(state, type) {
      state.searchType = type
    },
    updateUserProfile(state, userProfile) {
      state.userProfile = userProfile
    },
    updateType(state, type) {
      state.type = type
    },
    updateEntity(state, entity) {
      state.entity = entity
    }
  },
  //always on components dispatch action which commit some mutations. Never commit mutations from component because of async
  actions: {
    updateUser(context, {user}) {
      context.commit('updateUser', {user})
    },
    updatePosts(context, posts) {
      context.commit('updatePosts', posts)
    },
    updateUsers(context, users) {
      context.commit('updateUsers', users)
    },
    updateSearchType(context, type) {
      context.commit('updateSearchType', type)
    },
    updateUserProfile(context, userProfile) {
      context.commit('updateUserProfile', userProfile)
    },
    updateType(context, type) {
      context.commit('updateType', type)
    },
    updateEntity(context, entity) {
      context.commit('updateEntity', entity)
    }
  }
})