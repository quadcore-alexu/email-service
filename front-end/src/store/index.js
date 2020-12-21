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
        user: null,
    },
    mutations: {
        setDark(state, payload) {
            state.dark = payload;
        },
        setUser(state, payload) {
            state.user = payload;
        },
        resetUser(state) {
            state.user = null;
        },
        resetDark(state) {
            state.dark = null;
        },
    },

    getters: {
        getUser(state) {
            return state.user;
        },
        getDark(state) {
            return state.dark;
        }
    }
})

export default store
