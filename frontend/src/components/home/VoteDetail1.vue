<template>
  <div class="body">
    <!-- 화면 제목 -->
    <div class="box-row-left">
      <div
        class="text-title text-h1"
        :class="{
          'yellow-3-text': vote.category === '메뉴',
          'purple-3-text': vote.category === '스타일',
          'green-3-text': vote.category === '장소',
        }"
      >
        투표 상세
      </div>
      <div
        class="btn-status text-h3"
        :class="{
          'yellow-1': vote.category === '메뉴' && !vote.status,
          'purple-1': vote.category === '스타일' && !vote.status,
          'green-1': vote.category === '장소' && !vote.status,
          'yellow-0': vote.category === '메뉴' && vote.status,
          'purple-0': vote.category === '스타일' && vote.status,
          'green-0': vote.category === '장소' && vote.status,
        }"
      >
        {{ checkStatus }}
      </div>
    </div>

    <div class="box-align-center">
      <!-- 투표 카드 -->
      <div class="vote-card">
        <div class="vote-title-box">
          <div class="vote-title-text text-h2">
            {{ vote.title }}
          </div>
        </div>
        <div class="vote-writer-box">
          <div class="vote-writer-text text-h3">
            작성자 :
            <router-link :to="`/record/user/${vote.userId}`">{{
              vote.name
            }}</router-link>
          </div>
        </div>
        <div class="vote-info-box">
          <div class="vote-category-box">
            <div class="vote-category-main text-h4"># {{ vote.category }}</div>
            <div class="vote-category-sub text-h4">
              # {{ vote.subCategory }}
            </div>
          </div>
          <div class="vote-count-box">
            <div class="text-h4">{{ vote.createdAt }}</div>
          </div>
        </div>
        <div
          class="box-btn-right"
          @click="deleteVote"
          v-if="this.$store.state.userStore.userId === this.vote.userId"
        >
          <div class="btn-rectangle-tiny text-h4 red">삭제</div>
        </div>
      </div>
      <!-- 투표 선택지 -->
      <div class="vote-options-box-big">
        <div
          id="vote-option-a"
          class="vote-option-box-big"
          :class="{
            'yellow-2-border': vote.category === '메뉴',
            'purple-2-border': vote.category === '스타일',
            'green-2-border': vote.category === '장소',
            white: clickedOption === 0,
            'vote-option-box-big-menu':
              vote.category === '메뉴' && clickedOption === 0,
            'vote-option-box-big-style':
              vote.category === '스타일' && clickedOption === 0,
            'vote-option-box-big-location':
              vote.category === '장소' && clickedOption === 0,
            'yellow-1': vote.category === '메뉴' && clickedOption === 1,
            'purple-1': vote.category === '스타일' && clickedOption === 1,
            'green-1': vote.category === '장소' && clickedOption === 1,
          }"
          @click="changeClickedOptionA"
        >
          <div class="vote-option-text text-h3">
            {{ vote.optionA }}
          </div>
        </div>
        <div
          id="vote-option-b"
          class="vote-option-box-big"
          :class="{
            'yellow-2-border': vote.category === '메뉴',
            'purple-2-border': vote.category === '스타일',
            'green-2-border': vote.category === '장소',
            white: clickedOption === 0,
            'vote-option-box-big-menu':
              vote.category === '메뉴' && clickedOption === 0,
            'vote-option-box-big-style':
              vote.category === '스타일' && clickedOption === 0,
            'vote-option-box-big-location':
              vote.category === '장소' && clickedOption === 0,
            'yellow-1': vote.category === '메뉴' && clickedOption === 2,
            'purple-1': vote.category === '스타일' && clickedOption === 2,
            'green-1': vote.category === '장소' && clickedOption === 2,
          }"
          @click="changeClickedOptionB"
        >
          <div class="vote-option-text text-h3">
            {{ vote.optionB }}
          </div>
        </div>
      </div>
      <!-- 투표 버튼 -->
      <div class="box-row">
        <!-- 종료 버튼 -->
        <div class="btn-rectangle-big grey" v-if="this.vote.status">
          <div class="text-h2">투표종료</div>
        </div>
        <!-- 마감 버튼 -->
        <div
          class="btn-rectangle-big"
          :class="{
            'yellow-4': vote.category === '메뉴',
            'purple-4': vote.category === '스타일',
            'green-4': vote.category === '장소',
          }"
          @click="changeStatus"
          v-else-if="this.$store.state.userStore.userId === this.vote.userId"
        >
          <div class="text-h2">투표마감</div>
        </div>
        <!-- 투표하기 비활성화 버튼 -->
        <div
          class="btn-rectangle-big"
          :class="{
            'yellow-1': vote.category === '메뉴',
            'purple-1': vote.category === '스타일',
            'green-1': vote.category === '장소',
          }"
          v-else-if="
            this.vote.voted === null &&
            this.$store.state.userStore.userId !== this.vote.userId &&
            this.clickedOption === 0
          "
        >
          <div class="text-h2">투표하기</div>
        </div>
        <!-- 투표하기 활성화 버튼 -->
        <div
          class="btn-rectangle-big"
          :class="{
            'yellow-2': vote.category === '메뉴',
            'purple-2': vote.category === '스타일',
            'green-2': vote.category === '장소',
          }"
          @click="clickVote, (data2.isShow = true)"
          v-else-if="
            this.vote.voted === null &&
            this.$store.state.userStore.userId !== this.vote.userId &&
            this.clickedOption !== 0
          "
        >
          <div class="text-h2">투표하기</div>
        </div>
        <!-- 취소 버튼 -->
        <div
          class="btn-rectangle-big"
          :class="{
            'yellow-3': vote.category === '메뉴',
            'purple-3': vote.category === '스타일',
            'green-3': vote.category === '장소',
          }"
          @click="cancelVote(), (data3.isShow = true)"
          v-else
        >
          투표취소
        </div>

        <!-- 투표 팝업 창 -->
        <vue-confirm-dialog
          :data="data2"
          :voteId="vote.voteId"
          :select="curClickedOption"
          v-if="data2.isShow"
        ></vue-confirm-dialog>
        <vue-confirm-dialog
          :data="data3"
          :ballotId="ballotId"
          v-if="data3.isShow"
        ></vue-confirm-dialog>
        <vue-confirm-dialog
          :data="data4"
          :voteId="vote.voteId"
          v-if="data4.isShow"
        ></vue-confirm-dialog>
      </div>
    </div>
    <!-- 투표율 -->
    <div
      class="box-column"
      v-if="
        this.vote.userId === this.$store.state.userStore.userId ||
        this.vote.voted !== null ||
        this.vote.status
      "
    >
      <div class="box-row">
        <div
          class="text-h2"
          :class="{
            'yellow-3-text': vote.category === '메뉴',
            'purple-3-text': vote.category === '스타일',
            'green-3-text': vote.category === '장소',
          }"
        >
          투표율✨
        </div>
      </div>

      <div class="box-align-center">
        <div
          class="vote-percent-bar"
          :class="{
            'yellow-2': vote.category === '메뉴',
            'purple-2': vote.category === '스타일',
            'green-2': vote.category === '장소',
          }"
        ></div>
      </div>
      <div class="box-align-center">
        <div
          class="vote-percent-bar"
          :class="{
            'yellow-4': vote.category === '메뉴',
            'purple-4': vote.category === '스타일',
            'green-4': vote.category === '장소',
          }"
        ></div>
      </div>
    </div>
    <!-- 투표 통계 -->
    <div
      class="box-column"
      v-if="
        this.vote.userId === this.$store.state.userStore.userId ||
        this.vote.voted !== null ||
        this.vote.status
      "
    >
      <div class="box-row">
        <div
          class="text-h2"
          :class="{
            'yellow-3-text': vote.category === '메뉴',
            'purple-3-text': vote.category === '스타일',
            'green-3-text': vote.category === '장소',
          }"
        >
          투표 통계📊
        </div>
        <!-- 통계 토글 버튼 -->
        <div
          class="btn-toggle text-h3 white"
          @click="openChart, (isOpened = true)"
          v-if="!isOpened"
        >
          열기🔽
        </div>
        <div
          class="btn-toggle text-h3"
          :class="{
            'yellow-0': vote.category === '메뉴',
            'purple-0': vote.category === '스타일',
            'green-0': vote.category === '장소',
          }"
          @click="isOpened = false"
          v-else
        >
          접기🔼
        </div>
      </div>
      <!-- 통계 조건 선택 버튼 -->
      <div class="" v-if="isOpened">
        <div class="box-btn-right">
          <select
            v-model="chartOption"
            class="sb-rectangle-medium"
            :class="{
              'yellow-0': vote.category === '메뉴',
              'purple-0': vote.category === '스타일',
              'green-0': vote.category === '장소',
            }"
            @change="changeChart"
          >
            <option v-for="(item, index) in chartOptionList" :key="index">
              {{ item }}
            </option>
          </select>
        </div>
        <div class="box-btn-right" v-if="chartOption == '연령'">
          <select
            v-model="ageOption"
            class="sb-rectangle-medium"
            :class="{
              'yellow-0': vote.category === '메뉴',
              'purple-0': vote.category === '스타일',
              'green-0': vote.category === '장소',
            }"
            @change="changeChart"
          >
            <option v-for="(item, index) in ageOptionList" :key="index">
              {{ item }}
            </option>
          </select>
        </div>
        <div class="box-btn-right" v-if="chartOption == '성별'">
          <select
            v-model="genderOption"
            class="sb-rectangle-medium"
            :class="{
              'yellow-0': vote.category === '메뉴',
              'purple-0': vote.category === '스타일',
              'green-0': vote.category === '장소',
            }"
            @change="changeChart"
          >
            <option v-for="(item, index) in genderOptionList" :key="index">
              {{ item }}
            </option>
          </select>
        </div>
        <div class="box-align-center">
          <!-- 차트를 넣으면 아래 div 삭제 -->
          <div class="pie-chart">
            <pie-chart-view
              :voteId="vote.voteId"
              :age="ageOption"
              :idx="chartOption"
              :gender="genderOption"
              :key="reload"
            />
          </div>
          <!-- <div class="vote-percent-bar">통계가 나오도록 변경 필요 !</div> -->
        </div>
      </div>
    </div>
    <!-- 댓글 -->
    <div class="box-column">
      <div class="box-row">
        <div
          class="text-h2"
          :class="{
            'yellow-3-text': vote.category === '메뉴',
            'purple-3-text': vote.category === '스타일',
            'green-3-text': vote.category === '장소',
          }"
        >
          댓글💬
        </div>
      </div>
      <!-- 댓글 목록 -->
      <div
        class="white box-content box-align-center"
        :class="{
          'yellow-2-border': vote.category === '메뉴',
          'purple-2-border': vote.category === '스타일',
          'green-2-border': vote.category === '장소',
        }"
      >
        <!-- 댓글 구현 필요 -->
        <!-- <div class="vote-percent-bar">댓글이 나오도록 변경 필요 !</div> -->
        <input
          type="text"
          class="vote-percent-bar"
          placeholder="댓글을 작성하세요"
          v-model="comment"
          @keyup.enter="registComment"
        />

        <!-- <div class="comment-input">
          <img src="../../assets/icon/avatar.png" alt="" />
          <input type="text" class="comment-box" />
          <img src="../../assets/icon/send.png" alt="" class="send-btn" />
        </div> -->
      </div>
      <div class="comment"><vote-comment-list /></div>
    </div>
  </div>
