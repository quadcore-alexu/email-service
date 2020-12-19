<template>
<div>
  <h1>SignIn</h1>
  <v-btn @click="signIn" class = "btn">Go To Home</v-btn>

</div>
</template>

<script>


export default {
  name: "SignIn",

  methods: {
    signIn() {
      window.location.href = 'home.html';
    }
  }
}
</script>

<style scoped>

.btn {
  margin: 2%;
}
</style>
