<template>
  <div id="signInComponent">
    <v-card outlined>
      <v-card-title>
        <h3>Sign-in</h3>
      </v-card-title>

      <v-card-text>
        <v-form v-model="validForm">
          <v-container>
            <v-row>
              <v-text-field
                  v-model="email"
                  filled
                  label="Email"
                  append-icon="mdi-close"
                  @click:append="email = ''"
                  :rules="[rules.required]"
                  required
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                  filled
                  label="Password"
                  v-model="password"
                  :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show ? 'text' : 'password'"
                  @click:append="show = !show"
                  :rules="[rules.required]"
                  required
              ></v-text-field>
            </v-row>
          </v-container>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-container class="btnContainer">
          <p v-show="!valid" style="color: indianred">Oops! The email or password seems to be incorrect.<br>
            Try again or <a @click="signUp" class="link">Sign-up instead</a>.</p>
          <v-btn color="primary" text @click="signIn">Sign-in</v-btn>
        </v-container>
      </v-card-actions>
    </v-card>

  </div>
</template>

<script>


export default {
  name: "SignIn",

  data() {
    return {
      email: "",
      password: "",
      show: false,
      valid: true,
      rules: {
        required: value => !!value || 'Required',
      },
      validForm: false,
    }
  },

  methods: {
    signIn() {
      if (this.validForm) {
        if (this.password === "admin")
          window.location.href = 'home.html';
        else
          this.valid = false;
      }
    },

    signUp() {
      this.$router.push("/signup");
    }
  },

}
</script>

<style scoped>

#signInComponent {
  width: 30%;
}

.btnContainer {
  margin-top: -10%;
}

.link {
  color: indianred;
  text-decoration: underline;
  text-underline: indianred;
}
</style>
