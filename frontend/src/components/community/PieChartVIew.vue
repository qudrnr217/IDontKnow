<template>
  <div id="chart">
    <apex-chart
      type="pie"
      width="300"
      :options="chartOptions"
      :series="series"
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
        colors: ["#7B61FF", "#FF9500"],
        chart: {
          width: 300,
          type: "pie",
        },
        labels: ["Team A", "Team B"],
        responsive: [
          {
            breakpoint: 250,
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
