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
      <vote-list v-on:pass="updateCategory"></vote-list>
    </div>
  </div>
</template>

<script>
import VoteList from "../components/home/VoteList.vue";

export default {
  // name: "HomeView",
  components: {
    VoteList,
  },
  data() {
    return {
      category: "메뉴",
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
  height: 105vh;
  padding-bottom: 73px;
}
</style>
