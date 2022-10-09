<template>
  <div class="background-footer">
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
          src="../../assets/icon/btn/btn_record.png"
          alt="Record"
          id="record"
          @click="record()"
        />
      </div>
      <div class="btn-footer">
        <img
          :class="{ 'img-active': activated === 4 }"
          class="img-footer"
          src="../../assets/icon/btn/btn_profile.png"
          alt="Profile"
          id="profile"
          @click="profile()"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterView",
  props: ["click"],
  data() {
    return {
      activated: this.click,
    };
  },
  mounted() {
    if (this.$store.state.started === 1) {
      const currPath = this.$route.path;
      if (currPath.includes("chat")) {
        this.activated = 2;
      } else if (currPath.includes("record")) {
        this.activated = 3;
      } else if (currPath.includes("profile")) {
        this.activated = 4;
      } else if (currPath.includes("home")) {
        this.activated = 1;
      } else {
        this.activated = 1;
      }
    }
  },
  methods: {
    home() {
      if (decodeURI(this.$route.path) !== "/home/메뉴") {
        this.activated = 1;
        this.$router.push({
          name: "voteList",
          path: "/",
          params: { status: "진행", category: "메뉴" },
        });
      } else if (this.activated !== 1) {
        this.activated = 1;
        this.$emit("move", this.activated);
        this.$router.push({
          name: "home",
          path: "/",
        });
      }
    },
    chat() {
      if (this.activated !== 2) {
        this.activated = 2;
        this.$emit("move", this.activated);
        this.$router.push({ name: "chat", path: "/chat" });
      }
    },
    record() {
      if (this.activated !== 3) {
        this.activated = 3;
        this.$emit("move", this.activated);
        if (this.$store.state.userStore.userId === 0) {
          if (this.$route.path !== "/profile/login") {
            this.$router.push({ name: "userLogin", path: "/profile/login" });
          }
        } else {
          this.$router.push({
            name: "recordList",
            path: "/record/user",
            params: { userId: this.$store.state.userStore.userId },
            query: { name: this.$store.state.userStore.name },
          });
          location.reload();
        }
      }
    },
    profile() {
      if (this.activated !== 4) {
        this.activated = 4;
        this.$emit("move", this.activated);
        if (this.$store.state.userStore.userId === 0) {
          if (this.$route.path !== "/profile/login") {
            this.$router.push({ name: "userLogin", path: "/profile/login" });
          }
        } else {
          this.$router.push({
            name: "userInfo",
            path: "/profile/user",
            params: { userId: this.$store.state.userStore.userId },
          });
        }
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
