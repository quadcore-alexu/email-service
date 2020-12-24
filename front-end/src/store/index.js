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
        folder: 2,
        page:1
    },
    mutations: {
        setDark(state, payload) {
            state.dark = payload;
        },
        setUser(state, payload) {
            state.user = payload;
        },
        setFolder(state, payload) {
            state.folder = payload;
        },
        setPage(state, payload) {
            state.page = payload;
        },
        resetUser(state) {
            state.user = null;
            state.openedMail = null;
            state.folder = 2;
            state.page = 0;
        },
        resetDark(state) {
            state.dark = null;
        },
        resetFolder(state) {
            state.folder = null;
        },
        resetPage(state) {
            state.page = null;
        },
    },
    getters: {
        getUser(state) {
            return state.user;
        },
        getDark(state) {
            return state.dark;
        },
        getFolder(state) {
            return state.folder;
        },
        getPage(state) {
            return state.page;
        }
    }
})

export default store
