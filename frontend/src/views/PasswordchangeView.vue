<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="title">마이페이지</div>
      <div class="myprofile">
        <!-- <img src="../assets/image/profile.png" /> -->
      </div>
      <div class="content_title">비밀번호 변경</div>
      <div class="content">
        <table class="mypage">
          <tr>
            <th>
              <input
                class="content_input"
                type="password"
                id="password"
                v-model="password"
                placeholder="현재 비밀번호"
              />
            </th>
          </tr>
          <tr>
            <td>
              <input
                class="content_input"
                type="password"
                id="password"
                v-model="password"
                placeholder="새로운 비밀번호"
              />
            </td>
          </tr>
          <tr>
            <td>
              <input
                class="content_input"
                type="password"
                id="password"
                v-model="password"
                placeholder="새로운 비밀번호 확인"
              />
            </td>
          </tr>
        </table>
      </div>
      <div class="content_check">
        <button class="mypagebutton" @click="click">비밀번호 변경</button>
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
.myprofile {
  margin: auto;
  width: 200px;
  height: 200px;
}
.content {
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #ffffff;
  width: 300px;
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
table {
  border-collapse: collapse;
  width: 100%;
}
th,
td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
.content_check {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px;
}
.mypagebutton {
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
