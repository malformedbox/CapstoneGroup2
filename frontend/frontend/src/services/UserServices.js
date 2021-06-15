import axios from 'axios';
import { USER_ACCOUNTS } from '../constants/constants';
//Note, on controller do @CrossOrigin(origins = "http://localhost:3000") port being used by react end

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
    getUserAccounts(){
        return axios.get(USER_ACCOUNTS);
    }
}

export default new UserService()