<template>
  <div
    class="background"
    :class="{
      'bgd-menu': category === '메뉴',
      'bgd-style': category === '스타일',
      'bgd-location': category === '장소',
    }"
  >
    <div class="body">
      <vote-detail v-on:pass="updateCategory"></vote-detail>
    </div>
  </div>
</template>

<script>
import VoteDetail from "../components/home/VoteDetail.vue";
export default {
  name: "VoteDetailView",
  components: {
    VoteDetail,
  },
  data() {
    return {
      category: "",
    };
  },
  methods: {
    updateCategory(param) {
      this.category = param;
    },
  },
};
</script>

<style scoped>
.body {
  padding-bottom: 73px;
}
</style>
