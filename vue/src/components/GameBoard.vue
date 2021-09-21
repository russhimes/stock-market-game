<template>
  <div id = "main ">
    <the-header></the-header>
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
// does header need a corresponding view? 
import TheHeader from './TheHeader.vue'


export default {
  components: { TheHeader, PortfolioBalance, LeaderBoard, PortfolioHoldings },

}
</script>

<style>
  .boardTitle {
    color: palevioletred;
  }
  
</style>