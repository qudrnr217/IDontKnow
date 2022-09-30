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
      <vote-list-view v-on:pass="updateCategory"></vote-list-view>
    </div>
  </div>
</template>

<script>
import VoteListView from "../components/home/VoteListView.vue";
export default {
  name: "HomeViewCopy",
  components: {
    VoteListView,
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

<style scoped></style>
