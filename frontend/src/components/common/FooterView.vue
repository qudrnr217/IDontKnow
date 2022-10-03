<template>
  <div class="footer">
    <img src="../../assets/icon/home.png" alt="" id="home" @click="home()" />
    <img
      src="../../assets/icon/community.png"
      alt=""
      id="community"
      @click="community()"
    />
    <img src="../../assets/icon/vote.png" alt="" id="vote" @click="vote()" />
    <img
      src="../../assets/icon/mypage.png"
      alt=""
      id="mypage"
      @click="mypage()"
    />
  </div>
</template>

<script>
export default {
  methods: {
    home() {
      this.$router.push({ name: "clothes" });
    },
    community() {
      this.$router.push({ name: "community_home" });
    },
    vote() {
      this.$router.push({ name: "vote_home" });
    },
    mypage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style scoped>
.footer > * {
  margin-left: 55px;
  margin-top: 15px;
}
#home,
#community,
#vote,
#mypage {
  cursor: pointer;
}
</style>
