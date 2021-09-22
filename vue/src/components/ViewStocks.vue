<template>
  <h2>View our Stock List</h2>
<!--  Displaying stock ticker, name, price (maybe day change?)
  <ul>
    <li v-for="stock in stockList"> 
        {{stock.ticker}}
        {{stock.name}}
        {{stock.price}}
    </li>
  </ul> -->
</template>

<script>
import stocksService from '../services/PortfolioHoldingsService.js';
export default {
  data(){
    return {
      stockList: []
    }
  },
  created(){
    // when component loads taking data returned from getAllStocks() - need endpoints 
    // saving it into stockList
    stocksService.getAllStocks().then(
      (response) => {
      this.stockList = response.data;
      }
    ).catch(
        (error) => console.log(error)
    );
}
};
</script>

<style>

</style>