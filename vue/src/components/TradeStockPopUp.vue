<template>
  <div>
      <h2>{{stockTicker}}</h2>
      <p>Currrent Price: ${{currentPrice}}</p>

      <h2>Your Position</h2>
      <p>Shares: {{ stock.total_shares }}</p>
      <p>Market Value: ${{marketValue}} </p>
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
            stock: {}
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
                    
                }
            ).catch(
                (error) => console.log(error)
            );
    },
    computed: {
        marketValue() {
            return this.stock.total_shares * this.currentPrice;
        }
    }
}
</script>

<style>

</style>