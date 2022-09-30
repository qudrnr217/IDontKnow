<template>
  <div class="background">
    <div class="footer">
      <div class="btn-footer">
        <img
          :class="{ 'img-active': activated === 1 }"
          class="img-footer"
          src="../../assets/icon/btn/btn_home.png"
          alt="Home"
          id="home"
          @click="home()"
        />
      </div>
      <div class="btn-footer">
        <img
          :class="{ 'img-active': activated === 2 }"
          class="img-footer"
          src="../../assets/icon/btn/btn_chat.png"
          alt="Chat"
          id="chat"
          @click="chat()"
        />
      </div>
      <div class="btn-footer">
        <img
          :class="{ 'img-active': activated === 3 }"
          class="img-footer"
          src="../../assets/icon/btn/btn_vote.png"
          alt="Vote"
          id="vote"
          @click="vote()"
        />
      </div>
      <div class="btn-footer">
        <img
          :class="{ 'img-active': activated === 4 }"
          class="img-footer"
          src="../../assets/icon/btn/btn_mypage.png"
          alt="Mypage"
          id="mypage"
          @click="mypage()"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterViewCopy",
  props: ["click"],
  data() {
    return {
      activated: this.click,
    };
  },
  methods: {
    home() {
      if (this.activated !== 1) {
        this.activated = 1;
        this.$emit("move", this.activated);
        this.$router.push({ name: "newHome" });
      }
    },
    chat() {
      if (this.activated !== 2) {
        this.activated = 2;
        this.$emit("move", this.activated);
        this.$router.push({ name: "community_home" });
      }
    },
    vote() {
      if (this.activated !== 3) {
        this.activated = 3;
        this.$emit("move", this.activated);
        this.$router.push({ name: "vote_home" });
      }
    },
    mypage() {
      if (this.activated !== 4) {
        this.activated = 4;
        this.$emit("move", this.activated);
        this.$router.push({ name: "mypage" });
      }
    },
  },
};
</script>

<style scoped>
.footer {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
}
.btn-footer {
  margin-right: 20px;
  margin-left: 20px;
}
.img-active {
  opacity: 1 !important;
}
.img-footer {
  width: 60px;
  height: 60px;
  opacity: 0.5;
  cursor: pointer;
}
</style>
