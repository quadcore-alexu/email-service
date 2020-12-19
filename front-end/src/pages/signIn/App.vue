<template>
  <div id="app">
    <v-app>
      <v-main style="background-color: #F8F8F7">
        <div id="nav">
          <router-link to="/">Sign-in</router-link>
          |
          <router-link to="/signup">Sign-up</router-link>
        </div>
        <router-view class="centered"/>
      </v-main>
    </v-app>
  </div>
</template>

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
    color: #2c3e50;

    &.router-link-exact-active {
      color: #039BE5;
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
</style>
