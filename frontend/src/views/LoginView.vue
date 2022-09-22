<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <div class="title">로그인</div>
        <div class="textcontainer">
          <div class="columnname">E-Mail</div>
          <div class="columntextfield">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="이메일"
              v-model="idValue"
            />
          </div>
        </div>
        <div class="textcontainer">
          <div class="columnname">Password</div>
          <div class="columntextfield">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="비밀번호"
              v-model="idValue"
            />
          </div>
        </div>
        <iButton>비밀번호 재설정</iButton>
        <div>
          <button class="button1" opacity="0.2">회원가입</button>
          <button class="button2">로그인</button>
        </div>
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import iTextField from "../components/common/iTextField.vue";
import iButton from "../components/common/iButton.vue";

export default {
  components: {
    HeaderView,
    FooterView,
    iTextField,
    iButton,
  },
};
</script>

<style scoped>
.textcontainer {
  display: flex;
  outline: none;
  padding: 0.4rem 0.8rem;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
}
.button1 {
  background-color: #007aff; /* Green */
  border: none;
  border-radius: 6px;
  width: 80px;
  height: 30px;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}
.button2 {
  background-color: #007aff; /* Green */
  border: none;
  border-radius: 6px;
  width: 80px;
  height: 30px;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 10px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}
</style>
