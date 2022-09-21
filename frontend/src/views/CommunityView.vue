<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view />
        <div class="title">커뮤니티</div>
        <control-view />

        <div class="trending">
          <img src="../assets/icon/fire.png" alt="" class="trending-icon" />
          <div class="trending-title">Trending</div>
        </div>

        <carousel-3d class="slide">
          <slide :index="0"><comlist-view /> </slide>
          <slide :index="1"> <comlist-view /> </slide>
          <slide :index="2"> <comlist-view /> </slide>
        </carousel-3d>

        <!-- <carousel-3d>
          <slide v-for="(slide, i) in slides" :index="i" :key="i">
            <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
              <img
                :data-index="index"
                :class="{
                  current: isCurrent,
                  onLeft: leftIndex >= 0,
                  onRight: rightIndex >= 0,
                }"
                :src="slide.src"
              />
            </template>
          </slide>
        </carousel-3d> -->

        <comlist-view />
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import ControlView from "../components/common/ControlView.vue";
import FooterView from "../components/common/FooterView.vue";
import HeaderView from "../components/common/HeaderView.vue";
import ComlistView from "../components/community/ComlistView.vue";
import { Carousel3d, Slide } from "vue-carousel-3d";
export default {
  components: {
    FooterView,
    HeaderView,
    ControlView,
    ComlistView,
    Carousel3d,
    Slide,
  },
};
</script>

<style scoped>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}

/* 트랜드 */

.trending {
  display: flex;
  /* background-color: red; */
  width: 100px;
  margin-left: 25px;
  margin-top: 20px;
}

.trending-title {
  margin-left: 10px;
  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 13px;
  line-height: 20px;
}

.slide {
  width: 320px;
  height: 10px;
}

.carousel-3d-slide.current {
  width: 320px;
  height: 170px;
}

slide {
  width: 320px;
  height: 170px;
}
</style>
