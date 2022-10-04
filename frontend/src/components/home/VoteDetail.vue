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
          @click="cancelVote, (data3.isShow = true)"
          v-else
        >
          투표취소
        </div>

        <!-- 투표 팝업 창 -->
        <vue-confirm-dialog
          :data="data2"
          v-if="data2.isShow"
        ></vue-confirm-dialog>
        <vue-confirm-dialog
          :data="data3"
          v-if="data3.isShow"
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
        <div class="box-align-center">
          <!-- 차트를 넣으면 아래 div 삭제 -->
          <div class="vote-percent-bar">통계가 나오도록 변경 필요 !</div>
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
        <!-- <div class="vote-percent-bar">댓글이 나오도록 변경 필요 ! :value="`${comment.commentId}`"</div> -->
        <div class="box-comment-column">
          <div
            class="box-comment-row"
            v-for="comment in vote.commentList"
            :key="comment.commentId"
          >
            <div v-if="comment.checkAuthor" class="box-comment-row-right">
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
                  <div class="box-comment-text">{{ comment.content }}</div>
                </div>
                <div class="box-comment-btn-row box-comment-btn-left">
                  <div
                    class="btn-rectangle-tiny red-text text-h5"
                    @click="updateComment()"
                    :value="`${comment.commentId}`"
                  >
                    수정
                  </div>
                  <div
                    class="btn-rectangle-tiny text-h5"
                    @click="deleteComment()"
                    :value="`${comment.commentId}`"
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
            <div v-else>
              <div class="box-comment-column comment-profile-box">
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
                  <!-- <div v-if="isUpdated" class="box-comment-text">
                    <input
                      type="text"
                      v-model="commentForUpdate"
                      style="border: none"
                    />
                  </div> -->
                  <div class="box-comment-text">
                    {{ comment.content }}
                  </div>
                </div>
                <div class="box-comment-btn-row box-comment-btn-right">
                  <div
                    class="btn-rectangle-tiny red-text text-h5"
                    @click="updateComment()"
                    :value="`${comment.commentId}`"
                  >
                    수정
                  </div>
                  <div
                    class="btn-rectangle-tiny text-h5"
                    @click="deleteComment()"
                    :value="`${comment.commentId}`"
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
export default {
  name: "VoteDetail",
  props: ["voteId"],
  components: {
    // VoteBarChart,
    VueConfirmDialog,
    // VoteCommentList,
  },
  computed: {
    checkStatus() {
      return this.vote.status ? "종료" : "진행";
    },
  },
  data() {
    return {
      clickedOption: 0, // 0 이면 안눌린거, 1이면 A, 2면 B
      isOpened: false,
      chartOption: "연령",
      chartOptionList: ["연령", "성별", "거주지"],
      comment: "",
      commentForUpdate: "",
      vote: {
        voteId: 1,
        category: "메뉴",
        subCategory: "치킨",
        title:
          "지금까지 이런 맛은 없었다.지금까지 이런 맛은 없었다.지금까지 이런 맛은 없었다.지금까지까지",
        userId: 2,
        name: "수원왕갈비",
        hitCount: 12000,
        commentCount: 2,
        optionA: "교촌치킨교촌치킨교촌치킨",
        optionB: "노랑통닭노랑통닭노랑통닭",
        createdAt: "2022.09.27 06:46",
        status: false,
        result: null,
        ballotId: null,
        voted: null,
        commentList: [
          {
            commentId: 1,
            userId: 2,
            name: "수원왕갈비",
            content: "당연히 교촌",
            createdAt: "2022.09.30 06:16",
            checkAuthor: true,
          },
          {
            commentId: 2,
            userId: 1,
            name: "치킨마니아",
            content: "당연히 노통",
            createdAt: "2022.09.30 06:26",
            checkAuthor: false,
          },
          {
            commentId: 3,
            userId: 2,
            name: "수원왕갈비",
            content:
              "당연히 교촌당연히 교촌당연히 교촌당연히 교촌당연히 교촌당연히 교촌당연히 교촌",
            createdAt: "2022.09.30 06:36",
            checkAuthor: true,
          },
          {
            commentId: 4,
            userId: 1,
            name: "치킨마니아",
            content:
              "당연히 노통당연히 노통당연히 노통당연히 노통당연히 노통당연히 노통당연히 노통당연히 노통당연히 노통",
            createdAt: "2022.09.30 06:46",
            checkAuthor: false,
          },
        ],
        acount: 1,
        bcount: 1,
      },

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
      },
      data3: {
        isShow: false,
        title: "투표를 취소하시겠습니까?",
        no: "취소",
        yes: "투표취소",
      },
      data4: {
        isShow: false,
        title: "투표삭제 완료",
        message: "투표가 정상적으로 삭제되었습니다.",
        yes: "완료",
        no: null,
      },
    };
  },
  methods: {
    changeClickedOptionA() {
      if (this.vote.voted === null && !this.vote.status) {
        if (this.clickedOption === 1) {
          this.clickedOption = 0;
        } else {
          this.clickedOption = 1;
        }
      }
    },
    changeClickedOptionB() {
      if (this.vote.voted === null && !this.vote.status) {
        if (this.clickedOption === 2) {
          this.clickedOption = 0;
        } else {
          this.clickedOption = 2;
        }
      }
    },
    changeStatus() {
      // 종료 관련 팝업창 띄우기
      // 작성자가 투표 종료하는 api 호출
      // 새로고침
      location.reload();
    },
    clickVote() {
      // 투표 참여하는 api 호출 (clickedOption 구분해서 choice 전달)
      // 새로고침
      location.reload();
    },
    cancelVote() {
      // 투표 취소하는 api 호출 (vote.ballotId 활용)
      // 새로고침
      location.reload();
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
    },
    updateComment() {
      // 수정 관련 팝업창 띄우기
      // 댓글 작성자와 현재 로그인한 회원이 동일한지 확인
      // 권한 없음 표시 or 수정하기 위한 input으로 변경
    },
    deleteComment() {
      // 새로고침
      location.reload();
    },
    sendComment() {
      // this.comment 값을 활용하여 댓글 생성 api 호출
      // 새로고침
      location.reload();
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
.body {
  height: 105vh;
}
</style>
