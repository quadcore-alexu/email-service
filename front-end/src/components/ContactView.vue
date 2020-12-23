<template>
  <div>
    <v-card-text>
      <v-row>
        <v-btn color="primary" icon @click="back">
          <v-icon>mdi-keyboard-backspace</v-icon>
        </v-btn>
      </v-row>

      <v-container>
        <v-form>
          <v-row>
            <v-text-field
                v-model="name"
                label="Name"
            />
          </v-row>
          <v-row>
            <v-textarea v-model="emails" hint="enter emails separated by semicolon or newline"
                        label="Emails" persistent-hint/>
          </v-row>
        </v-form>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-container>
        <v-btn color="primary" @click="save">Save</v-btn>
      </v-container>
    </v-card-actions>
  </div>
</template>

<script>
export default {
  name: "ContactView",
  props: ['contact', 'isNew'],

  data() {
    return {
      name: this.contact.name,
      emails: null,
    }
  },

  methods: {
    back() {
      this.$root.$emit("contactsList");
    },

    save() {
      //TODO check isNew and act accordingly
      //call back-end
      //add to list
    }
  },

  created() {
    this.emails = this.contact.emails.replaceAll(";", ";\n");
  }
}
</script>

<style scoped>

</style>
