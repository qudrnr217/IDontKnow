<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <div class="title">회원가입</div>
        <div class="content">
          <div class="content_title">Name</div>
          <input
            class="content_input"
            type="text"
            id="name"
            v-model="name"
            placeholder="닉네임"
          />
        </div>
        <div class="content_check">
          <div class="conten_check_text">사용 가능한 닉네임입니다.</div>
          <button class="button">닉네임 중복 체크</button>
        </div>
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
        <div class="content_check">
          <div class="conten_check_text">사용 가능한 이메일입니다.</div>
          <button class="button">이메일 중복 체크</button>
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
        <div class="content">
          <div class="content_title">Password 확인</div>
          <input
            class="content_input"
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호 확인"
          />
        </div>
        <div class="content">
          <div class="content_title">거주지</div>
          <div class="dropdown"><dropdown-location /></div>
        </div>
        <div class="content">
          <div class="content_title">성별</div>
        </div>
        <div class="content">
          <div class="content_title">연령대</div>
          <div class="dropdown"><dropdown-age /></div>
        </div>
        <button class="button">회원가입</button>

        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import DropdownLocation from "../components/regist/DropdownLocation.vue";
import DropdownAge from "../components/regist/DropdownAge.vue";

export default {
  components: {
    HeaderView,
    FooterView,
    DropdownLocation,
    DropdownAge,
  },
};
</script>

<style scoped>
.body {
  width: 390px;
  height: 844px;
}
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
.conten_check_text {
  margin: 10px;
  color: #007aff;
  text-align: center;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
}
.button {
  background-color: #007aff;
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  color: white;
  text-align: center;
  text-decoration: none;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
}
.dropdown {
  flex-grow: 3;
}
</style>
