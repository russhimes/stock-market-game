<template>
  <div class="home">
    <p v-if="!$store.state.user.username">You must be authenticated to see this</p>
    <div v-else>
      
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
