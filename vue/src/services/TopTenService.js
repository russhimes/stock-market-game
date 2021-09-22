import axios from 'axios';

export default {
//this should be replaced with calls to the TradeStockService once complete
        getBySymbol(symbol) {
            return axios.get(`stockinfo/${symbol}`);
        },
        getTopTen() {
            let topTen = []
            this.getBySymbol('FB').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    console.log(response.data);
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'FB',
                        name: 'FACEBOOK INC-CLASS A'
                    }
                    topTen.push(stock);
                }
            });
            this.getBySymbol('TSLA').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'TSLA',
                        name: 'TESLA INC'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('AAPL').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'AAPL',
                        name: 'APPLE INC'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('AMZN').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'AMZN',
                        name: 'AMAZON.COM INC'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('MSFT').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'MSFT',
                        name: 'MICROSOFT CORP'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('NVDA').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'NVDA',
                        name: 'NVIDIA CORP'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('FDX').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'FDX',
                        name: 'FEDEX CORP'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('MRNA').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'MRNA',
                        name: 'MODERNA INC'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('ADBE').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'ADBE',
                        name: 'ADOBE INC'
                    }
                    topTen.push(stock);
                }   
            });
            this.getBySymbol('AMD').then(response => {
                if (response.status >= 200 && response.status < 300) {
                    let stock = {
                        price: response.data.currentPrice,
                        ticker: 'AMD',
                        name: 'ADVANCED MICRO DEVICES'
                    }
                    topTen.push(stock);
                }   
            });
            return topTen;
        }
    }

