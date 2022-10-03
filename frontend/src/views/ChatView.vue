<template>
  <div
    class="background"
    :class="{
      'bgd-sunnyday': precipitationProbability <= 50,
      'bgd-rainyday': precipitationProbability > 50,
    }"
  >
    <div class="body">
      <div class="box-row-left">
        <div class="text-title text-h1">모르에게 물어봐</div>
      </div>
      <div class="box-row-left">
        <div class="text-title text-h4">당신의 고민을 함께 해요♥</div>
      </div>

      <div class="box-column">
        <!-- 오늘의 날씨 -->
        <div class="box-row">
          <div class="profile-image-box-small">
            <img class="profile-image-small" src="@/assets/image/김모르.png" />
          </div>
          <div class="box-chat-background-moleu">
            <div class="box-chat-text text-chat">
              오늘의 날씨는, <br />
              기온🌡️ {{ temperatures }}도, 습도💧 {{ humidity }}%, <br />
              풍속🌬 {{ checkWindSpeed }}, <br />
              강수확률☔ {{ precipitationProbability }}%!<br />
              결정에 참고해~ <br />
            </div>
          </div>
        </div>
        <!-- 질문 선택  -->
        <div class="box-row">
          <div class="profile-image-box-small">
            <img class="profile-image-small" src="@/assets/image/김모르.png" />
          </div>
          <div class="box-column box-chat-background-moleu">
            <div class="box-chat-text text-chat">
              오늘은 어떤 고민이 있어서 왔어?
            </div>
          </div>
        </div>
        <div class="box-row">
          <div class="profile-image-box-small">
            <img
              class="profile-image-small"
              src="@/assets/image/김모르.png"
              style="opacity: 0"
            />
          </div>
          <div class="box-column box-chat-background-moleu">
            <div class="box-chat-row text-chat">
              <div
                class="btn-rectangle-small yellow-1"
                id="1"
                value="메뉴"
                @click="checkCategory"
              >
                뭐먹을까?
              </div>
              <div
                class="btn-rectangle-small purple-1"
                id="2"
                value="스타일"
                @click="checkCategory"
                style="margin-left: 4px"
              >
                뭐입을까?
              </div>
              <div
                class="btn-rectangle-small green-1"
                id="3"
                value="장소"
                @click="checkCategory"
                style="margin-left: 4px"
              >
                어디가지?
              </div>
            </div>
          </div>
        </div>
        <!-- 메뉴추천 -->
        <div v-if="category === '메뉴'">
          <div class="box-row">
            <div class="profile-image-box-small">
              <img
                class="profile-image-small"
                src="@/assets/image/김모르.png"
              />
            </div>
            <div class="box-column box-chat-background-moleu">
              <div class="box-chat-text text-chat">
                메뉴 고민이구나! 그럼 이건 어때?
              </div>
            </div>
          </div>
          <div class="box-row">
            <div class="profile-image-box-small">
              <img
                class="profile-image-small"
                src="@/assets/image/김모르.png"
                style="opacity: 0"
              />
            </div>
            <div class="box-column box-chat-background-moleu">
              <div class="box-row">
                <div class="box-align-center">
                  <div class="box-row">
                    <img
                      src="../assets/icon/food/중식.png"
                      alt
                      class="food-image"
                      style=""
                    />
                  </div>
                  <div class="box-row">
                    <div class="btn-rectangle-small blue-1 text-h4">중식</div>
                  </div>
                </div>
                <div class="box-align-center">
                  <div class="box-row">
                    <img
                      src="../assets/icon/food/피자.png"
                      alt
                      class="food-image"
                    />
                  </div>
                  <div class="box-row box-chat-text">
                    <div class="btn-rectangle-small blue-1 text-h4">피자</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 장소추천 -->
        <div v-else-if="category === '장소'">
          <div class="box-row">
            <div class="profile-image-box-small">
              <img
                class="profile-image-small"
                src="@/assets/image/김모르.png"
              />
            </div>
            <div class="box-column box-chat-background-moleu">
              <div class="box-chat-text text-chat">
                장소 고민이구나! 그럼 이건 어때?
              </div>
            </div>
          </div>

          <div class="box-row">
            <div class="profile-image-box-small">
              <img
                class="profile-image-small"
                src="@/assets/image/김모르.png"
                style="opacity: 0"
              />
            </div>
            <div class="box-column box-chat-background-moleu">
              <div class="box-row">
                <div class="box-align-center">
                  <div class="box-row">
                    <!-- TODO: 장소 이미지 불러오기 -->
                    <img
                      src="../assets/icon/food/중식.png"
                      alt
                      class="food-image"
                      style=""
                    />
                  </div>
                  <div class="box-row box-chat-text text-h5">장소 이름</div>
                </div>
                <div class="box-align-center">
                  <div class="box-row">
                    <!-- TODO: 장소 이미지 불러오기 -->
                    <img
                      src="../assets/icon/food/피자.png"
                      alt
                      class="food-image"
                    />
                  </div>
                  <div class="box-row box-chat-text text-h5">장소 이름</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 스타일추천 -->
        <div v-else-if="category === '스타일'">
          <div class="box-row">
            <div class="profile-image-box-small">
              <img
                class="profile-image-small"
                src="@/assets/image/김모르.png"
              />
            </div>
            <div class="box-column box-chat-background-moleu">
              <div class="box-chat-text text-chat">
                스타일 고민이구나! 그럼 이건 어때?
              </div>
            </div>
          </div>
          <div class="box-row">
            <div class="profile-image-box-small">
              <img
                class="profile-image-small"
                src="@/assets/image/김모르.png"
                style="opacity: 0"
              />
            </div>
            <div class="box-column box-chat-background-moleu text-chat">
              <div class="box-row">
                <div class="box-row">
                  <img
                    src="../assets/image/clothes/12~16.png"
                    alt
                    class="clothes_img"
                  />
                </div>
              </div>
              <div class="box-row">
                얇은 니트, 맨투맨, 가디건, 청바지 가 좋을 것 같네요!
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      temperatures: 0,
      humidity: 0,
      windSpeed: 0,
      precipitationProbability: 0,
      category: "",
    };
  },
  computed: {
    checkWindSpeed() {
      if (this.windSpeed < 4) {
        return "약한 바람";
      } else if (this.windSpeed < 9) {
        return "약간 강한 바람";
      } else if (this.windSpeed < 14) {
        return "강한 바람";
      } else {
        return "매우 강한 바람";
      }
    },
  },
  methods: {
    checkCategory(event) {
      const clickedCategory = event.target.getAttribute("value");
      if (this.category === "" || this.category !== clickedCategory) {
        this.category = clickedCategory;
      } else {
        this.category = "";
      }
    },
  },
};
</script>

<style scoped>
.body {
  height: 105vh;
}
</style>
