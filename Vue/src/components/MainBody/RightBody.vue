<template>
  <v-col
      cols="3"
      md="4"
  >
    <div style="margin: 20px 50px">
      <v-date-picker
          v-model="date2"
          :event-color="date => date[9] % 2 ? 'red' : 'yellow'"
          :events="functionEvents"
      ></v-date-picker>
    </div>
  </v-col>
</template>

<script>

export default {
  name: "RightBody",
  data() {
    return {
      date2: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
    }
  },
  mounted() {
    this.arrayEvents = [...Array(6)].map(() => {
      const day = Math.floor(Math.random() * 30)
      const d = new Date()
      d.setDate(day)
      return d.toISOString().substr(0, 10)
    })
  },
  methods: {
    functionEvents(date) {
      const [, , day] = date.split('-')
      if ([12, 17, 28].includes(parseInt(day, 10))) return true
      if ([1, 19, 22].includes(parseInt(day, 10))) return ['red', '#00f']
      return false
    }
  },
}
</script>

<style scoped>

</style>
