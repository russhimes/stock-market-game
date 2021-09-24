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
export default {
    data: () => ({
        displayDays: 0,
        displayHours: 0,
        displayMinutes: 0,
        displaySeconds: 0
    }),

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
      //  this.showRemaining();
    },

    methods: {
        showRemaining(){
            const timer = setInterval(() => {
                const now = new Date();
                const end = new Date(2022, 4, 22, 10, 10, 10, 10); // placeholder to test 
                const distance = end.getTime() - now.getTime();

                // when time is up
                if(distance < 0){
                   //stop timer
                   clearInterval(timer);
                   return

                }
                // calculate values from milisec 
                const days = Math.floor((distance / this._days));
                const hours = Math.floor((distance % this._days) / this._hours);
                const minutes = Math.floor((distance % this.hours) / this._minutes);
                const seconds = Math.floor((distance % this.minutes) / this.seconds);
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