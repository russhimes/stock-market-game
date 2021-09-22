<template>
  
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
    );
    }
}
</script>

<style>

</style>