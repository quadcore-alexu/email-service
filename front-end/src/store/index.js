import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const store = new Vuex.Store({
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })],
    state: {
        dark: false,
        hh: true,
    },
    mutations: {
        setDark(state, payload) {
            state.dark = payload;
        },
    }
})

export default store
