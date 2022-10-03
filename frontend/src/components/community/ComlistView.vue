<template>
  <div class="vote-card">
    <div class="com-header">
      <div class="com-header-title">{{ cards.title }}</div>
    </div>
    <div class="writer">
      <div class="writer-name">작성자 : {{ cards.name }}</div>
    </div>
    <div class="menu">
      <div class="select-box1">
        <div class="select1">{{ cards.optionA }}</div>
      </div>
      <div class="vs">vs</div>
      <div class="select-box2">
        <div class="select2">{{ cards.optionB }}</div>
      </div>
    </div>
    <div class="com-footer">
      <div class="tag1"># {{ cards.category }}</div>
      <div class="tag2"># {{ cards.subCategory }}</div>
      <div class="views">
        <img src="../../assets/icon/views.png" alt="" class="view-btn" />
        {{ cards.hitCount }}
      </div>
      <div class="chat">
        <img src="../../assets/icon/chat.png" alt="" class="chat-btn" />
        {{ cards.commentCount }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    cards: Object,
  },
  mounted() {
    // console.log("cards:" + this.cards);
  },
};
</script>

<style scoped>
.vote-card {
  width: 320px;
  height: 170px;
  border-radius: 10px;
  margin: 0 auto;
  background-image: linear-gradient(
      rgba(255, 255, 255, 0.5),
      rgba(255, 255, 255, 0.5)
    ),
    url("~@/assets/image/교촌치킨.jpg");
  background-position: center center;
  background-size: 100% 100%;
}

/* header */

.com-header {
  width: 100%;
  height: 45px;
  font-weight: 700;
  font-size: 15px;
  display: flex;
  align-items: center;
  /* background-color: antiquewhite; */
}

.com-header > .com-header-title {
  margin-left: 15px;
  /* font-family: "GmarketSansTTFLight"; */
  font-family: "GmarketSansTTFBold";
  font-style: normal;
}

/* writer */
.writer {
  width: 100%;
  height: 30px;
  /* background-color: aquamarine; */
  display: flex;
}

.writer > .writer-name {
  margin-left: 15px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  font-weight: 800;
  font-size: 11px;
  line-height: 11px;
}

/* menu */
.menu {
  display: flex;
  /* background-color: bisque; */
  height: 68px;
}

.menu > .select-box1 {
  box-sizing: border-box;
  width: 120px;
  height: 50px;

  background: #ffffff;
  border: 3px solid #7b61ff;
  border-radius: 10px;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-left: 15px;
}

.menu > .select-box2 {
  box-sizing: border-box;
  width: 120px;
  height: 50px;

  background: #ffffff;
  border: 3px solid #ff9500;
  border-radius: 10px;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-left: 15px;
}

.menu > .vs {
  margin-left: 15px;
  position: relative;
  top: 14px;
  line-height: 17px;
  font-weight: 400;
  font-size: 14px;
}

.select-box1 > .select1 {
  font-weight: 500;
  font-size: 13px;
  font-family: "GmarketSansTTFMedium";
  font-style: normal;
}

.select-box2 > .select2 {
  font-weight: 500;
  font-size: 13px;
  font-family: "GmarketSansTTFMedium";
  font-style: normal;
}

/* com-footer */
.com-footer {
  display: flex;
  justify-items: center;
}

.tag1,
.tag2 {
  width: 50px;
  height: 22px;
  font-weight: 500;
  font-size: 11px;
  line-height: 15px;
  /* background-color: antiquewhite; */
  font-family: "GmarketSansTTFMedium";
  font-style: normal;
}
.tag1 {
  margin-left: 20px;
}

.views,
.chat {
  width: 80px;
  height: 22px;
  display: flex;
  align-items: center;
  /* background-color: #7b61ff; */
  font-weight: 500;
  font-size: 11px;
  line-height: 15px;
  position: relative;
  bottom: 4px;
  left: 10px;
}

.views {
  margin-left: 100px;
}

/* img */
.view-btn {
  width: 14px;
}

.chat-btn {
  width: 12px;
}
</style>
