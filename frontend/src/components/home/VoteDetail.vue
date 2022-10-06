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
            <router-link
              :to="{
                path: `/record/user/${vote.userId}`,
                query: { name: vote.name },
              }"
              >{{ vote.name }}</router-link
            >
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

      <div class="box-align-center1">
        <div
          class="vote-percent-bar1"
          :class="{
            'yellow-2': vote.category === '메뉴',
            'purple-2': vote.category === '스타일',
            'green-2': vote.category === '장소',
          }"
        ></div>
      </div>
      <div class="box-align-center1">
        <div
          class="vote-percent-bar2"
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
        <div class="box-comment-column">
          <div
            class="box-comment-row"
            v-for="comment in vote.commentList"
            :key="comment.commentId"
          >
            <div
              v-if="comment.checkAuthor"
              class="box-row box-comment-row-right"
            >
              <div class="box-comment-column">
                <div class="box-comment-row text-h5">
                  {{ comment.createdAt }}
                </div>
                <div
                  class="box-comment-row text-align-left text-h4"
                  :class="{
                    'comment-background-menu-author': vote.category === '메뉴',
                    'comment-background-style-author':
                      vote.category === '스타일',
                    'comment-background-location-author':
                      vote.category === '장소',
                  }"
                >
                  <div
                    v-if="isUpdated && comment.commentId == modifyCommentId"
                    class="box-comment-text"
                  >
                    <input
                      class="input-update"
                      type="text"
                      v-model="commentForUpdate1"
                      :placeholder="comment.content"
                      style="border: none"
                    />
                  </div>
                  <!-- <div class="box-comment-text" v-else>
                    {{ comment.content }}
                  </div> -->
                  <div class="box-comment-text" v-else>
                    {{ comment.content }}
                  </div>
                </div>
                <div class="box-comment-btn-row box-comment-btn-left">
                  <div
                    class="btn-rectangle-tiny red-text text-h5"
                    @click="updateComment(comment.commentId)"
                    :value="`${comment.commentId}`"
                    v-if="userId == comment.userId"
                  >
                    수정
                  </div>
                  <div
                    class="btn-rectangle-tiny text-h5"
                    @click="deleteComment(comment.commentId)"
                    :value="`${comment.commentId}`"
                    v-if="userId == comment.userId"
                  >
                    삭제
                  </div>
                </div>
              </div>
              <div class="box-comment-column comment-profile-box">
                <div class="box-comment-row">
                  <img
                    class="comment-profile-image"
                    src="../../assets/image/김게따.png"
                    alt="작성자"
                  />
                </div>
                <div
                  class="box-comment-row comment-profile-name text-align-center text-h5"
                >
                  <router-link :to="`/record/user/${comment.userId}`">
                    {{ comment.name }}
                  </router-link>
                </div>
              </div>
            </div>
            <!-- 참여자 -->
            <div v-else class="box-row">
              <div
                class="box-comment-column comment-profile-box"
                style="margin: 5px"
              >
                <div class="box-comment-row">
                  <img
                    class="comment-profile-image"
                    src="../../assets/image/김모르.png"
                    alt="참여자"
                  />
                </div>
                <div
                  class="box-comment-row comment-profile-name text-align-center text-h5"
                >
                  <router-link :to="`/record/user/${comment.userId}`">
                    {{ comment.name }}</router-link
                  >
                </div>
              </div>
              <div class="box-comment-column">
                <div class="box-comment-row text-h5" style="margin-left: 90px">
                  {{ comment.createdAt }}
                </div>
                <div
                  class="box-comment-row text-align-left text-h4"
                  :class="{
                    'comment-background-menu-commenter':
                      vote.category === '메뉴',
                    'comment-background-style-commenter':
                      vote.category === '스타일',
                    'comment-background-location-commenter':
                      vote.category === '장소',
                  }"
                >
                  <!-- TODO: 수정 버튼 클릭 시 해당 댓글 바꾸는 처리 필요 -->

                  <div
                    v-if="
                      isUpdated &&
                      comment.commentId == modifyCommentId &&
                      comment.checkAuthor == false
                    "
                    class="box-comment-text"
                  >
                    <input
                      class="input-update"
                      type="text"
                      v-model="commentForUpdate2"
                      :placeholder="comment.content"
                      style="border: none"
                    />
                  </div>
                  <div class="box-comment-text" v-else>
                    {{ comment.content }}
                  </div>
                </div>
                <!-- 안녕 -->
                <div class="box-comment-btn-row box-comment-btn-right">
                  <div
                    class="btn-rectangle-tiny red-text text-h5"
                    :value="`${comment.commentId}`"
                    @click="updateComment2(comment.commentId)"
                    v-if="userId == comment.userId"
                  >
                    수정
                  </div>
                  <div
                    class="btn-rectangle-tiny text-h5"
                    @click="deleteComment(comment.commentId)"
                    :value="`${comment.commentId}`"
                    v-if="userId == comment.userId"
                  >
                    삭제
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="box-row">
        <div style="margin: 5px">
          <img
            v-if="this.$store.state.userStore.userId === this.vote.userId"
            class="comment-profile-image"
            src="../../assets/image/김게따.png"
            alt=""
          />
          <img
            v-else
            class="comment-profile-image"
            src="../../assets/image/김모르.png"
            alt=""
          />
        </div>
        <input
          type="text"
          v-model="comment"
          class="input-rectangle-short text-h4"
          :class="{
            'yellow-2-border': vote.category === '메뉴',
            'purple-2-border': vote.category === '스타일',
            'green-2-border': vote.category === '장소',
          }"
          placeholder="댓글을 입력해주세요."
        />
        <img
          src="../../assets/icon/send.png"
          alt="전송"
          @click="sendComment()"
        />
      </div>
    </div>
  </div>
