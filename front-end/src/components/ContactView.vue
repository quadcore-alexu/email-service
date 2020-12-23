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
                label="Name"
                :rules="[requiredRules]"
                clearable
            />
          </v-row>
          <v-row>
            <v-textarea v-model="emails" hint="Enter emails separated by semicolon or newline"
                        class="overflow-y-auto" label="Emails" persistent-hint rows="5"/>
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
      emails: this.contact.emails.replaceAll(";", ";\n"),
      validForm: false,
      requiredRules: value => !!value || 'Required',
    }
  },

  methods: {
    back() {
      this.$root.$emit("contactsList");
    },

    save() {
      this.$refs.form.validate();
      if (this.validForm) {
        //TODO check isNew and act accordingly
        //call back-end
        //add to list
      }
    }
  },

}
</script>

<style scoped>

</style>
