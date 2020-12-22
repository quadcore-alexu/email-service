<template>
  <div class="fill-height">
    <v-form v-model="validForm" class="fill-height">
      <v-container>
        <v-flex>

          <v-card-text>
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
                  :key="temp"
                  ref="attachments"
                  v-model="files"
                  chips
                  counter
                  label="Attachments"
                  multiple
                  @change="filesChanged"
                  @click:clear="clearFiles"
              />
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-container style="text-align: right">
              <v-btn>
                <v-icon left>
                  mdi-send
                </v-icon>
                Send
              </v-btn>
            </v-container>
          </v-card-actions>
        </v-flex>

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
      temp: 1, //workaround to force file-input to update
    }
  },
  methods: {
    filesChanged() {
      this.files.forEach(this.addToAllFiles);
      this.files = this.allFiles;
      this.temp += 1; //forcing file-input to update
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
