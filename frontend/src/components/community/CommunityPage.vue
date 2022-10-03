<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view />
        <div class="title">커뮤니티</div>
        <!-- <control-view :segments="segments" /> -->

        <!-- 셀렉트 박스 -->
        <div class="content">
          <div class="content_title">오늘의</div>
          <div class="content_dropdown">
            <select
              class="sel"
              v-model="main_category"
              @change="change_trend(main_category)"
            >
              <!-- v-model=""  -->
              <!-- <option value="메뉴" selected>메뉴</option> -->
              <option
                v-for="(menu, index) in Mainmenu"
                :key="index"
                :value="menu.text"
              >
                {{ menu.text }}
              </option>
            </select>
          </div>
        </div>
        <!-- {{ main_category }} -->
        <!-- 트랜딩 -->
        <div class="trending">
          <img src="../../assets/icon/fire.png" alt class="trending-icon" />
          <div class="trending-title">Trending</div>
        </div>

        <!-- <Flicking
          :options="{ align: 'prev', circular: true }"
          @move-end="onMoveEnd"
          class="flicking"
        >
          <div class="trends" v-for="trend in trends" :key="trend.userId">
            <div class="panel"><comlist-view :cards="trend" /></div>
          </div>
        </Flicking> -->

        <div class="body-mid">
          <!-- <control-view-2 :segments="segments2" class="toggle-btn" /> -->
          <div class="content">
            <div class="content_title">현재</div>
            <div class="content_dropdown">
              <select
                class="sel"
                v-model="sub_category"
                @change="change_trend(sub_category)"
              >
                <!-- v-model=""  -->
                <!-- <option value="진행" selected>진행</option> -->
                <option
                  v-for="(menu, index) in Submenu"
                  :key="index"
                  :value="menu.value"
                >
                  {{ menu.text }}
                </option>
              </select>
              <!-- {{ sub_category }} -->
            </div>
          </div>
          <div class="create-btn" @click="create_vote()">만들기</div>
          <!-- <router-link to="/community/createvote" class="create-btn" -->
          <!-- >만들기</router-link -->
        </div>

        <div
          class="cards"
          v-for="(list, index) in vote_list"
          :key="index"
          @click="detail_card(list.voteId)"
        >
          <comlist-view class="card" :cards="list" />
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
          <div
            slot="no-more"
            style="
              color: rgb(102, 102, 102);
              font-size: 14px;
              padding: 10px 0px;
            "
          >
            목록의 끝입니다 :)
          </div>
        </infinite-loading>
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
// import ControlView from "../common/ControlView.vue";
// import ControlView2 from "../common/ControlView2.vue";
import FooterView from "../common/FooterView.vue";
import HeaderView from "../common/HeaderView.vue";
import ComlistView from "./ComlistView.vue";
// import { showVoteList } from "@/api/community.js";
import { mapState, mapActions, mapMutations } from "vuex";
import { trendVote } from "@/api/community.js";
import InfiniteLoading from "vue-infinite-loading";
// import { Carousel3d, Slide } from "vue-carousel-3d";
// import { Flicking } from "@egjs/vue-flicking";

var token =
  "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxOCIsImF1ZCI6IuuvvO2VmOydgCIsImV4cCI6MTY2NDczMDM0NX0.uFzNm7uqYyqiYvxj4DQKMKSlNG5swevdgTIooo5af8xRiVtPFx5JL1_I6EtR9ByI";
