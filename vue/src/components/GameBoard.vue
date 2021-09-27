<template>
  <div id="main">
    <div id = "game" v-if="gameOver == false">
        <h2 class = "boardTitle">{{ game.name }} Dashboard</h2>
        <div class="flex">
        <countdown-timer v-bind:gameId="gameId"></countdown-timer>
          <leader-board v-bind:gameId="gameId"></leader-board>
        </div>
        <div class="flex">
          <portfolio-holdings v-bind:gameId="gameId" class="portfolio"></portfolio-holdings>
          <trade-stocks class="trade"></trade-stocks>

        </div>
    </div>
    <div v-else>
      <game-over v-bind:gameId="gameId"/>
    </div>
  </div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioHoldings from './PortfolioHoldings.vue'
import TradeStocks from './TradeStocks.vue'
import CountdownTimer from '../components/CountdownTimer'
import GameService from '../services/GamesService'
import GameOver from './GameOver.vue'

// timer
// pass game id to timer comp via props 

// grab game info from database using games services and gameId
// store that in data section here
// pass to countdown timer using v-bind (follow line 5)
// inside of countdown have props to accept games to access information 
export default {
  components: { LeaderBoard, PortfolioHoldings, TradeStocks, CountdownTimer, GameOver},
  data() {
    return {
      gameId: this.$route.params.id,
      game: {
        id: "",
        name: "",
        organizer_id:"",
        end_date: "",
        end_time: ""
      },
      gameOver: false
    }
  },
  created() {
    GameService.getGameById(this.gameId)
    .then(result => {
      if(result.data != ""){
        this.game.id = result.data.id;
        this.game.name = result.data.name;
        this.game.organizer_id = result.data.organizer_id;
        this.game.end_date = result.data.end_date;
        this.game.end_time = result.data.end_time;
        // console.log(this.game);
      }
    }).then(() => {
      const timer = setInterval(() => {
        const now = new Date();
        const end = new Date(this.game.end_date + 'T' + this.game.end_time + '.000Z'); 
        const distance = end.getTime() - now.getTime();
        if (distance < 0) {
          clearInterval(timer);
          this.gameOver = true;
          return;
        }
      }, 1000);
    })   
  }
}
  

</script>

<style scope>
  #main {
    padding: 2rem var(--padding);
  }

  .flex {
    display: flex;
    flex-direction: row;
  }

  .portfolio, .trade {
    flex-grow: 1;
  }


</style>