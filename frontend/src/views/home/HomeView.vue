<template>
  <div>
    <div class="home">
      <div class="body">
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import FooterView from "../common/FooterView.vue";
export default {
  components: {
    FooterView,
  },
};
</script>

<style scoped>
.home {
  display: flex;
  justify-content: center;
}
.body {
  width: 390px;
  height: 844px;
  /* width: 100vw; */
  /* height: 100vh; */
  left: 100;
  background-color: black;
}
</style>
