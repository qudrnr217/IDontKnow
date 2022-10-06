<template>
  <div>
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">투표 만들기</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        고민되는 두 가지의 선택지를 공유해보세요 !
      </div>
    </div>

    <div class="box-row-left">
      <div class="text-title text-h2 blue-4-text">카테고리🏷</div>
    </div>
    <div class="box-row">
      <select
        v-model="info.category"
        class="sb-rectangle-long sel text-h3 blue-3-border"
      >
        <option selected disabled value="">카테고리를 선택해주세요.</option>
        <option
          v-for="(category, index) in Category"
          :key="index"
          :value="category"
        >
          {{ category }}
        </option>
      </select>
    </div>
    <div class="box-row-left" v-if="info.category === '메뉴'">
      <div class="text-title text-h2 blue-4-text">세부 카테고리🍙</div>
    </div>
    <div class="box-row-left" v-if="info.category === '스타일'">
      <div class="text-title text-h2 blue-4-text">세부 카테고리👕</div>
    </div>
    <div class="box-row-left" v-if="info.category === '장소'">
      <div class="text-title text-h2 blue-4-text">세부 카테고리🚗</div>
    </div>
    <div class="box-row" v-if="info.category === '메뉴'">
      <select
        v-model="info.subCategory"
        class="sb-rectangle-long sel text-h3 blue-3-border"
      >
        <option selected disabled value="">
          세부 카테고리를 선택해주세요.
        </option>
        <option
          v-for="(item, index) in Category_Menu"
          :key="index"
          :value="item"
        >
          {{ item }}
        </option>
      </select>
    </div>
    <div class="box-row" v-if="info.category === '스타일'">
      <select
        v-model="info.subCategory"
        class="sb-rectangle-long sel text-h3 blue-3-border"
      >
        <option selected disabled>세부 카테고리를 선택해주세요.</option>
        <option
          v-for="(item, index) in Category_Style"
          :key="index"
          :value="item"
        >
          {{ item }}
        </option>
      </select>
    </div>
    <div class="box-row" v-if="info.category === '장소'">
      <select
        v-model="info.subCategory"
        class="sb-rectangle-long sel text-h3 blue-3-border"
      >
        <option selected disabled>세부 카테고리를 선택해주세요.</option>
        <option
          v-for="(item, index) in Category_Location"
          :key="index"
          :value="item"
        >
          {{ item }}
        </option>
      </select>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h2 blue-4-text">제목📌</div>
    </div>
    <div class="box-row text-h3">
      <input
        id="title"
        class="input-rectangle-long blue-3-border"
        type="text"
        style="font-size: 16px; border-radius: 10px"
        v-model="info.title"
        placeholder="제목을 작성해주세요(25자 이내)."
      />
    </div>
    <!-- 투표 선택지 -->
    <div class="box-row-left">
      <div class="text-title text-h2 blue-4-text">선택지📝</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-3-text">
        25자 이내로 작성해주세요.
      </div>
    </div>
    <div class="vote-options-box-big">
      <div
        id="vote-option-a"
        class="vote-option-box-big text-h4 white blue-3-border"
      >
        <div class="box-row">
          <textarea
            v-model="info.optionA"
            id="optionA"
            type="text"
            class="vote-option-input"
            placeholder="A를 작성해주세요."
          />
        </div>
      </div>
      <div
        id="vote-option-b"
        class="vote-option-box-big text-h4 white blue-3-border"
      >
        <div class="box-row">
          <textarea
            v-model="info.optionB"
            id="optionB"
            type="text"
            class="vote-option-input"
            placeholder="B를 작성해주세요."
          />
        </div>
      </div>
    </div>
    <div class="box-btn-right" @click="createVote">
      <div class="btn-rectangle-medium blue-2">만들기</div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "VoteCreate",
  components: {},
  data() {
    return {
      info: {
        category: "",
        subCategory: "",
        title: "",
        optionA: "",
        optionB: "",
      },

      Category: ["메뉴", "스타일", "장소"],
      Category_Menu: [
        "한식",
        "분식",
        "카페_디저트",
        "돈까스_일식",
        "회",
        "치킨",
        "피자",
        "아시안_양식",
        "중식",
        "족발_보쌈",
        "야식",
        "찜탕",
        "도시락",
        "패스트푸드",
      ],
      Category_Location: ["실내", "실외"],
      Category_Style: ["머리", "옷", "신발", "악세서리"],
    };
  },

  computed: {
    ...mapState("userStore", ["accessToken"]),
  },
  methods: {
    ...mapActions("communityStore", ["REGIST_VOTE"]),
    createVote() {
      // 투표 만들기 api 호출
      // 투표 목록으로 돌아가기
      this.REGIST_VOTE({
        info: this.info,
        token:
          // "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiYXVkIjoi7LmY7YKo65-s67KEIiwiZXhwIjoxNjY0OTU0NzQ3fQ.BVaQ9ohkpvxgUeAvM1Z6pl6ywg5bsDw7HwUjagTkwEIuWdmbc2oTRqbpKekbRO1D",
          this.accessToken,
      });
      console.log(this.info.category);
      this.$router.push({
        name: "voteList",
        path: "/",
        params: { status: false, category: this.info.category },
      });
    },
  },
};
</script>

<style scoped></style>
