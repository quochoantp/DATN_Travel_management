<template>
  <div>
    <v-card class="d-flex">
      <v-img
        :src="tour.mainImageUrl"
        :aspect-ratio="16 / 9"
        gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
        height="300px"
        class="elevation-2"
        style="border-radius: 5px"
        width="35%"
      ></v-img>
      <v-card-text>
        <div class="text-h6 font-weight-bold black--text">
          {{ tour.name }}
        </div>
        <div class="text-body-1 pt-5 black--text">
          Khởi hành:
          <span class="font-weight-bold">{{ formatDate(tour.startTime) }}</span>
        </div>
        <div class="text-body-1 black--text">
          Nơi khởi hành:
          <span class="font-weight-bold">{{ tour.startPlaceName }}</span>
        </div>
        <div class="text-body-1 black--text">
          Thời gian: <span class="font-weight-bold">{{ tour.period }}</span>
        </div>
        <div class="text-body-1 black--text">
          <span>Số chỗ còn:</span>
          <span class="font-weight-bold">{{ tour.placeOrderMax }}</span>
        </div>
      </v-card-text>
    </v-card>

    <h2 class="font-weight-bold mt-16">Tổng quan về chuyến đi</h2>
    <v-row>
      <v-col lg="8">
        <h4>Thông tin liên lạc</h4>
        <v-card>
          <v-card-text class="black--text">
            <v-row>
              <v-col>
                <h6>Họ và tên <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.fullname"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Email <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.email"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <h6>Ngày sinh <span class="red--text">*</span></h6>
                <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="order.dob"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="order.dob"
                      v-bind="attrs"
                      v-on="on"
                      outlined
                      dense
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="order.dob"
                    no-title
                    scrollable
                    @change="$refs.menu.save(order.dob)"
                  >
                  </v-date-picker>
                </v-menu>
              </v-col>
              <v-col>
                <h6>Số CMND/CCCD <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.identityCard"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <h6>Số điện thoại <span class="red--text">*</span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.phoneNumber"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Địa chỉ</h6>
                <v-text-field
                  outlined
                  dense
                  v-model="order.address"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <h4 class="mt-8">Số lượng hành khách</h4>
        <v-card>
          <v-card-text class="black--text">
            <v-row>
              <v-col>
                <h6>
                  Người lớn (Từ 12 tuổi trở lên) <span class="red--text"></span>
                </h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.adultCount"
                  @input="onChangeTourist"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Trẻ em (Từ 5 - 11 tuổi) <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.childrenCount"
                  @input="onChangeTourist"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Trẻ nhỏ (Dưới 4 tuổi) <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  type="number"
                  v-model="order.kidCount"
                  @input="onChangeTourist"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <h4 class="mt-8">Thông tin hành khách</h4>
        <v-card>
          <v-card-text class="black--text">
            <v-row v-for="(item, index) in touristList" :key="index">
              <v-col>
                <h6>Họ và tên <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  v-model="item.fullName"
                ></v-text-field>
              </v-col>
              <v-col>
                <h6>Giới tính <span class="red--text"></span></h6>
                <v-select dense outlined v-model="item.gender" :items="gender">
                </v-select>
              </v-col>
              <v-col>
                <h6>Ngày sinh <span class="red--text"></span></h6>
                <v-text-field
                  outlined
                  dense
                  type="date"
                  v-model="item.dob"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col>
        <h4>Tóm tắt chuyến đi</h4>
        <v-card>
          <v-card-text class="black--text">
            <h6>Tour trọn gói</h6>
            <v-row>
              <v-col lg="4">
                <v-img
                  :src="tour.mainImageUrl"
                  :aspect-ratio="16 / 9"
                  gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                  height="70px"
                  class="elevation-2"
                  style="border-radius: 5px"
                ></v-img>
              </v-col>
              <v-col>
                <div class="font-weight-bold black--text">
                  {{ tour.name }}
                </div>
              </v-col>
            </v-row>
            <div class="d-flex mt-4">
              <v-icon>mdi-calendar</v-icon>
              <div class="ml-2">
                <div class="black--text">Bắt đầu chuyến đi</div>
                <div class="black--text font-weight-bold">
                  {{ formatDate(tour.startTime) }}
                </div>
              </div>
            </div>

            <div class="d-flex mt-4">
              <v-icon>mdi-calendar</v-icon>
              <div class="ml-2">
                <div class="black--text">Kết thúc chuyến đi</div>
                <div class="black--text font-weight-bold">
                  {{ endTime }}
                </div>
              </div>
            </div>

            <div class="d-flex justify-space-between mt-6">
              <p class="font-weight-bold">Hành khách</p>
              <p class="font-weight-bold">2 người</p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Người lớn</p>
              <p class="font-weight-bold">
                {{ order.adultCount }} x
                {{ formatCurrency(tour.tourPrice.adult) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Trẻ em</p>
              <p class="font-weight-bold">
                {{ order.childrenCount }} x
                {{ formatCurrency(tour.tourPrice.children) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Trẻ nhỏ</p>
              <p class="font-weight-bold">
                {{ order.kidCount }} x {{ formatCurrency(tour.tourPrice.kid) }}
              </p>
            </div>

            <div class="d-flex justify-space-between">
              <p>Phụ thu phòng riêng</p>
              <p class="font-weight-bold">
                {{ formatCurrency(tour.tourPrice.surcharge) }}
              </p>
            </div>
            <div class="d-flex justify-space-between">
              <p>Phụ thu phòng riêng</p>
              <p class="font-weight-bold">
                {{ formatCurrency(tour.tourPrice.surcharge) }}
              </p>
            </div>
            <div
              class="d-flex justify-space-between"
              v-if="tour.discount != null"
            >
              <p>Giảm giá</p>
              <p class="font-weight-bold" style="text-color: red">
                - {{ tour.discount }} %
              </p>
            </div>
            <v-divider></v-divider>
            <div class="d-flex justify-space-between">
              <p class="text-h6">Tổng cộng</p>
              <p class="text-h6 red--text">{{ formatCurrency(sumPrice) }}</p>
            </div>
            <v-radio-group v-model="payOnline" column>
              <v-radio
                label="Thanh toán tại công ty Lửa Việt tour"
                value="false"
              ></v-radio>
              <v-radio
                label="Thanh toán online qua cổng VNPay"
                value="true"
              ></v-radio>
            </v-radio-group>
            <v-btn color="error" width="100%" large @click="bookTour"
              >Đặt ngay</v-btn
            >
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import moment from "moment";
import { mapActions, mapGetters } from "vuex";
import axios from "axios";
export default {
  name: "Order",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  data() {
    return {
      menu: false,
      menu1: false,
      payOnline: false,
      touristList: [],
      gender: [
        { value: "Nam", text: "Nam" },
        { value: "Nữ", text: "Nữ" },
      ],
      response: {
        isPaymentOnline: "",
        orderInfoDTO: {},
        touristDTOList: [],
      },
      order: {
        userId: localStorage.getItem("userId"),
        fullname: "",
        email: "",
        dob: "",
        identityCard: "",
        phoneNumber: "",
        address: "",
        adultCount: 1,
        childrenCount: 0,
        kidCount: 0,
        sumPrice: this.sumPrice,
      },
    };
  },
  computed: {
    ...mapGetters({
      tour: "tourList/getTour",
      authentication: "user/getAuthentication",
      user: "user/getUser",
    }),
    endTime() {
      return moment(this.tour.startTime)
        .add(this.tour.period, "days")
        .format("DD-MM-yyyy");
    },
    sumPrice() {
      if (this.tour.discount == null) {
        return (
          this.order.adultCount * this.tour.tourPrice.adult +
          this.order.childrenCount * this.tour.tourPrice.children +
          this.order.kidCount * this.tour.tourPrice.kid
        );
      } else {
        var sum =
          this.order.adultCount * this.tour.tourPrice.adult +
          this.order.childrenCount * this.tour.tourPrice.children +
          this.order.kidCount * this.tour.tourPrice.kid;
        return sum - (this.tour.discount * sum) / 100;
      }
    },
  },

  destroyed() {
    document.title = "Lửa Việt Tours";
  },
  mounted() {
    if (this.authentication == true) {
      this.getUser(localStorage.getItem("username"));
      this.order.fullname = this.user.fullname;
      this.order.phoneNumber = this.user.phoneNumber;
      this.order.identityCard = this.user.identityCard;
      this.order.email = this.user.email;
      this.order.address = this.user.address;
      let date = new Date(this.user.dob);
      let yyyy = date.getFullYear();
      let mm = date.getMonth() + 1; // Months start at 0!
      let dd = date.getDate();
      if (dd < 10) dd = "0" + dd;
      if (mm < 10) mm = "0" + mm;
      this.order.dob = yyyy + "-" + mm + "-" + dd;
      this.touristList.push({
        fullName: "",
        gender: "",
        dob: "",
      });
    }
  },

  created() {
    this.getTour(this.$route.params.id);
    if (this.authentication == true) {
      this.getUser(localStorage.getItem("username"));
    }
    document.title = "Đặt tour";
  },
  methods: {
    ...mapActions({
      getTour: "tourList/getTour",
      orderTour: "order/bookTour",
      getUser: "user/getUser",
    }),
    onChangeTourist() {
      this.touristList = [];
      let amountTourist =
        Number(this.order.adultCount) +
        Number(this.order.childrenCount) +
        Number(this.order.kidCount);
      console.log(this.touristList);
      for (let i = 0; i < amountTourist; i++) {
        let tourist = {
          fullName: "",
          gender: "",
          dob: "",
        };
        this.touristList.push(tourist);
      }
    },
    redirectToURL(url) {
      window.location.replace(url);
    },

    async bookTour() {
      this.order.adultCount = this.order.adultCount.toString();
      this.order.childrenCount = this.order.childrenCount.toString();
      this.order.kidCount = this.order.kidCount.toString();
      console.log(this.tour);
      this.order = {
        ...this.order,
        priceId: this.tour.priceId,
        sumPrice: this.sumPrice,
      };
      this.response.isPaymentOnline = this.payOnline;
      this.response.orderInfoDTO = this.order;
      this.response.touristDTOList = this.touristList;
      const res = await this.orderTour(this.response);
      var item = {
        ordertype: res.data.id.toString(),
        amount: this.sumPrice.toString(),
      };
      console.log(res);
      console.log(item);
      if (res.status == 201) {
        const str = await axios.get(
          `http://localhost:8091/api/v1/payment/payvn?amount=${this.sumPrice}&ordertype=${res.data.id}`,
          item
        );
        console.log(str);
        window.location = str.data;
        // alert(
        //   "Đặt tour thành công. Thông tin chi tiết sẽ được gửi đến email của quý khách!"
        // );
      }
    },

    formatDate(strDate) {
      const date = new Date(strDate);
      const yyyy = date.getFullYear();
      let mm = date.getMonth() + 1; // Months start at 0!
      let dd = date.getDate();
      if (dd < 10) dd = "0" + dd;
      if (mm < 10) mm = "0" + mm;
      return dd + "-" + mm + "-" + yyyy;
    },
    formatCurrency(currency) {
      return new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
      }).format(currency);
    },
  },
};
</script>

<style lang="scss" scoped>
.authors {
  position: relative;
  top: -50px;
  margin-bottom: -50px;
}
</style>
