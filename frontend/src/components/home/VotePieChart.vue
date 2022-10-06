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
export default {
  name: "VotePieChart",
  props: {
    category: String,
  },
  mounted() {
    console.log(this.category);
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
    };
  },
};
</script>

<style scoped></style>
