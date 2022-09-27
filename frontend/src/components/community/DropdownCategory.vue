<template>
  <div class="age">
    <div class="age_selected">카테고리 선택</div>
    <ul class="age_list">
      <li class="option">10대</li>
      <li class="option">20대</li>
      <li class="option">30대</li>
      <li class="option">40대</li>
      <li class="option">50대</li>
      <li class="option">60대</li>
      <li class="option">70대</li>
    </ul>
  </div>
</template>

<script>
window.onload = function () {
  const ageEL = document.querySelector(".age");
  const ageListEl = document.querySelector(".age_list");

  let isActiveAgeList = false;
  ageEL.addEventListener("click", function () {
    isActiveAgeList = !isActiveAgeList; // transition
    if (isActiveAgeList) {
      // active age list
      ageListEl.classList.add("active");
    } else {
      // hide age list
      ageListEl.classList.remove("active");
    }
  });
};
export default {};
</script>

<style scoped>
.age {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-left: 10px;
  cursor: pointer;
}
.age .age_list {
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
.age .age_list .active {
  /* 리스트 보이기 */
  overflow-x: hidden;
  overflow-y: auto;
  opacity: 1;
  max-height: 200px;
}
.age .age_list::-webkit-scrollbar {
  width: 4px;
}
.age .age_list::-webkit-scrollbar-thumb {
  background-color: #8d8d8d;
  border-radius: 2px;
}
.age .age_list::-webkit-scrollbar-track {
  background-color: #ebe9e9;
  border-radius: 5px;
}
</style>
