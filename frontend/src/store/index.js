import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import homeStore from "@/store/modules/homeStore.js";
import userStore from "@/store/modules/userStore.js";

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    homeStore,
    userStore,
  },
  plugins: [
    createPersistedState({
      paths: ["userStore"],
    }),
  ],
});
