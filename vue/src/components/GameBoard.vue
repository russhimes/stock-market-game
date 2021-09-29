<template>
  <div id="main">
    <div v-if="isLoading == false">
    <div id = "game" v-if="gameFinished == false">
        <h1 class = "boardTitle">{{ game.name }} Dashboard</h1>
        <div class="flex">
            <portfolio-holdings v-bind:gameId="gameId" class="portfolio container"></portfolio-holdings>
            <trade-stocks class="trade container"></trade-stocks>
          <div>
            <countdown-timer v-bind:gameId="gameId" class="container"></countdown-timer>
            <leader-board v-bind:gameId="gameId" class="container"></leader-board>
          </div>

        </div>
            <!-- <trade-history></trade-history> -->
    </div>
    <div v-if="gameFinished== true">
      <game-over v-bind:gameId="gameId"/>
    </div>
    
    </div>
    <div v-else>
      <p>loading</p>
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
import PlayerService from '../services/PlayerService'
// import TradeHistory from './TradeHistory.vue'

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
      gameFinished: false,
      isLoading: true
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
      PlayerService.getPlayerByGame(this.game.id).then(result => {
        if (result.data.game_status == 'Finished') {
          this.gameFinished = true;
        }
      })
    }).then(() => {
      if (this.gameFinished == false) {
      const timer = setInterval(() => {
        const now = new Date();
        const end = new Date(this.game.end_date + 'T' + this.game.end_time + '.000Z'); 
        const distance = end.getTime() - now.getTime();
        if (distance < 0) {
          clearInterval(timer);
          this.gameFinished = true;
          return;
        }
      }, 100);
      }
    })   
  },
  mounted() {
    setTimeout(() => {
    this.isLoading = false;
  }, 500)}
}
  

</script>

<style scoped>
  * {
    color: var(--background-color);
  }

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

  h3{
    padding: 0.5rem;
  }

  .boardTitle {
    margin-bottom: 1rem;
    text-align: center;
    color: var(--color-primary);
  }

  .container {
    border: none;
    background-color: var(--color-lighter);
    margin: 0.3rem;
    border-radius: var(--border-radius);
    color: var(--background-color);
  }
</style>