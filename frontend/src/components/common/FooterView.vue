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
  name: "FooterView",
};
</script>

<style scoped>
.footer > * {
  margin-left: 55px;
  margin-top: 15px;
}
#homem,
#community,
#vote,
#mypage {
  cursor: pointer;
}
</style>
