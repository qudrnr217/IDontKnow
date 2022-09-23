<template>
  <div class="box">
    <div class="selectBox2">
      <button class="label">fruits 🍊</button>
      <ul class="optionList">
        <li class="optionItem">apple</li>
        <li class="optionItem">orange</li>
        <li class="optionItem">grape</li>
        <li class="optionItem">melon</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {};
window.onload = function () {
  const label = document.querySelector(".label");
  const options = document.querySelectorAll(".optionItem");
  const handleSelect = function (item) {
    label.innerHTML = item.textContent;
    label.parentNode.classList.remove("active");
  };
  options.forEach(function (option) {
    option.addEventListener("click", function () {
      handleSelect(option);
    });
  });

  label.addEventListener("click", function () {
    if (label.parentNode.classList.contains("active")) {
      label.parentNode.classList.remove("active");
    } else {
      label.parentNode.classList.add("active");
    }
  });
};
</script>

<style>
.body {
  /* background: #ffffff; */
}
.box {
  padding: 10px;
}

/* ------------------------------------------------------- */
/* IE */
select::-ms-expand {
  display: none;
}

select {
  -o-appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

.select {
  width: 150px;
  height: 35px;
  /* background: url("https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png")
    calc(100% - 5px) center no-repeat; */
  background-size: 20px;
  padding: 5px 30px 5px 10px;
  border-radius: 4px;
  outline: 0 none;
}
.select option {
  background: black;
  color: #eee;
  padding: 3px 0;
}
.select option:hover {
  background: rgb(58, 58, 58);
}

/* ------------------------------------------------------- */
.selectBox2 * {
  box-sizing: border-box;
}
.selectBox2 {
  position: relative;
  /* left: 50px; */
  width: 150px;
  height: 35px;
  border-radius: 4px;
  /* background: url("@/assets/icon/selecticon.png") center no-repeat; */
  /* background-size: 20px; */
  cursor: pointer;
}
.selectBox2:before {
  content: url("@/assets/icon/selecticon.png");
  display: block;
  width: 2px;
  height: 100%;
  position: absolute;
  top: 0;
  right: 35px;
  /* background: lightcoral; */
  /* background: url("@/assets/icon/selecticon.png") center no-repeat; */
}

.selectBox2:after {
  content: url("@/assets/icon/selecticon.png");
  display: block;
  width: 2px;
  height: 100%;
  position: absolute;
  top: 0;
  right: 35px;
  background: #ededed;
}

.selectBox2 .label {
  display: flex;
  align-items: center;
  width: inherit;
  height: inherit;
  border: 0 none;
  outline: 0 none;
  padding-left: 15px;
  background: transparent;
  cursor: pointer;
}

.selectBox2 .optionList {
  position: absolute;
  top: 28px;
  left: 0;
  width: 100%;
  background: #ededed;
  color: #000000;
  list-style-type: none;
  padding: 0;
  border-radius: 6px;
  overflow: hidden;
  max-height: 0;
  transition: 0.3s ease-in;
}

.selectBox2 .optionList::-webkit-scrollbar {
  width: 6px;
}
.selectBox2 .optionList::-webkit-scrollbar-track {
  background: transparent;
}
.selectBox2 .optionList::-webkit-scrollbar-thumb {
  background: #303030;
  border-radius: 45px;
}
.selectBox2 .optionList::-webkit-scrollbar-thumb:hover {
  background: #303030;
}

.selectBox2.active .optionList {
  max-height: 500px;
}

.selectBox2 .optionItem {
  border-bottom: 1px dashed;
  padding: 5px 15px 5px;
  transition: 0.1s;
  text-align: end;
}

.selectBox2 .optionItem:hover {
  background: rgb(175, 93, 93);
}

.selectBox2 .optionItem:last-child {
  border-bottom: 0 none;
}
</style>
