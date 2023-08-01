<template>
  <div>
    <!-- <WidgetsStatsA /> -->
    <CRow>
      <CCol :md="12">
        <CCard class="mb-4">
          <CCardBody>
            <CRow>
              <CCol :sm="5">
                <h4 id="traffic" class="card-title mb-0">
                  Đồ thị doanh thu theo tháng
                </h4>
              </CCol>
              <CCol :sm="7" class="d-none d-md-block">
                <CButton color="primary" class="float-end" disabled>
                  Năm 2023
                </CButton>
              </CCol>
            </CRow>
            <CRow>
              <MainChartExample
                style="height: 300px; max-height: 300px; margin-top: 40px"
              />
            </CRow>
          </CCardBody>
          <CCardFooter>
            <CRow :xs="{ cols: 1 }" :md="{ cols: 4 }" class="text-center">
              <CCol class="mb-sm-2 mb-0">
                <div class="text-medium-emphasis">Doanh thu Quý 1</div>
                <strong>45.000.000 VND</strong>
                <CProgress
                  class="mt-2"
                  color="success"
                  thin
                  :precision="1"
                  :value="100"
                />
              </CCol>
              <CCol class="mb-sm-2 mb-0 d-md-down-none">
                <div class="text-medium-emphasis">Doanh thu Quý 2</div>
                <strong>5.000.000 VND</strong>
                <CProgress
                  class="mt-2"
                  color="info"
                  thin
                  :precision="1"
                  :value="100"
                />
              </CCol>
              <CCol class="mb-sm-2 mb-0">
                <div class="text-medium-emphasis">Doanh thu Quý 3</div>
                <strong>5.000.000 VND</strong>
                <CProgress
                  class="mt-2"
                  color="warning"
                  thin
                  :precision="1"
                  :value="100"
                />
              </CCol>
              <CCol class="mb-sm-2 mb-0">
                <div class="text-medium-emphasis">Doanh thu Quý 4</div>
                <strong>0 VND</strong>
                <CProgress
                  class="mt-2"
                  color="danger"
                  thin
                  :precision="1"
                  :value="100"
                />
              </CCol>
            </CRow>
          </CCardFooter>
        </CCard>
      </CCol>
    </CRow>
    <!-- <WidgetsStatsD /> -->
    <CRow>
      <CCol :md="12">
        <CCard class="mb-4">
          <CCardHeader> Tours </CCardHeader>
          <CCardBody>
            <CRow>
              <CCol :sm="12" :lg="6">
                <div
                  class="border-start border-start-4 border-start-info py-1 px-3 mb-3"
                >
                  <div class="text-medium-emphasis small">Tổng số Tours</div>
                  <div class="fs-5 fw-semibold">45</div>
                </div>
              </CCol>
              <CCol :sm="12" :lg="6">
                <div
                  class="border-start border-start-4 border-start-warning py-1 px-3 mb-3"
                >
                  <div class="text-medium-emphasis small">
                    Tổng số thành viên
                  </div>
                  <div class="fs-5 fw-semibold">34</div>
                </div>
              </CCol>
            </CRow>
            <br />
            <CRow>
              <CCol :sm="12" :lg="2">
                <el-form-item label="Tháng">
                  <el-select v-model="month" placeholder="Chọn tháng">
                    <el-option
                      v-for="item in monthList"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id"
                    />
                  </el-select>
                </el-form-item>
              </CCol>
              <CCol :sm="12" :lg="2">
                <el-form-item label="Năm">
                  <el-input v-model="year" />
                </el-form-item>
              </CCol>
              <CCol :sm="12" :lg="2">
                <el-button type="success" plain @click="onClickChart"
                  >Lọc</el-button
                >
              </CCol>
            </CRow>
            <CRow v-if="isShow">
              <CCol :sm="12" :lg="8">
                <h4 id="traffic" class="card-title mb-0">
                  Đồ thị số lượng người đặt Tour trong tháng
                </h4>
                <CChartBarExample></CChartBarExample>
              </CCol>
              <CCol :sm="12" :lg="4">
                <h4 id="traffic" class="card-title mb-0">Trạng thái Tour</h4>
                <CChartDoughnutExample></CChartDoughnutExample>
              </CCol>
            </CRow>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import MainChartExample from './charts/MainChartExample'
import CChartDoughnutExample from './charts/CChartDoughnutExample.vue'
import CChartBarExample from './charts/CChartBarExample.vue'
// import WidgetsStatsA from './widgets/WidgetsStatsTypeA.vue'
// import WidgetsStatsD from './widgets/WidgetsStatsTypeD.vue'

export default {
  name: 'Dashboard',
  components: {
    MainChartExample,
    CChartDoughnutExample,
    CChartBarExample,
  },
  data() {
    return {
      month: 1,
      year: 2023,
      isShow: false,
    }
  },
  setup() {
    const monthList = [
      { id: 1, name: 'Tháng 1' },
      { id: 2, name: 'Tháng 2' },
      { id: 3, name: 'Tháng 3' },
      { id: 4, name: 'Tháng 4' },
      { id: 5, name: 'Tháng 5' },
      { id: 6, name: 'Tháng 6' },
      { id: 7, name: 'Tháng 7' },
      { id: 8, name: 'Tháng 8' },
      { id: 9, name: 'Tháng 9' },
      { id: 10, name: 'Tháng 10' },
      { id: 11, name: 'Tháng 11' },
      { id: 12, name: 'Tháng 12' },
    ]

    return {
      monthList,
    }
  },
  methods: {
    onClickChart() {
      this.isShow = !this.isShow
    },
  },
}
</script>
