<template>
  <div class="bgd-common">
    <div class="body">
      <div class="title">모르에게 물어봐</div>
      <div class="title-description">로그인</div>
      <div class="box-regist" style="background: transparent; height: 10px" />

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">E-Mail</div>
          <input
            class="input-regist"
            type="email"
            id="email"
            v-model="email"
            placeholder="이메일"
          />
        </div>
      </div>

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">Password</div>
          <input
            class="input-regist"
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호"
          />
        </div>
      </div>

      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
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
          @click="data.isShow = true"
          style="background-color: #1187cf"
        >
          로그인
        </button>
        <vue-confirm-dialog
          :data="data"
          v-if="data.isShow"
        ></vue-confirm-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
export default {
  components: {
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
  background-color: #ffffff;
  border-radius: 10px;
  margin: 0px 10px 10px 10px;
  padding: 0px 10px 0px 10px;
}
.content-regist {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-content: center;
  width: 100%;
}
.title-regist {
  font-family: "ONEMobileTitle";
  font-size: 16px;
  vertical-align: center;
  padding: 10px;
}
.input-regist {
  justify-content: flex-end;
  border: none;
  font-family: "ONEMobileTitle";
  font-size: 16px;
  text-align: right;
}
.text-regist {
  font-family: "ONEMobileTitle";
  font-size: 12px;
  display: flex;
  align-items: center;
  margin-right: 10px;
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
  font-family: "ONEMobileTitle";
  font-size: 16px;
  line-height: 22px;
  cursor: pointer;
}
</style>
