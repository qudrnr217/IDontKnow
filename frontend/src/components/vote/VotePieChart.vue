<template>
  <div id="chart">
    <apex-chart
      type="pie"
      :options="chartOptions"
      :series="series"
      class="donut"
    ></apex-chart>
  </div>
</template>

<script>
export default {
  name: "DonutExample",
  data() {
    return {
      series: [50, 50],
      chartOptions: {
        chart: {
          width: 200,
          type: "pie",
          fontFamily: "ONEMobileTitle",
          legend: {
            horizontalAlign: "center",
            floating: false,
          },
        },
        labels: ["맞춘 확률", "틀린 확률"],
        colors: ["#58CCFF", "#B8E9FF"],
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
    };
  },
};
</script>

<style scoped></style>
