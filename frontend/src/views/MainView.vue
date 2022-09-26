<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />

      <div class="content-box3">
        <div class="content">
          <div class="content_title">오늘의</div>
          <div><dropdown-menu /></div>
        </div>
      </div>

      <dropdown-location />
      <div class="content-box3">
        <div class="weather">
          <table>
            <tr>
              <th><img /></th>
              <th>기온</th>
            </tr>
            <tr>
              <th><img /></th>
              <th>바람세기</th>
            </tr>
            <tr>
              <th><img /></th>
              <th>습도</th>
            </tr>
            <tr>
              <th><img /></th>
              <th>강수량</th>
            </tr>
            <tr>
              <th><img /></th>
              <th>자외선지수</th>
            </tr>
          </table>
        </div>
      </div>
      <div class="content-box3">
        <div class="weather_comment">오늘은 덥습니다.</div>
      </div>

      <footer-view class="footer" />
    </div>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import DropdownMenu from "../components/main/DropdownMenu.vue";
import DropdownLocation from "../components/regist/DropdownLocation.vue";
export default {
  components: {
    HeaderView,
    FooterView,
    DropdownMenu,
    DropdownLocation,
  },
};
</script>

<style>
.content-box3 {
  display: flex;
  justify-content: center;
  align-content: center;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
}
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  width: 250px;
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
.weather {
  width: 300px;
  height: 600px;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
}
.weather_comment {
  width: 300px;
  height: 100px;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
}
</style>
