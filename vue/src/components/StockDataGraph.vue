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
import VChart from "vue-echarts";
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
    props: ['ticker'],
    data() {
        return {
            option: {
                 title: {
                    text: this.ticker + " Stock Value",
                    left: "center",
                    textStyle: {
                        fontSize: 24,
                        fontFamily: 'Poppins',
                        color: '#eee',
                    }
                },
                xAxis: {
                    type: 'category',
                    data: this.getWeekDates(),
                    show: false,
                },
                yAxis: {
                    type: 'value',
                    yAxisIndex: 1,
                    min: '',
                    max: '',
                    // name: 'Value',
                    // nameTextStyle: {
                    //     fontFamily: 'Poppins',
                    //     fontSize: 20,
                    // }
                },
                axisPointer: {
                    show: true,
                    snap: true,
                    label: {
                        fontFamily: 'Poppins',
                    }
                },
                series: [
                    {
                        data: [],
                        type: 'line',
                        color: '#ff4f79',
                    }
                ],
                backgroundColor: '#454545',
                textStyle: {
                    color: '#eee',
                    fontFamily: 'Poppins',
                }
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

            stockService.getHistoricalStockData(this.ticker, 60, unixTsLastWeek, unixTsToday)
                .then(result => {
                    this.option.series[0].data = result.data;
                    let length = this.option.series[0].data.length;
                    let minValue = this.option.series[0].data[0];
                    let maxValue = 0;

                    let dateValue = lastWeek;
                    for(let i = 0; i < length; i++) {
                        // data.push(i);
                        let currentValue = this.option.series[0].data[i];
                        if(currentValue < minValue) {
                            minValue = currentValue;
                        } else if (currentValue > maxValue) {
                            maxValue = currentValue;
                        }
                        dateValue = new Date(dateValue.getFullYear(), dateValue.getMonth(),
                            dateValue.getDate(), dateValue.getHours() + 1, dateValue.getMinutes(), 
                            dateValue.getSeconds(), dateValue.getMilliseconds());

                        data.push(dateValue.toString().substring(0, 24));
                    }

                    minValue = Math.floor(minValue);
                    maxValue = Math.ceil(maxValue);
                    
                    this.option.yAxis.min = minValue;
                    this.option.yAxis.max = maxValue;
                 
                    this.setColor(this.option.series[0].data[0], this.option.series[0].data[this.option.series[0].data.length-1]);

                })

            return data;
        },
        setColor(startValue, endValue) {
            if(endValue > startValue) {
                this.option.series[0].color =  '#9bc53d';
            }
        }
    },

    

}
</script>

<style>
    .chart {
        width: 100vh;
        height: 100vh;
    }

</style>