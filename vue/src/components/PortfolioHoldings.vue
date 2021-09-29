<template>
  <div class="holdings">
    <div>
      <h3 class= "holdingsTitle">Your Position</h3>
      <div class="portfolioValue">
        <h4>Total Portfolio Value: </h4>
        <p> ${{ decimalPortfolioValue }} </p>
      </div>
      <div class="buyingPower">
        <h4>Buying Power: </h4>
        <p> ${{ availableFunds}} </p>

        <!-- new -->
      <div class="portfolioButton">
        <button v-on:click="toggleHistory = !toggleHistory">{{ !toggleHistory ? "Trade History" : "Portfolio Holdings"}}</button>
      </div>
      <div v-if="toggleHistory" class="flex">
        <div class="tradeHistory">
        <trade-history></trade-history>
        </div>
        </div>
            

            <!--end new -->
      
      <div v-else>
      <stock-list v-bind:stocks="stocks"></stock-list>
      </div>
      </div>
      </div>

      
      </div>
</template>

<script>
import playerService from "../services/PlayerService";
import stockService from "../services/StockService";
import StockList from "../components/StockList"
import TradeHistory from './TradeHistory.vue';
export default {
  components: {
    StockList,
    TradeHistory,
  },
  props: ['gameId'],
  data() {
    return {
      player: {},
      stocks: [],
      portfolioValue: 0,
      toggleHistory:  false
    }
  },
  computed: {
    availableFunds() {
      return Number(this.player.availableFunds).toFixed(2);
    },
    decimalPortfolioValue() {
      return Number(this.portfolioValue).toFixed(2);
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
          }).then(() => {
            this.getPortfolioValue();
          });

      })
  },
  methods: {
    getPortfolioValue() {
      this.portfolioValue = this.player.availableFunds;
      for(let i = 0; i <  this.stocks.length; i++) {
        let shares = this.stocks[i].total_shares;
        let stock_ticker = this.stocks[i].stock_ticker;
        stockService.getStockInfo(stock_ticker)
          .then(response => {
            let currentValue = response.data.currentPrice;
            this.portfolioValue = this.portfolioValue + (currentValue * shares);
          })
      }
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

button {
    font-size: 1rem;
    padding: 0.4rem 2rem;
    color: var(--background-color);
    border: 2px solid var(--background-color);
    background-color: transparent;
    border-radius: 4rem;
    cursor: pointer;
    transition: 0.4s;
    text-transform: uppercase;
    width: auto;
    align-items: center;
    justify-content: center;
    width: 100%;
}

    button:hover {
        border: 2px solid var(--color-green);
        background-color: var(--color-green);
    }

</style>