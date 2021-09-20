<template>
  <div id="gameCard">
      <p id="gameName">{{game.name}}</p>
      <p id="gameEnd">Game ends on {{game.end_date}}, {{game.end_time}}</p>
      <button v-if="true" v-on:click="acceptGame">Accept Invite</button>
      <button v-if="true" v-on:click="rejectGame">Reject Invite</button>
      <router-link v-if="true" to="/Board">Go To Game</router-link>
      
  </div>
</template>

<script>
export default {
    props: ["game"],
    method: {
        acceptGame(){},
        rejectGame(){}
    }
}
</script>

<style scoped>
#gameCard {
    border: 2px black solid;
    margin: 0.2rem;
}

</style>