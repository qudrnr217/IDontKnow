<template>
  <div class="hi">
    <div class="progress-bar">
      <div class="left-progress"></div>

      <!-- <div class="right-progress">1</div>
      <div class="third-progress"></div>-->
    </div>
    <div class="detail-toggle">
      <div class="switch-title">상세보기</div>
      <label class="switch">
        <input type="checkbox" />
        <span class="slider round" @click="detail_show = !detail_show"></span>
      </label>
    </div>
    <div class="chart-box" v-if="detail_show">
      <control-view :segments="segments" class="control-view" />
      <div class="pie-chart">
        <pie-chart-view />
      </div>
    </div>
  </div>
</template>

<script>
// import $ from "jquery";

import ControlView from "../common/ControlView.vue";
import PieChartView from "./PieChartVIew.vue";
export default {
  components: {
    ControlView,
    PieChartView,
  },
  data() {
    return {
      values: [50, 30, 20],
      max: 100,

      segments: [
        {
          title: "연령",
          id: "42",
        },
        {
          title: "성별",
          id: "93",
        },
        {
          title: "거주지",
          id: "11",
        },
      ],
      detail_show: false,
    };
  },

  mounted() {
    const bar = document.querySelector(".left-progress");
    // const bar2 = document.querySelector(".right-progress");
    // const bar3 = document.querySelector(".third-progress");
    let t = 0;
    let totalMinwon = 75;
    // let resolveMinwon = 25;
    const barAnimation = setInterval(() => {
      bar.style.width = t + "%";
      t++ >= totalMinwon && clearInterval(barAnimation);
    }, 10);

    // let t2 = 0;
    // const barAnimation2 = setInterval(() => {
    //   bar2.style.background = `linear-gradient(to right, #4F98FF 0 ${t2}%, #dedede ${t2}% 100% )`;
    //   t2++ >= resolveMinwon && clearInterval(barAnimation2);
    // }, 10);

    // let t3 = 0;
    // const barAnimation3 = setInterval(() => {
    //   bar3.style.background =
    //     totalMinwon > t
    //       ? `linear-gradient(to right, #4F98FF 0 ${t}%, #dedede ${t}% 100% )`
    //       : totalMinwon + resolveMinwon > t
    //       ? `linear-gradient(to right, #4F98FF 0 ${totalMinwon}%, #f44336 ${totalMinwon}% ${t3}%, #dedede ${t3}% 100%)`
    //       : `linear-gradient(to right, #4F98FF 0 ${totalMinwon}%, #f44336 ${totalMinwon}% ${
    //           totalMinwon + resolveMinwon
    //         }%, #dedede ${totalMinwon + resolveMinwon}% 100%)`;
    //   t3++ >= 100 && clearInterval(barAnimation3);
    // }, 10);
  },
};
</script>

<style scoped>
.progress-bar {
  width: 340px;
  height: 30px;
  margin-left: 22px;
  background-color: #ff9500;
  font-weight: 600;
  font-size: 0.8rem;
  margin-top: 10px;
}

.progress-bar .left-progress {
  width: 72%;
  height: 30px;
  padding: 0;
  text-align: center;
  background-color: #7b61ff;
  color: #111;

  display: flex;
}

.progress-bar .third-progress {
  width: 100%;
  height: 30px;
  background: linear-gradient(to right, #4f98ff 0 72%, #dedede 72% 100%);
  font-weight: 600;
  font-size: 0.8rem;
}

/* switch */

.detail-toggle {
  display: flex;
  width: 380px;
  margin-top: 20px;
}

.switch-title {
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 20px;
  line-height: 20px;
  margin-left: 210px;
  margin-top: 10px;
}

.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
  margin-left: 10px;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #34c759;
}

input:focus + .slider {
  box-shadow: 0 0 1px #34c759;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}

/* controlView */
.control-view {
  margin-left: 50px;
}
.pie-chart {
  /* background-color: red; */
  width: 390px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 60px;
}
</style>
