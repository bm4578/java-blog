<template>

<!--  <base-btn-->
<!--      class="mr-0"-->
<!--      square-->
<!--      title="Go to top"-->
<!--      @click="$vuetify.goTo(0)"-->
<!--  >-->
<!--    <v-icon>mdi-chevron-up</v-icon>-->
<!--  </base-btn>-->
  <v-footer
      dark
      padless
  >
    <v-card
        flat
        tile
        width="2300px"
        class="indigo lighten-1 white--text text-center"
    >
      <v-card-text style="background-color: black">
        <v-btn
            v-for="icon in icons"
            :key="icon"
            class="mx-4 white--text"
            icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>
      <v-card-text class="white--text" style="background-color: black">
        {{ new Date().getFullYear() }} — <strong>Vuetify</strong>
        <v-btn style="float:right;bottom: 40px;background-color: #409EFF;right: 10px"
            elevation="2"
            square
            title="Go to top"
               @click="$vuetify.goTo(0)"
        > <v-icon>mdi-chevron-up</v-icon></v-btn>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
  export default {
    name: 'CoreFooter',

    data: () => ({
      icons: [
        'mdi-facebook',
        'mdi-twitter',
        'mdi-linkedin',
        'mdi-instagram',
      ],
    }),
  }
</script>
