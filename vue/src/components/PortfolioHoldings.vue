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
        <p> ${{ player.availableFunds}} </p>

      </div>
      </div>
      <!-- <trade-history></trade-history> -->
      <stock-list v-bind:stocks="stocks"></stock-list>
      </div>
</template>

<script>
import playerService from "../services/PlayerService";
import stockService from "../services/StockService";
import StockList from "../components/StockList"
// import TradeHistory from './TradeHistory.vue';
export default {
  components: {
    StockList,
    // TradeHistory,
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
        this.player.availableFunds = this.player.availableFunds.toFixed(2);
        stockService.getPlayerStocks(this.player.id)
          .then(response => {
            this.stocks = response.data.filter(stock => {
              return stock.total_shares > 0;
            })
            // let i =
            this.portfolioValue =  Number(this.getPortfolioValue()).toFixed(2);
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
            portfolioValue += (currentValue * shares);
          })
      }
      return Number(portfolioValue);
    }
  }

}
</script>

<style scoped >
.holdingsTitle{
  margin: 1rem;
}

.holdings{
  border-style: solid;
  padding: 1rem;
}

.portfolioValue, .buyingPower {
  margin: 0.4rem 1rem;
}

</style>