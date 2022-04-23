<template>
  <v-app>
    <Bar />
    <v-main style="padding: 0;margin: 0">
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

