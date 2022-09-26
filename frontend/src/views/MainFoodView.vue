<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="content-box3">
        <div class="content">
          <div class="content_title">오늘의</div>
          <div class="content_dropdown"><dropdown-menu /></div>
        </div>
      </div>
      <div class="current-location"><dropdown-location /></div>

      <div class="content-box3">
        <div class="content" style="flex-direction: column">
          <div class="content-title">오늘의 메뉴 추천</div>
          <div class="content-img">
            <img src="" alt class="clothes_img" />
            <img src="" alt class="clothes_img" />
          </div>
          <div class="content-description">
            <button class="button">메뉴 1</button>
            <button class="button">메뉴 2</button>
          </div>
        </div>
      </div>
      <div class="content-box3">
        <weather-view />
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
import WeatherView from "@/components/main/WeatherView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
    DropdownMenu,
    DropdownLocation,
    WeatherView,
  },
};
</script>

<style scoped>
.content-box3 {
  display: flex;
  justify-content: center;
  align-content: center;
  width: 100%;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
}
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  width: 250px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content-title {
  display: center;
  justify-content: center;
  flex-grow: 2;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  vertical-align: center;
  margin: 10px;
}
.content-img {
  margin: 10px;
}
.content-description {
  margin: 10px;
}
</style>
