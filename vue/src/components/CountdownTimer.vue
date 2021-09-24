<template>
  <div>
      <head>
            <title>Game Countdown!</title>
      </head>
      <body>
          <table class="countdownContainer">
              <tr>
                  <td colspan="4" class="info">Game Countdown</td>
              </tr>
              <tr>
                  <td id= "days">{{displayDays}}</td>
                  <td id= "hours">{{displayHours}}</td>
                  <td id= "minutes">{{displayMinutes}}</td>
                  <td id= "seconds">{{displaySeconds}}</td>
              </tr>
              <tr>
                  <td>Days</td>
                  <td>Hours</td>
                  <td>Minutes</td>
                  <td>Seconds</td>
              </tr>
          </table>
      </body>
  </div>
</template>

<script>
import GameService from '../services/GamesService'
export default {
    data: () => ({
        displayDays: 0,
        displayHours: 0,
        displayMinutes: 0,
        displaySeconds: 0,
        game: {
        id: "",
        name: "",
        organizer_id:"",
        end_date: "",
        end_time: ""
      }
    }),
   created() {
        GameService.getGameById(this.gameId)
        .then(result => {
          if(result.data != ""){
              this.game.id = result.data.id;
              this.game.name = result.data.name;
              this.game.organizer_id = result.data.organizer_id;
              this.game.end_date = result.data.end_date;
              this.game.end_time = result.data.end_time;
          }
       this.showRemaining();
        })
        
        
   },
    props:  ["gameId"],

    computed: {
        // values in milisec
        _seconds: () => 1000,
        _minutes() {
            return this._seconds * 60;
        },
        _hours(){
            return this._minutes * 60;
        },
        days(){
            return this._hours * 24;
        }
    },
    mounted() {
       
       //this.showRemaining();
      
    },
// date = new Date('2021-09-16T10:20:00.000Z');

    methods: {
        showRemaining(){
            const timer = setInterval(() => {
                const now = new Date();
                const end = new Date(this.game.end_date + 'T' + this.game.end_time + '.000Z'); // placeholder to test 
                //console.log(end)
                const distance = end.getTime() - now.getTime();
               //     console.log(distance);
                // when time is up
                if(distance < 0){
                   //stop timer
                   clearInterval(timer);
                   return

                }
                // get total seconds between the times
                var delta = Math.abs(end -now) / 1000;

                // calculate (and subtract) whole days
                var days = Math.floor(delta / 86400);
                delta -= days * 86400;

                // calculate (and subtract) whole hours
                var hours = Math.floor(delta / 3600) % 24;
                delta -= hours * 3600;

                // calculate (and subtract) whole minutes
                var minutes = Math.floor(delta / 60) % 60;
                delta -= minutes * 60;

                // what's left is seconds
                var seconds = Math.floor(delta % 60);  // in theory the modulus is not required

               
                console.log(days);
                console.log(hours);
                console.log(minutes);
                console.log(seconds);
                // adding 0 to time 
                this.displayMinutes = minutes < 10 ? "0" + minutes : minutes;
                this.displaySeconds = seconds < 10 ? "0" + seconds : seconds;
                this. displayHours = hours <  10 ? "0" + hours : hours;
                this.displayDays = days <  10 ? "0" + days : days;
            }, 1000);
        }
    }
};
 
</script>

<style>
body{
    background: #f6f6f6;
}
.countdownContainer {
    text-align: center;
    background: #ddd;
    border: 1px, solid #999;
    padding: 10px;
    box-shadow: 0 0 5px 3px #ccc; 
}

.info {
    font-size: 30px;
}

</style>