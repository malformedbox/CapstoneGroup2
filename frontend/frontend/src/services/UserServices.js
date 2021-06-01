import axios from 'axios';

const USERS_REST_API_URL = 'http://localhost:8080/api/users'; //from spring host
//Note, on controller do @CrossOrigin(origins = "http://localhost:3000") port being used by react end

class UserService  {

    getUsers(){
        return axios.get(USERS_REST_API_URL);
    }
}

export default new UserService()