</template>
<script>
// import VoteBarChart from "./VoteBarChart.vue";
import VoteCommentList from "./VoteCommentList.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
import { mapMutations } from "vuex";
import PieChartView from "../community/PieChartVIew.vue";
import { detailVote, commentCreate } from "@/api/community.js";
var token =
  "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiYXVkIjoi7LmY7YKo65-s67KEIiwiZXhwIjoxNjY0OTM0MTQ3fQ.qwUBEo_m9h_w5VWVtAtUtJdIH_uDa0h4ysMV5cxZZ-Nkrq2EPZYjAEhPhnSDhZ4i";
export default {
  name: "VoteDetail",
  // props: {
  //   voteId: String,
  // },
  components: {
    // VoteBarChart,
    VueConfirmDialog,
    PieChartView,
    VoteCommentList,
  },
  computed: {
    checkStatus() {
      return this.vote.status ? "종료" : "진행";
    },
  },
  mounted() {
    // console.log(this.$route.path);

    console.log(this.$route.params);

    detailVote(token, this.$route.params, ({ data }) => {
      console.log(data);
      this.vote = data;
      console.log("vote: ", this.vote.category);
      console.log(this.$store.state.userStore.userId + ":" + this.vote.userId);
      if (this.vote.voted == "A") {
        this.clickedOption = 1;
      } else if (this.vote.voted == "B") {
        this.clickedOption = 2;
      } else {
        this.clickedOption = 0;
      }
      // this.set_vote_detail();
    });
  },
  data() {
    return {
      clickedOption: 0, // 0 이면 안눌린거, 1이면 A, 2면 B
      curClickedOption: "",
      isOpened: false,
      chartOption: "연령",
      chartOptionList: ["연령", "성별", "거주지"],
      ageOption: 10,
      ageOptionList: [10, 20, 30, 40, 50, 60],
      genderOption: "F",
      genderOptionList: ["F", "M"],
      comment: "",

      vote: [],
      ballotId: 0,
      reload: 0,
      data: {
        isShow: false,
        title: "투표를 삭제하시겠습니까?",
        no: "취소",
        yes: "삭제",
      },
      data2: {
        isShow: false,
        title: "투표하시겠습니까?",
        no: "취소",
        yes: "투표",
        mode: "1",
      },
      data3: {
        isShow: false,
        title: "투표를 취소하시겠습니까?",
        no: "취소",
        yes: "투표취소",
        mode: "2",
      },
      data4: {
        isShow: false,
        title: "투표삭제 완료",
        message: "투표가 정상적으로 삭제되었습니다.",
        yes: "완료",
        no: null,
      },
      data5: {
        mode: "3",
        isShow: false,
        title: "투표를 마감하시겠습니까?",
        no: "취소",
        yes: "마감",
      },
    };
  },
  methods: {
    ...mapMutations("communityStore", ["SET_SELECT", "SET_VOTE_DETAIL"]),
    changeClickedOptionA() {
      if (this.vote.voted === null && !this.vote.status) {
        if (this.clickedOption === 1) {
          this.clickedOption = 0;
          this.curClickedOption = "";
        } else {
          this.clickedOption = 1;
          this.curClickedOption = "A";
        }
      }
    },
    changeClickedOptionB() {
      if (this.vote.voted === null && !this.vote.status) {
        if (this.clickedOption === 2) {
          this.clickedOption = 0;
          this.curClickedOption = "";
        } else {
          this.clickedOption = 2;
          this.curClickedOption = "B";
        }
      }
    },
    changeStatus() {
      // 종료 관련 팝업창 띄우기
      // 작성자가 투표 종료하는 api 호출
      // 새로고침
      this.reload += 1;
    },
    clickVote() {
      // 투표 참여하는 api 호출 (clickedOption 구분해서 choice 전달)
      // 새로고침
      location.reload();
    },
    cancelVote() {
      // 투표 취소하는 api 호출 (vote.ballotId 활용)
      // 새로고침
      this.ballotId = this.vote.ballotId;
      console.log(this.ballotId);
      // location.reload();
    },
    deleteVote() {
      // 투표 삭제하는 api 호출 (vote.ballotId 활용)
      // 새로고침
      location.reload();
    },
    openChart() {
      // 투표 현황 가져오는 api 호출 (vote.voteId 활용)
    },
    changeChart() {
      // 값에 따라 차트 변경 !
      this.reload += 1;
    },
    set_vote_detail() {
      this.SET_VOTE_DETAIL(this.vote);
      console.log("인포: ", this.vote);
    },
    registComment() {
      var params = { voteId: this.vote.voteId, content: this.comment };
      commentCreate(token, params, ({ data }) => {
        console.log(data);
        this.$router.go();
      });
    },
  },
  created() {
    // vote 값을 this.$route.params.voteId로 api 호출
    this.$emit("pass", this.vote.category);
    if (this.vote.result === null) {
      if (this.vote.voted === "A") {
        this.clickedOption = 1;
      }
      if (this.vote.voted === "B") {
        this.clickedOption = 2;
      }
    } else {
      if (this.vote.result === "A") {
        this.clickedOption = 1;
      } else if (this.vote.result === "B") {
        this.clickedOption = 2;
      }
    }
  },
};
</script>

<style scoped>
/* 여기에만 적용이 안되어서 추가 */
.body {
  max-width: 390px;
  height: 110vh;
  min-height: 844px;
  padding-bottom: 73px;
}

.comment-title {
  font-size: 17px;
  line-height: 22px;
  margin-top: 15px;
  margin-left: 15px;
}
.comment-input {
  display: flex;
  margin-left: 10px;
  align-items: center;
}
.comment-input > .comment-box {
  background: #ffffff;
  border: 1px solid #007aff;
  border-radius: 10px;

  width: 304px;
  height: 24px;
}
.send-btn {
  position: relative;
  right: 26px;
}

.vote-percent-bar {
  outline: none;
  border: none;
  background: transparent;
}
</style>
