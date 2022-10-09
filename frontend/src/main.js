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

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

import VueApexCharts from "vue-apexcharts";
Vue.use(VueApexCharts);

Vue.component("ApexChart", VueApexCharts);
// Vue.component("apexchart", VueApexCharts);

// import VueConfirmDialog from "./components/community/VueComfirmDialog.vue";

// Vue.use(VueConfirmDialog);
// Vue.component("vue-confirm-dialog", VueConfirmDialog.default);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
