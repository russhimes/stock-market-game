<template>
  <div class="holdings">
    <div>
      <h3 class= "holdingsTitle">Your Position</h3>
      <div class="portfolioValue">
        <h4>Total Portfolio Value: </h4>
        <p> ${{ portfolioValue }} </p>
      </div>
      <div class="buyingPower">
        <h4>Buying Power: </h4>
        <p> ${{ player.availableFunds }} </p>

      </div>
      </div>

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
            this.stocks = response.data.filter(stock => {
              return stock.total_shares > 0;
            })
            this.portfolioValue = this.getPortfolioValue();
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
      return portfolioValue.toFixed(2);
    }
  }

}
</script>

<style>
.holdingsTitle{
  margin: 1rem;
}
.holdings{
  border-style: solid;
}

.portfolioValue, .buyingPower {
  margin: 1rem;
}

</style>