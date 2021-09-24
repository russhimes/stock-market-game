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
                  <td id= "days">120</td>
                  <td id= "hours">4</td>
                  <td id= "minutes">12</td>
                  <td id= "seconds">22</td>
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

    computed: {
         Countdown() {
        let now = new Date()
        // inside of Date will be the date that the game ends 
        //let eventDate = new Date();
        return now;
        

    },
    
}
}
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