<template>
  <div>
    <v-card color="#f9f9f9">
      <v-card-title class="d-flex justify-space-between">
        <div class="text-h5 font-weight-bold">{{ tour.name }}</div>
        <div class="d-flex flex-column">
          <v-btn
            color="error"
            width="160px"
            :to="{ name: 'Order', params: { id: tour.id } }"
            >Đặt ngay</v-btn
          >
          <v-btn class="mt-4" width="160px">Liên hệ tư vấn</v-btn>
        </div>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col>
            <v-img
              :src="tour.image100"
              :aspect-ratio="16 / 9"
              gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
              style="border-radius: 5px"
              height="600px"
            ></v-img>
          </v-col>
          <v-col>
            <v-row>
              <v-img
                :src="tour.image50"
                :aspect-ratio="16 / 9"
                gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                style="border-radius: 5px"
                height="300px"
              ></v-img
            ></v-row>
            <v-row>
              <v-col>
                <v-img
                  :src="tour.image251"
                  :aspect-ratio="16 / 9"
                  gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                  style="border-radius: 5px"
                  height="300px"
                ></v-img
              ></v-col>
              <v-col>
                <v-img
                  :src="tour.image252"
                  :aspect-ratio="16 / 9"
                  gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                  style="border-radius: 5px"
                  height="300px"
                ></v-img
              ></v-col>
            </v-row>
          </v-col>
        </v-row>
        <v-row>
          <v-card
            height="400px"
            class="overflow-auto"
            v-if="tour.shortDesc != ''"
          >
            <div
              v-for="(item2, index2) in splitText(tour.shortDesc)"
              :key="index2"
            >
              <div v-html="item2"></div>
            </div>
          </v-card>
        </v-row>
        <v-row>
          <v-col>
            <v-card height="300px">
              <v-card-text class="black--text">
                <p>
                  Khởi hành:
                  <span class="font-weight-bold">{{
                    formatDate(tour.startTime)
                  }}</span>
                </p>
                <p>
                  Thời gian:
                  <span class="font-weight-bold">{{ tour.period }} ngày</span>
                </p>
                <p>
                  Nơi khởi hành:
                  <span class="font-weight-bold">{{
                    tour.startPlaceName
                  }}</span>
                </p>
                <p>
                  Số chỗ còn nhận:
                  <span class="font-weight-bold">{{ tour.placeOrderMax }}</span>
                </p>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col>
            <v-card height="300px">
              <v-card-title>Địa điểm tham quan</v-card-title>
              <v-card-text>
                <li v-for="(item, index) in locations" :key="index">
                  {{ item }}
                </li>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>

    <h2 class="mt-16 text-center">Lịch trình</h2>
    <v-card>
      <v-row>
        <v-col lg="4" style="background-color: #f9f9f9">
          <v-timeline dense>
            <v-card-text
              class="mb-10"
              v-for="(item, index) in tour.tourScheduleList"
              :key="index"
              ><v-timeline-item dot-color="teal-lighten-3">
                <small>{{ formatDate(item.time) }}</small>
                <div style="height: 5px"></div>
                {{ item.alias }}</v-timeline-item
              >
            </v-card-text></v-timeline
          >

          <!-- <div class="d-flex justify-space-between">
              <div>{{ item.alias }}</div>
              <div>{{ formatDate(item.time) }}</div>
            </div>

            <div>{{ item.location }}</div> -->
        </v-col>
        <v-col>
          <v-card-text
            v-for="(item, index) in tour.tourScheduleList"
            :key="index"
          >
            <h4>
              <li>{{ item.alias }}</li>
            </h4>

            <div
              v-for="(item2, index2) in splitText(item.detail)"
              :key="index2"
            >
              <div v-html="item2"></div>
            </div>
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>

    <h2 class="mt-16 text-center">Thông tin tour</h2>

    <v-row>
      <v-col>
        <v-card>
          <v-card-title class="justify-center"
            >Thông tin tập trung</v-card-title
          >
          <v-card-text>
            <div>Ngày giờ tập trung:</div>
            <div>Nơi tập trung:</div>
          </v-card-text>
        </v-card>
        <v-card class="mt-4">
          <v-card-title class="justify-center"
            >Thông tin hướng dẫn viên</v-card-title
          >
          <!-- <v-card-text>
            <div>HDV dẫn đoàn: {{ tour.guideName }}</div>
            <div>HDV tiễn: {{ tour.guideName }}</div>
          </v-card-text> -->
        </v-card>
      </v-col>
      <v-col>
        <v-card>
          <v-card-title class="justify-center"
            >Giá tour và Phụ thu</v-card-title
          >
          <v-card-text>
            <v-simple-table>
              <thead>
                <tr class="font-weight-bold">
                  <th class="text-left">Loại khách</th>
                  <th class="text-center">Giá tour</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Người lớn (Từ 12 tuổi trở lên)</td>
                  <td class="red--text text-right">
                    {{ formatCurrency(tour.tourPrice.adult) }}
                  </td>
                </tr>
                <tr>
                  <td>Trẻ em (Từ 5 - 11 tuổi)</td>
                  <td class="red--text text-right">
                    {{ formatCurrency(tour.tourPrice.children) }}
                  </td>
                </tr>
                <tr>
                  <td>Trẻ nhỏ (Dưới 4 tuổi)</td>
                  <td class="red--text text-right">
                    {{ formatCurrency(tour.tourPrice.kid) }}
                  </td>
                </tr>

                <tr class="font-weight-bold">
                  <td class="font-weight-bold">Phụ thu phòng đơn</td>
                  <td class="red--text text-right">
                    {{ formatCurrency(tour.tourPrice.surcharge) }}
                  </td>
                </tr>
              </tbody>
            </v-simple-table>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "Category",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  mounted() {
    // const route = useRoute();
    // console.log("route", route.params.id)
    console.log(this.$route.params.id);
  },
  computed: {
    ...mapGetters({
      tour: "tourList/getTour",
    }),
  },
  data() {
    return {
      locations: [],
    };
  },
  created() {
    this.getTour(this.$route.params.id);
  },
  methods: {
    ...mapActions({
      getTour: "tourList/getTour",
    }),
    splitText(para) {
      if (para != null) {
        const a = para.split("\\n");
        console.log(a);
        return a;
      }
      return [];
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
