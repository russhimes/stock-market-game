<template>
<div class="historyContainer">
  
  <div class= "history" v-for="trade in tradeHistory" v-bind:key="trade.id">
      <h3>{{trade.stock_name }}</h3>
      <h5>{{trade.date}}</h5>
      <h5>{{trade.time }}</h5>
      <h4>Position: {{trade.buy_or_sell}}</h4>
      <h4>Shares: {{trade.shares_traded }} </h4>
      <h4>Price: ${{trade.price }} </h4>
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

.historyContainer{
     background-color: var(--background-color);
        color: var(--color-primary);
        border-radius: var(--border-radius);
        margin-bottom: 0.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between; 
        padding: 0.3rem  1rem;
        align-items: center;
        transition: 0.4s; 
        width: 150px;
}

  h3 {
        padding: 0.4rem 0;
        text-align: center;
        font-size: 15px;
    }

    h4 {
        font-size: 12px;
        text-align: center;
    }

    h5 {
        text-align: center;
    }

    
   .historyContainer:hover {
        background-color: white;
    }
   
   

</style>