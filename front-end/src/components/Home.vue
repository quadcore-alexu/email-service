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
                        {{ name }}
                      </v-list-item-title>
                      <v-list-item-subtitle>
                        {{ email }}
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
                </v-navigation-drawer>
              </v-card>
            </v-col>
            <v-col cols="9">
              <v-container class="compCont">
                <v-card height="560">
                  <keep-alive>
                    <component v-bind:is="currentComponent"/>
                  </keep-alive>
                </v-card>
              </v-container>
            </v-col>
          </v-row>
        </v-card>
        <pre class="bottomP">
        Quadcore.inc  All rights reserved.
      </pre>
      </v-container>
    </v-row>
  </div>
</template>

<script>
import HomeHeader from "./HomeHeader";
import MailList from "./MailList";
import Compose from "./Compose";

export default {
  name: "Home",
  components: {HomeHeader, MailList},

  data() {
    return {
      currentComponent: MailList,
      items: [
        {title: 'Compose', icon: 'mdi-view-dashboard', key: 1},
        {title: 'Inbox', icon: 'mdi-image', key: 2},
        {title: 'Archive', icon: 'mdi-help-box', key: 3},
        {title: 'Draft', icon: 'mdi-help-box', key: 4},
      ],

      email: 'a.waleedothman@quadcore.com',
      name: 'Ahmad Waleed',
    }
  },

  methods: {
    navigate(key) {
      switch (key) {
        case 1:
          this.currentComponent = Compose;
          break
        case 2:
          this.currentComponent = MailList;
          break
        case 3:
          this.currentComponent = MailList;
          break
        case 4:
          this.currentComponent = MailList;
          break
      }
    }
  }
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
}

.bottomP {
  font-size: small;
  color: gray;
  padding: 2px 0 0;
  margin: 0;
}
</style>
