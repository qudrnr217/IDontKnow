<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <div class="title">로그인</div>
        <div class="textcontainer" style="margin: 20px">
          <div class="columnname" style="flex-grow: 1">E-Mail</div>
          <div class="columntextfield" style="flex-grow: 3">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="이메일"
              v-model="idValue"
            />
          </div>
        </div>
        <div class="textcontainer" style="margin: 20px">
          <div class="columnname" style="flex-grow: 1">Password</div>
          <div class="columntextfield" style="flex-grow: 3">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="비밀번호"
              v-model="idValue"
            />
          </div>
        </div>
        <div style="display: flex; justify-content: flex-end; margin: 20px">
          <iButton>비밀번호 재설정</iButton>
        </div>
        <div>
          <div style="display: flex; justify-content: flex-end; margin: 20px">
            <button class="button1" opacity="0.2">회원가입</button>
            <button class="button2">로그인</button>
          </div>
        </div>

        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderView from "../common/HeaderView.vue";
import FooterView from "../common/FooterView.vue";
import iTextField from "../common/iTextField.vue";
import iButton from "../common/iButton.vue";

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
  width: 100px;
  height: 30px;
  color: white;
  /* padding: 16px 32px; */
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}
.button2 {
  background-color: #007aff; /* Green */
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  color: white;
  /* padding: 16px 32px; */
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}
</style>
