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
      <div class="box-btn-right">
        <!-- 서비스 소개 토글 버튼 -->
        <div
          class="btn-rectangle-long text-h3 blue-0"
          @click="isOpened = true"
          v-if="!isOpened"
        >
          모르에 대해🔽
        </div>
        <div class="btn-toggle text-h3 white" @click="isOpened = false" v-else>
          접기🔼
        </div>
      </div>
      <div class="box-column">
        <!-- 모르에 대해 -->
        <div class="box-row" v-if="isOpened">
          <div class="profile-image-box-small">
            <img class="profile-image-small" src="@/assets/image/김모르.png" />
          </div>
          <div class="box-chat-background-moleu-info">
            <div class="box-chat-text text-chat">
              안녕 나는 김모르 ! <br />
              지금부터 너의 고민을 함께 할거야 ~ <br />
              메뉴는 36만 건의 배달 빅데이터를<br />
              지역구와 시간대별로 분산처리해서<br />
              추천해주고 있어 :)<br />
              스타일은 기상청 날씨 정보를 바탕으로<br />
              기온별 옷차림을 추천해주고 있고 ! <br />
              마지막으로, 장소는<br />
              서울시 실시간 도시 데이터 api를 통해<br />
              현재 사람들이 많이 찾는 인기 장소를 추천해줄거야 ! <br />
            </div>
          </div>
        </div>

        <div class="box-row" v-if="isOpened">
          <div class="profile-image-box-small">
            <img class="profile-image-small" src="@/assets/image/김모르.png" />
          </div>
          <div class="box-chat-background-moleu-info">
            <div class="box-chat-source-text text-chat">
              본 저작물은 '기상청'에서 '2022년'작성하여 공공누리 제1유형으로
              개방한 '기상청_단기예보 ((구)_동네예보)
              조회서비스(작성자:기상청)'을 이용하였으며, 해당 저작물은
              <a
                href="https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15084084"
                >'공공데이터포털'</a
              >
              에서 무료로 다운받으실 수 있습니다.
            </div>
          </div>
        </div>

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
          <div class="box-chat-background-moleu">
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
          <div class="box-chat-background-moleu-info">
            <div class="text-chat box-chat-row">
              <div
                class="box-chat-btn btn-rectangle-small yellow-1"
                id="1"
                value="메뉴"
                @click="checkCategory"
              >
                뭐먹을까?
              </div>
              <div
                class="box-chat-btn btn-rectangle-small purple-1"
                id="2"
                value="스타일"
                @click="checkCategory"
              >
                뭐입을까?
              </div>
              <div
                class="box-chat-btn btn-rectangle-small green-1"
                id="3"
                value="장소"
                @click="checkCategory"
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
            <div class="box-chat-background-moleu">
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
            <div class="box-chat-background-moleu-info">
              <div class="box-column">
                <div class="box-chat-row" style="margin-left: 18px">
                  <div class="box-chat-image box-align-center">
                    <div class="box-chat-row">
                      <img
                        src="../assets/icon/food/중식.png"
                        alt
                        class="menu-image"
                        style=""
                      />
                    </div>
                    <div class="box-chat-row">
                      <div class="btn-rectangle-small blue-1 text-h4">
                        아시안/양식
                      </div>
                    </div>
                  </div>
                  <div class="box-chat-image box-align-center">
                    <div class="box-chat-row">
                      <img
                        src="../assets/icon/food/피자.png"
                        alt
                        class="menu-image"
                      />
                    </div>
                    <div class="box-chat-rowbox-chat-text">
                      <div class="btn-rectangle-small blue-1 text-h4">피자</div>
                    </div>
                  </div>
                </div>
                <div class="box-chat-row" style="margin-left: 26px">
                  <div class="box-chat-row style-text text-h5">
                    메뉴를 클릭해봐 ! 근처 맛집을 찾아줄게 ~
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
            <div class="box-chat-background-moleu">
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
            <div class="box-chat-background-moleu-info">
              <div class="box-chat-row">
                <div class="box-chat-image box-align-center">
                  <div class="box-chat-row">
                    <!-- TODO: 장소 이미지 불러오기 -->
                    <img
                      src="../assets/image/hotspots/국립중앙박물관·용산가족공원.jpg"
                      alt
                      class="location-image"
                      style=""
                    />
                  </div>
                  <div class="box-chat-row location-text text-h5">
                    국립중앙박물관·용산가족공원
                  </div>
                </div>
                <div class="box-chat-image box-align-center">
                  <div class="box-chat-row">
                    <img
                      src="../assets/image/hotspots/강남 MICE 관광특구.jpg"
                      alt
                      class="location-image"
                    />
                  </div>
                  <div class="box-chat-row location-text text-h5">
                    강남 MICE 관광특구
                  </div>
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
            <div class="box-chat-background-moleu">
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
            <div class="box-chat-background-moleu-info">
              <div class="box-chat-image box-align-center">
                <div class="box-chat-row">
                  <img
                    src="../assets/image/clothes/12~16.png"
                    alt
                    class="style-image"
                  />
                </div>
                <div class="box-chat-row style-text text-h5">
                  얇은 니트, 맨투맨, 가디건, 청바지
                </div>
                <div class="box-chat-row style-text text-h5">
                  오늘 같은 날씨엔 이렇게 입는 게 딱이야 !
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
export default {
  components: {},
  data() {
    return {
      isOpened: false,
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
  height: 110vh;
}
</style>
