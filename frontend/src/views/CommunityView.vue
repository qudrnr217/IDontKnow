<template>
  <div class="background">
    <div class="body">
      <router-view></router-view>
      <footer-view />
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

<style></style>
