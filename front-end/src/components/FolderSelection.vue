<template>
  <div>
    <div class="modal-body">
      <v-card>
        <v-list two-line>
            <v-list-item-group
            v-model="selected"
            active-class="pink--text"
            >
            <template v-for="(folder) in this.$store.getters.dumpFolders">
                <v-list-item :key="folder.ID" @click="showFolder(folder.ID)">
                <template>
                    <v-list-item-content>
                    <v-list-item-subtitle
                        class="text--primary"
                        v-text="folder.name"
                    ></v-list-item-subtitle>
                    </v-list-item-content>
                </template>
                </v-list-item>
                
            </template>
            </v-list-item-group>
        </v-list>
      </v-card>
    </div>
    <v-btn color="accent" text @click="closeFolderSelection">
      Close
    </v-btn>
  </div>
</template>

<script>

export default {
  name: "FolderSelection",
  data() {
    return {
        selected :[]
    }
  },
  methods: {
    showFolder(id) {
      this.$root.$emit("FolderSelected", id);
    },
    closeFolderSelection() {
        this.$root.$emit("CloseFoldersModal");
    }
  }
}
</script>

<style scoped>

.modal-body {
  margin: 20px 0;
}

</style>
