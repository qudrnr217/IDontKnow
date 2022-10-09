<template>
  <div id="chart">
    <apex-chart
      type="pie"
      width="310"
      :options="chartOptions"
      :series="series"
    ></apex-chart>
  </div>
</template>

<script>
import { participateVoteAge, participateVoteGender } from "@/api/community.js";
import { mapState } from "vuex";
export default {
  name: "VotePieChart",
  props: {
    voteId: Number,
    idx: String,
    age: String,
    gender: String,
    location: Number,
    category: String,
    optionA: String,
    optionB: String,
  },
  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  mounted() {
    participateVoteAge(this.accessToken, this.voteId, ({ data }) => {
      if (this.idx == "연령") {
        //연령
        for (var i = 0; i < data.length; i++) {
          if (data[i].age == this.age) {
            if (data[i].choice == "A") this.age_A = data[i].count;
            else if (data[i].choice == "B") this.age_B = data[i].count;
          }
        }
        this.series = [this.age_A, this.age_B];
      }
    });

    participateVoteGender(this.accessToken, this.voteId, ({ data }) => {
      if (this.idx == "성별") {
        for (var i = 0; i < data.length; i++) {
          if (data[i].gender == this.gender) {
            if (data[i].choice == "A") this.gender_A = data[i].count;
            else if (data[i].choice == "B") this.gender_B = data[i].count;
          }
        }
        this.series = [this.gender_A, this.gender_B];
      }
    });
  },
  data() {
    return {
      series: [50, 50],
      chartOptions: {
        colors: [
          () => {
            if (this.category == "메뉴") {
              return "#FD6F22";
            } else if (this.category == "스타일") {
              return "#692498";
            } else {
              return "#568A35";
            }
          },
          () => {
            if (this.category == "메뉴") {
              return "#FD9F28";
            } else if (this.category == "스타일") {
              return "#9A30AE";
            } else {
              return "#7DB249";
            }
          },
        ],
        chart: {
          width: 300,
          type: "pie",
          fontFamily: "ONEMobileTitle",
        },
        labels: ["선택지 A", "선택지 B"],
        responsive: [
          {
            breakpoint: 2000,
            options: {
              chart: {
                width: 200,
              },
              legend: {
                position: "bottom",
              },
            },
          },
        ],
      },
      age_A: 0,
      age_B: 0,
      gender_A: 0,
      gender_B: 0,
    };
  },
};
</script>

<style scoped></style>
