<template>
<div>
  <h2>Make a Trade!</h2>
</div>
 
</template>

<script>
import tradeTopStocks from '../services/TradeStockService.js'
export default {
    data(){
        return topStocks: []
    }
},

created(){
    tradeTopStocks.getTopStocks().then(
        (response) => {
            this.tradeTopStocks = response.data;
        }
    ).catch(
        (error) => console.log(error)
    );

}
</script>

<style>

</style>