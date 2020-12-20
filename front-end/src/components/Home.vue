<template>
<div>
  <v-row>
    <v-container fluid class="myContainer">
      <HomeHeader/>
    </v-container>
  </v-row>
  <v-row>
    <div class="centered">
    <v-container>
      HOME
    </v-container>
    </div>
  </v-row>
</div>
</template>

<script>
import HomeHeader from "./HomeHeader";
export default {
  name: "Home",
  components: {HomeHeader}
}
</script>

<style scoped>
.centered {
  position: fixed;
  top: 50%;
  left: 50%;
  /* bring your own prefixes */
  transform: translate(-50%, -50%);
}

.myContainer {
  padding-right: 5%;
  padding-left: 5%;
}
</style>
