import axios from 'axios';

export default{
// need path name
    getTopStocks(){
        return axios.get();
    },
    getStockInfo(ticker){
        return axios.get('stockinfo' + ticker);
    },
    getPlayerStocks(playerId) {
        return axios.get(`/player/${playerId}/stocks`);
    }


}