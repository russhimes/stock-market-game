<template>
  <div id = "main ">
    <the-header></the-header>
      <h1>Welcome to the Game Board!</h1>
      <portfolio-balance></portfolio-balance>
      <portfolio-holdings></portfolio-holdings>
      <leader-board></leader-board>
  </div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioBalance from '../views/Balance'
// does header need a corresponding view? 
import TheHeader from './TheHeader.vue'
import PortfolioHoldings from '../views/Portfolio'
import PortfolioHoldings from './PortfolioHoldings.vue'

export default {
  components: { TheHeader, PortfolioBalance, LeaderBoar, PortfolioHoldings },

}
</script>

<style>

</style>