</template>
<script>
// import VoteBarChart from "./VoteBarChart.vue";
// import VoteCommentList from "./VoteCommentList.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
import { mapMutations, mapState } from "vuex";
import PieChartView from "../community/PieChartVIew.vue";
import {
  detailVote,
  commentCreate,
  commentDelete,
  commentModify,
} from "@/api/community.js";
// import VoteChat from "./VoteChat.vue";
// var token =
//   "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiYXVkIjoi7LmY7YKo65-s67KEIiwiZXhwIjoxNjY0OTg1MDk0fQ.oJIXeeV8whA5Q_IV1t3NH64-fq5LlUD0DP-V7Dvd5tRXbm7epQlvkZrnfag6yXmy";
export default {
  name: "VoteDetail",
  // props: {
  //   voteId: String,
  // },
  components: {
    // VoteBarChart,
    VueConfirmDialog,
    PieChartView,
    // VoteChat,
    // VoteCommentList,
  },
  computed: {
    checkStatus() {
      return this.vote.status ? "종료" : "진행";
    },
    ...mapState("userStore", ["accessToken", "userId"]),
  },
  mounted() {
    // console.log(this.$route.path);
    console.log("안녕하세요");
    console.log(this.$route.params);

    detailVote(
      this.accessToken,
      this.$route.params,
      ({ data }) => {
        console.log(data);
        this.vote = data;
        this.$emit("pass", this.vote.category);
        console.log("vote: ", this.vote.category);
        console.log(
          this.$store.state.userStore.userId + ":" + this.vote.userId
        );

        if (this.vote.voted == "A") {
          this.clickedOption = 1;
        } else if (this.vote.voted == "B") {
          this.clickedOption = 2;
        } else {
          this.clickedOption = 0;
        }
        // this.set_vote_detail();

        //프로그래스 바
        const bar1 = document.querySelector(".vote-percent-bar1");
        const bar2 = document.querySelector(".vote-percent-bar2");

        let t1 = 0;
        let t2 = 0;
        let a = data.acount;
        let b = data.bcount;
        let totalMinwon = (a / (a + b)) * 100;
        let resolveMinwon = (b / (a + b)) * 100;
        if (a == 0 && b == 0) {
          totalMinwon = 0;
          resolveMinwon = 0;
        } else if (a == 0) {
          totalMinwon = 0;
        } else if (b == 0) {
          resolveMinwon = 0;
        }
        console.log("민원:" + this.totalMinwon + ":" + this.resolveMinwon);

        const barAnimation1 = setInterval(() => {
          bar1.style.width = t1 + "%";
          t1++ >= totalMinwon && clearInterval(barAnimation1);
        }, 10);

        const barAnimation2 = setInterval(() => {
          bar2.style.width = t2 + "%";
          t2++ >= resolveMinwon && clearInterval(barAnimation2);
        }, 10);
      },
      (error) => {
        if (error.response.status == 401) {
          this.$router.push({ name: "userLogin", path: "/profile/login" });
        }
      }
    );
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
      isUpdated: false,
      commentForUpdate1: "",
      commentForUpdate2: "",
      modifyCommentId: 0,
      isModify: false,
      token: "",
      vote: [],
      ballotId: 0,
      reload: 0,
      flag: false,
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
        mode: "6",
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
    sendComment() {
      var params = { voteId: this.vote.voteId, content: this.comment };
      commentCreate(this.accessToken, params, ({ data }) => {
        console.log("sendComment: " + data);
        this.$router.go();
      });
    },
    updateComment(commentId) {
      console.log("update comment1");
      // console.log(commentId);
      // console.log("modify:" + this.modifyCommentId);
      // console.log(this.isUpdated);
      if (!this.flag) {
        this.isUpdated = true;
        console.log(this.isUpdated);
        this.modifyCommentId = commentId;
        console.log(this.modifyCommentId + ":" + commentId);
        // this.isModify = true;
        this.flag = true;
      } else {
        console.log("들어왔다~~~");
        let content = { content: this.commentForUpdate1 };
        // console.log(this.commentForUpdate1);
        commentModify(
          this.accessToken,
          commentId,
          content,
          ({ data }) => {
            console.log(data);
            this.flag = false;
            this.isUpdated = false;
            this.$router.go();
          },
          (error) => {
            console.log(error);
          }
        );
      }

      // this.isUpdated = false;
      // this.$router.go();
    },
    updateComment2(commentId) {
      console.log("updatecomment2");
      // console.log(commentId);
      // console.log("modify:" + this.modifyCommentId);
      // console.log(this.isUpdated);
      if (!this.flag) {
        //인풋창 뜰 때
        this.isUpdated = true;
        console.log(this.isUpdated);
        this.modifyCommentId = commentId;
        console.log(this.modifyCommentId + ":" + commentId);
        // this.isModify = true;
        this.flag = true;
      } else {
        // 실제로 수정작업
        console.log("들어왔다~~~");
        let content = { content: this.commentForUpdate2 };
        commentModify(
          this.accessToken,
          commentId,
          content,
          ({ data }) => {
            console.log(data);
            this.flag = false;
            this.isUpdated = false;
            this.$router.go();
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    deleteComment(commentId) {
      commentDelete(this.accessToken, commentId, ({ data }) => {
        console.log(data);
        this.$router.go();
      });
    },
  },
  created() {
    // vote 값을 this.$route.params.voteId로 api 호출

    console.log("배경 카테고리 : " + this.vote.category);
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

.box-align-center1 {
  width: 280px;
  margin-left: 20px;
}

.vote-percent-bar1,
.vote-percent-bar2 {
  width: 280px;
  height: 30px;
  /* background-color: #dedede; */
  font-weight: 600;
  font-size: 0.8rem;
}

.input-update {
  width: 145px;
}
</style>
