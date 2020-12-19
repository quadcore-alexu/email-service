<template>
  <div id="app">
    <v-app>
      <v-main>
        <div id="nav">
          <v-row>
            <v-col>
              <v-switch v-model="dark" @click="toggleDarkMode" label="dark mode" style="margin-top: 0"></v-switch>
            </v-col>
            <v-col>
              <router-link to="/">Sign-in</router-link>
              |
              <router-link to="/signup">Sign-up</router-link>
            </v-col>
          </v-row>
        </div>
        <router-view class="centered"/>
      </v-main>
    </v-app>
  </div>
</template>

<script>
export default {

  data() {
    return {
      dark: false,
    }
  },

  methods: {
    toggleDarkMode() {
      this.$vuetify.theme.dark = this.dark;
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

#nav {
  padding: 30px;
  text-align: right;

  a {
    font-weight: bold;
    color: #039BE5;
    text-decoration: none;

    &.router-link-exact-active {
      text-decoration: underline;
    }
  }
}

.centered {
  position: fixed;
  top: 50%;
  left: 50%;
  /* bring your own prefixes */
  transform: translate(-50%, -50%);
}

.v-application {
  background-color: var(--v-background-base) !important;
}
</style>
