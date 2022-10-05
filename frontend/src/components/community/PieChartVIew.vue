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
import {
  participateVoteAge,
  participateVoteGender,
  participateVoteLocation,
} from "@/api/community.js";
export default {
  name: "DonutExample",
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
  mounted() {
    var token =
      "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxOCIsImF1ZCI6IuuvvO2VmOydgCIsImV4cCI6MTY2NDg2MjI0N30.Y9Olw4mSf2cGBFNCuoxAnXFrgSDq7qkbEp7RWnK4iGjscAOrqw09HT_0UMw_fcsG";
    participateVoteAge(token, this.voteId, ({ data }) => {
      console.log("연령: ", data);
      if (this.idx == "연령") {
        //연령
        for (var i = 0; i < data.length; i++) {
          if (data[i].age == this.age) {
            if (data[i].choice == "1") this.age_A = data[i].count;
            else if (data[i].choice == "2") this.age_B = data[i].count;
          }
        }
        console.log(this.age_A + ":" + this.age_B);
        this.series = [this.age_A, this.age_B];
      }
    });

    participateVoteGender(token, this.voteId, ({ data }) => {
      console.log(data);
      if (this.idx == "성별") {
        for (var i = 0; i < data.length; i++) {
          if (data[i].gender == this.gender) {
            if (data[i].choice == "1") this.gender_A = data[i].count;
            else if (data[i].choice == "2") this.gender_B = data[i].count;
          }
        }
        this.series = [this.gender_A, this.gender_B];
      }
    });

    participateVoteLocation(token, this.voteId, ({ data }) => {
      console.log(data);
      for (var i = 0; i < data.length; i++) {
        if (data[i].districtId == this.districtId) {
          if (data[i].choice == "A") this.district_A += data[i].count;
          else this.district_B += data[i].count;
        }
      }
    });
  },
  data() {
    return {
      series: [50, 50],
      chartOptions: {
        colors: [
          function ({ category }) {
            if (category === "메뉴") {
              return "#FD6F22";
            } else if (category === "스타일") {
              return "#692498";
            } else {
              return "#568A35";
            }
          },
          function ({ category }) {
            if (category === "메뉴") {
              return "#FD9F28";
            } else if (category === "스타일") {
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
          legend: {
            horizontalAlign: "center",
            floating: false,
          },
        },
        labels: ["Team A", "Team B"],
        responsive: [
          {
            breakpoint: 1500,
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
      district_A: 0,
      district_B: 0,
    };
  },
};
</script>

<style scoped></style>
