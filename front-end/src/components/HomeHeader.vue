<template>
  <div>
    <v-row>
      <v-col>
        <v-switch v-model="dark" @click="toggleDarkMode" label="dark mode"></v-switch>
      </v-col>
      <v-col>
        <div id = "logout">
          <v-btn color="error" small @click="logout">Logout</v-btn>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "HomeHeader",

  data() {
    return {
      dark: this.$store.state.dark,
    }
  },

  methods: {
    toggleDarkMode() {
      this.$root.$emit("toggleDarkMode", this.dark);
    },
    logout() {
      this.$root.$emit("logOut");
    }
  },

  activated() {
    this.dark = this.$store.state.dark;
  }
}
</script>

<style lang="scss">
#logout {
  padding: 30px;
  text-align: right;
}
</style>
