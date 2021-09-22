<template>
  <!-- JUST CHECKING THAT THIS WORKS!  Once we move over to stock view we'll use that. -->
  <div id="topTen">
      <ul>
      <li v-for="stock in stocks" v-bind:key="stock.id">{{stock.ticker}}    {{stock.name}}    {{stock.price}}</li>
      </ul>
  </div>
</template>

<script>
import topTenService from '../services/TopTenService.js';
export default {
    data() {
        return {
            stocks: []
        }
    },
    created() {
        this.stocks = topTenService.getTopTen();

    }

}
</script>

<style>

</style>