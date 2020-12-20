<template>
  <div>
    <v-row>
      <v-col style="text-align: left">
        <v-switch v-model="dark" @click="toggleDarkMode" label="dark mode"
                  style="display: inline-block" class="ma-0 pa-0" hide-details/>
      </v-col>
      <v-col>
        <div id="nav">
          <router-link to="/">Sign-in</router-link>
          |
          <router-link to="/signup">Sign-up</router-link>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "SignInHeader",

  data() {
    return {
      dark: this.$store.state.dark,
    }
  },
  methods: {
    toggleDarkMode() {
      this.$root.$emit("toggleDarkMode", this.dark);
    },

  },

  activated() {
    this.dark = this.$store.state.dark;
  }
}
</script>

<style lang="scss">
#nav {
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
</style>
