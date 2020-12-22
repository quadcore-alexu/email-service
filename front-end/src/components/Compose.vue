<template>
  <div>
    <v-form ref="form" v-model="validForm">
      <v-container>
        <v-row>
          <v-text-field
              v-model="subject"
              label="Subject"
          ></v-text-field>
        </v-row>
        <v-row>
          <v-text-field
              v-model="to"
              :rules="[requiredRules]"
              label="To:"
              required
          ></v-text-field>
        </v-row>
        <v-row>
          <v-textarea
              counter
              label="Body"
          />
        </v-row>
        <v-row>
          <v-file-input
              v-model="files"
              chips
              counter
              label="Attachments"
              multiple
              @change="filesChanged"
              @click:clear="clearFiles"
          />
        </v-row>
        <v-row style="padding-top: 7%">
          <v-container style="text-align: right">
            <v-btn>
              <v-icon left>
                mdi-send
              </v-icon>
              Send
            </v-btn>
          </v-container>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script>
export default {
  name: "Compose",
  data() {
    return {
      validForm: false,
      subject: "",
      to: "",
      requiredRules: value => !!value || 'Required',
      files: [],
      allFiles: [],
    }
  },

  methods: {
    filesChanged() {
      this.files.forEach(this.addToAllFiles);
      console.log(this.allFiles)
      this.files = this.allFiles;
      console.log(this.files)
    },
    addToAllFiles(item) {
      if (!this.allFiles.some(function (oldFile) {
        return oldFile.name === item.name;
      })) {
        this.allFiles.push(item);
      }
    },


    clearFiles() {
      this.allFiles = [];
    }
  }
}
</script>

<style scoped>

</style>
