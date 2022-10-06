<template>
  <div ref="slider" class="keen-slider">
    <div class="keen-slider__slide number-slide1">
      <div class="vote-list" v-if="vote_list1">
        <!-- v-for="vote in voteList" :key="vote.voteId"  -->
        <div
          class="vote-card"
          @click="detailCard"
          :value="`${vote_list1.voteId}`"
          :style="{
            backgroundImage: `linear-gradient(
                rgba(255, 255, 255, 0.5),
                rgba(255, 255, 255, 0.5)
                ), url(${require('@/assets/image/category/' +
                  vote_list1.category +
                  '/' +
                  vote_list1.subCategory.replace('/', '_') +
                  '.jpg')})`,
          }"
        >
          <div
            class="vote-title-box"
            @click="detailCard"
            :value="`${vote_list1.voteId}`"
          >
            <div
              class="vote-title-text text-h3"
              @click="detailCard"
              :value="`${vote_list1.voteId}`"
            >
              {{ vote_list1.title }}
            </div>
          </div>
          <div
            class="vote-writer-box"
            @click="detailCard"
            :value="`${vote_list1.voteId}`"
          >
            <div
              class="vote-writer-text text-h4"
              @click="detailCard"
              :value="`${vote_list1.voteId}`"
            >
              작성자 : {{ vote_list1.name }}
            </div>
          </div>
          <div
            class="vote-options-box"
            @click="detailCard"
            :value="`${vote_list1.voteId}`"
          >
            <div
              class="vote-option-box"
              :class="{
                'yellow-2-border': category === '메뉴',
                'purple-2-border': category === '스타일',
                'green-2-border': category === '장소',
              }"
              @click="detailCard"
              :value="`${vote_list1.voteId}`"
            >
              <div
                class="vote-option-text text-h4"
                @click="detailCard"
                :value="`${vote_list1.voteId}`"
              >
                {{ vote_list1.optionA }}
              </div>
            </div>
            <div class="vote-option-vs">vs</div>
            <div
              class="vote-option-box"
              :class="{
                'yellow-2-border': category === '메뉴',
                'purple-2-border': category === '스타일',
                'green-2-border': category === '장소',
              }"
              @click="detailCard"
              :value="`${vote_list1.voteId}`"
            >
              <div
                class="vote-option-text text-h4"
                @click="detailCard"
                :value="`${vote_list1.voteId}`"
              >
                {{ vote_list1.optionB }}
              </div>
            </div>
          </div>
          <div
            class="vote-info-box"
            @click="detailCard"
            :value="`${vote_list1.voteId}`"
          >
            <div
              class="vote-category-box"
              @click="detailCard"
              :value="`${vote_list1.voteId}`"
            >
              <div
                class="vote-category-main text-h5"
                @click="detailCard"
                :value="`${vote_list1.voteId}`"
              >
                # {{ vote_list1.category }}
              </div>
              <div
                class="vote-category-sub text-h5"
                @click="detailCard"
                :value="`${vote_list1.voteId}`"
              >
                # {{ vote_list1.subCategory }}
              </div>
            </div>
            <div
              class="vote-count-box"
              @click="detailCard"
              :value="`${vote_list1.voteId}`"
            >
              <div
                class="vote-view-box"
                @click="detailCard"
                :value="`${vote_list1.voteId}`"
              >
                <img
                  src="../../assets/icon/views.png"
                  alt="조회"
                  class="vote-btn-view"
                />
                <div class="text-h5">
                  {{ vote_list1.hitCount }}
                </div>
              </div>
              <div
                class="vote-comment-box"
                @click="detailCard"
                :value="`${vote_list1.voteId}`"
              >
                <img
                  src="../../assets/icon/chat.png"
                  alt="댓글"
                  class="vote-btn-comment"
                />
                <div class="text-h5">
                  {{ vote_list1.commentCount }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="keen-slider__slide number-slide1">
      <div class="vote-list" v-if="vote_list2">
        <!-- v-for="vote in voteList" :key="vote.voteId"  -->
        <div
          class="vote-card"
          @click="detailCard"
          :value="`${vote_list2.voteId}`"
          :style="{
            backgroundImage: `linear-gradient(
                rgba(255, 255, 255, 0.5),
                rgba(255, 255, 255, 0.5)
                ), url(${require('@/assets/image/category/' +
                  vote_list2.category +
                  '/' +
                  vote_list2.subCategory.replace('/', '_') +
                  '.jpg')})`,
          }"
        >
          <div
            class="vote-title-box"
            @click="detailCard"
            :value="`${vote_list2.voteId}`"
          >
            <div
              class="vote-title-text text-h3"
              @click="detailCard"
              :value="`${vote_list2.voteId}`"
            >
              {{ vote_list2.title }}
            </div>
          </div>
          <div
            class="vote-writer-box"
            @click="detailCard"
            :value="`${vote_list2.voteId}`"
          >
            <div
              class="vote-writer-text text-h4"
              @click="detailCard"
              :value="`${vote_list2.voteId}`"
            >
              작성자 : {{ vote_list2.name }}
            </div>
          </div>
          <div
            class="vote-options-box"
            @click="detailCard"
            :value="`${vote_list2.voteId}`"
          >
            <div
              class="vote-option-box"
              :class="{
                'yellow-2-border': category === '메뉴',
                'purple-2-border': category === '스타일',
                'green-2-border': category === '장소',
              }"
              @click="detailCard"
              :value="`${vote_list2.voteId}`"
            >
              <div
                class="vote-option-text text-h4"
                @click="detailCard"
                :value="`${vote_list2.voteId}`"
              >
                {{ vote_list2.optionA }}
              </div>
            </div>
            <div class="vote-option-vs">vs</div>
            <div
              class="vote-option-box"
              :class="{
                'yellow-2-border': category === '메뉴',
                'purple-2-border': category === '스타일',
                'green-2-border': category === '장소',
              }"
              @click="detailCard"
              :value="`${vote_list2.voteId}`"
            >
              <div
                class="vote-option-text text-h4"
                @click="detailCard"
                :value="`${vote_list2.voteId}`"
              >
                {{ vote_list2.optionB }}
              </div>
            </div>
          </div>
          <div
            class="vote-info-box"
            @click="detailCard"
            :value="`${vote_list2.voteId}`"
          >
            <div
              class="vote-category-box"
              @click="detailCard"
              :value="`${vote_list2.voteId}`"
            >
              <div
                class="vote-category-main text-h5"
                @click="detailCard"
                :value="`${vote_list2.voteId}`"
              >
                # {{ vote_list2.category }}
              </div>
              <div
                class="vote-category-sub text-h5"
                @click="detailCard"
                :value="`${vote_list2.voteId}`"
              >
                # {{ vote_list2.subCategory }}
              </div>
            </div>
            <div
              class="vote-count-box"
              @click="detailCard"
              :value="`${vote_list2.voteId}`"
            >
              <div
                class="vote-view-box"
                @click="detailCard"
                :value="`${vote_list2.voteId}`"
              >
                <img
                  src="../../assets/icon/views.png"
                  alt="조회"
                  class="vote-btn-view"
                />
                <div class="text-h5">
                  {{ vote_list2.hitCount }}
                </div>
              </div>
              <div
                class="vote-comment-box"
                @click="detailCard"
                :value="`${vote_list2.voteId}`"
              >
                <img
                  src="../../assets/icon/chat.png"
                  alt="댓글"
                  class="vote-btn-comment"
                />
                <div class="text-h5">
                  {{ vote_list2.commentCount }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="keen-slider__slide number-slide1">
      <div class="vote-list" v-if="vote_list3">
        <!-- v-for="vote in voteList" :key="vote.voteId"  -->
        <div
          class="vote-card"
          @click="detailCard"
          :value="`${vote_list3.voteId}`"
          :style="{
            backgroundImage: `linear-gradient(
                rgba(255, 255, 255, 0.5),
                rgba(255, 255, 255, 0.5)
                ), url(${require('@/assets/image/category/' +
                  vote_list3.category +
                  '/' +
                  vote_list3.subCategory.replace('/', '_') +
                  '.jpg')})`,
          }"
        >
          <div
            class="vote-title-box"
            @click="detailCard"
            :value="`${vote_list3.voteId}`"
          >
            <div
              class="vote-title-text text-h3"
              @click="detailCard"
              :value="`${vote_list3.voteId}`"
            >
              {{ vote_list3.title }}
            </div>
          </div>
          <div
            class="vote-writer-box"
            @click="detailCard"
            :value="`${vote_list3.voteId}`"
          >
            <div
              class="vote-writer-text text-h4"
              @click="detailCard"
              :value="`${vote_list3.voteId}`"
            >
              작성자 : {{ vote_list3.name }}
            </div>
          </div>
          <div
            class="vote-options-box"
            @click="detailCard"
            :value="`${vote_list3.voteId}`"
          >
            <div
              class="vote-option-box"
              :class="{
                'yellow-2-border': category === '메뉴',
                'purple-2-border': category === '스타일',
                'green-2-border': category === '장소',
              }"
              @click="detailCard"
              :value="`${vote_list3.voteId}`"
            >
              <div
                class="vote-option-text text-h4"
                @click="detailCard"
                :value="`${vote_list3.voteId}`"
              >
                {{ vote_list3.optionA }}
              </div>
            </div>
            <div class="vote-option-vs">vs</div>
            <div
              class="vote-option-box"
              :class="{
                'yellow-2-border': category === '메뉴',
                'purple-2-border': category === '스타일',
                'green-2-border': category === '장소',
              }"
              @click="detailCard"
              :value="`${vote_list3.voteId}`"
            >
              <div
                class="vote-option-text text-h4"
                @click="detailCard"
                :value="`${vote_list3.voteId}`"
              >
                {{ vote_list3.optionB }}
              </div>
            </div>
          </div>
          <div
            class="vote-info-box"
            @click="detailCard"
            :value="`${vote_list3.voteId}`"
          >
            <div
              class="vote-category-box"
              @click="detailCard"
              :value="`${vote_list3.voteId}`"
            >
              <div
                class="vote-category-main text-h5"
                @click="detailCard"
                :value="`${vote_list3.voteId}`"
              >
                # {{ vote_list3.category }}
              </div>
              <div
                class="vote-category-sub text-h5"
                @click="detailCard"
                :value="`${vote_list3.voteId}`"
              >
                # {{ vote_list3.subCategory }}
              </div>
            </div>
            <div
              class="vote-count-box"
              @click="detailCard"
              :value="`${vote_list3.voteId}`"
            >
              <div
                class="vote-view-box"
                @click="detailCard"
                :value="`${vote_list3.voteId}`"
              >
                <img
                  src="../../assets/icon/views.png"
                  alt="조회"
                  class="vote-btn-view"
                />
                <div class="text-h5">
                  {{ vote_list3.hitCount }}
                </div>
              </div>
              <div
                class="vote-comment-box"
                @click="detailCard"
                :value="`${vote_list3.voteId}`"
              >
                <img
                  src="../../assets/icon/chat.png"
                  alt="댓글"
                  class="vote-btn-comment"
                />
                <div class="text-h5">
                  {{ vote_list3.commentCount }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "keen-slider/keen-slider.min.css";
import KeenSlider from "keen-slider";
import { trendVote } from "@/api/community.js";
const animation = { duration: 50000, easing: (t) => t };
// var token =
//   "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxOCIsImF1ZCI6IuuvvO2VmOydgCIsImV4cCI6MTY2NDg5Nzk1Mn0.OuwHaksv6Ws9-mqijohxmnPmnpixSxEByza4jCxMdXRRDSPHpI8GMpB2D-uMmPjJ";
export default {
  name: "SliderChart",
  props: {
    category: String,
  },
  mounted() {
    trendVote(this.category, ({ data }) => {
      console.log("트랜드: ", data);
      this.vote_list = data;
      this.vote_list1 = data[0];
      this.vote_list2 = data[1];
      this.vote_list3 = data[2];
    });

    this.slider = new KeenSlider(this.$refs.slider, {
      loop: true,
      renderMode: "performance",
      drag: false,
      created(s) {
        s.moveToIdx(5, true, animation);
      },
      updated(s) {
        s.moveToIdx(s.track.details.abs + 5, true, animation);
      },
      animationEnded(s) {
        s.moveToIdx(s.track.details.abs + 5, true, animation);
      },
    });
  },
  beforeDestroy() {
    if (this.slider) this.slider.destroy();
  },
  data() {
    return {
      vote_list: [],
      vote_list1: { category: "메뉴", subCategory: "분식" },
      vote_list2: { category: "메뉴", subCategory: "분식" },
      vote_list3: { category: "메뉴", subCategory: "분식" },
    };
  },
  methods: {
    detailCard(e) {
      // console.log("여기는 유저아이디: " + this.$store.state.userStore.userId);
      if (this.$store.state.userStore.userId === 0) {
        this.$router.push({ name: "userLogin" });
      } else {
        const clickedId = e.target.getAttribute("value");
        // 파라미터 같이 보내기 !
        this.$router.push({
          name: "voteDetail",
          // path: "/vote",
          params: {
            voteId: clickedId,
          },
        });
      }
    },
  },
};
</script>

<style scoped>
[class^="number-slide"],
[class*=" number-slide"] {
  /* background: grey; */
  display: flex;
  align-items: flex-start;
  justify-content: center;

  color: #fff;
  height: 200px;
  color: black;
}

.keen-slider {
  width: 350px;
  height: 190px;
}

.keen-slider__slide.number-slide1 {
  height: 250px;
}
</style>
