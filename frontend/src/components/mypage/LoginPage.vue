<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="title">로그인</div>
      <div class="content-box1">
        <div class="content">
          <div class="content_title">E-MAIL</div>
          <input
            class="content_input"
            type="text"
            id="email"
            v-model="email"
            placeholder="이메일"
          />
        </div>
      </div>
      <div class="content-box2">
        <div class="content">
          <div class="content_title">PASSWORD</div>
          <input
            class="content_input"
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호"
          />
        </div>
      </div>

      <div class="content_check">
        <button
          class="password_reset_button"
          @click="(data2.isShow = true), (data2.dialog = true)"
        >
          비밀번호 재설정
        </button>
        <vue-confirm-dialog
          :data="data2"
          v-if="data2.isShow"
        ></vue-confirm-dialog>
      </div>
      <div class="content_check">
        <div>
          <button class="button" @click="regist()" style="opacity: 0.6">
            회원가입
          </button>
          <button class="button" @click="data.isShow = true">로그인</button>
          <vue-confirm-dialog
            :data="data"
            v-if="data.isShow"
          ></vue-confirm-dialog>
        </div>
      </div>
      <footer-view class="footer" />
    </div>
  </div>
</template>

<script>
import HeaderView from "../common/HeaderView.vue";
import FooterView from "../common/FooterView.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
export default {
  components: {
    HeaderView,
    FooterView,
    VueConfirmDialog,
  },

  data() {
    return {
      email: "",
      password: "",
      data: {
        isShow: false,
        title: "로그인 실패",
        message: "이메일과 비밀번호를 다시 확인해주세요.",
        yes: "확인",
      },
      data2: {
        dialog: false,
        isShow: false,
        title: "비밀번호를 재설정 하시겠습니까?",
        message: "입력한 이메일로 메일이 발송됩니다.",
        yes: "보내기",
        no: "취소",
      },
    };
  },

  methods: {
    regist() {
      this.$router.push({ name: "regist" });
    },
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
.content {
  margin-top: 25px;
  display: flex;
  background-color: #ffffff;
  width: 360px;
  height: 40px;
  border: none;
  border-radius: 10px;
  /* 
  margin-left: 0 auto;
  margin-right: 0 auto; */
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content_title {
  display: flex;
  /* justify-content: center;
   */

  align-items: center;
  /* margin: 0 auto; */
  flex-grow: 1;
  font-size: 15px;
  font-family: "GmarketSansTTFLight";
  vertical-align: center;
}
.content_input {
  /* justify-content: flex-end; */
  flex-grow: 3;
  border: 0 solid #ffffff;
  outline: none;

  font-family: "GmarketSansTTFLight";
  height: 4;
  text-align: right;
}
.content_check {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px;
}
.password_reset_button {
  background-color: transparent;
  border: none;
  border-radius: 6px;
  width: 150px;
  height: 30px;
  color: #007aff;
  text-align: center;
  text-decoration: none;
  margin-top: 10px;
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 17px;
  line-height: 22px;
  cursor: pointer;
}
.button {
  background-color: #007aff;
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  margin: 10px;
  color: white;
  text-align: center;
  text-decoration: none;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
}
.content-box1,
.content-box2 {
  width: 100%;
  display: flex;
  justify-content: center;
  align-content: center;
}
</style>
