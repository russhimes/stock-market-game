import axios from 'axios';

export default{
// need path name
    getTopStocks(){
        return axios.get(`/stockinfo/topten`);
    },
    getStockInfo(ticker){
        return axios.get(`/stockinfo/${ticker}`);
    },
    getSearchInfo(searchTerm){
        return axios(`stockinfo/search/${searchTerm}`, {
            method: 'GET',
            mode: 'no-cors',
            headers: {
              'Access-Control-Allow-Origin': '*',
              'Content-Type': 'application/json',
            },
           credentials: 'same-origin',
          });
        //return axios.get(`/stockifo/search/${searchTerm}`);
    },
    getPlayerStocks(playerId) {
        return axios.get(`/player/${playerId}/stocks`);
    },
    getStockByPlayerAndTicker(playerId, ticker) {
        return axios.get(`/player/${playerId}/stocks/${ticker}`);
    }

}