<template>
  <div id = "main ">
      <h2 class = "boardTitle">Welcome to the Game Board!</h2>
      <div id="flex">
        <portfolio-holdings v-bind:gameId="gameId"></portfolio-holdings>
        <trade-stocks></trade-stocks>
        <leader-board></leader-board>
      </div>
  </div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioHoldings from './PortfolioHoldings.vue'
import TradeStocks from './TradeStocks.vue'


export default {
  components: { LeaderBoard, PortfolioHoldings, TradeStocks },
  data() {
    return {
      gameId: this.$route.params.id,
    }
  }
}
</script>

<style>
  #flex {
    display: flex;
  }
</style>