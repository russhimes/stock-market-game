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
        <div>
          <label for="timezone">Time Zone</label>
          <select name="timezone" id="timezone">
            <option value="CT">CT</option>
            <option value="ET">ET</option>
            <option value="MT">MT</option>
            <option value="PT">PT</option>
        </select>
        </div>
        <button type="submit">Submit</button>
      </form>
</div>
</template>

<script>
import gamesService from '../services/GamesService.js'
import playerService from '../services/PlayerService.js'
export default {
data() {
    return {
      game: {
        organizer_id: this.$store.state.user.id,
        name: "",
        end_date: '',
        end_time: ''
      },
      gameId: -1
    }
  },
  methods: {
    createGame() {
      gamesService.createGame(this.game)
      .then(response => {
        this.gameId = response.data;
        console.log(this.$store.state.user);
        this.inviteUser(this.$store.state.user);
        this.$router.push({ name: 'add-players', params: { id: this.gameId } });

      })
    },
    inviteUser(user) {
      playerService.create({
        user_id: user.id,
        game_id: this.gameId,
        game_status: 'Accepted'
      })
      .then((response) => {
        if(response.status === 200) {
          user.invited = true;
        }
      })
    },
  }
}
</script>

<style scope>

#createGame > * {
  display: flex;
} 

#createGame > button {
  min-width: 10vw;
  min-height: 50px;
  vertical-align: middle;
}
</style>