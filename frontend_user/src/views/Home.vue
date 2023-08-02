<template>
  <div>
    <div class="background-img">
      <!-- <b-card class="menu-tab"> -->
      <!-- <b-tabs>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
          <b-tab title="Tour du lịch">
            aaaaaaaaaaaaaa
          </b-tab>
        </b-tabs> -->
      <v-card class="menu-tab" style="border-radius: 10px">
        <v-tabs
          v-model="tab"
          color="white"
          background-color="#99ceff"
          icons-and-text
          style="border-radius: 10px"
        >
          <v-tabs-slider></v-tabs-slider>

          <v-tab href="#tab-1" class="menu-tab-item"> Tour du lịch </v-tab>

          <v-tab href="#tab-2" class="menu-tab-item"> Khách sạn </v-tab>

          <v-tab href="#tab-3" class="menu-tab-item"> Liên hệ </v-tab>

          <v-tab href="#tab-4" class="menu-tab-item"> Tra cứu booking </v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
          <v-tab-item value="tab-1">
            <v-card flat>
              <v-card-title class="justify-center"
                >Tìm kiếm tour phù hợp với bạn</v-card-title
              >
              <v-card-text class="d-flex justify-space-between align-center">
                <div class="filter-box">
                  <v-autocomplete
                    prepend-icon="mdi-map-marker-outline"
                    label="Điểm đi"
                    :items="places"
                    v-model="startPlace"
                    item-value="name"
                    item-text="name"
                  >
                  </v-autocomplete>
                </div>

                <v-icon large>mdi-swap-horizontal</v-icon>

                <div class="filter-box ml-4">
                  <v-autocomplete
                    prepend-icon="mdi-map-marker-outline"
                    label="Điểm đến"
                    :items="places"
                    v-model="endPlace"
                    item-value="name"
                    item-text="name"
                  >
                  </v-autocomplete>
                </div>

                <div class="filter-box">
                  <!-- <v-text-field type="date" label="Ngày đi"></v-text-field> -->
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :return-value.sync="startDate"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="startDate"
                        label="Ngày đi"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="startDate" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu = false">
                        Cancel
                      </v-btn>
                      <v-btn
                        text
                        color="primary"
                        @click="$refs.menu.save(startDate)"
                      >
                        OK
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </div>

                <div class="filter-box">
                  <v-select
                    prepend-icon="mdi-calendar"
                    label="Số ngày"
                    :items="periods"
                    v-model="numOfDay"
                  >
                  </v-select>
                </div>
                <v-btn
                  large
                  color="#2d4271"
                  @click="clickSearch"
                  to="/category"
                >
                  <v-icon large color="yellow">mdi-arrow-right</v-icon>
                </v-btn>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item value="tab-2">
            <v-card flat>
              <v-card-text> Tính năng đang phát triển </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
      <!-- </b-card> -->
    </div>

    <v-row class="mt-16">
      <v-col cols="12" lg="12">
        <div>
          <div class="pt-16 mt-16">
            <h2 class="text-h4 font-weight-bold pb-4 text-center">Ưu đãi</h2>
            <v-sheet class="mx-auto" elevation="8" max-width="100%">
              <v-slide-group v-model="model" active-class="success">
                <v-slide-item v-for="n in placeAds" :key="n.id">
                  <v-img
                    height="400"
                    width="500"
                    :src="n.imageUrl"
                    style="cursor: pointer; margin-left: 20px"
                  ></v-img>
                </v-slide-item>
              </v-slide-group>
            </v-sheet>
          </div>

          <div>
            <v-row class="mt-16">
              <v-col cols="12" lg="12">
                <div>
                  <div class="mt-16">
                    <h2 class="text-h4 font-weight-bold pb-4 text-center">
                      Tour giảm giá
                    </h2>

                    <v-row>
                      <v-col
                        v-for="(item, i) in toursDiscount"
                        :key="i"
                        cols="12"
                        lg="4"
                        md="6"
                      >
                        <v-hover
                          v-slot:default="{ hover }"
                          close-delay="50"
                          open-delay="50"
                        >
                          <div>
                            <v-card
                              :color="hover ? 'white' : 'transparent'"
                              :elevation="hover ? 12 : 0"
                              flat
                              hover
                              :to="{ name: 'Detail', params: { id: item.id } }"
                              min-height="500px"
                            >
                              <v-img
                                :aspect-ratio="16 / 9"
                                class="elevation-2"
                                gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                                height="200px"
                                :src="item.mainImageUrl"
                                style="border-radius: 5px"
                              >
                                <div class="d-flex">
                                  <v-spacer></v-spacer>
                                  <p
                                    style="
                                      background-color: red;
                                      font-weight: bold;
                                      text-align: center;
                                      color: white;
                                      width: 90px;
                                      border-radius: 5px;
                                    "
                                  >
                                    Giảm {{ item.discount }}%
                                  </p>
                                </div>
                                <!-- <v-card-text>
                          <v-btn color="accent" to="category">TIPS</v-btn>
                        </v-card-text> -->
                              </v-img>

                              <v-card-text>
                                <div class="text-body-1">
                                  Ngày khởi hành:
                                  {{ formatDate(item.startTime) }} -
                                  {{ item.period }}N{{ item.period - 1 }}D - Giờ
                                  đi: {{ formatHour(item.startTime) }}
                                </div>
                                <div
                                  class="text-h6 font-weight-bold black--text"
                                >
                                  {{ item.name }}
                                </div>

                                <div class="text-body-1 pt-5 black--text">
                                  Nơi khởi hành: {{ item.startPlaceName }}
                                </div>
                                <div class="text-body-1 black--text">
                                  Giá:
                                  <span class="text-decoration-line-through">{{
                                    formatCurrency(item.price)
                                  }}</span>
                                </div>
                                <div
                                  class="text-body-1 red--text font-weight-bold"
                                >
                                  {{
                                    formatCurrency(
                                      item.price * (1 - item.discount / 100)
                                    )
                                  }}
                                </div>

                                <div
                                  id="countdown"
                                  class="text-body-1 text-center py-2 indigo--text"
                                  style="
                                    border: 1px solid blue;
                                    border-radius: 5px;
                                  "
                                >
                                  Khuyến mãi còn:
                                  {{ calRemainingTime(item.endDateDiscount) }}
                                </div>
                                <div
                                  class="text-body-1 text-right py-2 black--text"
                                >
                                  <span class="text-decoration-underline"
                                    >Số chỗ còn:</span
                                  >
                                  <span class="red--text">
                                    {{ item.placeOrderMax }}</span
                                  >
                                </div>

                                <!-- <div class="d-flex align-center">
                          <v-avatar color="accent" size="36">
                            <v-icon dark>mdi-feather</v-icon>
                          </v-avatar>

                          <div class="pl-2">Yan Lee · 22 July 2019</div>
                        </div> -->
                              </v-card-text>
                            </v-card>
                          </div>
                        </v-hover>
                      </v-col>
                    </v-row>
                  </div>

                  <div class="pt-16" :style="{ 'margin-bottom': '100px' }">
                    <h2 class="text-h4 font-weight-bold pb-4 text-center">
                      Gợi ý cho bạn
                    </h2>
                    <v-row>
                      <v-col
                        v-for="(item, i) in tours"
                        :key="i"
                        cols="12"
                        lg="4"
                        md="6"
                      >
                        <v-hover
                          v-slot:default="{ hover }"
                          close-delay="50"
                          open-delay="50"
                        >
                          <div>
                            <v-card
                              :color="hover ? 'white' : 'transparent'"
                              :elevation="hover ? 12 : 0"
                              flat
                              hover
                              :to="{ name: 'Detail', params: { id: item.id } }"
                            >
                              <v-img
                                :aspect-ratio="16 / 9"
                                class="elevation-2"
                                gradient="to top, rgba(25,32,72,.4), rgba(25,32,72,.0)"
                                height="200px"
                                :src="item.mainImageUrl"
                                style="border-radius: 5px"
                              >
                              </v-img>

                              <v-card-text>
                                <div
                                  class="text-h6 font-weight-bold black--text"
                                >
                                  {{ item.name }}
                                </div>

                                <div class="text-body-1 pt-5 black--text">
                                  Nơi khởi hành: {{ item.startPlaceName }}
                                </div>
                                <div class="text-body-1 black--text">
                                  Giá:
                                  {{ formatCurrency(item.price) }}
                                </div>

                                <div
                                  class="text-body-1 text-center py-5 indigo--text"
                                ></div>
                                <div class="text-body-1 text-right black--text">
                                  <span class="text-decoration-underline"
                                    >Số chỗ còn:</span
                                  >
                                  <span class="red--text">{{
                                    item.placeOrderMax
                                  }}</span>
                                </div>
                              </v-card-text>
                            </v-card>
                          </div>
                        </v-hover>
                      </v-col>
                    </v-row>
                  </div>
                </div>
              </v-col>

              <!-- <v-col>
        <div class="pt-16">
          <siderbar />
        </div>
      </v-col> -->
            </v-row>
          </div>
        </div>
      </v-col>

      <!-- <v-col>
        <div class="pt-16">
          <siderbar />
        </div>
      </v-col> -->
    </v-row>
  </div>
