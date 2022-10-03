<template>
  <div class="body">
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">로그인</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        결정왕 김모르에 오신 것을 환영합니다 !
      </div>
    </div>
    <div class="box-column">
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="title-regist text-h3">E-Mail</div>
        <input
          class="input-regist"
          type="email"
          id="email"
          style="font-size: 16px; border-radius: 10px"
          v-model="email"
          placeholder="이메일"
        />
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="title-regist text-h3">Password</div>
        <input
          class="input-regist"
          type="password"
          id="password"
          style="font-size: 16px; border-radius: 10px"
          v-model="password"
          placeholder="비밀번호"
        />
      </div>
    </div>

    <div class="box-btn-right">
      <!-- TODO: 비밀번호 재설정 이메일 발송 api 연결 필요 -->
      <div
        class="password_reset_button text-h3 blue-3-text"
        @click="(data2.isShow = true), (data2.dialog = true)"
      >
        비밀번호 재설정
      </div>
      <vue-confirm-dialog
        :data="data2"
        v-if="data2.isShow"
      ></vue-confirm-dialog>
    </div>
    <div class="box-btn-right">
      <div class="btn-rectangle-small blue-2" @click="regist()">회원가입</div>
      <div class="btn-rectangle-small blue-3" @click="login()">로그인</div>
      <vue-confirm-dialog :data="data" v-if="data.isShow"></vue-confirm-dialog>
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
      this.$router.push({ name: "userRegist", path: "/profile/regist" });
    },
    login() {
      //  로그인 api 호출
      // response 에러일 때, 알림창
      // (data.isShow = true)
      // home 으로 이동
    },
  },
};
</script>

<style scoped>
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
  vertical-align: center;
  padding: 4px;
}
.input-regist {
  justify-content: flex-end;
  border: none;
  font-family: "ONEMobileTitle";
  font-size: 16px;
  text-align: right;
}

.btn-rectangle-small {
  margin-left: 10px;
}
.password_reset_button {
  background-color: transparent;
  border: none;
  border-radius: 6px;
  width: 150px;
  height: 30px;
  text-align: right;
  text-decoration: none;
  /* margin-top: 10px; */
  /* line-height: 22px; */
  cursor: pointer;
}
</style>
