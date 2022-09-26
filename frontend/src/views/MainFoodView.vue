<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <dropdown-menu />
      <dropdown-location />
      <div class="food">
        <div class="content_title">메뉴 추천</div>
        <div>
          <table>
            <tr>
              <th style="width: 100px"><img /></th>
              <th style="width: 100px"><img /></th>
            </tr>
            <tr>
              <th><button class="button1">메뉴1</button></th>
              <th><button class="button2">메뉴2</button></th>
            </tr>
          </table>
        </div>
      </div>
      <div class="weather">
        <table>
          <tr>
            <th><img /></th>
            <th>기온</th>
            <th><img /></th>
            <th>바람세기</th>
          </tr>
          <tr>
            <th><img /></th>
            <th>습도</th>
            <th><img /></th>
            <th>강수량</th>
          </tr>
          <tr>
            <th><div class="weather_comment">오늘은 덥습니다.</div></th>
          </tr>
        </table>
      </div>
      <footer-view class="footer" />
    </div>
  </div>
</template>

<script>
import HeaderView from "../components/common/HeaderView.vue";
import FooterView from "../components/common/FooterView.vue";
import DropdownMenu from "../components/main/DropdownMenu.vue";
import DropdownLocation from "@/components/regist/DropdownLocation.vue";
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
.food {
  width: 300px;
  height: 300px;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
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
  height: 300px;
  background-color: #ffffff;
  border: none;
  border-radius: 6px;
}
.button1 {
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
.button2 {
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
</style>
