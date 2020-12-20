<template>
  <div>
    <v-row>
      <v-container fluid class="myContainer">
        <SignInHeader/>
      </v-container>
    </v-row>
    <v-row>
      <div id="signInComponent" class="centered">
        <v-card outlined>
          <v-card-title>
            <h3>Sign-in</h3>
          </v-card-title>

          <v-card-text style="padding-bottom: 0">
            <v-form v-model="validForm" ref="form">
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

          <v-card-actions style="padding: 0">
            <v-container class="btnContainer">
              <p v-show="!valid" style="color: indianred">Oops! The email or password seems to be incorrect.<br>
                Try again or <a @click="signUp" class="link">Sign-up instead</a>.</p>
              <v-btn color="primary" text @click="signIn">Sign-in</v-btn>
            </v-container>
          </v-card-actions>
        </v-card>
      </div>
    </v-row>
  </div>
</template>

<script>


import SignInHeader from "./SignInHeader";

export default {
  name: "SignIn",
  components: {SignInHeader},
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
      this.$refs.form.validate();
      if (this.validForm) {
        if (this.password === "admin")
          this.$router.push("/home")
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

.centered {
  position: fixed;
  top: 50%;
  left: 50%;
  /* bring your own prefixes */
  transform: translate(-50%, -50%);
}

.btnContainer {
  padding-top: 0;
}

.link {
  color: indianred;
  text-decoration: underline;
  text-underline: indianred;
}

.myContainer {
  padding-right: 5%;
  padding-left: 5%;
}
</style>
