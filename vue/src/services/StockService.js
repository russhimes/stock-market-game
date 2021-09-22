import axios from 'axios';

export default{
// need path name
    getTopStocks(){
        return axios.get();
    },
    getStockInfo(ticker){
        return axios.get(`/stockinfo/${ticker}`);
<<<<<<< HEAD:vue/src/services/TradeStockService.js
=======
    },
    getPlayerStocks(playerId) {
        return axios.get(`/player/${playerId}/stocks`);
>>>>>>> c01fe76e90f00401520ffcfc0915017cccf83caa:vue/src/services/StockService.js
    }


}