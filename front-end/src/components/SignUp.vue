<template>
  <div>
    <v-row>
      <v-container fluid class="myContainer">
        <SignInHeader/>
      </v-container>
    </v-row>
    <v-row>
      <div id="signUpComponent" class="centered">
        <v-card outlined>
          <v-card-title><h3>Sign-up</h3></v-card-title>

          <v-card-text style="padding-bottom: 0">
            <v-form v-model="validForm" ref="form">
              <v-container>
                <v-row>
                  <v-col style="padding-left: 0">
                    <v-text-field
                        filled
                        v-model="firstname"
                        :rules="[nameRules,requiredRules]"
                        :counter="10"
                        label="First name"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col style="padding-right: 0">
                    <v-text-field
                        filled
                        v-model="lastname"
                        :rules="[nameRules,requiredRules]"
                        :counter="10"
                        label="Last name"
                        required
                    ></v-text-field>
                  </v-col>
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
                      v-model="confirmPassword"
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

          <v-card-actions style="padding: 0">
            <v-container class="btnContainer">
              <p v-show="!valid" style="color: indianred">Oops! It looks like this email address is already taken.</p>
              <v-btn color="primary" text @click="signUp">Sign-up</v-btn>
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
  name: "SignUp",
  components: {SignInHeader},
  data() {
    return {
      validForm: false,
      firstname: '',
      lastname: '',
      email: '',
      password: '',
      confirmPassword: '',
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
      this.$refs.form.validate();
      if (this.validForm) {
        if (this.email === "a.waleedothman") {
          this.$store.commit("setUser", {
            name: "Ahmed Waleed",
            email: "a.waleedothman@quadcore.com",
            key: "mockID"
          });
          this.$router.push("/home")
        } else {
          this.valid = false;
        }
      }
    }
  },

  mounted() {
    this.$root.$on("goHome", () => {
      this.$destroy();
    });
  },
  deactivated() {
    this.password = "";
    this.confirmPassword = "";
  },

  created() {
    this.$store.commit("resetUser");
    this.$root.$emit("delHome");
  },
}
</script>

<style scoped>
#signUpComponent {
  width: 50%;
  position: fixed;
  top: 50%;
  left: 50%;
  /* bring your own prefixes */
  transform: translate(-50%, -50%);
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

.myContainer {
  padding-right: 4%;
  padding-left: 4%;
}
</style>
