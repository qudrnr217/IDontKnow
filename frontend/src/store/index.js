import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import homeStore from "@/store/modules/homeStore.js";
import userStore from "@/store/modules/userStore.js";
import communityStore from "./modules/communityStore";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    started: 0,
    // userId: 1,
  },
  getters: {},
  mutations: {
    UPDATE_STARTED: (state) => {
      state.started = 1;
    },
  },
  actions: {
    update_started: ({ commit }) => {
      return commit("UPDATE_STARTED");
    },
  },
  modules: {
    homeStore,
    userStore,
    communityStore,
  },
  plugins: [
    createPersistedState({
      paths: ["userStore", "started"],
    }),
  ],
});
