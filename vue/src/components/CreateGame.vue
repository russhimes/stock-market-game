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
        <input type="time" id="endTime"  v-model="game.end_time"/> 

        <!-- v-on:click="convertTimetoUTC()" -->
        </div>
        <!-- <div>
          <label for="timezone">Time Zone</label>
          <select name="timezone" id="timezone">
            <option v-on:click="convertCdtTime()" value="CDT">CDT</option>
            <option v-on:click="convertEdtTime()" value="EDT">EDT</option>
            <option v-on:click="convertMdtTime()" value="MDT">MDT</option>
            <option v-on:click="convertPdtTime()" value="PDT">PDT</option>
        </select>
        </div> -->
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
// create date/ time object then convert and move back to seperate objs
  methods: {
    convertTimetoUTC(){
        let moment = (this.game.end_date + 'T' + this.game.end_time + '.000Z');
        let utc_offset = moment.getTimezoneOffset();
        moment.setMinutes(moment.getMinutes() + utc_offset)
        console.log(this.game)
    },

    createGame() {
      // call converision method
     this.convertTimetoUTC()
  
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