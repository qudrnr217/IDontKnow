<template>
  <div class="body">
    <div class="box-row-left">
      <div class="text-title text-h1">모두에게 물어봐</div>
      <select
        v-model="category"
        class="sb-rectangle-big"
        :class="{
          'yellow-0': category === '메뉴',
          'purple-0': category === '스타일',
          'green-0': category === '장소',
        }"
        @change="changeCategory"
      >
        <option v-for="(item, index) in categoryList" :key="index">
          {{ item }}
        </option>
      </select>
    </div>
    <div class="box-row-left">
      <div
        class="text-title text-h4"
        :class="{
          'yellow-3-text': category === '메뉴',
          'purple-3-text': category === '스타일',
          'green-3-text': category === '장소',
        }"
      >
        사람들과 고민을 공유해보세요 !
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
          인기 투표🔥
        </div>
      </div>
      <div class="box-row">
        <div class="vote-percent-bar">인기투표가 나오도록 변경 필요 !</div>
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
          @click="createVote"
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
          @change="changeStatus"
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
      <div>
        <div class="vote-list">
          <!-- v-for="vote in voteList" :key="vote.voteId"  -->
          <div class="vote-card" @click="detailCard" :value="`${vote.voteId}`">
            <div
              class="vote-title-box"
              @click="detailCard"
              :value="`${vote.voteId}`"
            >
              <div
                class="vote-title-text text-h3"
                @click="detailCard"
                :value="`${vote.voteId}`"
              >
                {{ vote.title }}
              </div>
            </div>
            <div
              class="vote-writer-box"
              @click="detailCard"
              :value="`${vote.voteId}`"
            >
              <div
                class="vote-writer-text text-h4"
                @click="detailCard"
                :value="`${vote.voteId}`"
              >
                작성자 : {{ vote.name }}
              </div>
            </div>
            <div
              class="vote-options-box"
              @click="detailCard"
              :value="`${vote.voteId}`"
            >
              <div
                class="vote-option-box"
                :class="{
                  'yellow-2-border': category === '메뉴',
                  'purple-2-border': category === '스타일',
                  'green-2-border': category === '장소',
                }"
                @click="detailCard"
                :value="`${vote.voteId}`"
              >
                <div
                  class="vote-option-text text-h4"
                  @click="detailCard"
                  :value="`${vote.voteId}`"
                >
                  {{ vote.optionA }}
                </div>
              </div>
              <div class="vote-option-vs">vs</div>
              <div
                class="vote-option-box"
                :class="{
                  'yellow-2-border': category === '메뉴',
                  'purple-2-border': category === '스타일',
                  'green-2-border': category === '장소',
                }"
                @click="detailCard"
                :value="`${vote.voteId}`"
              >
                <div
                  class="vote-option-text text-h4"
                  @click="detailCard"
                  :value="`${vote.voteId}`"
                >
                  {{ vote.optionB }}
                </div>
              </div>
            </div>
            <div
              class="vote-info-box"
              @click="detailCard"
              :value="`${vote.voteId}`"
            >
              <div
                class="vote-category-box"
                @click="detailCard"
                :value="`${vote.voteId}`"
              >
                <div
                  class="vote-category-main text-h5"
                  @click="detailCard"
                  :value="`${vote.voteId}`"
                >
                  # {{ vote.category }}
                </div>
                <div
                  class="vote-category-sub text-h5"
                  @click="detailCard"
                  :value="`${vote.voteId}`"
                >
                  # {{ vote.subCategory }}
                </div>
              </div>
              <div
                class="vote-count-box"
                @click="detailCard"
                :value="`${vote.voteId}`"
              >
                <div
                  class="vote-view-box"
                  @click="detailCard"
                  :value="`${vote.voteId}`"
                >
                  <img
                    src="../../assets/icon/views.png"
                    alt="조회"
                    class="vote-btn-view"
                  />
                  <div class="text-h5">
                    {{ vote.hitCount }}
                  </div>
                </div>
                <div
                  class="vote-comment-box"
                  @click="detailCard"
                  :value="`${vote.voteId}`"
                >
                  <img
                    src="../../assets/icon/chat.png"
                    alt="댓글"
                    class="vote-btn-comment"
                  />
                  <div class="text-h5">
                    {{ vote.commentCount }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script></script>

<script>
// import { Carousel3d, Slide } from "vue-carousel-3d";
// import { Flicking } from "@egjs/vue-flicking";
export default {
  name: "VoteList",
  components: {
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
      id: 0,
      vote: {
        voteId: 1,
        title: "어떤 치킨 좋아하세요?",
        userId: 1,
        name: "치킨러버",
        optionA: "교촌치킨",
        optionB: "노랑통닭",
        hitCount: 12000,
        commentCount: 20000,
        category: "메뉴",
        subCategory: "치킨",
      },
    };
  },
  methods: {
    createVote() {
      if (this.$store.state.userStore.userId === 0) {
        this.$router.push({ name: "userLogin" });
      } else this.$router.push({ name: "voteCreate" });
    },
    detailCard(e) {
      if (this.$store.state.userStore.userId === 0) {
        this.$router.push({ name: "userLogin" });
      } else {
        const clickedId = e.target.getAttribute("value");
        // 파라미터 같이 보내기 !
        this.$router.push({
          name: "voteDetail",
          params: {
            voteId: clickedId,
          },
        });
      }
    },
    changeCategory() {
      this.$emit("pass", this.category);
      this.status = "진행";
      this.$router.push({
        name: "voteList",
        path: "/",
        params: { status: this.status, category: this.category },
      });
    },
    changeStatus() {
      // 여기서 진행 종료 바꾸는 목록 함수 호출
    },
  },
  created() {
    // voteList api 호출
  },
};
</script>

<style scoped>
.body {
  height: 105vh;
}
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
