<template>
  <div>
    <div class="background">
      <div class="body">
        <com-vote-view />
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import FooterView from "../common/FooterView.vue";
import ComVoteView from "./ComVoteView.vue";

export default {
  components: {
    FooterView,
    ComVoteView,
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

.vote-card {
  margin-top: 8%;
}
</style>
