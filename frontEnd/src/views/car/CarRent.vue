<script setup lang="ts">
import {computed, type ComputedRef, onMounted, ref} from 'vue'
import {useCarRentsStore} from '@/stores/carsRents'
import {storeToRefs} from 'pinia'
import type {CarRent} from '@/interfaces/ws'
import {endCarRent} from '@/api/api'
import Navbar from '@/views/Navbar.vue'

const store = useCarRentsStore()
const { carRents, loading, error } = storeToRefs(store)

onMounted(() => {
  store.fetchCarRentsList()
})

const idnp = ref('')
const phone = ref('')
const email = ref('')
const showCompleted = ref(false)

const filteredCarRents: ComputedRef<Array<CarRent>> = computed(() => {
  return carRents.value.filter((rent: CarRent) => {
    const matchesIdnp = idnp.value ? rent.idnp.includes(idnp.value) : true
    const matchesPhone = phone.value ? rent.tel.includes(phone.value) : true
    const matchesEmail = email.value ? rent.email.includes(email.value) : true
    const matchesCompleted = showCompleted.value ? true : rent.finishDate === null

    return matchesIdnp && matchesPhone && matchesEmail && matchesCompleted
  })
})

const endRent = async (id: number) => {
  const isConfirmed = confirm('Are you sure you want to end this rent?')

  if (!isConfirmed) {
    return
  }

  try {
    await endCarRent(id)
    await store.fetchCarRentsList()
  } catch (error) {
    console.error('Failed to end car rent:', error)
  }
}
</script>

<template>
  <v-container>
    <Navbar />

    <div class="mb-6 px-6 pt-6 pb-0 bg-surface-variant">
      <v-row>
        <v-col cols="10">
          <v-row>
            <v-col cols="4">
              <v-text-field
                v-model="idnp"
                :counter="10"
                label="IDNP"
                hide-details
                required
              ></v-text-field>
            </v-col>

            <v-col cols="4">
              <v-text-field
                v-model="phone"
                :counter="10"
                label="Phone"
                hide-details
                required
              ></v-text-field>
            </v-col>

            <v-col cols="4">
              <v-text-field v-model="email" label="E-mail" hide-details required></v-text-field>
            </v-col>
          </v-row>
        </v-col>

        <v-col cols="2">
          <v-checkbox label="Show completed" v-model="showCompleted"></v-checkbox>
        </v-col>
      </v-row>
    </div>

    <div
      class="mb-10 bg-surface-variant mb-4 px-4 py-4"
      v-for="rent in filteredCarRents"
      :key="rent.id"
    >
      <v-row>
        <v-col cols="10">
          <v-row class="ml-3 mr-3">
            <h4>
              <b>{{
                `Vehicle info: rentId-${rent.id}, startDate-${rent.startDate}, finishDate-${rent.finishDate},
                rent days-${rent.numberOfDays}, price per day-${rent.pricePerDay}, carId-${rent.car.id}`
              }}</b>
            </h4>
          </v-row>

          <v-row class="ml-3 mr-3">
            <h4>
              <b>{{ `Client info: IDNP-${rent.idnp}, tel-${rent.tel}, email-${rent.email}` }}</b>
            </h4>
          </v-row>
        </v-col>

        <v-col cols="2">
          <v-btn
            variant="outlined"
            class="mb-2 button-border w-100"
            color="blue"
            @click="endRent(rent.id)"
            :disabled="rent.finishDate != null"
          >
            <h3><b>end rent</b></h3>
          </v-btn>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<style scoped>
.button-border {
  border-width: 3px;
}
</style>
