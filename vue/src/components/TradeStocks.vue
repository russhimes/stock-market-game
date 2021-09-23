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
      <th></th>
      <th>Ticker</th>
      <th>Stock</th>
      <th>Value</th>
  </tr>
  <tr v-for="stock in topStocks" v-bind:key="stock.id">
      <td><img :src="stock.logoURL"></td>
    <td> {{stock.stockSymbol}} </td>
    <td> {{stock.companyName}} </td>
    <td> {{stock.currentPrice}} </td>
    <!--<td><router-link tag="button" v-bind:to="{name:'stock-info', params: {ticker: stock-info.id}}">View Details</router-link></td>
  --> 
    <td>
        <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stockSymbol}}"> Trade Stock </router-link>
    </td>
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
            this.topStocks = response.data;
            //console.log(this.topStocks);
        }
    ).catch(
        (error) => console.log(error)
    );

}
};
</script>

<style>

</style>