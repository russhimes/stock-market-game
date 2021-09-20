import axios from 'axios';

export default {
    getGames(userId) {
        return axios.get('/games', userId);
    }
}