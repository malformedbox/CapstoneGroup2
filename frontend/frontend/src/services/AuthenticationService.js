import axios from "axios";

class AuthenticationService {

    login = (username, password) => {
        return axios.post("/login", {username, password})
            .then(response => {
                if(response.data.accessToken){
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
        })
    }
    
    signOut(){
        localStorage.removeItem("user");
    }

    register = async(username, password, firstName, middleName, lastName, ssn) => {
        return axios.post("/createuser", {
            username,
            password,
            firstName,
            middleName,
            lastName,
            ssn
        });
    }

    getCurrentUser(){
        console.log(JSON.parse(localStorage.getItem('user')).accessToken);
        return JSON.parse(localStorage.getItem('user'));
    }
}

export default new AuthenticationService();