<template>
  <div>
    <v-card absolute app color="#99ceff" dark flat>
      <v-container>
        <v-row align="start">
          <v-col cols="12" md="5">
            <h4 class="text-h5 font-weight-bold">Du lịch trong nước</h4>

            <v-row
              ><v-col cols="12" md="4">
                <v-subheader class="text-h6">Miền Bắc</v-subheader>
                <v-list-item-group>
                  <v-list-item
                    v-for="(item, i) in mienBac"
                    :key="i"
                    :href="item.href"
                    dense
                    target="_black"
                  >
                    <v-list-item-content>
                      <v-list-item-title
                        class="text-body-1"
                        v-text="item.text"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item> </v-list-item-group
              ></v-col>
              <v-col cols="12" md="4">
                <v-subheader class="text-h6">Miền Trung</v-subheader>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, i) in mienTrung"
                    :key="i"
                    :href="item.href"
                    dense
                    target="_black"
                  >
                    <v-list-item-content>
                      <v-list-item-title
                        class="text-body-1"
                        v-text="item.text"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item> </v-list-item-group
              ></v-col>
              <v-col cols="12" md="4">
                <v-subheader class="text-h6">Miền Nam</v-subheader>
                <v-list-item-group color="primary">
                  <v-list-item
                    v-for="(item, i) in mienNam"
                    :key="i"
                    :href="item.href"
                    dense
                    target="_black"
                  >
                    <v-list-item-content>
                      <v-list-item-title
                        class="text-body-1"
                        v-text="item.text"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item> </v-list-item-group></v-col
            ></v-row>
          </v-col>
          <v-col cols="12" md="1"></v-col>
          <v-col cols="12" md="2">
            <v-subheader class="text-h6">Chứng nhận</v-subheader>
            <v-img
              height="119"
              width="195"
              src="https://www.luavietours.com/assets/img/common/header/certifi.png"
              style="margin: 10px"
            ></v-img>
          </v-col>

          <v-col cols="12" md="4">
            <div style="margin-top: 30px">
              <p>
                Giấy chứng nhận đăng ký kinh doanh số 0301659981 do Sở Kế Hoạch
                và Đầu Tư TPHCM cấp ngày 14/01/1999.
              </p>
              <p class="text-body-1">
                The latest Rocket news, articles, and resources, sent straight
                to your inbox every month.
              </p>
              <p>Đăng ký thay đổi lần thứ: 14, ngày 13 tháng 12 năm 2018.</p>
              <p>
                Số giấy phép kinh doanh lữ hành quốc tế: <br class="pc" />79
                -228 /20 16TCDL - GP LHQT cấp ngày 28/07/2016.
              </p>
            </div>
          </v-col>
          <div style="margin-top: 50px">
            <h5 class="text-h6 font-weight-bold">Liên hệ</h5>
          </div>
          <v-col cols="12" md="4">
            <v-row>
              <v-col cols="12">
                <p>Văn phòng chính:</p>

                <p>677 Trần Hưng Đạo, Phường 1, Quận 5, TP. HCM</p>

                <p>1900 6420</p>

                <p>1900 6420 info@luavietours.com</p>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="12" md="8">
            <v-row>
              <v-col cols="12">
                <p>Văn phòng Hà Nội:</p>

                <p>
                  Tầng 3, Tòa nhà Dolphin Plaza, số 28 Trần Bình, Phường Mỹ Đình
                  2, Quận Nam Từ Liêm, TP. Hà Nội
                </p>

                <p>1900 6420</p>

                <p>1900 6420 info@luavietours.com</p>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    mienBac: [
      {
        text: "Quảng Ninh",
        href: "/",
      },
      {
        text: "Hòa Bình",
        href: "/",
      },
      {
        text: "Sơn La",
        href: "/",
      },
      {
        text: "Điện Biên",
        href: "/",
      },
      {
        text: "Lai Châu",
        href: "/",
      },
    ],
    mienTrung: [
      {
        text: "Pleiku",
        href: "/",
      },
      {
        text: "Đà Nẵng",
        href: "/",
      },
      {
        text: "Huế",
        href: "/",
      },
      {
        text: "Quảng Bình",
        href: "/",
      },
      {
        text: "Đà Lạt",
        href: "/",
      },
    ],
    mienNam: [
      {
        text: "TP Hồ Chí Minh",
        href: "/",
      },
      {
        text: "Côn Đảo",
        href: "/",
      },
      {
        text: "Phú Quốc",
        href: "/",
      },
    ],
    shock: [
      {
        text: "Download",
        href: "https://github.com/AGDholo/giraffe",
      },
      {
        text: "Issues",
        href: "https://github.com/AGDholo/giraffe/issues",
      },
      {
        text: "LICENSE",
        href: "https://github.com/AGDholo/giraffe/blob/master/LICENSE",
      },
    ],
  }),
};
</script>
