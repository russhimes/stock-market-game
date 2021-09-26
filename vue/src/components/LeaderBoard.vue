<template>
<div class = "LeaderBoard">
  <h3 class="title">Leaderboard!</h3>
   
  <p class="body"></p>
  <div v-for="player in playerList" v-bind:key="player.id">
    <div>{{player.username}} : {{player.availableFunds}}</div>
    <div></div>
  </div>

  <!-- <div class='playerCard' v-for='player in playerList'>
    {{ player.name }}
  </div> -->
</div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import userService from '../services/UserService.js'
//import CountdownTimer from '../components/CountdownTimer';

export default {
    data(){
      return {
        playerList: [],
        sorted : []
      }
    },
    created(){
      // first thing that happens when component loads 
      // taking data returned from getAllPlayers() and saving into our playerList
      
      //since this is the leaderboard page and the only use will be sorting, I just implemented it in the created 
      //portion, will need to do some work to get player names to show, and maybe stocks owned/shares owned
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
.title{
  color: pink;
}
.LeaderBoard{
  border-style: solid;
}
</style>