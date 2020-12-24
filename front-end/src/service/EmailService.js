import axios from "axios";
const API_URL = "http://localhost:8080/api/";
class EmailService {
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

  dumpRetrieve() {
    return axios.get(API_URL + "dumpRetrieve");
  }
}
export default new EmailService();
