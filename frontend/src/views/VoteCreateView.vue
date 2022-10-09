<template>
  <div class="background bgd-common">
    <div class="body">
      <vote-create></vote-create>
    </div>
  </div>
</template>

<script>
import VoteCreate from "../components/home/VoteCreate.vue";
export default {
  name: "VoteCreateView",
  components: {
    VoteCreate,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
