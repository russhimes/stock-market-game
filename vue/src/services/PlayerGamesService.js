import axios from 'axios';

export default {
    getGames(userId) {
        return axios.get('/games', userId);
    },
    createGame(game) {
        return axios.post('/games', game);
    }
}