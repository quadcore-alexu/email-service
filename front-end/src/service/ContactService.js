import axios from "axios";
const API_URL = "http://localhost:9000/api/";

class ContactService{

    loadContacts(){
        return axios.get(API_URL + "loadContacts");
    }

    addContact(contactMap){
        return axios.post(API_URL + "addContact", contactMap
        );
    }
    editContact(contactMap){
        return axios.put(API_URL + "editContact", contactMap
        );
    }


}
export default new ContactService();
