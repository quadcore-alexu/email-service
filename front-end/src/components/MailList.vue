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
        <v-btn icon @click="refresh">
                <v-icon>mdi-reload</v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <v-text-field
          v-model="searchKey"
          hide-details
          label="Search"
          single-line
        ></v-text-field>
        <v-btn icon @click="applySearch">
                <v-icon>mdi-magnify</v-icon>
        </v-btn>
        <v-btn icon v-if="searchKey != ''" @click="clearSearch">
                <v-icon>mdi-close-box-outline</v-icon>
        </v-btn>
        <v-btn icon @click="filterDialog=true" >
              <v-icon>mdi-filter-menu-outline</v-icon>
        </v-btn>
        <v-dialog
          v-model="filterDialog"
          max-width="500px">
          <v-card>
            <v-card-title>
              Filtering
            </v-card-title>
            <v-card-text>
              <v-select
                v-model="selectedFilter"
                :items="filters"
                label="Filter"
                item-value="text"
                color="accent"
              ></v-select>
              <v-date-picker v-model="fileringDate" v-if="selectedFilter=='Date'"></v-date-picker>
              <v-text-field
                v-model="searchKey"
                v-if="selectedFilter!='Date'"
                color="accent"
              />
            </v-card-text>
            <v-card-actions>
              <v-btn
                color="accent"
                text
                @click="filterDialog = false"
              >
                Close
              </v-btn>
              <v-btn
                color="accent"
                text
                @click="applySearch"
              >
                Filter
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
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
            <span>Sort by sender name</span>
          </v-tooltip>
          <v-tooltip bottom>
            <template v-slot:activator="{ on }" >
              <v-btn v-on="on" @click="setSortingCriteria('subject')">
                Title
              </v-btn>
            </template>
            <span>Sort by mail subject</span>
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
          <v-btn v-if="page > 1" color="accent" @click="paging(-1)">
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
          <v-btn @click="deleteMails" block color="accent">
            <v-icon left>
              mdi-delete-circle
            </v-icon>
            Delete
          </v-btn>
        </v-col>
        <v-col>
          <v-btn v-if="emailHeaders != null && emailHeaders.length >= 6" color="accent" @click="paging(1)">
            Next
            <v-icon right>
              mdi-page-next
            </v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import EmailService from "../service/EmailService";
import FolderSelection from './FolderSelection';
import MyMask from "./MyMask";

export default {
    name: "MailList",
    components: {MyMask},
    data: () => ({
      emailHeaders: null,
      selected: [],
      clearSelection: false,
      sortingCriteria: 'date',
      reverseSorting: false,
      page: 1,
      showMask: false,
      bulkOperation:'',
      maskComponent: {component: FolderSelection},
      searchKey: '',
      filterDialog: false,
      filters: ["Date", "Title", "Sender"],
      selectedFilter: 'search',
      fileringDate: null
    }),
    mounted() {
      this.selected = [];
      this.refresh();

      this.$root.$on("FolderSelected", (folderIndex) => {
        console.log("Bulk operation "+ this.bulkOperation + " from folder " + this.$store.getters.getFolder + " to folder "+folderIndex);
        let headersID = []
        this.selected.forEach(num=> {
          headersID.push(this.emailHeaders[num].emailHeaderID);
        });
        switch(this.bulkOperation) {
          case 'move':
            console.log("move");
            EmailService.moveMails(headersID, this.$store.getters.getFolder-1, folderIndex)
            .then(() => {
                this.refresh();
            });
            break
          case 'copy':
            EmailService.copyMails(headersID, this.$store.getters.getFolder-1, folderIndex)
            .then(() => {
                this.refresh();
            });
            break
        }
        this.showMask = false;
      });
      this.$root.$on("CloseFoldersModal", () => {
        this.bulkOperation = '';
        this.selected = [];
        this.showMask = false;
      });
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
        //console.log("Mail ID", id);
        this.clearSelection = true;
        if (this.currentFolder != 3) {
          this.$root.$emit("openMail", id);
        } else {
          this.$root.$emit("openDraft", id);
        }
      },
      showSelected() {
        this.selected.forEach(num=>console.log(this.emailHeaders[num]));
      },
      setSortingCriteria(criteria) {
        if (this.sortingCriteria == criteria) return;
        this.sortingCriteria = criteria;
        console.log(this.sortingCriteria);
        console.log(this.page);
        console.log(this.$store.getters.getFolder-1);
        this.refresh();
      },
      toggleReverseSorting() {
        this.reverseSorting = !this.reverseSorting;
        console.log(this.reverseSorting);
        this.refresh();
      },
      openFolderSelection(operation) {
        if (this.selected.length == 0) return
        this.bulkOperation = operation;
        this.showMask = true;
      },
      paging(step) {
        this.page += step;
        this.refresh();
      },
      applySearch() {
        if(this.filterDialog && this.selectedFilter == "Date") {
          this.searchKey = this.fileringDate;
        }
        EmailService.getFilteredMailHeaders(this.$store.getters.getFolder-1, this.page, this.selectedFilter, this.searchKey)
        .then(response => {
          this.emailHeaders = response.data;
          console.log(response.data);
        });
        this.filterDialog = false;
      },
      clearSearch(){
        this.searchKey = '';
        this.filterDialog = false;
        this.selectedFilter = 'search';
        this.fileringDate = null;
        this.refresh();
      },
      refresh() {
        EmailService.getMailHeaders(this.$store.getters.getFolder-1, this.page,
                                    this.sortingCriteria, this.reverseSorting)
        .then(response => {
          this.emailHeaders = response.data;
          this.selected = [];
        });
      },
      deleteMails() {
        if (this.selected.length == 0) return
        let headersID = []
        this.selected.forEach(num=> {
          headersID.push(this.emailHeaders[num].emailHeaderID);
        });
        EmailService.deleteMails(headersID, this.$store.getters.getFolder-1)
        .then(() => {
            this.refresh();
        });
      }
    },
    computed: {
      currentFolder () {
          return this.$store.getters.getFolder;
        }
    },
    watch: {
      selected: function () {
        if (this.clearSelection == true) {
          this.selected = [];
          this.clearSelection = false;
        }
      },
      currentFolder: function() {
        if (this.currentFolder > 0) {
          console.log(this.currentFolder);
          this.refresh();
        }
      }
    }
  }
</script>
<style scoped>

</style>
