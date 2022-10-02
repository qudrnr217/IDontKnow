<template>
  <div class="hi">
    <div class="progress-bar">
      <!-- <div class="left-progress"></div> -->

      <!-- <div class="right-progress">1</div> -->
      <div class="third-progress"></div>
    </div>
    <div class="detail-toggle">
      <div class="switch-title">상세보기</div>
      <label class="switch">
        <input type="checkbox" />
        <span class="slider round" @click="detail_show = !detail_show"></span>
      </label>
    </div>
    <div class="chart-box" v-if="detail_show">
      <!-- <control-view :segments="segments" class="control-view" /> -->
      <!-- 셀렉트 박스 -->
      <div class="content">
        <div class="content_title">내가 선택한 카테고리</div>
        <div class="content_dropdown">
          <select class="sel" v-model="select_category">
            <!-- v-model=""  -->
            <!-- <option value="메뉴" selected>메뉴</option> -->
            <option
              v-for="(menu, index) in category"
              :key="index"
              :value="menu.text"
            >
              {{ menu.text }}
            </option>
          </select>
        </div>
      </div>

      <div class="content" v-if="select_category == '연령'">
        <div class="content_title">선택</div>
        <div class="content_dropdown">
          <select
            class="sel"
            v-model="select_age_category"
            @change="change_chart()"
          >
            <!-- v-model=""  -->
            <!-- <option value="메뉴" selected>메뉴</option> -->
            <option
              v-for="(menu, index) in age_category"
              :key="index"
              :value="menu.text"
            >
              {{ menu.text }}
            </option>
          </select>
        </div>
      </div>

      <div class="content" v-if="select_category == '성별'">
        <div class="content_title">선택</div>
        <div class="content_dropdown">
          <select
            class="sel"
            v-model="select_gender_category"
            @change="change_chart()"
          >
            <!-- v-model=""  -->
            <!-- <option value="메뉴" selected>메뉴</option> -->
            <option
              v-for="(menu, index) in gender_category"
              :key="index"
              :value="menu.value"
            >
              {{ menu.text }}
            </option>
          </select>
        </div>
      </div>

      <div class="pie-chart">
        <pie-chart-view
          :voteId="voteId"
          :age="select_age_category"
          :idx="select_category"
          :gender="select_gender_category"
          :key="reload"
        />
      </div>
      <div class="line-chart">
        <line-chart :voteId="voteId" :idx="select_category" />
      </div>
    </div>
  </div>
</template>

<script>
// import $ from "jquery";
import { detailVote } from "@/api/community.js";
// import ControlView from "../common/ControlView.vue";
import PieChartView from "./PieChartVIew.vue";
import LineChart from "./LineChart.vue";
// import { mapState } from "vuex";
export default {
  components: {
    // ControlView,
    PieChartView,
    LineChart,
  },

  props: {
    // acount: Number,
    // bcount: Number,
    voteId: Number,
  },
  data() {
    return {
      values: [50, 30, 20],
      max: 100,
      category: [
        {
          value: 1,
          text: "연령",
        },
        {
          value: 2,
          text: "성별",
        },
        {
          value: 3,
          text: "거주지",
        },
      ],
      age_category: [
        {
          value: 1,
          text: 10,
        },
        {
          value: 2,
          text: 20,
        },
        {
          value: 3,
          text: 30,
        },
        {
          value: 4,
          text: 40,
        },
        {
          value: 5,
          text: 50,
        },
        {
          value: 6,
          text: 60,
        },
      ],
      gender_category: [
        {
          value: "F",
          text: "여자",
        },
        {
          value: "M",
          text: "남자",
        },
      ],
      detail_show: false,
      info: [],
      acount: 0,
      bcount: 0,
      idx: 0,
      age: 0,
      gender: "",
      location: "",
      select_category: "연령",
      select_age_category: 10,
      select_gender_category: "F",
      reload: 0,
    };
  },

  methods: {
    change_chart() {
      this.reload += 1;
    },
  },
  // computed: {
  //   ...mapState("communityStore", ["acount", "bcount"]),
  // },
  mounted() {
    var token =
      "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiYXVkIjoi7LmY7YKo65-s67KEIiwiZXhwIjoxNjY0NzE2ODExfQ.TUtMYZuidjffk5TO8oEkmhSNkm6LAUU-hJOKg--MjqfCQCknCJj9-dHuDAEeyFNA";
    // console.log(this.$route.query);

    detailVote(token, this.$route.query, ({ data }) => {
      console.log(data);
      this.info = data;

      // console.log("ㅎㅇㅎㅇ:" + this.info.bcount);
    });

    // const bar = document.querySelector(".left-progress");
    // const bar2 = document.querySelector(".right-progress");

    // const bar3 = document.querySelector(".third-progress");

    // // let t = 0;
    // // let totalMinwon = (this.acount / this.acount + this.bcount) * 100;
    // console.log("gdgd:" + this.acount + ":" + this.bcount);

    // setTimeout(() => {
    //   this.acount = this.info.acount;
    //   this.bcount = this.info.bcount;
    //   let totalMinwon = (this.acount / this.acount + this.bcount) * 100;
    //   let resolveMinwon = (this.bcount / this.acount + this.bcount) * 100;
    //   if (this.acount == 0 && this.bcount == 0) {
    //     totalMinwon = 0;
    //     resolveMinwon = 0;
    //   } else if (this.acount == 0) {
    //     totalMinwon = 0;
    //   } else if (this.bcount == 0) {
    //     resolveMinwon = 0;
    //   }

    //   // const barAnimation = setInterval(() => {
    //   //   bar.style.width = t + "%";
    //   //   t++ >= totalMinwon && clearInterval(barAnimation);
    //   // }, 10);

    //   // let t2 = 0;
    //   // const barAnimation2 = setInterval(() => {
    //   //   bar2.style.background = `linear-gradient(to right, #4F98FF 0 ${t2}%, #dedede ${t2}% 100% )`;
    //   //   t2++ >= resolveMinwon && clearInterval(barAnimation2);
    //   // }, 10);

    //   let t3 = 0;
    //   const barAnimation3 = setInterval(() => {
    //     bar3.style.background =
    //       totalMinwon > t3
    //         ? `linear-gradient(to right, #4F98FF 0 ${t3}%, #dedede ${t3}% 100% )`
    //         : totalMinwon + resolveMinwon > t3
    //         ? `linear-gradient(to right, #4F98FF 0 ${totalMinwon}%, #f44336 ${totalMinwon}% ${t3}%, #dedede ${t3}% 100%)`
    //         : `linear-gradient(to right, #4F98FF 0 ${totalMinwon}%, #f44336 ${totalMinwon}% ${
    //             totalMinwon + resolveMinwon
    //           }%, #dedede ${totalMinwon + resolveMinwon}% 100%)`;
    //     t3++ >= 100 && clearInterval(barAnimation3);
    //   }, 10);
    // }, 50);
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
