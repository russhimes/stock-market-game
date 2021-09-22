<template>
  <div id = "main ">
      <h2 class = "boardTitle">Welcome to the Game Board!</h2>
      <portfolio-balance></portfolio-balance>
      <portfolio-holdings></portfolio-holdings>
      <trade-stocks></trade-stocks>
      <leader-board></leader-board>
  </div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioBalance from '../views/Balance'
import PortfolioHoldings from './PortfolioHoldings.vue'
import TradeStocks from './TradeStocks.vue'


export default {
  components: { PortfolioBalance, LeaderBoard, PortfolioHoldings, TradeStocks },

}
</script>

<style>
  .boardTitle {
    color: palevioletred;
  }
  
</style>