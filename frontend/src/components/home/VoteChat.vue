<template>
  <div></div>
</template>

<script>
// import { commentDelete, commentModify } from "@/api/community.js";
import { commentDelete } from "@/api/community.js";
import { mapState } from "vuex";
var token =
  "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiYXVkIjoi7LmY7YKo65-s67KEIiwiZXhwIjoxNjY0OTU4NDQwfQ.zevo64PasEuPNiBQAaI0Fs-ZKZjlCb7catVf4AQnfTd-0vmJqbRU_rUQrJUxgGip";
export default {
  props: {
    vote: [Array, Object],
  },
  computed: {
    ...mapState("communityStore", ["vote_detail"]),
  },
  methods: {
    updateComment(commentId) {
      console.log(commentId);
      //   commentModify(token, commentId, ({ data }) => {
      //     console.log(data);
      //   });

      this.isUpdated = true;
    },
    deleteComment(commentId) {
      commentDelete(token, commentId, ({ data }) => {
        console.log(data);
        this.$router.go();
      });
    },
  },
  mounted() {
    console.log(typeof this.vote);
  },
  data() {
    return {
      isUpdated: false,
      commentForUpdate: "",
    };
  },
};
</script>

<style></style>
