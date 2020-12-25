import axios from "axios";
const API_URL = "http://localhost:9000/api/";
class EmailService {

  /**
   * @param emailID to be fetched
   * @returns success flag
   */
  getMail(emailID) {
    return axios.get(API_URL + "getMail", {
          params:{
            id: emailID
          }
        });
  }

  /**
   * @param formdata to be sent
   * @returns success flag
   */
  sendMail(formData) {
    return axios.put(API_URL + "sendMail", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      });
  }

    drafts(formData) {
        return axios.put(API_URL + "drafts", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        });
    }

    loadContacts(){
        return axios.get(API_URL + "loadContacts");
    }

    dumpRetrieve() {
    return axios.get(API_URL + "dumpRetrieve");
  }
}
export default new EmailService();
