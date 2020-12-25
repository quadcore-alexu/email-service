<template>
  <div>
    <v-card-text>
      <v-card outlined>
        <v-list v-if="list.length !== 0" class="overflow-y-auto" dense max-height="256px" nav>
          <v-list-item
              v-for="item in list"
              :key="item.contactID"
              link
              @click="viewContact(item)">
            <v-list-item-content>
              <v-list-item-title><h3>{{ item.contactName }}</h3></v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
        <v-container v-else><h3>Tap Add to create new contact</h3></v-container>
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

import ContactService from "../service/ContactService";

export default {
  name: "ContactsList",

  data() {
    return {
      list: []
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
    ContactService.loadContacts()
    .then(response => {
    this.list=response.data;
    })
  }
}
</script>

<style scoped>

</style>
