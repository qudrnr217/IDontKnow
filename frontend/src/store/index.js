import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import homeStore from "@/store/modules/homeStore.js";
import communityStore from "./modules/communityStore";

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    homeStore,
    communityStore,
  },
});