export default {
  components: {
    FooterView,
    HeaderView,
    // ControlView,
    ComlistView,
    InfiniteLoading,
    // ControlView2,
    // Carousel3d,
    // Slide,
    // Flicking: Flicking,
  },
  data() {
    return {
      Mainmenu: [
        {
          value: 1,
          text: "메뉴",
        },
        {
          value: 2,
          text: "스타일",
        },
        {
          value: 3,
          text: "장소",
        },
      ],
      Submenu: [
        {
          value: false,
          text: "진행",
        },
        {
          value: true,
          text: "종료",
        },
        // {
        //   value: 3,
        //   text: "장소",
        // },
      ],

      main_category: "메뉴",
      sub_category: "false",
      // lastVoteId: ,

      segments: [
        {
          title: "메뉴",
          id: "42",
        },
        {
          title: "스타일",
          id: "93",
        },
        {
          title: "장소",
          id: "11",
        },
      ],

      segments2: [
        {
          title: "진행",
          id: "1",
        },
        {
          title: "종료",
          id: "2",
        },
      ],
      // params: {
      //   category: this.main_category,
      //   status: this.sub_category,
      //   lastVoteId: "0",
      // },
      params: {},
      cards: [],
      trends: [],
      reload: 0,
    };
  },
  computed: {
    ...mapState("communityStore", ["vote_list", "lastVoteId", "last"]),
  },

  mounted() {
    var params = {
      category: this.main_category,
      status: this.sub_category,
      lastVoteId: 0,
    };
    this.set_init();
    this.votes_list(params);
    //트랜드 3가지 조회
    console.log("last:" + this.lastVoteId);
    trendVote(token, this.main_category, ({ data }) => {
      console.log("트렌드: ", data);
      this.trends = data;
    });
  },

  methods: {
    ...mapActions("communityStore", ["SHOW_VOTE_LIST"]),
    ...mapMutations("communityStore", ["SET_INIT"]),
    set_init() {
      this.SET_INIT();
    },
    create_vote() {
      this.$router.push({ name: "votecreate" });
    },
    detail_card(voteId) {
      this.$router.push({
        name: "votedetail",
        query: { voteId: voteId },
      });
    },

    votes_list(params) {
      // this.SET_INIT();
      this.SHOW_VOTE_LIST({
        params: params,
        token: token,
      });
      console.log("last:" + this.lastVoteId);
    },
    //메뉴 바꿀 때 트렌드 체인지와 리스트 체인지
    change_trend(main_category) {
      trendVote(token, main_category, ({ data }) => {
        console.log("트렌드: ", data);
        this.trends = data;
      });
      console.log(this.main_category + ":" + this.sub_category);
      let params = {
        category: this.main_category,
        status: this.sub_category,
        lastVoteId: 0,
      };
      this.SET_INIT();
      this.votes_list(params);

      console.log("vote_list:", this.vote_list);

      // this.reload += 1;
    },

    infiniteHandler($state) {
      let params = {
        category: this.main_category,
        status: this.sub_category,
        lastVoteId: this.lastVoteId,
      };
      this.votes_list(params);
      console.log("hi");
      setTimeout(() => {
        if (!this.last) {
          $state.loaded();
          this.lastVoteId += 1;
          // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면
          if (this.last) {
            $state.complete();
          }
        } else {
          // 끝 지정(No more data)
          $state.complete();
        }
      }, 3000);
    },
  },
};
</script>

<style scoped>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}

/* 트랜드 */

.trending {
  display: flex;
  /* background-color: red; */
  width: 100px;
  margin-left: 25px;
  margin-top: 20px;
}

.trending-title {
  margin-left: 10px;
  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 13px;
  line-height: 20px;
}

.slide {
  width: 320px;
  height: 10px;
}

.carousel-3d-slide.current {
  width: 320px;
  height: 170px;
}

.flicking-viewport:not(.vertical) {
  width: 100%;
  height: 180px;
}

.panel > .vote-card {
  margin: 10px;
}

/* 진행,종료,만들기 버튼 */
.body-mid {
  display: flex;
}
.toggle-btn {
  margin-left: 35px;
}

.body-mid > .create-btn {
  width: 60px;
  height: 30px;
  background: rgba(0, 122, 255, 0.15);
  border-radius: 8.91px;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: 25px;
  margin-left: 160px;

  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 13px;
  line-height: 20px;
  cursor: pointer;
}

.card {
  margin-top: 20px;
  cursor: pointer;
}
</style>
