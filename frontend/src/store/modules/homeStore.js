import { getHotspots } from "@/api/home.js";

const homeStore = {
  namespaced: true,
  state: {
    districtId: 0,
    hotspots: {},
  },

  getters: {},

  mutations: {
    SET_DISTRICT: (state, districtId) => {
      state.districtId = districtId;
    },
    SET_HOTSPOTS: (state, hotspots) => {
      state.hotspots = hotspots;
    },
  },

  actions: {
    FETCH_HOTSPOTS: ({ commit }, params) => {
      return getHotspots(params, ({ data }) => {
        commit("SET_HOTSPOTS", data);
      });
    },
  },
};

export default homeStore;
