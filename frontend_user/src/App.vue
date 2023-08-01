<template>
  <v-app style="width: 100%">
    <AppBar></AppBar>

    <div class="mt-16">
      <router-view></router-view>
      <!-- Messenger Plugin chat Code -->
      <div id="fb-root"></div>

      <!-- Your Plugin chat code -->
      <div id="fb-customer-chat" class="fb-customerchat"></div>
    </div>

    <Footer></Footer>
  </v-app>
</template>

<script>
export default {
  name: "App",

  components: {
    AppBar: () => import("@/components/layout/appbar.vue"),
    Footer: () => import("@/components/layout/footer.vue"),
  },
  data: () => ({
    chatboxId: "fb-customer-chat",
    pageId: "109181652265047",
    attribution: "biz_inbox",
  }),
  mounted() {
    window.fbAsyncInit = function () {
      FB.init({
        xfbml: true,
        version: "v17.0",
      });
    };

    (function (d, s, id) {
      var js,
        fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s);
      js.id = id;
      js.src = "https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js";
      fjs.parentNode.insertBefore(js, fjs);
    })(document, "script", "facebook-jssdk");
  },
};
</script>
