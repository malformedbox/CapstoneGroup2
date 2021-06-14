import axios from 'axios';

const USERS_REST_API_URL = 'http://localhost:8080/AccountHolders'; //from spring host
//Note, on controller do @CrossOrigin(origins = "http://localhost:3000") port being used by react end

const USERS_PERSONAL_CHECKING = 'http://localhost:8080/user/personalchecking';
const USER_ACCOUNTS = 'http://localhost:8080/user';

//applies to axios calls
axios.interceptors.request.use( config => {
    const user = JSON.parse(localStorage.getItem('user'));
    
    if(user && user.accessToken){
        const token = 'Bearer ' + user.accessToken;
        config.headers.Authorization = token;
    }
    return config;
});

class UserService  {

    getUsers(){
        return axios.get(USERS_REST_API_URL);
    }

    getUserAccounts(){
        return axios.get(USER_ACCOUNTS);
    }

}

export default new UserService()