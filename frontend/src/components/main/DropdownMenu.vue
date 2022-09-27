<template>
  <div class="location">
    <div class="location_selected">날씨는?</div>
    <ul class="location_list">
      <li class="option">날씨는?</li>
      <li class="option">뭐 입지?</li>
      <li class="option">뭐 먹지?</li>
      <li class="option">어디가지?</li>
    </ul>
  </div>
</template>

<script>
window.onload = function () {
  const locationEL = document.querySelector(".location");
  const locationListEl = document.querySelector(".location_list");

  let isActiveLocationList = false;
  locationEL.addEventListener("click", function () {
    isActiveLocationList = !isActiveLocationList; // transition
    if (isActiveLocationList) {
      // active Location list
      locationListEl.classList.add("active");
    } else {
      // hide Location list
      locationListEl.classList.remove("active");
    }
  });
};
export default {};
</script>

<style>
.location {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-left: 10px;
  cursor: pointer;
}
.location .location_list {
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  box-sizing: border-box;
  border-radius: 1px;
  border: 1px solid #d9d6d6;
  /* 리스트 안 보이게 하기 */
  overflow: hidden;
  max-height: 0;
  opacity: 0;
  transition: 0.3s;
}
.location .location_list .active {
  /* 리스트 보이기 */
  overflow-x: hidden;
  overflow-y: auto;
  opacity: 1;
  max-height: 200px;
}
.location .location_list::-webkit-scrollbar {
  width: 4px;
}
.location .location_list::-webkit-scrollbar-thumb {
  background-color: #8d8d8d;
  border-radius: 2px;
}
.location .location_list::-webkit-scrollbar-track {
  background-color: #ebe9e9;
  border-radius: 5px;
}
</style>
