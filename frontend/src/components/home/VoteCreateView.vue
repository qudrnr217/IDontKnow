<template>
  <div class="background">
    <div class="body">
      <header-view />
      <div class="title">커뮤니티</div>
      <div class="category-box">
        <div class="category-title">카테고리</div>
      </div>
      <footer-view />
    </div>
  </div>
</template>

<script>
import FooterView from "../common/FooterViewCopy.vue";
import HeaderView from "../common/HeaderView.vue";
export default {
  name: "VoteCreateView",
  components: {
    FooterView,
    HeaderView,
  },
};
</script>

<style>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
}
</style>
