<template>
  <div class="modu">
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
      <div
        class="vote-card"
        :style="{
          backgroundImage: `linear-gradient(
                rgba(255, 255, 255, 0.5),
                rgba(255, 255, 255, 0.5)
                ), url(${require('@/assets/image/category/' +
                  vote.category +
                  '/' +
                  vote.subCategory.replace('/', '_') +
                  '.jpg')})`,
        }"
      >
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
          @click="deleteVote()"
          v-if="this.$store.state.userStore.userId === this.vote.userId"
        >
          <div class="btn-rectangle-tiny text-h4 red">삭제</div>
        </div>
        <vue-confirm-dialog
          :data="data"
          v-if="data.isShow"
        ></vue-confirm-dialog>
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
          @click="changeStatus()"
          v-else-if="
            !this.vote.status &&
            this.$store.state.userStore.userId === this.vote.userId
          "
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
        <vue-confirm-dialog
          :data="data5"
          :voteId="vote.voteId"
          v-if="data5.isShow"
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
        <div class="vote-percent-title1">{{ vote.optionA }}</div>
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
        <div class="vote-percent-title1">{{ vote.optionB }}</div>
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
            <vote-pie-chart
              :category="vote.category"
              :voteId="vote.voteId"
              :age="ageOption"
              :idx="chartOption"
              :gender="genderOption"
              :key="reload"
            />
          </div>
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
                      id="clickedComment"
                      class="input-update"
                      type="text"
                      v-model="commentForUpdate"
                      @input="getUpdateComment()"
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
                    @click="updateComment(comment.commentId, comment.content)"
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
                  <router-link
                    :to="`/record/user/${comment.userId}?name=${comment.name}`"
                  >
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
                  <router-link
                    :to="`/record/user/${comment.userId}?name=${comment.name}`"
                  >
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
                  <div
                    v-if="
                      isUpdated &&
                      comment.commentId == modifyCommentId &&
                      comment.checkAuthor == false
                    "
                    class="box-comment-text"
                  >
                    <input
                      id="clickedComment"
                      class="input-update"
                      type="text"
                      v-model="commentForUpdate"
                      style="border: none"
                      @input="getUpdateComment()"
                    />
                  </div>
                  <div class="box-comment-text" v-else>
                    {{ comment.content }}
                  </div>
                </div>
                <div class="box-comment-btn-row box-comment-btn-right">
                  <div
                    class="btn-rectangle-tiny red-text text-h5"
                    :value="`${comment.commentId}`"
                    @click="updateComment(comment.commentId, comment.content)"
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
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
import { mapMutations, mapState } from "vuex";
import VotePieChart from "../home/VotePieChart.vue";
import {
  detailVote,
  commentCreate,
  commentDelete,
  commentModify,
} from "@/api/community.js";
export default {
  name: "VoteDetail",
  components: {
    VueConfirmDialog,
    VotePieChart,
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
        let totalMinwon = (data.acount / (data.acount + data.bcount)) * 100;
        let resolveMinwon = (data.bcount / (data.acount + data.bcount)) * 100;
        if (totalMinwon == 0 && resolveMinwon == 0) {
          totalMinwon = 0;
          resolveMinwon = 0;
        } else if (totalMinwon == 0) {
          resolveMinwon = 100;
        } else if (resolveMinwon == 0) {
          totalMinwon = 100;
        }

        const barAnimation1 = setInterval(() => {
          if (t1 >= 1) {
            bar1.style.width = t1 + "%";
            bar1.innerHTML = t1 + "%";
          } else if (t1 == 0) {
            bar1.style.width = t1 + "%";
          }

          t1++ >= totalMinwon && clearInterval(barAnimation1);
        }, 10);

        const barAnimation2 = setInterval(() => {
          if (t2 >= 1) {
            bar2.style.width = t2 + "%";
            bar2.innerHTML = t2 + "%";
          } else if (t2 == 0) {
            bar2.style.width = t2 + "%";
          }

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
      chartOptionList: ["연령", "성별"],
      ageOption: 10,
      ageOptionList: [10, 20, 30, 40, 50, 60],
      genderOption: "F",
      genderOptionList: ["F", "M"],
      comment: "",
      isUpdated: false,
      commentForUpdate: "",
      modifyCommentId: 0,
      isModify: false,
      token: "",
      vote: [],
      ballotId: 0,
      reload: 0,
      flag: false,
      data: {
        isShow: false,
        voteId: 0,
        title: "투표를 삭제하시겠습니까?",
        no: "취소",
        yes: "삭제",
        mode: "7",
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
    getUpdateComment() {
      const updateContent = document.getElementById("clickedComment").value;
      this.commentForUpdate = updateContent;
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
      this.data5.isShow = true;
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
      this.data.isShow = true;
      this.data.voteId = this.vote.voteId;
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
    updateComment(commentId, preContent) {
      if (!this.flag) {
        this.isUpdated = true;
        this.modifyCommentId = commentId;
        this.commentForUpdate = preContent;
        this.flag = true;
      } else {
        let content = { content: this.commentForUpdate };
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
  display: flex;
  justify-content: center;
  align-items: center;
}

.input-update {
  width: 145px;
}
</style>
