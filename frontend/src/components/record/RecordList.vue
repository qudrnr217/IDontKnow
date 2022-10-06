<template>
  <div>
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">{{ userName }}님의 투표</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        {{ userName }}님의 기록을 살펴보세요 !
      </div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h2 blue-4-text">
        {{ userName }}님의 예측률
      </div>
    </div>
    <div class="box-row">
      <record-pie-chart :percentage="calcPercentage" />
      <div class="box-align-center">
        <div
          class="text-h3"
          v-if="calcPercentage >= 80 || calcPercentage <= 20"
        >
          {{ userName }}님은
        </div>
        <div
          class="text-h3"
          v-if="calcPercentage >= 80"
          style="background-color: gold"
        >
          금손
        </div>
        <div
          class="text-h3"
          v-else-if="calcPercentage <= 20"
          style="background-color: darksalmon"
        >
          똥손
        </div>
        <div
          class="text-h3"
          v-if="calcPercentage >= 80 || calcPercentage <= 20"
        >
          입니다!
        </div>

        <div class="text-h3" v-else>조금 더 많은 투표에 참여해보세요 ~</div>
        <div class="text-h2">{{ calcPercentage }} %</div>
        <div class="text-h4">
          {{ voteCount.ballotCount }}개 중 {{ voteCount.correctCount }}개 정답
        </div>
      </div>
    </div>

    <div class="box-btn-right">
      <select
        v-model="record"
        class="sb-rectangle-long text-h3 blue-1"
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
        class="sb-rectangle-small text-h4 blue-0"
        @change="changeStatus()"
      >
        <option v-for="(item, index) in statusList" :key="index">
          {{ item }}
        </option>
      </select>
    </div>
    <div class="vote-list">
      <div
        v-for="vote in voteList"
        :key="vote.voteId"
        class="vote-card"
        @click="detailCard"
        :value="`${vote.voteId}`"
      >
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
            class="vote-option-box blue-2-border"
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
            class="vote-option-box blue-2-border"
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
import RecordPieChart from "../record/RecordPieChart.vue";
import { getRate } from "../../api/mypage";
import { mapState } from "vuex";
export default {
  components: {
    RecordPieChart,
  },
  name: "RecordList",
  data() {
    return {
      userId: this.$route.params.userId,
      userName: this.$route.query.name,
      clickedId: 0,
      status: "진행",
      statusType: false,
      record: "작성한 투표",
      recordType: 0, // 0 : 내가 작성한 투표, 1 : 내가 참여한 투표
      recordList: ["작성한 투표", "참여한 투표"],
      statusList: ["진행", "종료"],
      userInfo: {
        name: "",
        email: "",
        districtName: "",
        gender: "",
        age: 0,
      },
      voteCount: {
        ballotCount: 0,
        correctCount: 0,
      },
      voteList: [
        {
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
      ],
    };
  },
  mounted() {
    // 유저 정보 조회 api 호출해서 userInfo 채우기
    // 유저 예측률 조회 api 호출해서 userCount 채우기
    this.getVotesCount();
    // 유저의 (내가 작성한 투표-진행) api 호출해서 voteList 채우기
  },
  methods: {
    getVotesCount() {
      getRate(
        this.accessToken,
        this.userId, // 내가 선택해서 들어온 유저Id로 검색
        (response) => {
          this.voteCount.ballotCount = response.data.ballotCount;
          this.voteCount.correctCount = response.data.correctCount;
        },
        (error) => {
          if (error.response.status == 401) {
            this.$router.push({ name: "userLogin", path: "/profile/login" });
          }
          console.log(error);
        }
      );
    },
    detailCard(e) {
      const clickedId = e.target.getAttribute("value");
      this.$router.push({
        name: "voteDetail",
        params: {
          voteId: clickedId,
        },
      });
    },
    changeRecord() {
      // 내가 작성한 -> 참여한
      if (this.recordType === 0) {
        this.recordType = 1;
        this.record = "참여한 투표";
      }
      // 내가 참여한 -> 작성한
      else {
        this.recordType = 0;
        this.record = "작성한 투표";
      }
    },
    changeStatus() {
      if (!this.statusType) {
        this.statusType = true;
        this.status = "종료";
      } else {
        this.statusType = false;
        this.status = "진행";
      }
      if (this.recordType === 0) {
        // 내가 작성한 투표 목록 조회 api 호출
        // this.userId, this.statusType 포함
        // this.voteList에 담기
      } else {
        // 내가 참여한 투표 목록 조회 api 호출
        // this.userId, this.statusType 포함
        // this.voteList에 담기
      }
    },
  },
  computed: {
    calcPercentage() {
      if (this.voteCount.ballotCount !== 0) {
        return (
          Math.round(this.voteCount.correctCount / this.voteCount.ballotCount) *
          100
        );
      } else {
        return 0;
      }
    },
    ...mapState("userStore", ["accessToken"]),
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
