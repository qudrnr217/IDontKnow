<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <dropdown-menu />
      <dropdown-location />
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
      <div class="weather_comment">오늘은 덥습니다.</div>
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
