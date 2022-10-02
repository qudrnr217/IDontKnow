import Vue from "vue";
import Vuex from "vuex";

import createPersistedState from "vuex-persistedstate";

import homeStore from "@/store/modules/homeStore.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    started: 0,
    userId: 1,
  },
  getters: {},
  mutations: {
    UPDATE_STARTED(state, payload) {
      state.started = payload;
    },
  },
  actions: {
    update_started({ commit }, data) {
      commit("UPDATE_STARTED", data);
    },
  },
  modules: {
    homeStore,
  },
  plugins: [
    createPersistedState({
      whiteList: ["started"],
    }),
  ],
});
