<template>
  <div>
      <h2>{{stockTicker}}</h2>
      <h3>{{ companyName }}</h3>
      <img :src="imageUrl">
      <p>Currrent Price: ${{currentPrice}}</p>
      <p>Percent Change: {{percentChange}}%</p>

      <h3>Your Position</h3>
      <p>Shares: {{ stock.total_shares }}</p>
      <p>Market Value: ${{marketValue}} </p>

      <button v-on:click="toggleTrade = !toggleTrade">Trade</button>
        <div v-if="toggleTrade">
            <label for="amount">Amount:</label>
            <input type="number" name="amount" id="amount" v-model="amount">
            
            <select name="entryType" id="entryType" v-model="entryType">
                <option value="Shares">Shares</option>
                <option value="Dollars">Dollars</option>
            </select>

            <div v-if="entryType === 'Shares'">
                <p>Estimated Value: ${{ estimatedCost }}</p>
            </div>
            <div v-else>
                <p>Number of Stocks: {{ estimatedNumberOfStocks }}</p>
            </div>

            <button v-on:click="buyStocks()" v-bind:class="{ 'invalidTransaction' : !validBuyTransaction }">Buy</button>
            <button v-bind:class="{ 'invalidTransaction' : !validSellTransaction }" v-if="stock.total_shares> 0">Sell</button>
        </div>
  </div>
</template>

<script>
import stockService from '../services/StockService'
import tradeService from '../services/TradeService'
export default {
    data(){
        return {
            stockTicker: this.$route.params.ticker,
            currentPrice: 0,
            imageUrl: "",
            companyName: "",
            percentChange: 0,
            stock: {},
            toggleTrade: false,
            entryType: "Shares",
            amount: "",
        }
    },
    created(){
        stockService.getStockByPlayerAndTicker(this.$store.state.currentPlayerId, this.stockTicker)
            .then(result => {
                this.stock = result.data;
            });

        stockService.getStockInfo(this.stockTicker)
            .then(
                (response) => {
                    this.currentPrice = response.data.currentPrice;
                    this.imageUrl = response.data.logoURL;
                    this.percentChange = response.data.percentageChange;
                    this.companyName = response.data.companyName;
                }
            ).catch(
                (error) => console.log(error)
            );
    },
    computed: {
        marketValue() {
            return this.stock.total_shares * this.currentPrice;
        },
        estimatedCost() {
            return this.amount * this.currentPrice;
        },
        estimatedNumberOfStocks() {
            return this.amount / this.currentPrice;
        },
        validSellTransaction() {
            if(this.entryType=== "Shares" && this.stock.total_shares >= this.amount){
                      return true;
            }  if(this.entryType=== "Dollars" && this.marketValue >= this.amount){
                      return true;
            }
            return false; 
          
        },
        validBuyTransaction() {
            let player = this.getCurrentPlayer();
            if(this.entryType=== "Shares" && player.availableFunds >= (this.amount * this.currentPrice)){
                      return true;
            }  if(this.entryType=== "Dollars" && player.availableFunds >= this.amount){
                      return true;
            }
            return false; 
        }
    },
    methods: {
        getCurrentPlayer(){
           return this.$store.state.players.find(player => {
               return player.id === this.$store.state.currentPlayerId
            })
        },
        buyStocks() {
            if(this.validBuyTransaction) {
                // TODO - update logic so it handles stock not existing (AKA adds stock object instead of updating it)
                if(this.stock === {}) {
                    console.log("empty object");
                } else {
                    this.updateStock("Buy");
                    this.updatePlayer("Buy");
                    this.createTradeObject("Buy");
                    this.amount = "";
                }
            }
        },
        updateStock(buyOrSell) {
            let stock = this.stock;
            let currentShares = parseInt(stock.total_shares);
            let amount = parseInt(this.amount);

            let shareChange = this.entryType === "Shares" ? amount : amount / this.currentPrice;
            
            let newShares = buyOrSell == "Buy" ? currentShares + shareChange : currentShares - shareChange;
            stock.total_shares = newShares;

            // update stock in database
        },
        createTradeObject(buyOrSell) {
            let shares = this.entryType === "Shares" ? this.amount : this.amount / this.currentPrice;
            let price = this.entryType === "Dollars" ? this.amount : this.amount * this.currentPrice;
            let dateTime = new Date()
            let date = dateTime.toISOString().substring(0, 10);
            let time = dateTime.toString().substring(16, 24);

            // update trade in database
            let trade = {
                stock_id: this.stock.id,
                shares_traded: shares,
                buy_or_sell: buyOrSell,
                price: price,
                entered_in: this.entryType,
                date: date,
                time: time
            }
            tradeService.createTrade(trade);
        },
        updatePlayer(buyOrSell) {
            let player = this.getCurrentPlayer();
            let initialBalance = player.availableFunds;
            let amount = parseFloat(this.amount)
            let balanceChange = this.entryType === "Dollars" ? amount : amount * this.currentPrice;

            let newBalance = buyOrSell === "Buy" ? initialBalance - balanceChange : initialBalance + balanceChange;

            player.availableFunds = newBalance;
        }

    }

}
</script>

<style>
    .invalidTransaction {
        background-color: red;
    }
</style>