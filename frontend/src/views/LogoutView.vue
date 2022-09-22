<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <div class="title">로그아웃</div>
        <div>로그아웃 되었습니다.</div>
        <iButton>메인페이지로 돌아가기</iButton>
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
import iButton from "../components/common/iButton.vue";

export default {
  components: {
    HeaderView,
    FooterView,
    iButton,
  },
};
</script>

<style scoped>
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
