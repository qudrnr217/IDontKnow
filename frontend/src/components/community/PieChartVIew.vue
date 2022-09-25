<template>
  <div class="chart">
    <apex-charts
      width="380"
      type="donut"
      :options="options"
      :series="series"
    ></apex-charts>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";
export default {
  components: {
    ApexCharts: VueApexCharts,
  },
  data() {
    return {
      options: {},
      series: [44, 55, 41, 17, 15],
    };
  },
};
</script>

<style></style>
