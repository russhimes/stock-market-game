<template>
  <div id = "main ">
      <h2 class = "boardTitle">Welcome to the Game Board!</h2>
      <portfolio-balance></portfolio-balance>
      <portfolio-holdings></portfolio-holdings>
      <leader-board></leader-board>
  </div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioBalance from '../views/Balance'
import PortfolioHoldings from './PortfolioHoldings.vue'


export default {
  components: { PortfolioBalance, LeaderBoard, PortfolioHoldings },

}
</script>

<style>
  .boardTitle {
    color: palevioletred;
  }
  
</style>