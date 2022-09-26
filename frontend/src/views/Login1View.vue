<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="title">로그인</div>
      <div class="content">
        <div class="content_title">E-mail</div>
        <input
          class="content_input"
          type="text"
          id="email"
          v-model="email"
          placeholder="이메일"
        />
      </div>
      <div class="content">
        <div class="content_title">Password</div>
        <input
          class="content_input"
          type="password"
          id="password"
          v-model="password"
          placeholder="비밀번호"
        />
      </div>
      <div class="content_check">
        <button class="password_reset_button" @click="click">
          비밀번호 재설정
        </button>
      </div>
      <div class="content_check">
        <div>
          <button class="button" @click="click" style="opacity: 0.6">
            회원가입
          </button>
          <button class="button" @click="click">로그인</button>
        </div>
      </div>
      <footer-view class="footer" />
    </div>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
  },
};
</script>

<style>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}
.content {
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #ffffff;
  width: 300px;
  height: 30px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content_title {
  justify-content: center;
  flex-grow: 1;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  vertical-align: center;
}
.content_input {
  justify-content: flex-end;
  flex-grow: 3;
  border: none;
  font-family: "GmarketSansTTFLight";
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
  width: 100px;
  height: 30px;
  color: #007aff;
  text-align: center;
  text-decoration: none;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
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
</style>
