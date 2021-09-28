<template>
<div class = "leader-board">
  <h3 class="title">Leaderboard</h3>
  
  <div v-for="player, index in playerList" v-bind:key="player.id">
    <p>Rank {{index + 1}} : {{player.username}} : ${{player.portfolioValue.toFixed(2)}}</p>

  </div>

  <!-- <div class='playerCard' v-for='player in playerList'>
    {{ player.name }}
  </div> -->
</div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import stockService from '../services/StockService.js'
//import CountdownTimer from '../components/CountdownTimer';

export default {
    name: 'leader-board',
    data(){
      return {
        playerList: [],
        sorted : [],
        stocks : [],
        counter: 0
      }
    },
    created(){
      // first thing that happens when component loads 
      // taking data returned from getAllPlayers() and saving into our playerList
    
      playerService.getPlayersByGame(this.gameId).then(
          (response) => {
            this.playerList = response.data;
            this.playerlist = this.playerList.sort((a,b) => {
              return b.availableFunds - a.availableFunds
            })
        }).then(() => {
          for(let i = 0; i < this.playerList.length; i++) {
            stockService.getPlayerStocks(this.playerList[i].id)
            .then(response => {
              let playerStocks = response.data.filter(stock => {
                return stock.total_shares > 0;
              }); 
              for (let j = 0; j < playerStocks.length; j++) {
                this.stocks.push(playerStocks[j]);
              }
            }).then(() => {
              this.getPortfolioValue(i);
            });
          }
        })
      .catch(
        (error) => {console.log(error)
        });
    },
    props:  ["gameId"],
    computed: {
      // filteredList(){
      //   if()
      // }

  },
  methods: {
    getPortfolioValue(index) {
      this.playerList[index].portfolioValue = this.playerList[index].availableFunds;
      console.log(this.playerList[index]);
      for(let i = 0; i <  this.stocks.length; i++) {
        if (this.playerList[index].id == this.stocks[i].player_id) {
          let shares = this.stocks[i].total_shares;
          let stock_ticker = this.stocks[i].stock_ticker;
          stockService.getStockInfo(stock_ticker)
          .then(response => {
            let currentValue = response.data.currentPrice;
            this.playerList[index].portfolioValue = this.playerList[index].portfolioValue + (currentValue * shares);
            console.log(this.playerList[index].portfolioValue);
          })
        }
      }
    }
  }
}

</script>

<style>
  .leader-board{
    padding: 1rem;
  }
</style>