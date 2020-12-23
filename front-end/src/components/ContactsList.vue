<template>
  <div>
    <v-card-text>
      <v-card outlined>
        <v-list class="overflow-y-auto" dense max-height="256px" nav>
          <v-list-item
              v-for="item in list"
              :key="item.ID"
              link
              @click="viewContact(item)">
            <v-list-item-content>
              <v-list-item-title><h3>{{ item.name }}</h3></v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
      </v-card>
    </v-card-text>
    <v-card-actions>
      <v-container>
        <v-btn color="primary" @click="addNew">Add</v-btn>
      </v-container>
    </v-card-actions>
  </div>
</template>

<script>
export default {
  name: "ContactsList",

  data() {
    return {
      list: [
        {name: 'Contact 1', ID: '55', emails: "a.waleed;waleed;hey;"},
        {name: 'Contact 2', ID: '56', emails: "aaaa;aaa2;adw;"},

      ]
    }
  },

  methods: {
    viewContact(contact) {
      this.$root.$emit("viewContact", contact);
    },
    addNew() {
      this.$root.$emit("addContact");
    }
  },

  created() {
    //TODO load contact list
  }
}
</script>

<style scoped>

</style>
