<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="content-box3">
        <div class="content">
          <div class="title-box">
            <div class="content-title">오늘의</div>
            <dropdown-menu />
          </div>
        </div>
      </div>
      <div class="current-location"><dropdown-location /></div>
      <div class="content-box3">
        <div class="content">
          <div class="content-title">실시간 인기 장소</div>
          <div class="content-hotspot">
            <div class="content-hotspot-box-left">
              <img
                :src="require(`@/assets/image/hotspots/${hotspots.placeA}.jpg`)"
                alt
                class="hotspot-left-img"
              />
              <span class="hotspot-name">{{ hotspots.placeA }}</span>
            </div>
            <div class="content-hotspot-box-right">
              <img
                :src="require(`@/assets/image/hotspots/${hotspots.placeB}.jpg`)"
                alt
                class="hotspot-left-img"
              />
              <span class="hotspot-name">{{ hotspots.placeB }}</span>
            </div>
          </div>
          <div class="content-authorship">출처: 서울시 실시간 도시데이터</div>
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
import WeatherView from "../components/main/WeatherView.vue";
import { mapMutations, mapState, mapActions } from "vuex";
export default {
  name: "MainHotspotView",
  components: {
    HeaderView,
    FooterView,
    DropdownMenu,
    DropdownLocation,
    WeatherView,
  },
  mounted() {
    this.getHotspots();
  },
  computed: {
    ...mapState("homeStore", ["districtId", "hotspots"]),
  },
  methods: {
    getHotspots() {
      this.FETCH_HOTSPOTS();
    },
    ...mapActions("homeStore", ["FETCH_HOTSPOTS"]),
    ...mapMutations("homeStore", ["SET_DISTRICT", "SET_HOTSPOTS"]),
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
  flex-direction: column;
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
.title-box {
  display: flex;
  justify-content: space-between;
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
.hotspot-left-img {
  width: 7rem;
  height: 6rem;
}
.hotspot-right-img {
  width: 7rem;
  height: 6rem;
}
.content-hotspot {
  display: flex;
  justify-content: space-between;
}
.content-hotspot-box-left {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 0.3rem;
}
.content-hotspot-box-right {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 0.3rem;
}
.hotspot-name {
  font-size: 0.8rem;
}
.content-description {
  margin: 10px;
}
.content-authorship {
  font-size: 0.5rem;
  margin: 1rem 0 auto auto;
}
</style>
