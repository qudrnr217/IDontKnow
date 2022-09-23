import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Carousel3d from "vue-carousel-3d";
Vue.config.productionTip = false;

Vue.use(Carousel3d);
import Flicking from "@egjs/vue-flicking";
import "@egjs/vue-flicking/dist/flicking.css";
// Or, if you have to support IE9
import "@egjs/vue-flicking/dist/flicking-inline.css";

Vue.use(Flicking);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
