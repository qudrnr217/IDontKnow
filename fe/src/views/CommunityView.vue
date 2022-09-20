<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view />
        <div class="title">커뮤니티</div>
        <control-view />
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import ControlView from "../components/common/ControlView.vue";
import FooterView from "../components/common/FooterView.vue";
import HeaderView from "../components/common/HeaderView.vue";
export default {
  components: {
    FooterView,
    HeaderView,
    ControlView,
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
