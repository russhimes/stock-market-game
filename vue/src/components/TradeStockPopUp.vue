<template>
  <div></div>
</template>

<script>
import StockService from '../services/StockService'
export default {
    data(){
        return {
            stockInfo: []
        }
    },
    created(){
        StockService.getStockInfo().then(
            (response) => {
                this.StockService = response.data;
            }
        ).catch(
        (error) => console.log(error)
    ),
    this.stockInfoTicker = this.$route.params.ticker;
    }
}
</script>

<style>

</style>