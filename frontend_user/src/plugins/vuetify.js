import Vue from "vue";
import Vuetify from "vuetify/lib";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    options: {
      customProperties: true,
    },
    themes: {
      light: {
        primary: "#6666ff",
        secondary: "#818383",
        accent: "#2178ff",
      },
    },
  },
});
