import { getHotspots, getBestMenus } from "@/api/home.js";

const homeStore = {
  namespaced: true,
  state: {
    districtId: 0,
    hotspots: {},
    bestMenus: [],
  },

  getters: {},

  mutations: {
    SET_DISTRICT: (state, districtId) => {
      state.districtId = districtId;
    },
    SET_HOTSPOTS: (state, hotspots) => {
      state.hotspots = hotspots;
    },
    SET_BEST_MENUS: (state, bestMenus) => {
      state.bestMenus = bestMenus;
      state.bestMenus.forEach((el) => {
        if (el.menuName.includes("/")) {
          el.menuImgName = el.menuName.replace("/", "_");
        }
      });
    },
  },

  actions: {
    FETCH_HOTSPOTS: ({ commit }, params) => {
      return getHotspots(params, ({ data }) => {
        commit("SET_HOTSPOTS", data);
      });
    },
    FETCH_BEST_MENUS: ({ commit }, params) => {
      return getBestMenus(params, ({ data }) => {
        commit("SET_BEST_MENUS", data);
      });
    },
  },
};

export default homeStore;
