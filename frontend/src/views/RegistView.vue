<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <div class="title">회원가입</div>
        <div class="textcontainer" style="margin: 10px">
          <div class="columnname" style="flex-grow: 1">Name</div>
          <div class="columntextfield" style="flex-grow: 3">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="닉네임"
              type="text"
              v-model="idValue"
            />
          </div>
        </div>
        <div class="textbutton" style="margin: 10px">
          <div style="margin: 10px">사용가능한 닉네임입니다.</div>
          <button class="button">닉네임 중복 체크</button>
        </div>
        <div class="textcontainer" style="margin: 10px">
          <div class="columnname" style="flex-grow: 1">E-Mail</div>
          <div class="columntextfield" style="flex-grow: 3">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="이메일"
              type="text"
              v-model="idValue"
            />
          </div>
        </div>
        <div class="textbutton" style="margin: 10px">
          <div style="margin: 10px">사용가능한 이메일입니다.</div>
          <button class="button">이메일 중복 체크</button>
        </div>
        <div class="textcontainer" style="margin: 10px">
          <div class="columnname" style="flex-grow: 1">Password</div>
          <div class="columntextfield" style="flex-grow: 3">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="비밀번호"
              type="password"
              v-model="idValue"
            />
          </div>
        </div>
        <div class="textcontainer" style="margin: 10px">
          <div class="columnname" style="flex-grow: 1">Password</div>
          <div class="columntextfield" style="flex-grow: 3">
            <iTextField
              width="100%"
              maxlength="15"
              placeholder="비밀번호 확인"
              type="password"
              v-model="idValue"
            />
          </div>
        </div>
        <div class="columnname" style="margin: 10px">
          <div class="location" style="flex-grow: 1">거주지</div>
          <div class="selectorbox" style="flex-grow: 3">
            <select-location-view />
          </div>
        </div>
        <div class="columnname" style="margin: 10px">성별</div>
        <div class="columnname" style="margin: 10px">
          <div class="location" style="flex-grow: 1">연령대</div>
          <div class="selectorbox" style="flex-grow: 3">
            <select-age-view />
          </div>
        </div>
        <div style="display: flex; justify-content: flex-end; margin: 10px">
          <button class="buttonregist">회원가입</button>
        </div>
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import iTextField from "../components/common/iTextField";
import SelectLocationView from "../components/regist/SelectLocationView.vue";
import SelectAgeView from "../components/regist/SelectAgeView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
    iTextField,
    SelectLocationView,
    SelectAgeView,
  },
};
</script>

<style>
.body {
  font-family: "GmarketSansTTFLight";
}
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}
.textcontainer {
  display: flex;
  outline: none;
  padding: 0.4rem 0.8rem;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
  font-family: "GmarketSansTTFLight";
}
.columnname {
  display: flex;
  outline: none;
  padding: 0.4rem 0.8rem;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
  display: flex;
  font-family: "GmarketSansTTFLight";
}
.columntextfield {
  justify-content: flex-end;
  font-family: "GmarketSansTTFLight";
}
.button {
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
  font-size: 8px;
  /* margin: 4px 2px; */
  transition-duration: 0.4s;
  cursor: pointer;
  font-family: "GmarketSansTTFLight";
}
.textbutton {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  font-family: "GmarketSansTTFLight";
}
.location {
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "GmarketSansTTFLight";
}
.selectorbox {
  display: flex;
  justify-content: flex-end;
  font-family: "GmarketSansTTFLight";
}
.buttonregist {
  background-color: #007aff; /* Green */
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  color: white;
  /* padding: 16px 32px; */
  text-align: center;
  text-decoration: none;
  font-size: 8px;
  /* margin: 4px 2px; */
  transition-duration: 0.4s;
  cursor: pointer;
  font-family: "GmarketSansTTFLight";
}
</style>
