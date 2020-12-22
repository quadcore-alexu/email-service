<template>
  <div class="fill-height">
    <v-form v-model="validForm" class="fill-height">
      <v-flex>
          <v-card-text>
            <v-row>
              <v-text-field
                  v-model="subject"
                  label="Subject"
              ></v-text-field>
            </v-row>
             <v-row>
                <div class="receivers-container">
                  <div class="receiver" v-for="(receiver, index) in receivers" :key="'receiver'+index">
                    <span v-if="selectedReceiver !== index" @click="selectedReceiver = index">{{ receiver }}</span>
                    <input v-else 
                          v-model="receivers[index]" 
                          v-focus 
                          :style="{'width': receiver.length + 'ch'}" 
                          @keyup.space="selectedReceiver = null" 
                          @blur="selectedReceiver = null" />
                    <span @click="removeReceiver(index)"><i class="fas fa-times-circle"></i></span>
                  </div>
                  <div style="clear:both"/>
                  <v-text-field
                    v-model="receiverValue"
                    label="To:"
                    @keyup.space="addReceiver"
                  ></v-text-field> 
                </div>
              </v-row>
              <v-row>
                <v-col
                  class="d-flex"
                  cols="12"
                  sm="6">
                  <v-slider
                      v-model="priority"
                      label="Priority"
                      thumb-color="red"
                      :tick-labels="priorityLevels"
                      max="5"
                      min="1"
                      tick-size="5"
                    ></v-slider>
                  </v-col>
              </v-row>
            <v-row>
              <v-textarea
                  v-model="content"
                  counter
                  label="Body"
              />
            </v-row>
            <v-row>
              <v-file-input
                  :key="temp"
                  ref="attachments"
                  v-model="files"
                  chips
                  counter
                  label="Attachments"
                  multiple
                  @change="filesChanged"
                  @click:clear="clearFiles"
              />
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-container style="text-align: right">
              <v-btn @click="send">
                <v-icon left>
                  mdi-send
                </v-icon>
                Send
              </v-btn>
            </v-container>
          </v-card-actions>
        </v-flex>
    </v-form>
  </div>
</template>

<script>
import Email from "../models/Email";
import axios from "axios";

export default {
  name: "Compose",
  data() {
    return {
      validForm: false,
      content:'',
      subject: '',
      priorityLevels : [
        'Minor',
        'Regular',
        'Important',
        'Essential',
        'Crucial'
        ],
      requiredRules: value => !!value || 'Required',
      files: [],
      allFiles: [],
      temp: 1, //workaround to force file-input to update
      receiverValue: '',
      receivers: [],
      selectedReceiver: null,
      priority:3
    }
  },
  methods: {
    filesChanged() {
      this.files.forEach(this.addToAllFiles);
      this.files = this.allFiles;
      this.temp += 1; //forcing file-input to update
    },
    addToAllFiles(item) {
      if (!this.allFiles.some(function (oldFile) {
        return oldFile.name === item.name;
      })) {
        this.allFiles.push(item);
      }
    },
    clearFiles() {
      this.allFiles = [];
    },
    addReceiver() {
      if(!this.receiverValue == '')
        this.receivers.push(this.receiverValue);
      
      this.receiverValue = '';
    },
    removeReceiver(index) {
      this.receivers.splice(index, 1);
    },
    send() {
      console.log("Hello");
      if (this.receivers.length == 0) {
          alert("Please type receiver address followed by space")
          return
      }
      let formData = new FormData();
      let email = new Email();
      email.title = this.subject;
      email.content = this.content;
      email.priority = this.priority;
      let receiverStr = ''; 
      this.receivers.forEach(element => receiverStr += element);
      formData.append("email", JSON.stringify(email));
      formData.append("receivers", receiverStr);
      this.files.forEach(element => formData.append("attachments", element));
      axios.put("http://localhost:8080/api/sendMail", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
      .then(() => {
        console.log("Succeeded");  
      })
      .catch(error  => {
        console.log(error);
      });
    }
  },
  directives: {
      focus: {
        inserted: (el) => {
          el.focus()
        }
      }
    }
}
</script>

<style lang="scss" scoped>
   .receivers-container {
    width: 100%;
    max-width: 600px;
    padding: 10px;
    
    input {
      width: 100%;
      padding: 0;
      margin: 0;
      border: 0;
      outline: none;
      background-color: transparent;
      font-size: 1rem;
    }
    .receiver {
      float: left;
      padding: 3px 5px;
      display: flex;
      justify-content: center;
      cursor: pointer;
      &:hover {
        background-color: #ff5057;
        border-radius: 5px;
      } 
      span:first-child {
        margin-right: 8px;
      }  
      svg {
        color: #666;
        &:hover {
          color: #333;
        }
      }     
    }
  }
</style>
