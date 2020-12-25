<template>
  <div>
    <v-card-text>
      <v-row>
        <v-btn color="primary" icon @click="back">
          <v-icon>mdi-keyboard-backspace</v-icon>
        </v-btn>
      </v-row>
      <v-container>
        <v-form ref="form" v-model="validForm">
          <v-row>
            <v-text-field
                v-model="name"
                :rules="[requiredRules]"
                clearable
                label="Name"
            />
          </v-row>
        </v-form>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-container>
        <v-row>
          <v-col>
            <v-btn color="primary" @click="save">Save</v-btn>
          </v-col>
          <v-col>
            <v-btn color="error" @click="del">Delete</v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-card-actions>
  </div>
</template>

<script>
import FolderService from "../service/FolderService";

export default {
  name: "FolderView",
  props: ['folder', 'isNew'],

  data() {
    return {
      name: this.folder.name,
      id: this.folder.id,
      validForm: false,
      requiredRules: value => !!value || 'Required',

    }
  },

  methods: {
    back() {
      this.$root.$emit("foldersList");
    },

    save() {
      this.$refs.form.validate();
      if (this.validForm) {
        //TODO check isNew and act accordingly
        //call back-end
        //add to list

        let map= []

        if(this.isNew){
          map={name: this.name }
          FolderService.addFolder(map)
        }
        else{
          map={name: this.name,id: this.id}
          FolderService.editFolder(map)
        }
      }
    },

    del() {
      //delete from database and update store
    }
  }
}
</script>

<style scoped>

</style>
