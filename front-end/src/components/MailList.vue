<template>
  <div>
    <MyMask v-if="showMask" v-bind="maskComponent"/>
    <v-card
      class="mx-auto"
    >
      <v-toolbar
        color='accent'
        dark
      >
        <v-toolbar-title>Inbox</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn-toggle color="dark" shaped dense group mandatory>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }" >
              <v-btn v-on="on" @click="setSortingCriteria('date')">
                Date
              </v-btn>
            </template>
            <span>Sort by date</span>
          </v-tooltip>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }" >
              <v-btn v-on="on" @click="setSortingCriteria('priority')">
                Priority
              </v-btn>
            </template>
            <span>Sort by priority</span>
          </v-tooltip>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }" >
              <v-btn v-on="on" @click="setSortingCriteria('sender')">
                Sender
              </v-btn>
            </template>
            <span>Sort by sender address</span>
          </v-tooltip>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }" >
              <v-btn v-on="on" @click="setSortingCriteria('title')">
                Title
              </v-btn>
            </template>
            <span>Sort by mail title</span>
          </v-tooltip>
        </v-btn-toggle>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }" >
              <v-btn icon v-on="on" @click="toggleReverseSorting()">
                <v-icon>mdi-menu-swap-outline</v-icon>
              </v-btn>
            </template>
            <span>Reverse sorting</span>
          </v-tooltip>
      </v-toolbar>
      <v-list two-line>
        <v-list-item-group
          v-model="selected"
          active-class="pink--text"
          multiple
        >
          <template v-for="(emailHeader) in emailHeaders">
            <v-list-item :key="emailHeader.emailHeaderID">
              <template>
                <v-list-item-action>
                  <v-list-item-action-text v-text="getPriority(emailHeader.priority)"></v-list-item-action-text>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-subtitle
                    class="text--primary"
                    v-text="emailHeader.title"
                  ></v-list-item-subtitle>

                  <v-list-item-subtitle v-text="emailHeader.senderAddress"></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-list-item-action-text v-text="emailHeader.date"></v-list-item-action-text>
                  <v-btn color="accent" depressed outlined
                         rounded @click="showMail(emailHeader.emailID)">Preview
                  </v-btn>
                </v-list-item-action>
              </template>
            </v-list-item>
            
          </template>
        </v-list-item-group>
      </v-list>
    </v-card>
    <div class="py-0 py-2 px-4" style="bottom: 0;">
      <v-row>
        <v-col>
          <v-btn v-if="page > 1" color="accent" @click="page-=1">
              <v-icon left>
                mdi-page-previous
              </v-icon>
              Previous
            </v-btn>
        </v-col>
        <v-col>
          <v-btn @click="openFolderSelection('move')" block color="accent">
            <v-icon left>
              mdi-folder-swap
            </v-icon>
            Move
          </v-btn>
        </v-col>
        <v-col>
          <v-btn @click="openFolderSelection('copy')" block color="accent">
            <v-icon left>
              mdi-content-copy
            </v-icon>
            Copy
          </v-btn>
        </v-col>
        <v-col>
          <v-btn @click="openFolderSelection('delete')" block color="accent">
            <v-icon left>
              mdi-delete-circle
            </v-icon>
            Delete
          </v-btn>
        </v-col>
        <v-col>
          <v-btn v-if="emailHeaders != null && emailHeaders.length >= 6" color="accent" @click="page+=1">
            Next
            <v-icon right>
              mdi-page-next
            </v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-card-actions style="  position: absolute; bottom: 0; left:0;">
          <v-container>
            
          </v-container>
        </v-card-actions>
        <v-card-actions >
        <v-container>
          
        </v-container>
      </v-card-actions>
      </v-row>
    </div>
  </div>
</template>

<script>
import EmailService from "../service/EmailService";
import Folders from "./Folders";
import MyMask from "./MyMask";

export default {
    name: "MailList",
    components: {MyMask},
    data: () => ({
      emailHeaders: null,
      selected: [],
      sortingCriteria: 'date',
      reverseSorting: true,
      page: 1,
      showMask: false,
      bulkOperation:'',
      maskComponent: {component: Folders}
    }),
    mounted() {
      EmailService.dumpRetrieve()
      .then(response => {
        this.emailHeaders = response.data;
        console.log(response.data);
      })
      .catch(error => {
        console.log(error);
      })
    },
    methods: {
      getPriority(level) {
        switch(level) {
          case 1:
            return "Minor";
          case 2:
            return "Regular";
          case 3:
            return "Important";
          case 4:
            return "Crucial";
        }
      },
      showMail(id) {
        console.log("Mail ID", id);
        this.$root.$emit("openMail", id);
      },
      showSelected() {
        this.selected.forEach(num=>console.log(this.emailHeaders[num]));
      },
      setSortingCriteria(criteria) {
        if (this.sortingCriteria == criteria) return;
        this.sortingCriteria = criteria;
        console.log(this.sortingCriteria);
        console.log(this.page);
        /**
         * Here call the backend to apply the new sorting criteria
         */
      },
      toggleReverseSorting() {
        this.reverseSorting = !this.reverseSorting;
        console.log(this.reverseSorting);
      },
      openFolderSelection(operation) {
        this.bulkOperation = operation;
        this.showMask = true;
      },
    }
  }
</script>
<style scoped>

</style>
