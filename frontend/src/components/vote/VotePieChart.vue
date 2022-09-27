<template>
  <div id="chart">
    <apex-chart
      type="pie"
      width="300"
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
          width: 380,
          type: "pie",
        },
        labels: ["맞춘 확률", "틀린 확률"],
        responsive: [
          {
            breakpoint: 480,
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
