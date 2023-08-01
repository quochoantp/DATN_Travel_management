<template>
  <div>
    <v-navigation-drawer
      v-if="!$vuetify.breakpoint.smAndUp"
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
      color="primary"
      dark
    >
      <v-list color="primary" nav>
        <v-list-item
          v-for="(item, i) in btnItems"
          :key="i"
          :href="item.href"
          :target="item.target"
          :to="item.to"
          link
        >
        </v-list-item>
        <v-list-item
          v-for="(item, i) in barItems"
          :key="i"
          :href="item.href"
          :target="item.target"
          :to="item.to"
          link
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="white"
      elevate-on-scroll
      flat
    >
      <v-row
        :no-gutters="!$vuetify.breakpoint.smAndUp"
        align="center"
        justify="space-between"
      >
        <v-col class="d-flex align-center">
          <span class="accent--text"
            ><img
              src="https://drive.google.com/uc?export=view&id=1Ely9GEMNQMwPQylEQufPRPrq9Vcv7Gn3"
              alt="Lua viet"
          /></span>
          <v-app-bar-nav-icon
            v-if="!$vuetify.breakpoint.mdAndUp"
            @click.stop="drawer = !drawer"
          />
          <v-toolbar-title
            class="font-weight-bold text-h5 primary--text"
            style="cursor: pointer"
            @click="$router.push('/')"
          >
            <!-- <v-icon color="primary" large>mdi-feather</v-icon> -->
          </v-toolbar-title>
        </v-col>

        <v-col v-if="$vuetify.breakpoint.mdAndUp" cols="6">
          <v-btn
            v-for="(item, i) in barItems"
            :key="i"
            :to="item.to"
            class="text-capitalize"
            exact
            exact-active-class="accent--text"
            text
            >{{ item.title }}
          </v-btn>
          <v-btn
            :to="`/profile`"
            class="text-capitalize"
            v-if="authentication == true"
            exact
            exact-active-class="accent--text"
            text
            >Hồ sơ
          </v-btn>
        </v-col>

        <v-col v-if="$vuetify.breakpoint.mdAndUp" class="text-right">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                v-for="(item, i) in btnItems"
                :key="i"
                :color="item.color"
                :href="item.href"
                :target="item.target"
                :to="item.to"
                class="ml-3 text-capitalize"
                v-bind="attrs"
                v-on="on"
                fab
                outlined
                small
              >
                <v-icon large>{{ item.icon }}</v-icon> {{ item.text }}
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-if="authentication == false" router to="/login"
                >Đăng nhập</v-list-item
              >
              <v-list-item v-if="authentication == false" router to="/register"
                >Đăng ký</v-list-item
              >
              <v-list-item v-if="authentication == true"
                ><v-btn @click="logOut">Đăng xuất</v-btn></v-list-item
              >
            </v-list>
          </v-menu>
        </v-col>
      </v-row>
    </v-app-bar>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data: () => ({
    drawer: null,
    btnItems: [
      {
        color: "",
        icon: "mdi-account",
      },
    ],

    btnMenuItems: [
      {
        title: "Đăng nhập",
        to: "/login",
      },
      {
        title: "Đăng ký",
        to: "/register",
      },
      {
        title: "Đăng xuất",
      },
    ],

    barItems: [
      {
        title: "Du lịch",
        to: "/",
      },
      {
        title: "Danh sách tour",
        to: "/category",
      },
      {
        title: "Khuyến mãi",
        to: "/detail",
      },
    ],
  }),
  computed: {
    ...mapGetters({
      authentication: "user/getAuthentication",
    }),
  },
  methods: {
    ...mapActions({
      setAuthentication: "user/setAuthentication",
    }),
    logOut() {
      this.setAuthentication(false);
      // this.$router.push({ path: '/' })
      this.$router.push({ name: "Login" });
    },
  },
};
</script>
