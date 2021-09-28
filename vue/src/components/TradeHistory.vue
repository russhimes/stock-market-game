<template>
<div class="history">
  <h2>Trade History</h2>
  <div v-for="trade in tradeHistory" v-bind:key="trade.id">
  <table>
      <tr>
          <th>Date</th>
          <th>Stock</th>
          <th>Shares</th>
          <th>Price</th>
          <th>Position</th>
      </tr>
      <tr>
          <td>{{trade.date}} <br>{{trade.time }} </td>
        
          <td>{{trade.stock_name }}</td>
          <td>{{trade.shares_traded }}</td>
          <td>{{trade.price }}</td>
          <td>{{trade.buy_or_sell}}</td>
      </tr>
  </table>
   </div>  
  </div>
</template>

<script>
import TradeService from "../services/TradeService"
export default {
    data() {
        return {
            tradeHistory: [],
        }
    },

    created(){
        TradeService.getTradeHistory(this.$store.state.currentPlayerId)
        .then((response) => {
            this.tradeHistory = response.data;
        })
    }
}
</script>

<style scoped>
div {
    color: black;
}
td {
    font-size: 10px;
    text-align: center;
   
}
</style>