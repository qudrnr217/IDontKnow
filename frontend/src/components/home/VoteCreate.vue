<template>
  <div class="">
    <div class="box-row-title">
      <div class="text-title text-h1">투표 만들기</div>
    </div>
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
  </div>
</template>

<script>
// import DropdownCategory from "./DropdownCategory.vue";
// import SelectLocationView from "../regist/SelectLocationView.vue";
// import DropdownAgeVue from "../regist/DropdownAge.vue";
export default {
  name: "VoteCreate",
  components: {
    // DropdownCategory,
    // SelectLocationView,
    // DropdownAgeVue,
  },
  data() {
    return {
      category: "공통",
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

<style scoped></style>
