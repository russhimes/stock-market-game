<template>
<div>
    <!-- load data pulled into table  -->
    <!-- need to make it dyanamic - need to build out logic -->
    <!-- create component stock information with option buy and sell 
    pop up w logic on how much you can buy/ sell 
    form submission at the bottom to search and show results here  -->
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
    <td><button>View Details</button></td>
   
  </tr>
  </table>

</div>
 
</template>

<script>
import tradeTopStocks from '../services/StockService.js'
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