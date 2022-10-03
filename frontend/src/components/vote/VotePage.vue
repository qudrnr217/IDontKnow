<template>
  <div class="background">
    <div class="body">
      <header-view />
      <div class="title">weejee의 투표</div>
      <div class="prediction-title">나의 예측률</div>
      <div class="prediction-rate">
        <vote-pie-chart />

        <div class="result">
          <div class="result-rank">당신은 금손입니다!</div>
          <div class="percent">80%</div>
          <div class="answer">10개 중 8개 정답</div>
        </div>
      </div>
      <div class="vote-toggle"><control-view :segments="segments" /></div>
      <div class="progress-toggle">
        <control-view-2 :segments="segments2" />
      </div>
      <div class="vote-list"><comlist-view /></div>
      <footer-view />
    </div>
  </div>
</template>

<script>
import HeaderView from "../common/HeaderView.vue";
import FooterView from "../common/FooterView.vue";
import VotePieChart from "../vote/VotePieChart.vue";
import ControlView from "../common/ControlView.vue";
import ControlView2 from "../common/ControlView2.vue";
import ComlistView from "../community/ComlistView.vue";
export default {
  components: {
    HeaderView,
    FooterView,
    VotePieChart,
    ControlView,
    ControlView2,
    ComlistView,
  },
  data() {
    return {
      segments: [
        {
          title: "내가 작성한 투표",
          id: "42",
        },
        {
          title: "내가 참여한 투표",
          id: "93",
        },
      ],
      segments2: [
        {
          title: "진행",
          id: "1",
        },
        {
          title: "종료",
          id: "2",
        },
      ],
    };
  },
};
</script>
<style scoped>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}

/* 예측률 */
.prediction-rate {
  width: 100%;
  display: flex;
}

/* 진행여부 */
.progress-toggle {
  margin-left: 275px;
}

/* 투표 목록 */
.vote-list {
  margin-top: 20px;
}
</style>
