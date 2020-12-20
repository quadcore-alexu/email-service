<template>
  <div id="app">
    <v-app>
      <v-main>
        <v-container fluid>
          <keep-alive>
            <router-view/>
          </keep-alive>
        </v-container>
      </v-main>
    </v-app>
  </div>
</template>

<script>
export default {

  mounted() {
    this.$root.$on("toggleDarkMode", (state) => {
      this.$vuetify.theme.dark = state;
      this.$store.state.dark = null;
      this.$store.commit("setDark", state);
    });

    this.$root.$on("logOut", () => {
      this.$router.push("/");
    })
  },
  created() {
    this.$vuetify.theme.dark = this.$store.state.dark;
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

.v-application {
  background-color: var(--v-background-base) !important;
}
</style>

<!--TODO remove cached components on user logout-->
<!--TODO highlight selected tab in home's navigation drawer-->
