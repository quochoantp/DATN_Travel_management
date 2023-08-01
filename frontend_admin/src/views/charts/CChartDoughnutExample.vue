<template>
  <CChartDoughnut :data="defaultData" />
</template>

<script>
import { CChartDoughnut } from '@coreui/vue-chartjs'
export default {
  name: 'CChartDoughnutExample',
  components: { CChartDoughnut },
  computed: {
    defaultData() {
      return {
        labels: [
          'Đã xác nhận thanh toán',
          'Chưa xác nhận thanh toán',
          'Đã gửi hợp đồng',
          'Đã hủy',
        ],
        datasets: [
          {
            backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: [15, 10, 8, 2],
          },
        ],
      }
    },
  },
}
</script>
