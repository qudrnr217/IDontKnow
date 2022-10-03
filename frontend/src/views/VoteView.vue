<template>
  <div class="background bgd-common">
    <div class="body">
      <router-view />
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
