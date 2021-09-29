<template>
<div class="container">
  <div class="invite">
    <h1>Invite Users</h1>
    <div class="invited">
      <h2>Invited</h2>
      <ul>
        <li v-for="user in invitedUsers" v-bind:key="user.id"> {{ user }}</li>
      </ul>
    </div>
    <table>
      <th>
        <td class="title">ID</td>
        <td class="title">Username</td>
      </th>
      <tr v-for="user in users" v-bind:key="user.id">
        <td>{{ user.id }}</td>
        <td> {{ user.username }}</td>
        <button v-if="!user.invited" v-on:click="inviteUser(user)">Invite to Game</button>
        <button v-else v-on:click="cancelInvite(user)">Cancel Invite</button>
        <router-link tag="button" to="/">Finished Inviting Players</router-link>
      </tr>
    </table>
  </div>
  </div>
</template>

<script>
import userService from '../services/UserService';
import playerService from '../services/PlayerService';
export default {
  data() {
    return {
      users: [],
      invitedUsers: [],
    }
  },
  created() {
    userService.getAllUsers().then( response => {
      response.data.forEach(user => {
        if (user.id != this.$store.state.user.id) {
          this.users.push( { id: user.id, username: user.username, invited: false });
        }
      })
    })
  },
  methods: {
    inviteUser(user) {
      playerService.create({
        user_id: user.id,
        game_id: this.$route.params.id,
        game_status: 'Pending'
      })
      .then((response) => {
        if(response.status === 200) {
          user.invited = true;
          console.log (response.data);
          user.player_id = response.data;
          this.invitedUsers.push(user.username);
        }
      })
    },
    cancelInvite(user) {
      console.log(user.player_id);
      playerService.delete(user.player_id)
        .then(response => {
          if(response.status === 200) {
            let position = this.invitedUsers.indexOf(user.username);
            this.invitedUsers.splice(position, 1);
            user.invited = false;
          }
        })
    }
  }

}
</script>

<style scoped>

.container {
    padding: 2rem;
    display: flex;
    align-content: center;
    justify-content: center;
    height: 90vh;
  }

table {
  display: flex;
  flex-direction: column;
  border-collapse: collapse;
  border-spacing: 2px;
  text-align: center;
  width: 115%;
  margin-left: -10%; 
  }

td {
  font-size: 1.2rem;
  text-align: center;
  padding: 6px;
}

tr {
  display: flex;
  flex-direction: row;
}

h1, h2 {
  text-align: center;
}

ul li {
  font-size: 1.2rem;
  margin: 0.5rem;
}

button{
    font-size: 1.2rem;
    padding: 0.4rem 2rem;
    margin: 1rem;
    border: 2px solid var(--background-color);
    background-color: transparent;
    border-radius: 4rem;
    cursor: pointer;
    transition: 0.4s;
    width: auto; 
    border-color: var(--color-green);
    white-space:pre;
}

button:hover {
        border: 2px solid var(--color-green);
        background-color: var(--color-green);
    }

</style>