import Vue from 'vue'
import VueRouter from 'vue-router'
import RegistrationPage from '../views/RegistrationPage.vue'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import NewPost from '../views/NewPost.vue'
import NewStory from '../views/NewStory.vue'
import Profile from '../views/Profile.vue'
import Search from '../views/Search.vue'
import UserProfile from '../views/UserProfile.vue'
import AllUsers from '../views/AllUsers.vue'
import Collections from '../views/Collections.vue'

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/registrationPage',
    name: 'RegistrationPage',
    component: RegistrationPage
  },
  {
    path: '/loginPage',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/new-post',
    name: 'NewPost',
    component: NewPost
  },
  {
    path: '/new-story',
    name: 'NewStory',
    component: NewStory
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/userProfile',
    name: 'UserProfile',
    component: UserProfile
  },
  {
    path: '/allUsers',
    name: 'AllUsers',
    component: AllUsers
  },
  {
    path: '/collections',
    name: 'Collections',
    component: Collections
  }
]

export const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})