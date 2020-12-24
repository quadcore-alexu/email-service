<template>
  <div class="fill-height">
    <v-card-title>
      {{ mail.title }}
    </v-card-title>
    <v-card-subtitle style="text-align: left">
      From: {{ mail.senderName }} &lt; {{ mail.senderAddress }} &gt; <br/>
      Priority: {{ mail.priority|showPriority }}
    </v-card-subtitle>
    <v-spacer/>
    <v-divider/>
    <v-spacer/>
    <v-card-text style="text-align: left; height: 70%">
      <v-container class="pl-0">
        {{ mail.content }}
      </v-container>
      <v-container class="pl-0" style="position: absolute; bottom: 3%;">
        hey
      </v-container>
    </v-card-text>
  </div>
</template>

<script>
export default {
  name: "MailView",

  props: ['mail'],

  data() {
    return {}
  },

  filters: {
    showPriority(p) {
      switch (p) {
        case 1:
          return "Minor"
        case 2:
          return "Regular"
        case 3:
          return "Important"
        case 4:
          return "Crucial"
      }
    }
  }
}
</script>

<style scoped>

</style>
