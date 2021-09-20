<template>
  <div id="gameCard">
      <h3 id="gameName">{{game.name}}</h3>
      <p id="gameEnd">Game ends on {{game.end_date}}, {{game.end_time}}</p>
      <button id="acceptButton" class="cardButton" v-if="true" v-on:click="acceptGame">Accept Invite</button>
      <button id="rejectButton" class="cardButton" v-if="true" v-on:click="rejectGame">Reject Invite</button>
      <router-link id="gameLink" tag="button" v-if="true" to="/Board">Go To Game</router-link>
      
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
    background-color: rgb(47, 107, 50);
    text-emphasis: Bold;
    color: white;
    border-radius: 6px;
    font-family: sans-serif;
}

#gameName {
    font-size: 2rem;
}

</style>