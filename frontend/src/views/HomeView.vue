<template>
  <div>
    <div class="background">
      <div class="body">
        <router-view></router-view>
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import FooterView from "../components/common/FooterView.vue";
export default {
  components: {
    FooterView,
  },
};
</script>

<style scoped></style>
