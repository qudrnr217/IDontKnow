<template>
  <div class="background">
    <div class="body">
      <header-view class="header" />
      <div class="content-box3">
        <div class="content">
          <div class="content_title">오늘</div>
          <div class="content_dropdown">
            <select class="sel">
              <!-- v-model=""  -->
              <option value="">날씨는?</option>
              <option
                v-for="(menu, index) in Mainmenu"
                :key="index"
                :value="menu.value"
              >
                {{ menu.text }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div class="content-box3" style="justify-content: flex-end">
        <select class="sel2">
          <!-- v-model=""  -->
          <option value="">서울시 강남구</option>
          <option
            v-for="(cur_loc, index) in Location"
            :key="index"
            :value="cur_loc.value"
          >
            {{ cur_loc.text }}
          </option>
        </select>
      </div>

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
import WeatherView from "@/components/main/WeatherView.vue";
import { mapMutations, mapState, mapActions } from "vuex";
export default {
  components: {
    HeaderView,
    FooterView,
    WeatherView,
  },
<<<<<<< HEAD
  mounted() {
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
=======
  data() {
    return {
      Mainmenu: [
        {
          value: 1,
          text: "날씨는?",
        },
        {
          value: 2,
          text: "뭐 입지?",
        },
        {
          value: 3,
          text: "뭐 먹지?",
        },
        {
          value: 4,
          text: "어디가지?",
        },
      ],
      Location: [
        {
          value: 1,
          text: "서울시 강남구",
        },
        {
          value: 2,
          text: "서울시 강동구",
        },
        {
          value: 3,
          text: "서울시 강북구",
        },
        {
          value: 4,
          text: "서울시 강서구",
        },
        {
          value: 5,
          text: "서울시 관악구",
        },
        {
          value: 6,
          text: "서울시 광진구",
        },
        {
          value: 7,
          text: "서울시 구로구",
        },
        {
          value: 8,
          text: "서울시 금천구",
        },
        {
          value: 9,
          text: "서울시 노원구",
        },
        {
          value: 10,
          text: "서울시 도봉구",
        },
        {
          value: 11,
          text: "서울시 동대문구",
        },
        {
          value: 12,
          text: "서울시 동작구",
        },
        {
          value: 13,
          text: "서울시 마포구",
        },
        {
          value: 14,
          text: "서울시 서대문구",
        },
        {
          value: 15,
          text: "서울시 서초구",
        },
        {
          value: 16,
          text: "서울시 성동구",
        },
        {
          value: 17,
          text: "서울시 성북구",
        },
        {
          value: 18,
          text: "서울시 송파구",
        },
        {
          value: 19,
          text: "서울시 양천구",
        },
        {
          value: 20,
          text: "서울시 영등포구",
        },
        {
          value: 21,
          text: "서울시 용산구",
        },
        {
          value: 22,
          text: "서울시 은평구",
        },
        {
          value: 23,
          text: "서울시 종로구",
        },
        {
          value: 24,
          text: "서울시 중구",
        },
        {
          value: 25,
          text: "서울시 중랑구",
        },
      ],
    };
>>>>>>> fe
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
.sel {
  width: 150px;
  height: 30px;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  border: none;
}
.sel2 {
  width: 100px;
  height: 30px;
  margin-right: 30px;
  font-size: 12px;
  font-family: "GmarketSansTTFMedium";
  border: none;
  background: transparent;
}
</style>
