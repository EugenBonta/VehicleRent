<script setup lang="ts">
import {computed, reactive} from 'vue'
import {cloneCar} from '@/api/api'
import {useRoute, useRouter} from 'vue-router'
import Navbar from '@/views/Navbar.vue'

const route = useRoute()
const id = computed(() => route.params.id)

const router = useRouter()

const form = reactive({
  vin: '32106516',
  year: 2017,
  mileage: 250000,
  photo: 'https://live.staticflickr.com/65535/52533263334_85eeb4ee79_b.jpg'
} as CloneVehicleForm)

const submitForm = async () => {
  try {
    await cloneCar(Number(id.value), form)
    await router.push({ name: 'car' })
  } catch (error) {
    console.error('Failed to create car:', error)
  }
}
</script>

<template>
  <v-container>
    <Navbar />

    <v-form>
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.vin" label="VIN" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.year" label="Year" type="number" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="form.mileage"
            label="Mileage"
            type="number"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="form.photo" label="Photo URL" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-btn @click="submitForm" color="primary"> Submit</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<style scoped></style>
