<template>
  <div class="demo-wrapper2">
    <ios13-segmented-control2
      class="segmented-control--wrapper2"
      v-model="value"
      :elementName="elementName"
      :segments="segments"
    />
  </div>
</template>

<script>
import Vue from "vue";
import iOS13SegmentedControl from "./SegmentControlView2.vue";

Vue.component("ios13-segmented-control2", iOS13SegmentedControl);

export default {
  name: "HelloWorld",
  props: {
    msg: String,
    segments: Array,
  },

  data() {
    return {
      elementName: "music-service-picker",
      value: "42",
      newSegmentTitle: "",
      // segments: [
      //   {
      //     title: "메뉴",
      //     id: "42",
      //   },
      //   {
      //     title: "스타일",
      //     id: "93",
      //   },
      //   {
      //     title: "장소",
      //     id: "11",
      //   },
      // ],
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
@media screen and (min-width: 600px) {
  .demo-wrapper2 {
    margin-top: 28px;
  }
}

@media screen and (max-width: 400px) {
  input[type="text"] {
    font-size: 1.4rem;
    padding: 10px 8px;
    border-radius: 8px;
  }

  button {
    font-size: 1.4rem;
    border-radius: 8px;
    padding: 10px 14px;
    margin-left: 4px;
  }
}

@media screen and (min-width: 401px) {
  input[type="text"] {
    font-size: 1.4rem;
    padding: 4px 6px;
    padding-left: 5px;
    border-radius: 4px;
  }

  button {
    font-size: 1rem;
    border-radius: 4px;
    padding: 6px 10px;
  }
}

.demo-wrapper2 {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /* align-items: center; */

  margin-top: 25px;
  /* background-color: red; */
}

.segmented-control--wrapper2 {
  max-width: 800px;
  width: 100px;

  /* margin-top: 16px; */
}
</style>
