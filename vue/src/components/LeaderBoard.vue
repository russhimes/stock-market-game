<template>
<div class = "leader-board">
  <h3 class="title">Leaderboard</h3>

  <div v-for="player, index in playerList" v-bind:key="index">
    <p>Rank {{index + 1}} : {{player.username}} : ${{player.availableFunds.toFixed(2)}}</p>
  </div>

    <leader-chart class="leaderChart" v-bind:gameId="gameId"/>

  <!-- <div class='playerCard' v-for='player in playerList'>
    {{ player.name }}
  </div> -->
</div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import userService from '../services/UserService.js'
import LeaderChart from '../components/LeaderChart'
//import CountdownTimer from '../components/CountdownTimer';

export default {
    name: 'leader-board',
    components: {
      LeaderChart,
    },
    data(){
      return {
        playerList: [],
        sorted : [],
        counter: 0
      }
    },
    created(){
      // first thing that happens when component loads 
      // taking data returned from getAllPlayers() and saving into our playerList
    
      playerService.getPlayersByGame(this.gameId).then(
          (response) => {
            console.log(this.gameId);
            this.playerList = response.data;
            this.playerlist = this.playerList.sort((a,b) => {
              return b.availableFunds - a.availableFunds
            })
          for (let i = 0; i < this.playerList.length; i++) {
            userService.getUserById(this.playerList[i].user_id).then(userResponse => {
              this.playerList[i].username = userResponse.data.username;
            })
          }
        })
      
      .catch(
        (error) => console.log(error)
      );
    },
    props:  ["gameId"],
    computed: {
      // filteredList(){
      //   if()
      // }

  }
}

</script>

<style>
  .leader-board{
    padding: 1rem;
  }
</style>