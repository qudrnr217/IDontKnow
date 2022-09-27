<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view />
        <div class="title">커뮤니티</div>
        <control-view :segments="segments" />

        <div class="trending">
          <img src="../assets/icon/fire.png" alt class="trending-icon" />
          <div class="trending-title">Trending</div>
        </div>

        <!-- <Flicking
          :options="{ align: 'prev', circular: true }"
          @move-end="onMoveEnd"
          class="flicking"
        >
          <div class="panel"><comlist-view /></div>
          <div class="panel"><comlist-view /></div>
          <div class="panel"><comlist-view /></div>
          <div class="panel"><comlist-view /></div>
          <div class="panel"><comlist-view /></div>
        </Flicking> -->

        <div class="body-mid">
          <control-view-2 :segments="segments2" class="toggle-btn" />
          <div class="create-btn" @click="create_vote()">만들기</div>
          <!-- <router-link to="/community/createvote" class="create-btn" -->
          <!-- >만들기</router-link -->
        </div>

        <div class="cards" @click="detail_card()">
          <comlist-view class="card" />
        </div>

        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import ControlView from "../components/common/ControlView.vue";
import ControlView2 from "../components/common/ControlView2.vue";
import FooterView from "../components/common/FooterView.vue";
import HeaderView from "../components/common/HeaderView.vue";
import ComlistView from "../components/community/ComlistView.vue";
// import { Carousel3d, Slide } from "vue-carousel-3d";
// import { Flicking } from "@egjs/vue-flicking";
export default {
  components: {
    FooterView,
    HeaderView,
    ControlView,
    ComlistView,
    ControlView2,
    // Carousel3d,
    // Slide,
    // Flicking: Flicking,
  },
  data() {
    return {
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
    };
  },
  methods: {
    create_vote() {
      this.$router.push({ name: "createvote" });
    },
    detail_card() {
      console.log("hi");
      this.$router.push({ name: "votedetail" });
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
