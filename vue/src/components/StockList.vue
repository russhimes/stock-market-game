<template>
  <div class="stock-scroll">
      <stock-card v-for="stock in stocks" v-bind:stock="stock" v-bind:key="stock.id"></stock-card>
  </div>
</template>

<script>
import StockCard from './StockCard.vue';
export default {
  components: { StockCard },
  props: ['stocks'],
  data() {
    return {
    }
  },
};
</script>

<style scoped>
  .stock-scroll{
    max-height: 40vh;
    overflow-y: scroll;
    padding: 1rem;
  }
</style>