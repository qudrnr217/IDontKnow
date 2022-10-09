<template>
  <div v-if="this.$store.state.started === 0" class="background bgd-start">
    <div class="body">
      <div class="box-row-center">
        <div @click="start()" class="btn-rounded-rectangle yellow-1">
          <div class="btn-text">시작하기</div>
        </div>
      </div>
    </div>
  </div>
  <div v-else>
    <router-view />
    <footer-view v-on:move="updateNav" v-bind:click="activeNav"></footer-view>
  </div>
</template>
<script>
import FooterView from "./components/common/FooterView.vue";
import { mapState, mapActions } from "vuex";
export default {
  components: {
    FooterView,
  },
  computed: {
    ...mapState({
      started: (state) => state.data.started,
    }),
  },
  data() {
    return {
      activeNav: 0,
    };
  },
  methods: {
    updateNav(param) {
      this.activeNav = param;
    },
    start() {
      console.log(this.$store.state.started);
      this.activeNav = 1;
      this.update_started();
      this.$router.push({ name: "home" });
    },
    ...mapActions(["update_started"]),
  },
};
</script>

<style>
@import "./assets/css/default.css";
.background {
  display: flex;
  justify-content: center;
  height: 100vh;
  min-height: 844px;
  overflow-y: hidden;
  width: 390px;
  max-width: 390px;
}

.body {
  width: 390px;
  overflow-y: scroll;
}

.background-footer {
  display: flex;
  justify-content: center;
}

.footer {
  position: fixed;
  width: 100vw;
  max-width: 390px;
  height: 73px;
  bottom: 0;
  background: linear-gradient(
    rgba(255, 255, 255, 0.9),
    rgba(255, 255, 255, 0.9)
  );
}

.title {
  text-align: center;
  font-size: 40px;
  /* background-color: red; */
}

/* 폰트 */
.v-application {
  font-family: "ONEMobileTitle";
}
@font-face {
  font-family: "ONEMobileTitle";
  src: url("./assets/fonts/ONEMobileTitle.ttf") format("truetype");
}
</style>
<style scoped>
.box-row-center {
  display: flex;
  margin: 10px 20px;
  align-items: center;
  justify-content: space-around;
  position: relative;
}

.btn-rounded-rectangle {
  display: flex;
  width: 170px;
  height: 60px;
  border-radius: 30px;
  align-items: center;
  justify-content: center;
}

.btn-text {
  font-size: 20px;
  letter-spacing: 2px;
}

@media (hover: none) and (max-height: 1000px) {
  .box-row-center {
    left: 0px;
    top: 500px;
  }
}
@media (hover: none) and (min-height: 1000px) {
  .box-row-center {
    left: 0px;
    top: 750px;
  }
}
@media (hover: hover) {
  .box-row-center {
    left: 0px;
    top: 550px;
  }
  .btn-rounded-rectangle {
    cursor: pointer;
    transition: all 1s ease-in-out;
  }
  .btn-text {
    box-sizing: border-box;
    transition: all 1s ease-in-out;
    transform: scale(0);
    opacity: 0;
  }
  .btn-rounded-rectangle:hover {
    box-shadow: inset 0 0 0 100px rgba(148, 110, 110, 0.6);
  }
  .btn-rounded-rectangle:hover .btn-text {
    opacity: 1;
    transform: scale(1);
  }
}
</style>
