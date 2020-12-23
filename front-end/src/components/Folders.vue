<template>
  <div>
    <div class="modal-body">
      <FoldersList/>
    </div>
    <v-btn color="accent" text @click="closeFolders">
      Close
    </v-btn>
  </div>
</template>

<script>
import FoldersList from "./FoldersList";

export default {
  name: "Folders",
  components: {FoldersList},
  methods: {
    closeFolders() {
      this.$root.$emit("closeMask");
    }
  }
}
</script>

<style scoped>

.modal-body {
  margin: 20px 0;
}

</style>
