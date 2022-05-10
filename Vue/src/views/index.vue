<template>
  <v-app>
    <Bar/>
    <v-main class="main">
      <router-view></router-view>
    </v-main>
    <core-footer />
  </v-app>
</template>
<script>
export default {
  name: "index",
  components: {
    Bar:()=>import('@/components/core/Bar'),
    CoreFooter: () => import('@/components/core/Footer')
  },
}
</script>

<style>
.main{
  width: 80%;
  margin: 0 auto;
}
</style>

