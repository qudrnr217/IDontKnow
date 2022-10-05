import { getHotspots, getBestMenus, getTodayWeather } from "@/api/chat.js";

const chatStore = {
  namespaced: true,
  state: {
    bestHotspots: {},
    bestMenus: [],
    bestStyles: [],
    weather: {
      sky: "흐림",
      pty: "강수 없음",
      pop: "30",
      reh: "95",
      tmp: "18",
      wsd: "3.7",
    },
  },

  getters: {
    checkWindSpeed(state) {
      if (state.weather.wsd < 4) {
        return "약한 바람";
      } else if (state.weather.wsd < 9) {
        return "약간 강한 바람";
      } else if (state.weather.wsd < 14) {
        return "강한 바람";
      } else {
        return "매우 강한 바람";
      }
    },
  },

  mutations: {
    SET_BEST_HOTSPOTS: (state, bestHotspots) => {
      state.bestHotspots = bestHotspots;
    },
    SET_BEST_MENUS: (state, bestMenus) => {
      state.bestMenus = bestMenus;
      state.bestMenus.forEach((el) => {
        if (el.menuName.includes("/")) {
          el.menuImgName = el.menuName.replace("/", "_");
        } else {
          el.menuImgName = el.menuName;
        }
      });
    },
    SET_TODAY_WEATHER: (state, weather) => {
      state.weather = weather;
    },
    SET_BEST_STYLES: (state) => {
      if (state.weather.tmp <= 4) {
        state.bestStyles = ["패딩/두꺼운 코트, 누빔 옷/기모, 목도리", "0~4"];
      } else if (state.weather.tmp <= 8) {
        state.bestStyles = ["울 코드, 히트텍, 가죽 옷", "5~8"];
      } else if (state.weather.tmp <= 11) {
        state.bestStyles = ["트렌치 코트, 야상/점퍼, 스타킹, 기모바지", "9~11"];
      } else if (state.weather.tmp <= 16) {
        state.bestStyles = [
          "자켓/가디건, 청자켓, 니트, 스타킹, 청바지",
          "12~16",
        ];
      } else if (state.weather.tmp <= 19) {
        state.bestStyles = ["얇은 가디건/니트, 맨투맨, 후드, 긴바지", "17~19"];
      } else if (state.weather.tmp <= 22) {
        state.bestStyles = ["블라우스, 긴팔 티, 면바지, 슬랙스", "20~22"];
      } else if (state.weather.tmp <= 27) {
        state.bestStyles = ["반팔, 얇은 셔츠, 반바지, 면바지", "23~27"];
      } else {
        state.bestStyles = ["민소매/반팔, 반바지, 린넨 옷", "28"];
      }
    },
  },

  actions: {
    FETCH_BEST_HOTSPOTS: ({ commit }, params) => {
      return getHotspots(params, ({ data }) => {
        commit("SET_BEST_HOTSPOTS", data);
      });
    },
    FETCH_BEST_MENUS: ({ commit }, params) => {
      return getBestMenus(params, ({ data }) => {
        commit("SET_BEST_MENUS", data);
      });
    },
    FETCH_TODAY_WEATHER: ({ commit }, params) => {
      return getTodayWeather(params, ({ data }) => {
        commit("SET_TODAY_WEATHER", data);
      });
    },
    FETCH_BEST_STYLES: ({ commit }) => {
      commit("SET_BEST_STYLES");
    },
  },
};

export default chatStore;
