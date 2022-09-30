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
          <div class="content-content" v-if="bestMenus">
            <div class="content-menu">
              <div class="content-img">
                <img
                  :src="
                    require(`@/assets/icon/food/${bestMenus[0].menuImgName}.png`)
                  "
                  alt
                  class="food1_img"
                  style="width: 100px; height: 100px"
                />
              </div>
              <div class="content-description">
                <button
                  class="button1"
                  @click="
                    $router.push({
                      name: 'food/detail',
                      query: { menu_name: bestMenus[0].menuName },
                    })
                  "
                >
                  {{ bestMenus[0].menuName }}
                </button>
              </div>
            </div>
            <div class="content-menu">
              <div class="content-img">
                <img
                  :src="
                    require(`@/assets/icon/food/${bestMenus[1].menuImgName}.png`)
                  "
                  alt
                  class="food2_img"
                  style="width: 100px; height: 100px"
                />
              </div>
              <div class="content-description">
                <button
                  class="button2"
                  @click="
                    $router.push({
                      name: 'food/detail',
                      query: { menu_name: bestMenus[1].menuName },
                    })
                  "
                >
                  {{ bestMenus[1].menuName }}
                </button>
              </div>
            </div>
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
import { mapMutations, mapState, mapActions } from "vuex";
export default {
  components: {
    HeaderView,
    FooterView,
    DropdownMenu,
    DropdownLocation,
    WeatherView,
  },
  mounted() {
    const script = document.createElement("script");
    script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&libraries=services`;
    document.head.appendChild(script);
    // TODO: userStore에 districId state 생성되면 파라미터로 그걸 넘겨준다.
    this.getBestMenus(1);
  },
  computed: {
    ...mapState("homeStore", ["districtId", "bestMenus"]),
  },
  methods: {
    getBestMenus(districtId) {
      this.FETCH_BEST_MENUS(districtId);
    },
    ...mapActions("homeStore", ["FETCH_BEST_MENUS"]),
    ...mapMutations("homeStore", ["SET_DISTRICT"]),
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
.content-menu {
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.content-content {
  display: flex;
  justify-content: center;
}
.content-img {
  margin: 10px;
  width: 100px;
  height: 100px;
}
.content-description {
  margin: 10px;
}
.button1 {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 30px;
  background: #ff9500;
  border: none;
  border-radius: 10px;
  font-family: "GmarketSansTTFMedium";
  color: #ffffff;
  opacity: 0.6;
}
.button1:hover {
  background: #ff9500;
  opacity: 1;
}
.button2 {
  width: 100px;
  height: 30px;
  background: #af52de;
  border: none;
  border-radius: 10px;
  font-family: "GmarketSansTTFMedium";
  color: #ffffff;
  opacity: 0.6;
}
.button2:hover {
  background: #af52de;
  opacity: 1;
}
</style>
