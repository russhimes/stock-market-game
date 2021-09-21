import axios from 'axios';

// need path names  
export default {
    getAllUsers() {
        return axios.get('/users');
    }
}
    
