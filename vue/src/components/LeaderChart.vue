<template>
  <v-chart class="chart" :option ="option" />
</template>

<script>
import playerService from '../services/PlayerService'
import playerHistoryService from '../services/PlayerHistoryService'
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart, { THEME_KEY } from "vue-echarts";

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);
export default {
  name: "LeaderStats",
  components: {
    VChart
  },
  provide: {
    [THEME_KEY]: "dark"
  },
  data() {
    return {
      playerHistories: [],
      option: {
        title: {
          text: "Leader Stats",
          left: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: "horizontal",
            left: "left",
            data: this.returnLegendData()
        },
        xAxis: {
            type: 'category',
            name: 'Time',
            data: this.times
        },
        yAxis: {
            type: 'value',
            name: 'numbers'
        },
        series: this.returnData()
      }
    }
  },
  props: ['gameId'],
  created() {
    playerService.getPlayersByGame(this.gameId).then(response => {
        this.playerHistories = [];
        for (let i = 0; i < response.data.length; i++) {
            this.playerHistories.push([]);
            this.playerHistories[i].id  = response.data[i].id
            this.playerHistories[i].username = response.data[i].username;
        }
    }).then(() => {
            for (let i = 0; i < this.playerHistories.length; i++) {
                playerHistoryService.getHistoryByPlayerId(this.playerHistories[i].id)
                .then((response) => {
                    this.playerHistories[i].data = response.data;
                    console.log("I think this is the issue");
                });
            }    
        });
    },
    methods: {
        returnLegendData() {const timer = setInterval(() => {
            console.log(this.playerHistories);
            let usernames = [];
            if (this.playerHistories) {
                for (let i = 0; i < this.playerHistories.length; i++) {
                    usernames.push(this.playerHistories[i].username);
                    console.log(usernames);
                }
                clearInterval(timer);
                return usernames;
            }
         }, 300)},
        returnData() { const timer = setInterval(() => {
            let test = false;
            if(test) clearInterval(timer);
            test = true;
            return [ {
                name: "user",
                type: "line",
                data: [
                    1111,
                    1548,
                    1632
                ]
            },
            {
            name: "admin",
            type: "line",
            data: [
              335,
              234,
              135,
            ]
            }];
        }, 300)}
    }
}
</script>

<style>
    .chart {
        height: 100%;
        width: 100%;
    }

</style>