<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="title">로그아웃</div>
      <div class="logout-title">로그아웃 되었습니다.</div>
      <div class="return">
        <a href="../mainpage" target="_blank">메인페이지로 돌아가기</a>
      </div>
      <div class="content_check">
        <div>
          <button class="button" @click="regist()" style="opacity: 0.6">
            회원가입
          </button>
          <button class="button" @click="login()">로그인</button>
        </div>
      </div>
      <footer-view class="footer" />
    </div>
  </div>
</template>

<script>
import HeaderView from "../common/HeaderView.vue";
import FooterView from "../common/FooterView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
  },
  methods: {
    regist() {
      this.$router.push({ name: "regist" });
    },
    // login() {
    //   this.$router.push({ path: "/mypage/login" });
    // },
  },
};
</script>

<style scoped>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}
.return {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
  font-size: 20px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
}
.content_check {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px;
}
.button {
  background-color: #007aff;
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  margin-top: 10px;
  margin-left: 10px;
  color: white;
  text-align: center;
  text-decoration: none;
  font-size: 14px;
  font-family: "GmarketSansTTFLight";
}

.logout-title {
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 24px;
  line-height: 22px;
  text-align: center;
  margin-top: 45px;
}
</style>
