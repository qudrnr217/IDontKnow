<template>
  <div class="background">
    <div class="body">
      <HeaderView />
      <div class="title">투표 만들기</div>
      <div class="category-box">
        <div class="category-title">카테고리</div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">카테고리</div>
            <dropdown-age-vue />
          </div>
        </div>
        <div class="content-box2">
          <div class="content">
            <div class="content_title">세부 카테고리</div>
            <select-location-view />
          </div>
        </div>

        <div class="category-title">제목</div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">제목</div>
            <input
              class="content_input"
              type="text"
              id="email"
              v-model="email"
              placeholder="제목을 작성해주세요"
            />
          </div>
        </div>
      </div>
      <div class="category-title">선택지</div>
      <div class="select-box">
        <div class="vote-box1">
          <div class="select1">A</div>
          <div class="select2">A 선택지를 작성해주세요.</div>
        </div>
        <div class="vote-box2">
          <div class="select1">B</div>
          <div class="select2">B 선택지를 작성해주세요.</div>
        </div>
      </div>
      <div class="create-btn">만들기</div>
      <footer-view />
    </div>
  </div>
</template>

<script>
import FooterView from "../common/FooterView.vue";
import HeaderView from "../common/HeaderView.vue";
// import DropdownCategory from "./DropdownCategory.vue";
import SelectLocationView from "../regist/SelectLocationView.vue";
import DropdownAgeVue from "../regist/DropdownAge.vue";
export default {
  components: {
    FooterView,
    HeaderView,
    // DropdownCategory,
    SelectLocationView,
    DropdownAgeVue,
  },
};
</script>

<style scoped>
.select-box {
  display: flex;
}
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
}

.content {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  width: 300px;
  height: 30px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}

.content_title {
  justify-content: center;
  flex-grow: 1;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  vertical-align: center;
}

.content_input {
  justify-content: flex-end;
  flex-grow: 3;
  border: none;
  font-family: "GmarketSansTTFLight";
  text-align: right;
}

.vote-box1 {
  /* display: flex; */

  margin-top: 20px;
  margin-left: 40px;
  /* width: 330px; */
  width: 150px;
  height: 179.94px;
  background: #7b61ff;
  border-radius: 10px;

  /* display: flex;
  justify-content: center;
  align-items: center; */
  text-align: center;
}

.vote-box2 {
  /* display: flex; */

  margin-top: 20px;
  margin-left: 10px;
  /* width: 330px; */
  width: 150px;
  height: 179.94px;
  background: #ff9500;
  border-radius: 10px;
  text-align: center;
}

.select1 {
  margin-top: 30px;
  font-family: "GmarketSansTTFBold";
  font-weight: 700;
  font-size: 36px;
  line-height: 41px;
  color: #ffffff;
}
.select2 {
  width: 100px;
  margin-top: 30px;
  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 13px;
  line-height: 22px;
  color: #ffffff;
  margin-left: 20px;
}

/* 카테고리 */
.category-title {
  font-family: "GmarketSansTTFMedium";
  font-weight: 500;
  font-size: 20px;
  line-height: 23px;
  color: rgba(60, 60, 67, 0.36);
  margin-top: 30px;
  margin-left: 45px;
}

.content_title {
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 17px;
  line-height: 22px;
}

.content-box1,
.content-box2 {
  display: flex;
  justify-content: center;
  align-items: center;
}

.create-btn {
  font-family: "GmarketSansTTFLight";
  font-weight: 300;
  font-size: 14px;
  line-height: 22px;
  color: #ffffff;
  width: 100px;
  height: 26.99px;
  background: #007aff;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-left: 260px;
}
</style>
