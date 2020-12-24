import axios from "axios";


const API_URL = "http://localhost:8080/api/login";
class loginService {
    login(email, password) {
        const token = Buffer.from(`${email}:${password}`, 'utf8').toString('base64')
        axios.post(API_URL, {
            headers: {
                'Authorization': `Basic ${token}`

            },
        }).then((response) => {
            console.log(response);
        })

    }
    async  fetchLogin(email,password)
    {
        return fetch('http://localhost:8080/api/login', {
            method: 'post',
            headers: new Headers({
                'Authorization': 'Basic ' + btoa(email + ':' + password),
                'Content-Type': 'application/x-www-form-urlencoded'
            })
        }) .then((response)=>response.json())
            .then((result)=>{return result});
    }




}
export default new loginService();