<template>
  <div>
      <h2>{{stockTicker}}</h2>
      <h3>{{stock.stock_name}}</h3>
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
                <p>Estimated Cost: ${{ estimatedCost }}</p>
            </div>
            <div v-else>
                <p>Number of Stocks: {{ estimatedNumberOfStocks }}</p>
            </div>

            <button v-bind:class="{ 'invalidTransaction' : !validBuyTransaction }">Buy</button>
            <button v-if="stock.total_shares > 0">Sell</button>
        </div>

      <!-- 
        options to
        buy - shares or dollars
        sell
       -->
  </div>
</template>

<script>
import stockService from '../services/StockService'
export default {
    data(){
        return {
            stockTicker: this.$route.params.ticker,
            currentPrice: 0,
            imageUrl: "",
            percentChange: 0,
            stock: {},
            toggleTrade: false,
            entryType: "Shares",
            amount: 0,
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
                    console.log(response.data)
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
            return true;
        },
        validBuyTransaction() {
            return true;
        }
    }
}
</script>

<style>
    .invalidTransaction {
        background-color: red;
    }
</style>