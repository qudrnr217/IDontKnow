<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import FooterView from "../common/FooterView.vue";
import HeaderView from "../common/HeaderView.vue";
export default {
  components: {
    FooterView,
    HeaderView,
  },
};
</script>

<style scoped>
.background {
  display: flex;
  justify-content: center;
}

.body {
  background-color: #f2f2f7;
  width: 390px;
  height: 844px;
  /* width: 100vw; */
  /* height: 100vh; */
  left: 100;
  /* background-color: black; */
}
.header {
  position: fixed;
  top: 0;
}
.footer {
  position: fixed;
  bottom: 0;
}
</style>
