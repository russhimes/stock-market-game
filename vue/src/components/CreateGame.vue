<template>
<div id="createGame">
  <h2>Create Game</h2>
      <form id="createGame" @submit.prevent="createGame">
        <div>
          <p>   Name: </p>
          <input type="text" id="gameName" v-model="game.name"/>
        </div>
        <div>
        <p>   End Date: </p>
        <input type="date" id="endDate" v-model="game.end_date"/>
        </div>
        <div>
        <p>   End Time: </p>
        <input type="time" id="endTime" v-model="game.end_time"/>
        </div>
        <button type="submit">Submit</button>
      </form>
</div>
</template>

<script>
import gamesService from '../services/GamesService.js'
export default {
data() {
    return {
      game: {
        organizer_id: this.$store.state.user.id,
        name: "",
        end_date: '',
        end_time: ''
      }
    }
  },
  methods: {
    createGame() {
      gamesService.createGame(this.game)
      .then(response => {
        let gameId = response.data;
        this.$router.push({ name: 'add-players', params: { id: gameId } })
      })
      // Figure out what this does
      gamesService.getGames(0);
    }
  }
}
</script>

<style>
h2 {
  font-size: 3rem;
}

#createGame > * {
  display: flex;
} 

#createGame > button {
  min-width: 10vw;
  min-height: 50px;
  vertical-align: middle;
}
</style>