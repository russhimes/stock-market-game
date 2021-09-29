<template>
    <div class="gamesList">
        <div class="cardsContainer">
            <h2>Active Games</h2>
            <game-card v-for="game in accepted" v-bind:key="game.id" v-bind:game="game"/>
        </div>
        <div class="cardsContainer">
            <h2>Pending Invites</h2>
            <game-card v-for="game in pending" v-bind:key="game.id" v-bind:game="game"/>
        </div>
        <div class="cardsContainer">
            <h2>Completed Games</h2>
            <game-card v-for="game in finished" v-bind:key="game.id" v-bind:game="game"/>
        </div>
        <div class="cardsContainer">
            <h2>Rejected Games</h2>
            <game-card v-for="game in rejected" v-bind:key="game.id" v-bind:game="game"/>
        </div>

    </div>
</template>

<script>
import GameCard from '../components/GameCard.vue'
export default {
    components: {GameCard},  
    data() {
        return {
            finished: [],
        }
    },
    computed: {
            rejected() {
                let rejected = [];
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    for (let j = 0; j < this.$store.state.players.length; j++) {
                        if (this.$store.state.games[i].id == this.$store.state.players[j].game_id &&
                        this.$store.state.user.id == this.$store.state.players[j].user_id
                        && this.$store.state.players[j].game_status == 'Rejected') {
                            rejected.push(this.$store.state.games[i]);
                        }
                    }       
                }
                return rejected;
            },
            accepted() {
                let accepted = [];
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    for (let j = 0; j < this.$store.state.players.length; j++) {
                        if (this.$store.state.games[i].id == this.$store.state.players[j].game_id &&
                        this.$store.state.user.id == this.$store.state.players[j].user_id
                        && this.$store.state.players[j].game_status == 'Accepted') {
                            accepted.push(this.$store.state.games[i]);
                        }
                    }
                }
                return accepted;
            },
            pending() { 
                let pending = [];
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    for (let j = 0; j < this.$store.state.players.length; j++) {
                        if (this.$store.state.games[i].id == this.$store.state.players[j].game_id &&
                        this.$store.state.user.id == this.$store.state.players[j].user_id
                        && this.$store.state.players[j].game_status == 'Pending') {
                            pending.push(this.$store.state.games[i]);
                            
                        }
                    }
                }
                return pending;  
            }
    },
    created() {
        setInterval(() => {
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    let game = this.$store.state.games[i];
                    let isInList = false;
                    for (let j = 0; j < this.finished.length; j++) {
                        if (this.finished[j].id == game.id) {
                            isInList = true;
                            break;
                        }
                    }
                    if (!isInList) {
                        const now = new Date();
                        const end = new Date(game.end_date + 'T' + game.end_time + '.000Z'); 
                        if (end.getTime() - now.getTime() < 0) {
                            this.finished.push(this.$store.state.games[i]);  
                        } 
                      
                    }
                }
            }, 1000)}
}
</script>

<style scoped>
    .gamesList {
        display: flex;
        padding: 0 var(--padding);
        overflow-x: auto;
    }

    h2 {
        font-size: 1.4rem;
        color: var(--background-color);
        text-align: center;
        margin-bottom: 1rem;
    }

    .cardsContainer {
        background-color: var(--color-lighter);
        margin-right: 1rem;
        padding: 2rem 1rem;
        border-radius: var(--border-radius);
        min-width: 22rem;
        height: 70vh;
        overflow-y: auto;
    }

    button {
        text-transform: none;
    }

    button:hover {
        border: 0;
    }

</style>