</template>

<script>
import moment from "moment";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "Home",
  components: {
    siderbar: () => import("@/components/details/sidebar"),
  },
  data() {
    return {
      tab: null,
      menu: false,
      // places: [
      //   { value: 1, text: "Hà Nội" },
      //   { value: 2, text: "TPHCM" },
      // ],
      periods: [
        { value: 1, text: "1-3 ngày" },
        { value: 2, text: "4-7 ngày" },
        { value: 3, text: "8-14 ngày" },
        { value: 4, text: "Trên 14 ngày" },
      ],
      startPlace: "",
      endPlace: "",
      startDate: "",
      numOfDay: null,
    };
  },

  computed: {
    ...mapGetters({
      places: "place/getPlaces",
      toursDiscount: "tourList/getToursDiscount",
      toursRecommend: "tourList/getToursRecommend",
      placeAds: "tourList/getPlaceAdvertisment",
      tours: "tourList/getTours",
      objSearch: "tourList/getObjSearch",
    }),
    priceDiscount() {},
  },

  created() {
    this.getPlaces();
    this.getToursDiscount();
    this.getPlaceAds();
    this.getTours(this.objSearch);
  },

  methods: {
    ...mapActions({
      setObjSearch: "tourList/setObjSearch",
      getPlaces: "place/getAll",
      getToursDiscount: "tourList/getToursDiscount",
      getToursRecommend: "tourList/getToursRecommend",
      getPlaceAds: "tourList/getPlaceAdvertisment",
      getTours: "tourList/getTours",
    }),

    formatDate(date) {
      var d = new Date(date),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;

      return [day, month, year].join("/");
    },

    formatHour(date) {
      var d = new Date(date),
        hour = d.getHours(),
        minute = d.getMinutes();

      if (hour.length < 2) month = "0" + month;
      if (minute.length < 2) day = "0" + day;

      return [hour, minute].join(":");
    },
    clickSearch() {
      var fromPeriod;
      var toPeriod;
      switch (this.numOfDay) {
        case 1:
          fromPeriod = 1;
          toPeriod = 3;
          break;
        case 2:
          fromPeriod = 4;
          toPeriod = 7;
          break;
        case 3:
          fromPeriod = 8;
          toPeriod = 14;
          break;
        case 4:
          fromPeriod = 15;
          toPeriod = "";
          break;

        default:
          fromPeriod = "";
          toPeriod = "";
          break;
      }
      const obj = {
        startPlaceName: this.startPlace,
        endPlaceName: this.endPlace,
        startTime: this.startDate,
        period: this.numOfDay,
        fromPeriod: fromPeriod,
        toPeriod: toPeriod,
        fromPrice: "",
        toPrice: "",
      };
      console.log(obj);
      this.setObjSearch(obj);
    },

    calRemainingTime(date) {
      console.log(date);
      var a = moment();
      var b = moment(new Date(date));
      var diff = moment.duration(b.diff(a));
      var day = diff.days();
      var days = "";
      var minute = "";
      var second = "";
      if (day.length < 2) days = "0" + day;
      if (diff.minutes().length < 2) minute = "0" + diff.minutes();
      if (diff.seconds().length < 2) second += "0" + diff.seconds();
      return (
        day +
        " ngày " +
        diff.hours() +
        ":" +
        diff.minutes() +
        ":" +
        diff.seconds()
      );
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
<style>
.menu-tab {
  width: 90%;
  position: absolute;
  top: calc(100% - 72px);

  color: white !important;
}
.menu-tab-item {
  color: white !important;
}
.menu-tab-item:hover {
  background-color: #fff;
  color: #000 !important;
}
.background-img {
  animation: changeBg 60s infinite;
  height: 400px;
  position: relative;
  display: flex;
  justify-content: center;
  background-image: url("src/images/poster1.jpg");
}

@keyframes changeBg {
  0%,
  100% {
    background-image: url("src/images/poster1.jpg");
  }
  33% {
    background-image: url("src/images/poster2.png");
  }
  66% {
    background-image: url("src/images/poster3.jpg");
  }
}

.filter-box {
  border: 5px solid #ffc709;
  border-radius: 5px;
  padding: 0.5em;
  margin-right: 2em;
}
</style>
