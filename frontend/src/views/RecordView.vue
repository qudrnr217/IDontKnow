<template>
  <div class="background bgd-common">
    <div class="body">
      <record-list />
    </div>
  </div>
</template>

<script>
import RecordList from "../components/record/RecordList.vue";
export default {
  name: "RecordView",
  components: { RecordList },
};
</script>

<style scoped></style>
