<template>
  <CChartLine :data="defaultData" />
</template>

<script>
import { CChartLine } from '@coreui/vue-chartjs'
export default {
  name: 'CChartLineExample',
  components: { CChartLine },
  computed: {
    defaultData() {
      return {
        labels: ['01/08/2023', 'a', 'b', 'c', 'd'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: 'rgb(228,102,81,0.9)',
            data: [30, 39, 10, 50, 30, 70, 35],
          },
        ],
      }
    },
  },
}
</script>
