<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view />
        <div class="title">커뮤니티</div>
        <control-view />
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
export default {
  components: {
    FooterView,
    HeaderView,
    ControlView,
    ComlistView,
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
</style>
