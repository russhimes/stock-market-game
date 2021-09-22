<template>
  
</template>

<script>
import TradeStockService from '../services/TradeStockService'
export default {
    data(){
        return {
            stockInfo: []
        }
    },
    created(){
        TradeStockService.getStockInfo().then(
            (response) => {
                this.TradeStockService = response.data;
            }
        ).catch(
        (error) => console.log(error)
    );
    }
}
</script>

<style>

</style>