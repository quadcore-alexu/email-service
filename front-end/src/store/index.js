import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        dark: false,
        hh:true,
    },
    mutations: {
        setDark (state, payload) {
            state.dark = payload;
        },
    }
})

export default store
