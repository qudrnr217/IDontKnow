<template>
  <div class="detail">
    <div class="alarm">
      <img src="../../assets/icon/etc.png" alt class="alarm-btn" />
      <div class="hi" @click="data.isShow = true">
        <vue-confirm-dialog
          :data="data"
          v-if="data.isShow"
        ></vue-confirm-dialog>
        <vue-confirm-dialog :data="data4"></vue-confirm-dialog>
      </div>
    </div>
    <div class="title-card">
      <div class="progress">
        <div class="progress-title">진행중</div>
      </div>
      <div class="vote-title">지금까지 이런 맛은 없었다.</div>
      <div class="vote-writer">작성자 : 수원왕갈비</div>
      <div class="vote-date">2022.09.10 오후 13:00</div>
    </div>
    <div class="vote-box">
      <div class="select1" @click="changeShow1()" v-show="!show1">지금까지</div>
      <div class="select1-1" v-show="show1">지금까지</div>
      <div class="select2" @click="changeShow2()" v-show="!show2">노랑통닭</div>
      <div class="select2-1" v-show="show2">노랑통닭</div>
    </div>
    <div class="others">
      <div
        class="vote-btn"
        @click="(cur_vote = true), (data2.isShow = true)"
        v-if="!cur_vote"
      >
        투표하기
      </div>
      <div
        class="vote-btn-cancle"
        @click="(cur_vote = false), (data3.isShow = true)"
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
    <!-- <div class="writer">
      <div class="vote-btn-close">투표마감</div>
      <div class="vote-btn-end">투표하기</div>
    </div> -->
    <div class="detail-box">
      <div class="vote-percent-title">투표율</div>
      <div class="vote-percent-bar">
        <vote-bar-chart />
      </div>
    </div>

    <div class="comment-title">댓글</div>

    <div class="comment"><vote-comment-list /></div>
    <div class="comment-input">
      <img src="../../assets/icon/avatar.png" alt="" />
      <input type="text" class="comment-box" />
      <img src="../../assets/icon/send.png" alt="" class="send-btn" />
    </div>
  </div>
</template>

<script>
import VoteCommentList from "./VoteCommentList.vue";
import VoteBarChart from "./VoteBarChart.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
export default {
  name: "VoteDetail",
  props: ["voteId", "category"],
  components: {
    VoteCommentList,
    VoteBarChart,
    VueConfirmDialog,
  },
  data() {
    return {
      show1: false,
      show2: false,
      cur_vote: false,
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
    changeShow1() {
      this.show1 = true;
      this.show2 = false;
    },
    changeShow2() {
      this.show1 = false;
      this.show2 = true;
    },
  },
  created() {
    // vote 값을 api로 불러오면 고민할 필요가 없는 부분 !
    this.$emit("pass", this.$route.params.category);
    // console.log(this.$route.params.voteId);
    // console.log(this.$route.params.category);
  },
};
</script>

<style scoped>
/* 알람 */
.alarm {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: flex-end;
}

.alarm > .alarm-btn {
  margin-top: 5px;
  margin-right: 20px;
}

/* title-card */
.title-card {
  width: 353px;
  height: 221px;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 10px;
  margin: 0 auto;
  margin-top: 5%;
  background-image: linear-gradient(
      rgba(255, 255, 255, 0.5),
      rgba(255, 255, 255, 0.5)
    ),
    url("~@/assets/image/교촌치킨.jpg");
  background-position: center center;
  background-size: 100% 100%;
}

.progress {
  background: #ffffff;
  border-radius: 10px;
  width: 47px;
  height: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 8px;
  /* background-color: red; */

  position: relative;
  top: 8px;
}

.progress-title {
  font-weight: 300;
  font-size: 10px;
  line-height: 10px;

  font-family: "GmarketSansTTFLight";
}

.vote-title {
  font-family: "GmarketSansTTFBold";
  font-weight: 700;
  font-size: 20px;
  line-height: 23px;

  margin-top: 30px;
  margin-left: 20px;
}

.vote-writer {
  font-family: "GmarketSansTTFLight";
  font-weight: 1000;
  font-size: 15px;
  line-height: 15px;
  margin-top: 20px;
  margin-left: 20px;
}

.vote-date {
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 15px;
  line-height: 15px;
  margin-left: 53%;
  margin-top: 90px;
}

/* vote box*/
.vote-box {
  display: flex;
  margin-top: 20px;
}

.select1 {
  width: 163px;
  height: 169px;
  background: #ffffff;
  border: 2px solid #7b61ff;
  border-radius: 10px;
  margin-left: 22px;

  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 20px;
  line-height: 23px;
}

.select1:hover {
  background: rgba(123, 97, 255, 0.6);
}

.select1-1 {
  width: 163px;
  height: 169px;
  background: #ffffff;
  border: 2px solid #7b61ff;
  border-radius: 10px;
  margin-left: 22px;

  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 20px;
  line-height: 23px;
  background: rgba(123, 97, 255, 1);
}

.select2 {
  width: 163px;
  height: 169px;
  background: #ffffff;
  border: 2px solid #ff9500;
  border-radius: 10px;
  margin-left: 7px;

  display: flex;
  justify-content: center;
  align-items: center;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 20px;
  line-height: 23px;
}

.select2-1 {
  width: 163px;
  height: 169px;
  background: #ffffff;
  border: 2px solid #ff9500;
  border-radius: 10px;
  margin-left: 7px;

  display: flex;
  justify-content: center;
  align-items: center;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 20px;
  line-height: 23px;
  background: rgba(255, 149, 0, 1);
}

.select2:hover {
  background: rgba(255, 149, 0, 0.6);
}
/* 투표 버튼 */
.vote-btn {
  width: 340px;
  height: 61px;
  background: #007aff;
  border-radius: 10px;

  margin-top: 15px;
  margin-left: 22px;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 25px;
  line-height: 29px;
  color: #ffffff;

  display: flex;
  justify-content: center;
  align-items: center;

  cursor: pointer;
}

.vote-btn-cancle {
  width: 340px;
  height: 61px;
  background: #007aff;
  border-radius: 10px;

  margin-top: 15px;
  margin-left: 22px;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 25px;
  line-height: 29px;
  color: #ffffff;

  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.vote-btn-close {
  width: 340px;
  height: 61px;
  background: #ff3b30;
  border-radius: 10px;

  margin-top: 15px;
  margin-left: 22px;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 25px;
  line-height: 29px;
  color: #ffffff;

  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.vote-btn-end {
  width: 340px;
  height: 61px;
  background: #8e8e93;
  border-radius: 10px;

  margin-top: 15px;
  margin-left: 22px;

  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 25px;
  line-height: 29px;
  color: #ffffff;

  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

/* 투표율 */
.vote-percent-title {
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 15px;
  line-height: 15px;
  margin-left: 22px;
  margin-top: 20px;
}

.vote-percent-bar {
  width: 340px;
}

.comment-title {
  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 17px;
  line-height: 22px;
  margin-top: 15px;
  margin-left: 15px;
}

/* comment-input */
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
</style>
