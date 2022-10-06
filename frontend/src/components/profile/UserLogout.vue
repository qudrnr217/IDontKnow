<template>
  <div class="modu">
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">로그아웃</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        결정왕 김모르를 이용해주셔서 감사합니다 !
      </div>
    </div>
    <div class="profile-image-box">
      <img class="profile-image" src="@/assets/image/김모르.png" />
    </div>
    <div class="box-column">
      <div class="box-align-center">
        <div
          class="btn-rectangle-big blue-0 text-h2 blue-4-text"
          @click="backToHome()"
        >
          홈으로🏠
        </div>
      </div>
      <div class="box-btn-right">
        <div class="btn-rectangle-small blue-0" @click="regist()">회원가입</div>
        <div class="btn-rectangle-small blue-1" @click="login()">로그인</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  components: {},
  computed: {
    ...mapState({
      started: (state) => state.data.started,
    }),
  },
  methods: {
    regist() {
      this.update_started();
      this.$router.push({ name: "userRegist", path: "/profile/regist" });
    },
    login() {
      this.update_started();
      this.$router.push({ name: "userLogin", path: "/profile/login" });
    },
    backToHome() {
      this.$router.push({ name: "home", path: "/" });
    },
    ...mapActions(["update_started"]),
  },
};
</script>

<style scoped>
.btn-rectangle-small {
  margin-left: 10px;
}
</style>
