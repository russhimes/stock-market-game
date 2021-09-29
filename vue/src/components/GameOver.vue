<template>
  <div id="gameOver">
      <div v-if="winnerCalculated == true">
        <h2 class = "title"> Game Over </h2>
        <h3>{{winnerInfo[0].username}} WINS!</h3>
        <!-- <div class="leader">
        <leader-board v-bind:gameId="gameId"/>
        </div> -->

    <!-- new -->
        <div class="title">
    <h3>Leaderboard</h3>
    <div class="toggle">
              <input type="radio" id="rankings" value="rankings" v-model="leaderView">
              <label for="rankings">Rankings</label>
              <input type="radio" id="history" value="history" v-model="leaderView">
              <label for="history">History</label>
    </div>
  </div>

  <leader-board v-bind:gameId="gameId" v-if="leaderView=='rankings'" class="rankings"/>
  <div v-else>
    <leader-chart class="leaderChart" v-bind:gameId="gameId"/>
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
                if (result.data[i].game_status != "Finished") {
                    this.winnerCalculated = false;
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
    
}


 #gameOver {
    display: flex;
    flex-direction: column;
    /* margin: 0; */
  }

.title {
    margin: 1rem;
  }

 input[type=radio] {
        opacity: 0;
        position: fixed;
        width: 0;
    }

    .toggle {
        display: flex;
        justify-content: space-evenly;
        margin-right: 37%;
        margin-left: 37%;
        padding-top: 1rem;
    }
 .toggle label {
        display: inline-block;
        padding: 0.1rem 0.5rem;
        width: 6rem;
        text-align: center;
        font-family: 'Poppins', sans-serif;
        font-size: 0.8rem;
        border: 2px solid var(--background-color);
        border-radius: 8rem;
        cursor: pointer;
        transition: 0.4s;
    }
.toggle label:hover {
        background-color: var(--color-green);
        border: 2px solid var(--color-green);
       
    }
.toggle input[type="radio"]:checked + label {
        border: 2px solid var(--color-green);
        background-color: var(--color-green);
        font-weight: bold;
    }

    .toggle {
        text-align: center;
    }

    #rankings {
        width: 50px;
    }
</style>