<template>
<div class = "LeaderBoard">
  <h3 class="title">Leaderboard!</h3>
  <p class="body">This is where we will show the stats of the leaderboard!</p>
  <div v-for="player in playerList" v-bind:key="player.id">
    <div>{{player.id}} : {{player.availableFunds}}</div>
  </div>
  <countdown-timer></countdown-timer>
  <!-- <div class='playerCard' v-for='player in playerList'>
    {{ player.name }}
  </div> -->
</div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import CountdownTimer from '../components/CountdownTimer';

export default {
  components: {
    CountdownTimer
  },
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
      playerService.getAllPlayers().then(
          (response) => {
            this.playerList = response.data;
            this.playerlist = this.playerList.sort((a,b) => {
              return b.availableFunds - a.availableFunds
            })
          }
      ).catch(
        (error) => console.log(error)
      );

    },
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