<template>
  <div class="bgd-common">
    <div class="body">
      <div class="title">모르에게 물어봐</div>
      <div class="title-description">로그아웃</div>
      <div class="box-regist" style="background: transparent; height: 10px" />
      <div class="box-regist">
        <router-link to="/home" style="font-size: 24px"
          >메인 페이지로 돌아가기</router-link
        >
      </div>
      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
        <button
          class="btn-rectangle-small"
          @click="regist()"
          style="background-color: #58ccff"
        >
          회원가입
        </button>
        <button
          class="btn-rectangle-small"
          @click="login()"
          style="background-color: #1187cf"
        >
          로그인
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  methods: {
    regist() {
      this.$router.push({ name: "regist" });
    },
    login() {
      this.$router.push({ path: "/mypage/login" });
    },
  },
};
</script>

<style scoped>
.bgd-common {
  background-image: url("~@/assets/image/background/blue.png");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  margin: auto;
}
.body {
  padding: 10px;
}
.title {
  font-family: "ONEMobileTitle";
  font-size: 24px;
  text-align: left;
}
.title-description {
  font-family: "ONEMobileTitle";
  font-size: 20px;
  text-align: left;
}
.box-regist {
  display: flex;
  justify-content: space-around;
  background-color: transparent;
  border-radius: 10px;
  margin: 0px 10px 10px 10px;
  padding: 0px 10px 0px 10px;
  font-family: "ONEMobileTitle";
}
.btn-rectangle-small {
  font-family: "ONEMobileTitle";
  font-size: 8px;
  display: flex;
  width: 80px;
  height: 30px;
  border-radius: 10px;
  margin-left: 10px;
  align-items: center;
  justify-content: center;
}
</style>
