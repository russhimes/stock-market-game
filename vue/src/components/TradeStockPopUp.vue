<template>
  <div>
      <h2>{{stockTicker}}</h2>
      <p>Currrent Price: ${{currentPrice}}</p>

      <h2>Your Position</h2>
      <p>Shares: {{stock}}</p>
      <p>Market Value: </p>
      <!-- 
        options to
        buy - shares or dollars
        sell
       -->
  </div>
</template>

<script>
import StockService from '../services/StockService'
export default {
    props: ['stock'],
    data(){
        return {
            stockTicker: this.$route.params.ticker,
            currentPrice: 0,
        }
    },
    created(){
        StockService.getStockInfo(this.stockTicker)
            .then(
                (response) => {
                    this.currentPrice = response.data.currentPrice;
                }
            ).catch(
                (error) => console.log(error)
            )
        
    }
}
</script>

<style>

</style>