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

/* .historyContainer {
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
} */

.historyContainer{
     background-color: var(--background-color);
        color: var(--color-primary);
        border-radius: var(--border-radius);
        margin-bottom: 0.5rem;
        display: flex;
        justify-content: space-between;
        padding: 0.3rem  1rem;
        align-items: center;
        transition: 0.4s;
}

 h3 {
        padding: 0.4rem 0;
        text-align: center;
    }

    .historyContainer:hover {
        background-color: white;
    }
   

</style>