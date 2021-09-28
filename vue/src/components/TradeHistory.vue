<template>
<div class="historyContainer">
  <h2>Trade History</h2>
 <table>
     <tr>
         <th>Date</th>
          <th>Stock</th>
          <th>Shares</th>
          <th>Price</th>
          <th>Position</th>
     </tr>
 </table>
  <div class= "history" v-for="trade in tradeHistory" v-bind:key="trade.id">
  <table>
      <tr class = "row">
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

.historyContainer {
display: flex;
        flex-direction: column;
        padding: 3rem;
        margin: var(--padding);
        background-color: var(--color-lighter);
        border-radius: var(--border-radius);
        color: var(--background-color);
        width: 60vw;
        justify-content: center;
        
}
.history{
    display: flex;
    max-height: 50vh;
    overflow-y: scroll;
    width: 100%;
}
.row{
    font-size: 12px;
    
}

div {
    color: white;
}
td {
    font-size: 12px;
    text-align: center;
}


   

</style>