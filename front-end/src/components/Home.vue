<template>
  <div>
    <v-row>
      <v-container fluid class="myContainer">
        <HomeHeader/>
      </v-container>
    </v-row>
    <v-row>
      <v-container fluid class="mainContainer">
        <v-card outlined>
          <v-row>
            <v-col cols="3" class="navBar">
              <v-card height="560"
                      width="350"
                      style="margin-left: 3%">
                <v-navigation-drawer permanent width="350">
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title class="title">
                        {{ user.name }}
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        {{ user.email }}
                      </v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>

                  <v-divider></v-divider>

                  <v-list
                      dense
                      nav
                  >
                    <v-list-item
                        v-for="item in items"
                        :key="item.key"
                        link
                        @click="navigate(item.key)"
                    >
                      <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                      </v-list-item-icon>

                      <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <template v-slot:append>
                    <div class="py-2 px-4">
                      <v-row>
                        <v-col>
                          <v-btn block color="accent">
                            <v-icon left>
                              mdi-folder
                            </v-icon>
                            Folders
                          </v-btn>
                        </v-col>
                        <v-col>
                          <v-btn block color="accent">
                            <v-icon left>
                              mdi-account-multiple
                            </v-icon>
                            Contacts
                          </v-btn>
                        </v-col>
                      </v-row>
                    </div>
                  </template>
                </v-navigation-drawer>
              </v-card>
            </v-col>
            <v-col cols="9">
              <v-container class="compCont">
                <v-card height="560">
                  <keep-alive>
                    <component :is="currentComponent" v-bind="currentProps"/>
                  </keep-alive>
                </v-card>
              </v-container>
            </v-col>
          </v-row>
        </v-card>
        <v-row>
          <v-col cols="3" style="padding: 0">
          </v-col>
          <v-col cols="9" style="padding: 0">
                    <pre class="bottomP">
        Quadcore.inc  All rights reserved.
      </pre>
          </v-col>
        </v-row>
      </v-container>
    </v-row>
  </div>
</template>

<script>
import HomeHeader from "./HomeHeader";
import MailList from "./MailList";
import Compose from "./Compose";
import MailView from "./MailView";

export default {
  name: "Home",
  components: {HomeHeader, MailList},

  data() {
    return {
      currentComponent: MailList,
      items: [
        {title: 'Compose', icon: 'mdi-pencil', key: 1},
        {title: 'Inbox', icon: 'mdi-inbox', key: 2},
        {title: 'Sent', icon: 'mdi-telegram', key: 3},
        {title: 'Draft', icon: 'mdi-note', key: 4},
        {title: 'Trash', icon: 'mdi-delete', key: 5},
        {title: 'Archive', icon: 'mdi-archive', key: 6},
        {title: 'Mail View Test', icon: 'mdi-folder', key: 7},
      ],
      mail: null,
      user: this.$store.getters.getUser,
    }
  },

  computed: {
    currentProps: {
      get: function () {
        if (this.currentComponent === MailView) return {mail: this.$store.getters.getMail}
        else return null;
      },

      set: function () {
        //
      }
    }
  },

  methods: {
    navigate(key) {
      switch (key) {
        case 1:
          this.currentProps = null;
          this.currentComponent = Compose;
          break
        case 2:
          this.currentProps = null;
          this.currentComponent = MailList;
          break
        case 3:
          this.currentProps = null;
          this.currentComponent = MailList;
          break
        case 4:
          this.currentProps = null;
          this.currentComponent = MailList;
          break
        case 5:
          this.currentProps = null;
          this.currentComponent = MailList;
          break
        case 6:
          this.currentProps = null;
          this.currentComponent = MailList;
          break
        case 7:
          this.currentProps = {mail: 'cool mail'};
          this.currentComponent = MailView;
          break
      }
    }
  },

  created() {
    if (this.user === null) {
      this.$root.$emit("logOut");
      this.$destroy();
    }
  },
}
</script>

<style scoped>

.myContainer {
  padding-right: 4%;
  padding-left: 4%;
}

.navBar {
  text-align: left;
}

.mainContainer {
  padding-right: 4%;
  padding-left: 4%;
}

.compCont {
  padding-top: 0;
  padding-bottom: 0;
  padding-left: 0;
}

.bottomP {
  font-size: small;
  color: gray;
  padding: 2px 0 0;
  margin: 0;
}
</style>
