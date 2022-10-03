<template>
  <div class="body">
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
          v-model="password"
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
          v-model="newPassword"
          placeholder="새 비밀번호"
        />
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">재확인</div>
        <input
          class="input-align-right"
          type="password"
          id="confirmPassword"
          style="font-size: 16px; border-radius: 10px"
          v-model="confirmPassword"
          placeholder="비밀번호 확인"
        />
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
export default {
  components: {},
  data() {
    return {
      password: "",
      newPassword: "",
      confirmPassword: "",
    };
  },
  methods: {
    changePassword() {
      // TODO: 비밀번호 변경 api 호출
      // userInfo로 이동
    },
  },
};
</script>

<style scoped></style>
