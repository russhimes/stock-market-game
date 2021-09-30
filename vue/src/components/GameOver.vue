<template>
  <div id="gameOver">
      <div v-if="winnerCalculated == true">
        <h2 class = "title"> Game Over </h2>
        <h3>{{winnerInfo[0].username}} WINS!</h3>
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
      <p v-else>loading</p>
  </div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import userService from '../services/UserService.js'
import LeaderBoard from '../components/LeaderBoard.vue'
import LeaderChart from '../components/LeaderChart.vue'
// import LeaderBoard from '../views/Leader.vue'
export default {
    components: {LeaderBoard, LeaderChart},
    props: ["gameId"],
    data() {
        return {
            winnerCalculated : false,
            winnerInfo : [],
            leaderView: 'rankings'
        }
    },
    created() {
        this.checkWinner().then(() => {
        const timer = setInterval (() => {
            this.checkWinner();
            clearInterval(timer);
            return;
        }, 100)
        })
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
}

 #gameOver {
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

   
</style>