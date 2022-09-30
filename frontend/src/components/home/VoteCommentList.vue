<template>
  <!-- comment-box에는 v-for를 사용하자 -->
  <div class="comment-box">
    <div class="writer">
      <div class="comment-writer-date">2022.09.15 09:35</div>
      <div class="comment-writer">
        <div class="comment-user-wirter">교촌 레허반반 먹을까요?...</div>
      </div>
      <div class="comment-writer-footer">
        <div class="w-report">신고</div>
        <div class="w-delete">삭제</div>
        <div class="w-writer-name">
          <img
            src="../../assets/icon/avatar.png"
            alt=""
            class="w-comment-avatar"
          />
          수원왕갈비(작성자)
        </div>
      </div>
    </div>
    <div class="others">
      <div class="comment-others-date">2022.09.15 09:35</div>
      <div class="comment-others">
        <div class="comment-user-others">
          노통에 왕큰 후라이드가 진리입니다~ㅋㅋ
        </div>
      </div>
      <div class="comment-others-footer">
        <div class="o-writer-name">
          치킨에진심인사람
          <img
            src="../../assets/icon/avatar.png"
            alt=""
            class="o-comment-avatar"
          />
        </div>
        <div class="o-report">신고</div>
        <div class="o-delete">삭제</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "VoteCommentList",
};
</script>

<style scoped>
.comment-writer-date {
  margin-left: 175px;

  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 12px;
  line-height: 22px;
}

/* comment-writer */
.comment-writer {
  display: flex;
  margin-left: 20px;
}

.comment-writer {
  background-image: linear-gradient(
      rgba(255, 255, 255, 0),
      rgba(255, 255, 255, 0)
    ),
    url("~@/assets/image/bubble.png");
  background-position: center center;
  background-size: 100% 100%;
  width: 267px;
  min-height: 56px;
  position: relative;
}

.comment-user-wirter {
  margin-left: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  max-width: 247px;
  /* background-color: red; */
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 15px;
  line-height: 22px;
  color: #ffffff;
}

.w-writer-name > .w-comment-avatar {
  width: 42px;
  height: 42px;
  position: relative;
  bottom: 40px;
  left: 55px;
}

.comment-writer-footer {
  display: flex;
}

.w-report {
  margin-left: 30px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 10px;
  line-height: 22px;
  /* or 220% */

  letter-spacing: -0.408px;

  color: #ff3b30;
}

.w-delete {
  margin-left: 5px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 10px;
  line-height: 22px;
  /* or 220% */

  letter-spacing: -0.408px;

  color: #000000;
}

.w-writer-name {
  display: flex;
  margin-left: 165px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 12px;
  line-height: 22px;
  /* or 183% */

  letter-spacing: -0.408px;

  color: #000000;
  /* background-color: red; */
}

/* others */

.comment-others-date {
  margin-left: 85px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 12px;
  line-height: 22px;
}

.comment-others {
  display: flex;
  margin-left: 70px;
  position: relative;
}

.comment-others {
  background-image: linear-gradient(
      rgba(255, 255, 255, 0),
      rgba(255, 255, 255, 0)
    ),
    url("~@/assets/image/bubble2.png");
  background-position: center center;
  background-size: 100% 100%;
  width: 267px;
  min-height: 56px;
}

.comment-user-others {
  margin-left: 15px;
  padding-top: 10px;
  padding-bottom: 10px;
  max-width: 247px;
  /* background-color: red; */
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 15px;
  line-height: 22px;
  color: black;
}

.o-comment-avatar {
  width: 42px;
  height: 42px;
  position: relative;
  right: 65px;
  bottom: 40px;
}

.comment-others-footer {
  display: flex;
}

.o-report {
  margin-left: 190px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 10px;
  line-height: 22px;
  /* or 220% */

  letter-spacing: -0.408px;

  color: #ff3b30;
}

.o-delete {
  margin-left: 5px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 10px;
  line-height: 22px;
  /* or 220% */

  letter-spacing: -0.408px;

  color: #000000;
}

.o-writer-name {
  display: flex;
  margin-left: 5px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 12px;
  line-height: 22px;
  /* or 183% */

  letter-spacing: -0.408px;

  color: #000000;
}
</style>
