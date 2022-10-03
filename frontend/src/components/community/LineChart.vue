<template>
  <div id="chart">
    <apex-chart
      type="line"
      height="350"
      :options="chartOptions"
      :series="series"
    ></apex-chart>
  </div>
</template>

<script>
import { participateVoteLocation } from "@/api/community.js";
export default {
  props: {
    voteId: Number,
    idx: String,
  },
  data() {
    return {
      series: [
        {
          name: "High - 2013",
          data: [28, 29, 33, 36, 32, 32, 33],
        },
        {
          name: "Low - 2013",
          data: [12, 11, 14, 18, 17, 13, 13],
        },
      ],
      chartOptions: {
        chart: {
          height: 350,
          type: "line",
          dropShadow: {
            enabled: true,
            color: "#000",
            top: 18,
            left: 7,
            blur: 10,
            opacity: 0.2,
          },
          toolbar: {
            show: false,
          },
        },
        colors: ["#77B6EA", "#545454"],
        dataLabels: {
          enabled: true,
        },
        stroke: {
          curve: "smooth",
        },
        title: {
          text: "Average High & Low Temperature",
          align: "left",
        },
        grid: {
          borderColor: "#e7e7e7",
          row: {
            colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
            opacity: 0.5,
          },
        },
        markers: {
          size: 1,
        },
        xaxis: {
          categories: [
            "강남구",
            "강동구",
            "강북구",
            "강서구",
            "관악구",
            "광진구",
            "구로구",
            "금천구",
            "노원구",
            "도봉구",
            "동대문구",
            "동작구",
            "마포구",
            "서대문구",
            "서초구",
            "성동구",
            "성북구",
            "송파구",
            "양천구",
            "영등포구",
            "용산구",
            "은평구",
            "종로구",
            "중구",
            "중랑구",
          ],
          //   title: {
          //     text: "Month",
          //   },
        },
        // yaxis: {
        //   title: {
        //     text: "Temperature",
        //   },
        //   min: 5,
        //   max: 40,
        // },
        legend: {
          position: "top",
          horizontalAlign: "right",
          floating: true,
          offsetY: -25,
          offsetX: -5,
        },
      },
    };
  },
  mounted() {
    var token =
      "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiYXVkIjoi7LmY7YKo65-s67KEIiwiZXhwIjoxNjY0NzE2ODExfQ.TUtMYZuidjffk5TO8oEkmhSNkm6LAUU-hJOKg--MjqfCQCknCJj9-dHuDAEeyFNA";
    participateVoteLocation(token, this.voteId, ({ data }) => {
      console.log(data);
      if (this.idx == "거주지") {
        for (var i = 0; i < data.length; i++) {
          if (data[i].districtId == this.districtId) {
            if (data[i].choice == "A") this.district_A += data[i].count;
            else this.district_B += data[i].count;
          }
        }
      }
    });
  },
};
</script>

<style></style>
