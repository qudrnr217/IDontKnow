<template>
  <div class="background bgd-start">
    <div class="body">
      <div class="box-row-center">
        <div @click="start" class="btn-rounded-rectangle yellow-1">
          <div class="btn-text">시작하기</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "StartView",
  components: {},
  data() {
    return {
      category: "메뉴",
    };
  },
  methods: {
    start() {
      this.$router.push({ name: "newHome" });
    },
  },
};
</script>

<style scoped>
.box-row-center {
  display: flex;
  margin: 10px 20px;
  align-items: center;
  justify-content: space-around;
  position: relative;
}
@media (hover: none) {
  .box-row-center {
    left: 0px;
    top: 460px;
  }
}
@media (hover: hover) {
  .box-row-center {
    left: 0px;
    top: 500px;
  }
  .btn-rounded-rectangle {
    cursor: pointer;
    transition: all 1s ease-in-out;
  }
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
.btn-rounded-rectangle:hover {
  box-shadow: inset 0 0 0 100px rgba(148, 110, 110, 0.6);
}
.btn-rounded-rectangle:hover .btn-text {
  opacity: 1;
  transform: scale(1);
}
</style>
