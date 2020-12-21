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
        openedMail: null,
    },
    mutations: {
        setDark(state, payload) {
            state.dark = payload;
        },
        setUser(state, payload) {
            state.user = payload;
        },
        setMail(state, payload) {
            state.openedMail = payload;
        },
        resetUser(state) {
            state.user = null;
            state.openedMail = null;
        },
        resetDark(state) {
            state.dark = null;
        },
    },

    getters: {
        getUser(state) {
            return state.user;
        },
        getMail(state) {
            return state.openedMail;
        },
        getDark(state) {
            return state.dark;
        }
    }
})

export default store
