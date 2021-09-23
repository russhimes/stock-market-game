<template>
  <div class="holdings">
    <div>
      <h3>Total Portfolio Value: </h3>
      <p> ${{ portfolioValue }} </p>
    </div>
    <div class="buyingPower">
      <h3>Buying Power: </h3>
      <p> ${{ player.availableFunds }} </p>
    </div>
      <h3 class= "holdingsTitle">Stocks:</h3>
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
      stocks: [],
      portfolioValue: 0,
    }
  },
  created() {
    playerService.getPlayerByGame(this.gameId) 
      .then(response => {
        this.player = response.data;
        stockService.getPlayerStocks(this.player.id)
          .then(response => {
            this.stocks = response.data;
            this.portfolioValue = this.getPortfolioValue();
            console.log(this.stocks);
          })

      })
  },
  computed: {
  },
  methods: {
    getPortfolioValue() {
      let portfolioValue = this.player.availableFunds;
      for(let stock of this.stocks) {
        let shares = stock.total_shares;
        let stock_ticker = stock.stock_ticker;
        stockService.getStockInfo(stock_ticker)
          .then(response => {
            let currentValue = response.data.currentPrice;
            this.portfolioValue += (currentValue * shares);
          })
      }
      return portfolioValue;
    }
  }

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