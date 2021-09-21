<template>
  <div>
    <h1>Invite Users</h1>
    <div class="invited">
      <ul>
        <li v-for="user in invitedUsers" v-bind:key="user.id"> {{ user }}</li>
      </ul>
    </div>
    <table>
      <th>
        <td>ID</td>
        <td>Username</td>
      </th>
      <tr v-for="user in users" v-bind:key="user.id">
        <td>{{ user.id }}</td>
        <td> {{ user.username }}</td>
        <button v-if="!user.invited" v-on:click="inviteUser(user)">Invite to Game</button>
        <button v-else v-on:click="cancelInvite(user)">Cancel Invite</button>
      </tr>
    </table>
  </div>
</template>

<script>
import userService from '../services/UserService';
import playerService from '../services/PlayerService';
export default {
  data() {
    return {
      users: [],
      invitedUsers: []
    }
  },
  created() {
    userService.getAllUsers().then( response => {
      response.data.forEach(user => {
        this.users.push( { id: user.id, username: user.username, invited: false });
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
          this.invitedUsers.push(user.username);
        }
      })
    },
    cancelInvite(user) {
      playerService.delete(user.id)
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

<style>

</style>