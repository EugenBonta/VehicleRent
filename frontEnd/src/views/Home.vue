<script setup lang="ts">
import car from '@/assets/images/car.jpg'
import bike from '@/assets/images/bike.jpg'
</script>

<template>

  <v-container>
    <v-row class="d-flex justify-center mb-16 text-large">
      <h1>Welcome to CarHire</h1>
    </v-row>
    <v-row class="d-flex justify-center">

        <router-link :to="{ name: 'car' }">
          <v-img :width="380" cover :src="car"></v-img>
        </router-link>
        <router-link :to="{ name: 'bike' }">
          <v-img :width="380" cover :src="bike"></v-img>
        </router-link>

    </v-row>

  </v-container>

</template>

<style scoped>
.text-large h1 {
  font-size: 90px;
  font-family: 'Tomorrow', monospace;
}
</style>
