<template>
  <CChartBar :data="defaultData" />
</template>

<script>
import { CChartBar } from '@coreui/vue-chartjs'
export default {
  name: 'CChartBarExample',
  components: { CChartBar },
  computed: {
    defaultData() {
      return {
        labels: [
          '01/07/2023',
          '05/07/2023',
          '09/07/2023',
          '10/07/2023',
          '12/07/2023',
          '23/07/2023',
          '24/07/2023',
          '25/07/2023',
          '29/07/2023',
        ],
        datasets: [
          {
            label: 'Đồ thị số người đặt Tour trong tháng',
            backgroundColor: '#f87979',
            data: [1, 5, 2, 4, 6, 1, 1, 2, 7],
          },
        ],
      }
    },
  },
}
</script>
