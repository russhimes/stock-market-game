<template>
<div class="history">
  <h1>View Trade History</h1>
  <div v-for="trade in tradeHistory" :key="trade.id"></div>
  <div class="row">
      <p> {{trade.stock_name }} </p>
      <p> {{trade.shares_traded }} </p>
      <p> {{trade.buy_or_sell}} </p>
      <p> {{trade.price }} </p>
      <p> {{trade.entered_in }} </p>
      <p> {{trade.date}} </p>
      <p> {{trade.time }} </p>

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
        TradeService.getTradeHistory(this.player.id)
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
</style>