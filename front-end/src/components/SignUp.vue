<template>
  <div id="signUpComponent">
    <v-card outlined>
      <v-card-title><h3>Sign-up</h3></v-card-title>

      <v-card-text>
        <v-form v-model="validForm">
          <v-container>
            <v-row>
              <v-text-field
                  filled
                  v-model="firstname"
                  :rules="[nameRules,requiredRules]"
                  :counter="10"
                  label="First name"
                  required
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                  filled
                  v-model="lastname"
                  :rules="[nameRules,requiredRules]"
                  :counter="10"
                  label="Last name"
                  required
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                  filled
                  v-model="email"
                  :rules="[requiredRules]"
                  label="E-mail"
                  placeholder="example: a.waleedothman"
                  required
                  suffix="@quadcore.com"
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                  filled
                  v-model="password"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[requiredRules, minRules]"
                  :type="show1 ? 'text' : 'password'"
                  label="Password"
                  hint="At least 8 characters"
                  counter
                  @click:append="show1 = !show1"
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                  filled
                  :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[requiredRules, confirmRules]"
                  :type="show2 ? 'text' : 'password'"
                  label="Confirm Password"
                  @click:append="show2 = !show2"
              ></v-text-field>
            </v-row>
          </v-container>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-container class="btnContainer">
          <p v-show="!valid" style="color: indianred">Oops! It looks like this email address is already taken.</p>
          <v-btn color="primary" text @click="signUp">Sign-up</v-btn>
        </v-container>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SignUp",

  data() {
    return {
      validForm: false,
      firstname: '',
      lastname: '',
      email: '',
      password: '',
      show1: false,
      show2: false,
      nameRules: v => v.length <= 10 || 'Name must be less than 10 characters',
      requiredRules: value => !!value || 'Required',
      minRules: v => v.length >= 8 || 'Min 8 characters',
      confirmRules: v => v === this.password || 'Passwords not matching',
      valid: true,
    }
  },

  methods: {
    signUp() {
      if (this.validForm) {
        if (this.email === "a.waleedothman") {
          window.location.href = 'home.html';
        } else {
          this.valid = false;
        }
      }
    }
  }
}
</script>

<style scoped>
#signUpComponent {
  width: 50%;
}
</style>
