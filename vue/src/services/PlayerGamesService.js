import axios from 'axios';

export default {
    getGames(userId) {
        return('/games', userId);
    }
}