import Vue from 'vue'
import VueRouter from 'vue-router'
import SignIn from "../components/SignIn";
import SignUp from "../components/SignUp";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/home',
    name: 'Home',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/Home'),
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
