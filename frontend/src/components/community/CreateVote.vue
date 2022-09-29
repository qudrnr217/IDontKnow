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
            <select class="sel">
              <!-- v-model=""  -->
              <option value="">카테고리</option>
              <option
                v-for="(category, index) in Category"
                :key="index"
                :value="category.value"
              >
                {{ category.text }}
              </option>
            </select>
          </div>
        </div>
        <div class="content-box2">
          <div class="content">
            <div class="content_title">세부 카테고리</div>
            <select class="sel">
              <!-- v-model=""  -->
              <option value="">세부 카테고리</option>
              <option
                v-for="(category_menu, index) in Category_Menu"
                :key="index"
                :value="category_menu.value"
              >
                {{ category_menu.text }}
              </option>
            </select>

            <select class="sel">
              <!-- v-model=""  -->
              <option value="">세부 카테고리</option>
              <option
                v-for="(category_style, index) in Category_Style"
                :key="index"
                :value="category_style.value"
              >
                {{ category_style.text }}
              </option>
            </select>

            <select class="sel">
              <!-- v-model=""  -->
              <option value="">세부 카테고리</option>
              <option
                v-for="(category_location, index) in Category_Location"
                :key="index"
                :value="category_location.value"
              >
                {{ category_location.text }}
              </option>
            </select>
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
// import SelectLocationView from "../regist/SelectLocationView.vue";
// import DropdownAgeVue from "../regist/DropdownAge.vue";
export default {
  components: {
    FooterView,
    HeaderView,
    // DropdownCategory,
    // SelectLocationView,
    // DropdownAgeVue,
  },
  data() {
    return {
      Category: [
        {
          value: 1,
          text: "메뉴",
        },
        {
          value: 2,
          text: "장소",
        },
        {
          value: 3,
          text: "스타일",
        },
      ],
      Category_Menu: [
        {
          value: 1,
          text: "한식",
        },
        {
          value: 2,
          text: "분식",
        },
        {
          value: 3,
          text: "카페/디저트",
        },
        {
          value: 4,
          text: "돈까스/일식",
        },
        {
          value: 5,
          text: "회",
        },
        {
          value: 6,
          text: "치킨",
        },
        {
          value: 7,
          text: "피자",
        },
        {
          value: 8,
          text: "아시안/양식",
        },
        {
          value: 9,
          text: "중식",
        },
        {
          value: 10,
          text: "족발/보쌈",
        },
        {
          value: 11,
          text: "야식",
        },
        {
          value: 12,
          text: "찜탕",
        },
        {
          value: 13,
          text: "도시락",
        },
        {
          value: 14,
          text: "패스트푸드",
        },
      ],
      Category_Location: [
        {
          value: 1,
          text: "실내",
        },
        {
          value: 2,
          text: "실외",
        },
      ],
      Category_Style: [
        {
          value: 1,
          text: "머리",
        },
        {
          value: 2,
          text: "옷",
        },
        {
          value: 3,
          text: "신발",
        },
        {
          value: 4,
          text: "악세서리",
        },
      ],
    };
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
.sel {
  width: 150px;
  height: 30px;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  border: none;
}
.sel2 {
  width: 100px;
  height: 30px;
  margin-right: 30px;
  font-size: 12px;
  font-family: "GmarketSansTTFMedium";
  border: none;
  background: transparent;
}
</style>
