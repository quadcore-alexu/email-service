


//const API_URL = "http://localhost:8080/api/login";
class loginService {

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