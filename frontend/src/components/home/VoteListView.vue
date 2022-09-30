<template>
  <div class="body">
    <div class="box-row">
      <div class="text-h1">모두에게 물어봐</div>
      <select
        v-model="category"
        class="sb-rectangle-big"
        :class="{
          'yellow-0': category === '메뉴',
          'purple-0': category === '스타일',
          'green-0': category === '장소',
        }"
        @change="changeCategory()"
      >
        <option v-for="(item, index) in categoryList" :key="index">
          {{ item }}
        </option>
      </select>
    </div>
    <div class="box-column">
      <div class="box-row">
        <div
          class="text-h2"
          :class="{
            'yellow-3-text': category === '메뉴',
            'purple-3-text': category === '스타일',
            'green-3-text': category === '장소',
          }"
        >
          인기 투표🔥
        </div>
      </div>
    </div>
    <div class="box-column">
      <div class="box-row">
        <div
          class="text-h2"
          :class="{
            'yellow-3-text': category === '메뉴',
            'purple-3-text': category === '스타일',
            'green-3-text': category === '장소',
          }"
        >
          투표 목록💌
        </div>
        <div
          class="btn-rectangle-small"
          :class="{
            'yellow-2': category === '메뉴',
            'purple-2': category === '스타일',
            'green-2': category === '장소',
          }"
          @click="createVote()"
        >
          <div class="text-align-center">만들기</div>
        </div>
      </div>
      <div class="box-btn-right">
        <!-- <control-view-2 :segments="status" class="toggle-btn" /> -->
        <select
          v-model="status"
          class="sb-rectangle-small text-h3"
          :class="{
            'yellow-1': category === '메뉴' && status === '진행',
            'purple-1': category === '스타일' && status === '진행',
            'green-1': category === '장소' && status === '진행',
            'yellow-0': category === '메뉴' && status === '종료',
            'purple-0': category === '스타일' && status === '종료',
            'green-0': category === '장소' && status === '종료',
          }"
          @change="changeStatus()"
        >
          <option v-for="(item, index) in statusList" :key="index">
            {{ item }}
          </option>
        </select>
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

      <div @click="detailCard()">
        <vote-list />
      </div>
    </div>
  </div>
</template>

<script></script>

<script>
import VoteList from "./VoteList.vue";
// import { Carousel3d, Slide } from "vue-carousel-3d";
// import { Flicking } from "@egjs/vue-flicking";
export default {
  name: "VoteListView",
  components: {
    VoteList,
    // Carousel3d,
    // Slide,
    // Flicking: Flicking,
  },
  data() {
    return {
      status: "진행",
      category: "메뉴",
      categoryList: ["메뉴", "스타일", "장소"],
      statusList: ["진행", "종료"],
    };
  },
  methods: {
    createVote() {
      this.$router.push({ name: "voteCreate" });
    },
    detailCard() {
      this.$router.push({ name: "voteDetail" });
    },
    changeCategory() {
      this.$emit("pass", this.category);
    },
    changeStatus() {
      // 여기서 진행 종료 바꾸는 목록 함수 호출
      console.log(this.status);
    },
  },
};
</script>

<style scoped>
/* 트랜드 */

/* .slide {
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
} */

/* 진행,종료,만들기 버튼 */
/* .body-mid {
  display: flex;
}
.toggle-btn {
  margin-left: 35px;
} */

/* .body-mid > .create-btn {
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
} */

/* .card {
  margin-top: 20px;
  cursor: pointer;
} */
</style>
