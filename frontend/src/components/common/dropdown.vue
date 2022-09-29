<template>
  <div>
    <select class="sel">
      <!-- v-model=""  -->
      <option value="">날씨는?</option>
      <option
        v-for="(menu, index) in Mainmenu"
        :key="index"
        :value="menu.value"
      >
        {{ menu.text }}
      </option>
    </select>

    <select class="sel2">
      <!-- v-model=""  -->
      <option value="">서울시 강남구</option>
      <option
        v-for="(cur_loc, index) in Location"
        :key="index"
        :value="cur_loc.value"
      >
        {{ cur_loc.text }}
      </option>
    </select>

    <select class="sel">
      <!-- v-model=""  -->
      <option value="">거주지</option>
      <option
        v-for="(location, index) in Location"
        :key="index"
        :value="location.value"
      >
        {{ location.text }}
      </option>
    </select>

    <select class="sel">
      <!-- v-model=""  -->
      <option value="">연령대</option>
      <option v-for="(age, index) in Age" :key="index" :value="age.value">
        {{ age.text }}
      </option>
    </select>

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

    <select class="sel">
      <!-- v-model=""  -->
      <option value="">성별</option>
      <option
        v-for="(gender, index) in Gender"
        :key="index"
        :value="gender.value"
      >
        {{ gender.text }}
      </option>
    </select>
  </div>
</template>

<script>
export default {
  data() {
    return {
      Mainmenu: [
        {
          value: 1,
          text: "날씨는?",
        },
        {
          value: 2,
          text: "뭐 입지?",
        },
        {
          value: 3,
          text: "뭐 먹지?",
        },
        {
          value: 4,
          text: "어디가지?",
        },
      ],
      Gender: [
        {
          value: 1,
          text: "남성",
        },
        {
          value: 2,
          text: "여성",
        },
      ],
      Age: [
        {
          value: 1,
          text: "10대",
        },
        {
          value: 2,
          text: "20대",
        },
        {
          value: 3,
          text: "30대",
        },
        {
          value: 4,
          text: "40대",
        },
        {
          value: 5,
          text: "50대",
        },
        {
          value: 6,
          text: "60대",
        },
      ],
      Location: [
        {
          value: 1,
          text: "서울시 강남구",
        },
        {
          value: 2,
          text: "서울시 강동구",
        },
        {
          value: 3,
          text: "서울시 강북구",
        },
        {
          value: 4,
          text: "서울시 강서구",
        },
        {
          value: 5,
          text: "서울시 관악구",
        },
        {
          value: 6,
          text: "서울시 광진구",
        },
        {
          value: 7,
          text: "서울시 구로구",
        },
        {
          value: 8,
          text: "서울시 금천구",
        },
        {
          value: 9,
          text: "서울시 노원구",
        },
        {
          value: 10,
          text: "서울시 도봉구",
        },
        {
          value: 11,
          text: "서울시 동대문구",
        },
        {
          value: 12,
          text: "서울시 동작구",
        },
        {
          value: 13,
          text: "서울시 마포구",
        },
        {
          value: 14,
          text: "서울시 서대문구",
        },
        {
          value: 15,
          text: "서울시 서초구",
        },
        {
          value: 16,
          text: "서울시 성북구",
        },
        {
          value: 17,
          text: "서울시 송파구",
        },
        {
          value: 18,
          text: "서울시 양천구",
        },
        {
          value: 19,
          text: "서울시 영등포구",
        },
        {
          value: 20,
          text: "서울시 용산구",
        },
        {
          value: 21,
          text: "서울시 은평구",
        },
        {
          value: 22,
          text: "서울시 종로구",
        },
        {
          value: 23,
          text: "서울시 중구",
        },
        {
          value: 24,
          text: "서울시 중랑구",
        },
      ],
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

<style>
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
