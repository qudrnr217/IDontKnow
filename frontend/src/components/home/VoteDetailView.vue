<template>
  <div>
    <div class="background">
      <div class="body">
        <vote-detail />
        <div class="title">커뮤니티</div>
        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import FooterView from "../common/FooterViewCopy.vue";
import VoteDetail from "../home/VoteDetail.vue";

export default {
  name: "VoteDetailView",
  components: {
    FooterView,
    VoteDetail,
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
