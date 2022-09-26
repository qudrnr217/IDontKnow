<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <dropdown-menu />
      <dropdown-location />
      <div class="clothes">
        <div class="content_title">의상 추천</div>
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
.clothes {
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
/* .weather_comment {
  width: 300px;
  height: 100px;
} */
</style>
