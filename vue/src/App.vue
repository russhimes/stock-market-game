<template>

  <div id="app">
    <head>
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    </head>
      <the-header />
    <router-view />
  </div>
</template>


<script>

import TheHeader from './components/TheHeader.vue'
export default {
  components: { TheHeader },
  
}
</script>

<style>
  :root {
    --color-red: #ff4f79;
    --color-green: #9bc53d;
    --background-color: #eeeeee;
    --color-primary: #211a1e;
    --color-lighter: #454545;
    --max-width: 600px;
  }

  *,
  *::before,
  *::after {
    box-sizing: border-box;
    padding: 0;
    margin: 0;
  }

  * {
    color: var(--color-primary);
    font-family: 'Poppins', 'Helvetica', sans-serif; 
  }

  body {
    font-size: 62.5%;
    background-color: var(--background-color);
  }

  h1 {
    font-size: 2.2rem;
  }

  h2 {
    font-size: 2rem;
  }

  h3 {
    font-size: 1.8rem;
  }
  
  p, a {
    font-size: 1.2rem;
  }

  a {
    transition: 0.2s;
    border-bottom: 2px solid transparent;
  }

  a:hover {
    border-bottom: 2px solid var(--color-green);
  }

</style>
