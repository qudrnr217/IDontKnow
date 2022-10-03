<template>
  <div>
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">
        {{ this.vote.name }}의 투표
      </div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        {{ this.vote.name }}의 예측률
      </div>
    </div>
    <div class="box-row">
      <vote-pie-chart />
      <div class="box-align-center">
        <div class="text-h3">당신은 금손입니다!</div>
        <div class="text-h2">
          {{ (100 * this.count.correctCount) / this.count.ballotCount }} %
        </div>
        <div class="text-h4">
          {{ this.count.ballotCount }}개 중 {{ this.count.correctCount }}개 정답
        </div>
      </div>
    </div>

    <div class="box-btn-right">
      <select
        v-model="record"
        class="sb-rectangle-long text-h3"
        @change="changeRecord()"
        style="border-style: none; width: 150px"
      >
        <option v-for="(item, index) in recordList" :key="index">
          {{ item }}
        </option>
      </select>
    </div>
    <div class="box-btn-right">
      <select
        v-model="status"
        class="sb-rectangle-small text-h3"
        @change="changeStatus()"
      >
        <option v-for="(item, index) in statusList" :key="index">
          {{ item }}
        </option>
      </select>
    </div>
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
            <!-- <router-link :to="`/vote/${vote.voteId}`">{{
                  vote.title
                }}</router-link> -->
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
            <!-- <router-link :to="`/votes/user/${vote.userId}`">{{
                  vote.name
                }}</router-link> -->
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
</template>

<script>
import VotePieChart from "../vote/VotePieChart.vue";
import { getRate } from "../../api/mypage";
import { mapState } from "vuex";
export default {
  components: {
    VotePieChart,
  },
  data() {
    return {
      recordList: ["내가 작성한 투표", "내가 참여한 투표"],
      statusList: ["진행", "종료"],
      count: {
        id: 0,
        ballotCount: 100,
        correctCount: 30,
      },
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
      segments: [
        {
          title: "내가 작성한 투표",
          id: "42",
        },
        {
          title: "내가 참여한 투표",
          id: "93",
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
  mounted() {
    this.getVotesCount();
  },
  methods: {
    getVotesCount() {
      getRate(
        this.accessToken,
        this.userId,
        (response) => {
          console.log(response.data);
          this.count.id = response.data.id;
          this.count.ballotCount = response.data.ballotCount;
          this.count.correctCount = response.data.correctCount;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailCard(e) {
      const clickedId = e.target.getAttribute("value");
      // 파라미터 같이 보내기 !
      // console.log(clickedId);
      this.$router.push({
        name: "voteDetail",
        params: {
          voteId: clickedId,
          // categoryParam: this.category,
          // statusParam: this.status,
        },
      });
    },
    // changeRecord() {
    //   this.$emit("pass", this.record);
    //   this.status = "진행";
    //   this.$router.push({
    //     name: "voteList",
    //     path: "home",
    //     params: { status: this.status, record: this.record },
    //   });
    // },
    // changeStatus() {
    //   // 여기서 진행 종료 바꾸는 목록 함수 호출
    // },
  },
  computed: {
    ...mapState("userStore", ["userId", "name", "accessToken"]),
  },
};
</script>
<style scoped>
/* 예측률 */
.prediction-rate {
  width: 100%;
  display: flex;
}
</style>
