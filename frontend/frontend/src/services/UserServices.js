import axios from 'axios';

const USERS_REST_API_URL = 'http://localhost:8080/AccountHolders'; //from spring host
//Note, on controller do @CrossOrigin(origins = "http://localhost:3000") port being used by react end

const USERS_PERSONAL_CHECKING = 'http://localhost:8080/user/personalchecking';

class UserService  {

    getUsers(){
        return axios.get(USERS_REST_API_URL);
    }

    getPersonalChecking(){
        return axios.get(USERS_PERSONAL_CHECKING);
    }
}

export default new UserService()