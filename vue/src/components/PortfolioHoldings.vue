<template>
  <div class="holdings">
    <div class="buyingPower">
      <h3>Buying Power: </h3>
      <p> ${{ player.availableFunds }} </p>
    </div>
      <h3 class= "holdingsTitle">Your current stock portfolio includes:</h3>
      <stock-list v-bind:stocks="stocks"></stock-list>
  </div>
</template>

<script>
import playerService from "../services/PlayerService";
import stockService from "../services/StockService";
import StockList from "../components/StockList"
export default {
  components: {
    StockList,
  },
  props: ['gameId'],
  data() {
    return {
      player: {},
      stocks: []
    }
  },
  created() {
    playerService.getPlayerByGame(this.gameId) 
      .then(response => {
        this.player = response.data;
        stockService.getPlayerStocks(this.player.id)
          .then(response => {
            this.stocks = response.data;
          })

      })
  },

}
</script>

<style>
.holdingsTitle{
  color: pink;
}
.holdings{
  border-style: solid;
}
</style>