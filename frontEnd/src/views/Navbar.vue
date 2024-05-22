<script setup lang="ts"></script>

<template>
  <div class="bg-surface-variant mb-6 px-4 py-4">
    <v-row>
      <v-col class="d-flex justify-center align-center">
        <v-btn class="w-100 bold-text" color="cyan" :to="{ name: 'home' }"><b>Home</b></v-btn>
      </v-col>
      <v-col class="d-flex justify-center align-center">
        <v-btn class="w-100 bold-text" color="cyan" :to="{ name: 'bike' }"><b>Bike</b></v-btn>
      </v-col>
      <v-col class="d-flex justify-center align-center">
        <v-btn class="w-100 bold-text" color="cyan" :to="{ name: 'car' }"><b>Car</b></v-btn>
      </v-col>
      <v-col class="d-flex justify-center align-center">
        <v-btn class="w-100 bold-text" color="cyan" :to="{ name: 'bikeRent' }"
          ><b>Rent Bike</b></v-btn
        >
      </v-col>
      <v-col class="d-flex justify-center align-center">
        <v-btn class="w-100 bold-text" color="cyan" :to="{ name: 'carRent' }"
          ><b>Rent Car</b></v-btn
        >
      </v-col>
    </v-row>
  </div>
</template>

<style scoped></style>
