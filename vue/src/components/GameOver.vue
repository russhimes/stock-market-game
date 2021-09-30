<template>
    <div>
        <loading-page class="loading-page" v-if="!winnerCalculated"/>
        <div v-else>
            <div id="gameOver" >
                <div id="App">
                    <winner-confetti></winner-confetti>
                </div>
                <div>
                    <!-- <h2 class = "title"> Game Over </h2> -->
                    <h1>{{winnerInfo[0].username}} WINS!</h1>
                    <h3>Leaderboard</h3>
                <div class="finalContainer">
                <div class="rank">
                    <h4>Final Ranking</h4>
                    <leader-board v-bind:gameId="gameId"/>
                    </div>
                <div class="history">
                    <h4>Trading History</h4>
                    <leader-chart class="leaderChart" v-bind:gameId="gameId"/>
                </div>
                </div>
                </div>
                
            </div>
            <router-link class="back" v-bind:to="{ name: 'home' }">Back to all games</router-link>
        </div>
    </div>
</template>
<script>
import playerService from '../services/PlayerService.js'
import userService from '../services/UserService.js'
import LeaderBoard from '../components/LeaderBoard.vue'
import LeaderChart from '../components/LeaderChart.vue'
import Vue from 'vue'
import Particles from "particles.vue";
import WinnerConfetti from '../components/WinnerConfetti'
import LoadingPage from '../components/LoadingPage'
Vue.use(Particles);
export default {
    components: {LeaderBoard, LeaderChart, WinnerConfetti, LoadingPage},
    props: ["gameId"],
    data() {
        return {
            winnerCalculated : false,
            winnerInfo : [],
            leaderView: 'rankings'
        }
    },
    created() {
        //this.checkWinner().then(() => {
        const timer = setInterval (() => {
            this.checkWinner();
            clearInterval(timer);
            return;
        }, 1800)
       // })
    },
    methods: {
        checkWinner() {
            let result = playerService.getPlayersByGame(this.gameId).then(result => {
            for (let i = 0; i < result.data.length; i++) {
                if (result.data[i].game_status != "Finished" && result.data[i].game_status != "Rejected") {
                    this.winnerCalculated = false;
                    console.log(result.data[i].game_status);
                    break;
                }
                 if (i == result.data.length-1) this.winnerCalculated = true;
            }
            if (this.winnerCalculated) {
                for (let i = 0; i < result.data.length; i++) {
                    this.winnerInfo.push(result.data[i]);
                }
                this.winnerInfo.sort((a, b) => {return a.availableFunds - b.availableFunds});
                for (let i = 0; i < this.winnerInfo.length; i++) {
                    userService.getUserById(this.winnerInfo[i].user_id).then(userResult => {
                        this.winnerInfo[i].username = userResult.data.username;
                    })
                }
            }
            return result;
            });
            return result;
        }
    }   
}
</script>
<style scoped>
#gameOver{
    color: black;
    text-align: center;
    border: none;
    background-color: var(--color-lighter);
    margin: 0.3rem;
    border-radius: var(--border-radius);
    color: var(--background-color);
    align-items: center; 
    display: flex;
    flex-direction: column;
    margin: 0;
  }

.finalContainer {
    display: inline-block; 
    width: 700px; 
    height: auto; 
}
.title {
    margin: 1.2rem;
    margin-top: 1rem;
  }
    .rank {
        margin-top: 1rem;
        word-spacing: 30%;
        /* background: pink; */
        width: 50%;
        float: left;
    }
    .history {
        margin-top: 1rem;
        /* background: purple; */
        width: 50%;
        float: left;
    }
    #App{
        position: absolute;
        height: 1px;
        z-index: 100;
    }
  h1 {
      text-transform: uppercase;
      margin-top: 1.5rem;
  }

  .loading-page{
      background-color: transparent;
      margin: 0;
      padding: 0;
  }
</style>