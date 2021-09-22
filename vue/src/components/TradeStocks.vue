<template>
<div>
    <!-- load data pulled into table  -->
    <!-- need to make it dyanamic  -->
  <h3>Make a Trade!</h3>
  <table>
  <tr>
      <th>Ticker</th>
      <th>Stock</th>
      <th>Value</th>
  </tr>
  <tr>
    <td> {{topStocks.ticker}} </td>
    <td> {{topStocks.name }} </td>
    <td> {{topStocks.value }} </td>
  </tr>
  </table>

</div>
 
</template>

<script>
import tradeTopStocks from '../services/TradeStockService.js'
export default {
    data(){
        return {
            topStocks: []
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
};
</script>

<style>

</style>