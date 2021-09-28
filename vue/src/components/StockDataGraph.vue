<template>
   <v-chart class="chart" :option ="option" />
</template>

<script>
import stockService from '../services/StockService'
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from "echarts/components";
import VChart, { THEME_KEY } from "vue-echarts";
// import { ref, defineComponent } from "vue";

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
]);

export default {
    name: "stock-data-graph",
    commponents: {
        VChart,
    },
    provide: {
        [THEME_KEY]: "dark"
    },
    data() {
        return {
            option: {
                 title: {
                    text: "Stock",
                    left: "center"
                },
                xAxis: {
                    type: 'category',
                    data: this.getWeekDates(),
                },
                yAxis: {
                    type: 'value',
                    yAxisIndex: 1,
                    min: '',
                    max: '',
                    name: 'Value',
                },
                series: [
                    {
                    data: "",
                    type: 'line'
                    }
                ]
                }
        }
    },

    methods: {
        getWeekDates() {
            let today = new Date();
            let lastWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 7, today.getHours(), today.getMinutes(), today.getSeconds(), today.getMilliseconds());
            
            let unixTsToday = Math.round(today.getTime() / 1000);
            let unixTsLastWeek = Math.round(lastWeek.getTime() / 1000);
 
            let data = [];

            stockService.getHistoricalStockData('AAPL', 60, unixTsLastWeek, unixTsToday)
                .then(result => {
                    this.option.series[0].data = result.data;
                    let length = this.option.series[0].data.length;
                    let minValue = this.option.series[0].data[0];
                    let maxValue = 0;

                    let dateValue = lastWeek;
                    for(let i = 0; i < length; i++) {
                        data.push(i);
                        let currentValue = this.option.series[0].data[i];
                        if(currentValue < minValue) {
                            minValue = currentValue;
                        } else if (currentValue > maxValue) {
                            maxValue = currentValue;
                        }
                        dateValue = new Date(dateValue.getFullYear(), dateValue.getMonth(),
                            dateValue.getDate(), dateValue.getHours() + 1, dateValue.getMinutes(), 
                            dateValue.getSeconds(), dateValue.getMilliseconds());
                    }

                    minValue = Math.floor(minValue);
                    maxValue = Math.ceil(maxValue);
                    
                    this.option.yAxis.min = minValue;
                    this.option.yAxis.max = maxValue;
                })

            return data;
        },

    }
    

}
</script>

<style>
    .chart {
        width: 100vw;
        height: 100vh;
    }

</style>