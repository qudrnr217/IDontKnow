<template>
  <div>
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">회원정보</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        비밀번호 변경 페이지 입니다 !
      </div>
    </div>
    <div class="profile-image-box">
      <img class="profile-image" src="@/assets/image/김게따.png" />
    </div>
    <div class="box-column">
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">기존</div>
        <input
          class="input-align-right"
          type="password"
          id="password"
          style="font-size: 16px; border-radius: 10px"
          v-model="data.password"
          placeholder="비밀번호"
        />
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">변경</div>
        <input
          class="input-align-right"
          type="password"
          id="newPassword"
          style="font-size: 16px; border-radius: 10px"
          v-model="data.newPassword"
          placeholder="새 비밀번호"
        />
      </div>
      <div class="box-btn-right" v-if="valid.password">
        <div class="text-h5 red-text">
          영문, 숫자, 특수문자를 조합하여 입력해주세요.(8~16자).
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">재확인</div>
        <input
          class="input-align-right"
          type="password"
          id="confirmPassword"
          style="font-size: 16px; border-radius: 10px"
          v-model="data.confirmPassword"
          placeholder="비밀번호 확인"
        />
      </div>
      <div
        class="box-btn-right"
        v-if="!valid.password && data.confirmPassword != 0 && !checkPassword()"
      >
        <div class="text-h5 red-text">입력한 비밀번호가 다릅니다.</div>
      </div>
      <div class="box-btn-right">
        <div class="btn-rectangle-medium blue-2" @click="changePassword()">
          비밀번호 변경
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { changePassword } from "@/api/mypage";
import { mapState } from "vuex";
export default {
  components: {},
  data() {
    return {
      valid: {
        email: false,
        password: false,
        newPassword: false,
      },
      pw: {
        curPassword: "",
        newPassword: "",
      },
      data: {
        password: "",
        newPassword: "",
        confirmPassword: "",
      },
    };
  },
  methods: {
    changePassword() {
      // TODO: 비밀번호 변경 api 호출
      // userInfo로 이동
      if (
        !this.checkPassword() ||
        this.data.password == "" ||
        this.data.newPassword == "" ||
        this.data.confirmPassword == ""
      ) {
        console.log("비밀번호 변경 안돼!");
        return;
      }
      this.pw.curPassword = this.data.password;
      this.pw.newPassword = this.data.confirmPassword;
      changePassword(
        this.accessToken,
        this.userId,
        this.pw,
        (response) => {
          console.log(response.data);
          this.$router.push({ name: "userInfo", path: "/profile/userInfo" });
        },
        (error) => {
          console.log(error);
          console.log("기존 비밀번호 틀림");
        }
      );
    },
    confirmPassword() {
      const validatePassword =
        /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      if (!validatePassword.test(this.data.newPassword)) {
        this.valid.password = true;
        return;
      }
      this.valid.password = false;
    },
    checkPassword() {
      if (this.data.newPassword === this.data.confirmPassword) {
        return true;
      }
      return false;
    },
  },
  computed: {
    ...mapState("userStore", ["userId", "accessToken"]),
    password() {
      return this.data.newPassword;
    },
  },
  watch: {
    password() {
      this.confirmPassword();
    },
  },
};
</script>

<style scoped></style>
