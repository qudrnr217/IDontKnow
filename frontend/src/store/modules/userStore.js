const userStore = {
  namespaced: true,
  state: {
    userId: 0,
    name: "",
    districtId: 0,
    accessToken: "",
    refreshToken: "",
  },
  getters: {},
  mutations: {
    SET_USERID: (state, userId) => {
      state.userId = userId;
    },
    SET_NAME: (state, name) => {
      state.name = name;
    },
    SET_DISTRICT_ID: (state, districtId) => {
      state.districtId = districtId;
    },
    SET_INIT: (state) => {
      state.userId = 0;
      state.name = "";
      state.districtId = 0;
      state.accessToken = "";
      state.refreshToken = "";
    },
    SET_ACCESS_TOKEN: (state, accessToken) => {
      state.accessToken = "Bearer " + accessToken;
    },
    SET_REFRESH_TOKEN: (state, refreshToken) => {
      state.refreshToken = "Bearer " + refreshToken;
    },
  },
};

export default userStore;
