<template>
  <div>
    <CRow>
      <CCol>
        <el-form-item label="Mã Tour">
          <el-input type="text" v-model="objectSearch.tourCode" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Tên Tour">
          <el-input type="text" v-model="objectSearch.tourName" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Trạng thái đơn hàng">
          <el-select type="text" v-model="objectSearch.orderStatus" />
        </el-form-item>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <el-form-item label="Từ ngày">
          <el-input type="date" v-model="objectSearch.fromDate" />
        </el-form-item>
      </CCol>
      <CCol>
        <el-form-item label="Đến ngày">
          <el-input type="date" v-model="objectSearch.toDate" />
        </el-form-item>
      </CCol>
      <CCol> </CCol>
    </CRow>
    <Crow>
      <CCol class="d-flex justify-content-center" style="margin-bottom: 15px">
        <el-button @click="handleSearchClick" type="primary"
          >Tìm kiếm</el-button
        >
      </CCol>
    </Crow>
    <el-table
      :data="displayOrdersAllData"
      stripe
      border
      style="width: 100%"
      v-loading="loading"
      :summary-method="getSummaries"
      show-summary
    >
      <el-table-column type="index" fixed width="30" />
      <el-table-column prop="fullname" label="Họ và tên" width="200" />
      <el-table-column prop="email" label="Email" width="250" />
      <el-table-column prop="phoneNumber" label="Số điện thoại" width="150" />
      <el-table-column prop="address" label="Địa chỉ" width="250" />
      <el-table-column prop="createdDate" label="Ngày đặt đơn" width="150">
        <template #default="scope">
          {{ getFormattedDate(new Date(scope.row.createdDate)) }}
        </template>
      </el-table-column>
      <el-table-column prop="sumPrice" label="Tổng đơn" width="150" />
      <el-table-column prop="status" label="Trạng thái" width="200">
        <template #default="scope">
          <el-tag :key="tag" :type="success" effect="plain">
            {{ convertStatus(scope.row.status) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Xem chi tiết" width="120">
        <template #default="scope">
          <el-button
            link
            type="primary"
            size="small"
            @click="handleUpdateClick(scope.row)"
            >Chi tiết</el-button
          >
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="Thao tác" min-width="250px">
        <template #default="scope">
          <el-button
            v-if="scope.row.status == 1 || scope.row.status == 2"
            type="success"
            size="small"
            >Xác nhận thanh toán
          </el-button>

          <el-button
            v-else-if="scope.row.status == 3"
            link
            type="warning"
            size="small"
            >Gửi hợp đồng</el-button
          >
          <el-button link type="danger" size="small">Hủy đơn</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:currentPage="currentPage2"
      v-model:page-size="pageSize2"
      :page-sizes="[10, 20, 50, 100]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="sizes, prev, pager, next"
      :total="displayOrdersAllData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right"
    />
    <el-dialog v-model="dialogDetailVisible" title="Chi tiết đơn đặt tour">
      <OrderDetail
        :dataOrderDetail="guideUpdate"
        @close-dialog="closeDialog"
      ></OrderDetail>
    </el-dialog>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import OrderDetail from './OrderDetail.vue'
export default {
  name: 'Quản lý đơn đặt tour',
  components: { OrderDetail },
  data() {
    return {
      currentPage2: 1,
      pageSize2: 10,
      dialogDetailVisible: false,
      objectSearch: {
        tourCode: null,
        tourName: null,
        orderStatus: null,
        fromDate: null,
        toDate: null,
      },
      guideUpdate: {},
    }
  },
  computed: {
    ...mapGetters({
      getAllOrdersList: 'order/getAllOrdersList',
      authentication: 'user/getAuthentication',
    }),
    displayOrdersAllData() {
      if (!this.getAllOrdersList || this.getAllOrdersList.length === 0)
        return []
      return this.getAllOrdersList.slice(
        this.pageSize2 * this.currentPage2 - this.pageSize2,
        this.pageSize2 * this.currentPage2,
      )
    },
  },
  created() {
    if (this.authentication == true) {
      this.actionAllOrdersList(this.objectSearch)
    } else {
      this.$router.push({ name: 'Login' })
    }
  },
  methods: {
    ...mapActions({
      actionAllOrdersList: 'order/actionAllOrdersList',
      actionTourPriceById: 'tour/actionGetTourPrice1TourList',
      actionGetDiscountTour: 'tour/actionGetDiscountTour',
    }),
    handleAddClick() {},
    handleSearchClick() {
      console.log(this.objectSearch)
      var fromDate = this.objectSearch.fromDate
      var toDate = this.objectSearch.toDate
      if (this.objectSearch.fromDate != null) {
        this.objectSearch.fromDate = this.getFormattedDateForOrder(
          new Date(this.objectSearch.fromDate),
        )
      }
      if (this.objectSearch.toDate != null) {
        this.objectSearch.toDate = this.getFormattedDateForOrder(
          new Date(this.objectSearch.toDate),
        )
      }
      this.actionAllOrdersList(this.objectSearch)
      this.objectSearch.fromDate = fromDate
      this.objectSearch.toDate = toDate
    },
    convertStatus(id) {
      if (id == 1) {
        return 'Đang thanh toán'
      } else if (id == 2) {
        return 'Đã thanh toán'
      } else if (id == 3) {
        return 'Đã xác nhận thanh toán'
      } else if (id == 4) {
        return 'Đã gửi hợp đồng'
      } else if (id == 5) {
        return 'Hủy'
      } else return 'Chưa thanh toán'
    },
    getFormattedDateForOrder(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return year + '/' + month + '/' + day
    },
    getFormattedDate(date) {
      let year = date.getFullYear()
      let month = (1 + date.getMonth()).toString().padStart(2, '0')
      let day = date.getDate().toString().padStart(2, '0')

      return day + '/' + month + '/' + year
    },
    handleUpdateClick(data) {
      this.dialogDetailVisible = true
      this.guideUpdate = data
      this.actionTourPriceById(data.priceId)
      this.actionGetDiscountTour(data.priceId)
    },
    handleCurrentChange(val) {
      this.currentPage2 = val
    },
    handleSizeChange(val) {
      this.pageSize2 = val
    },
    closeDialog(e) {
      this.dialogDetailVisible = false
      console.log(e)
    },
    getSummaries(param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 1) {
          sums[index] = 'Tổng tiền'
          return
        }
        const values = data.map((item) => Number(item[column.property]))
        if (!values.every((value) => isNaN(value)) && index == 6) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
        } else {
          sums[index] = ''
        }
      })

      return sums
    },
  },
}
</script>
