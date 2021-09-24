<template>
  <div id = "main ">
      <h2 class = "boardTitle">Welcome to the Game Board!</h2>
       <countdown-timer v-bind:end-date="endDate" v-bind:end-time="endTime"></countdown-timer>
      <portfolio-holdings v-bind:gameId="gameId"></portfolio-holdings>
      <trade-stocks></trade-stocks>
      <leader-board></leader-board>
  </div>
</template>

<script>
import LeaderBoard from '../views/Leader'
import PortfolioHoldings from './PortfolioHoldings.vue'
import TradeStocks from './TradeStocks.vue'
import CountdownTimer from '../components/CountdownTimer'
import GameService from '../services/GamesService'

// timer
// pass game id to timer comp via props 

// within a computed() grab game info from database using games services and gameId
// store that in data section here
// pass to countdown timer using v-bind (follow line 5)
// inside of countdown have props to accept games to access information 
export default {
  components: { LeaderBoard, PortfolioHoldings, TradeStocks, CountdownTimer },
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
    }
  },
  
  created: {
    getTime(){
        GameService.getGameByUserId(this.$store.state.currentPlayerId)
        .then(result => {
          if(result.data != ""){
              this.game.id = result.data.id;
              this.game.name = result.data.name;
              this.game.organizer_id = result.data.organizer_id;
              this.game.end_date = result.data.end_date;
              this.game.end_time = result.data.end_time;
          }
        })
        
      }
    }
  }

</script>

<style>
  .boardTitle {
    color: palevioletred;
  }
  
</style>