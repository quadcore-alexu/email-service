<template>
  <div>
    <v-list
        dense
        nav
    >
      <v-list-item
          v-for="item in list"
          :key="item.ID"
          link
          @click="viewContact(item)"
      >
        <v-list-item-content>
          <v-list-item-title>{{ item.name }}</v-list-item-title>
        </v-list-item-content>

      </v-list-item>
    </v-list>
  </div>
</template>

<script>
export default {
  name: "ContactsList",

  data() {
    return {
      list: [
        {name: 'Contact 1', ID: '55', emails: ['a.waleed', 'a.waleed2']},
        {name: 'Contact 2', ID: '56', emails: ['aaa', 'aaa2']}
      ]
    }
  },

  methods: {
    viewContact(contact) {
      this.$root.$emit("viewContact", contact);
    }
  }
}
</script>

<style scoped>

</style>
