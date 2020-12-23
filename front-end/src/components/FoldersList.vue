<template>
  <div>
    <v-card-text>
      <v-card outlined>
        <v-list class="overflow-y-auto" dense max-height="256px" nav>
          <v-list-item
              v-for="item in list"
              :key="item.ID"
              link
              @click="viewFolder(item)">
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
  name: "FoldersList",

  data() {
    return {
      list: [
        {name: 'folder1', ID: '11'},
        {name: 'folder2', ID: '12'},
        {name: 'folder3', ID: '13'},
      ]
    }
  },

  methods: {
    viewFolder(folder) {
      this.$root.$emit("viewFolder", folder);
    },
    addNew() {
      this.$root.$emit("addFolder");
    }
  },

  created() {
    //TODO load list of folders from store
  }
}
</script>

<style scoped>

</style>
