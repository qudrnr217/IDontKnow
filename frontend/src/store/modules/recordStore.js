import { getVoteList, getBallotList } from "@/api/mypage";

const recordStore = {
  namespaced: true,
  state: {
    voteList: [],
    select: 0,
    acount: 0,
    bcount: 0,
    voteDetail: [],
    vallotList: [],
    lastVoteId: 0,
    last: false,
  },

  getters: {},

  mutations: {
    SET_MY_VOTE_LIST: (state, voteList) => {
      for (var vote of voteList.content) {
        state.voteList.push(vote);
      }
      state.lastVoteId = voteList.content[voteList.numberOfElements - 1].voteId;
      state.last = voteList.last;
    },
    SET_MY_BALLOT_LIST: (state, voteList) => {
      for (var vote of voteList.content) {
        state.voteList.push(vote);
      }
      state.lastVoteId = voteList.content[voteList.numberOfElements - 1].voteId;
      state.last = voteList.last;
      console.log(state.lastVoteId);
    },
    SET_INIT(state) {
      state.voteList = [];
    },
  },
  actions: {
    SHOW_MY_VOTE_LIST: ({ commit }, { params }) => {
      return getVoteList(params, ({ data }) => {
        commit("SET_MY_VOTE_LIST", data);
      });
    },
    SHOW_MY_BALLOT_LIST: ({ commit }, { params }) => {
      console.log("");
      return getBallotList(params, ({ data }) => {
        console.log("data: ", data);
        commit("SET_MY_BALLOT_LIST", data);
      });
    },
  },
};

export default recordStore;
