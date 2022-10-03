<template>
  <div class="background bgd-menu">
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
          <div class="yellow-2 text-chat">
            오늘의 날씨는 <br />
            기온은 18.9도, 습도는 73%, <br />
            풍속은 약한 바람, 강수확률은 10%!<br />
            결정에 참고해~ <br />
          </div>
        </div>
        <!-- 질문 선택  -->
        <div class="box-row">
          <div class="profile-image-box-small">
            <img class="profile-image-small" src="@/assets/image/김모르.png" />
          </div>
          <div class="box-column yellow-2 text-chat">
            <div class="box-row">오늘은 어떤 고민이 있어서 왔어?</div>
            <div class="box-row">
              <div class="btn-rectangle-small yellow-1" id="1">뭐먹을까?</div>
              <div class="btn-rectangle-small purple-1" id="2">뭐입을까?</div>
              <div class="btn-rectangle-small green-1" id="3">어디가지?</div>
            </div>
          </div>
        </div>
        <!-- 메뉴추천 -->
        <div class="box-row">
          <div class="profile-image-box-small">
            <img class="profile-image-small" src="@/assets/image/김모르.png" />
          </div>
          <div class="box-column yellow-2 text-chat">
            <div class="box-row">메뉴 고민이구나! 그럼 이건 어때?</div>
            <div class="box-row">
              <div classs="box-row-center">
                <div class="box-row-center">
                  <img
                    src="../assets/icon/food/중식.png"
                    alt
                    class="food-image"
                    style=""
                  />
                </div>
                <div class="box-row-center">
                  <div class="btn-rectangle-small yellow-1 text-h4">중식</div>
                </div>
              </div>
              <div classs="box-align-center">
                <div class="box-row-center">
                  <img
                    src="../assets/icon/food/피자.png"
                    alt
                    class="food-image"
                  />
                </div>
                <div class="box-row-center">
                  <div class="btn-rectangle-small yellow-1 text-h4">피자</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 장소추천 -->
      <div class="box-row">
        <div class="profile-image-box-small">
          <img class="profile-image-small" src="@/assets/image/김모르.png" />
        </div>
        <div class="box-column yellow-2 text-chat">
          <div class="box-row">장소 고민이구나! 그럼 이건 어때?</div>
          <div class="box-row">
            <div classs="box-row-center">
              <div class="box-row-center">
                <img
                  src="../assets/icon/food/중식.png"
                  alt
                  class="food-image"
                  style=""
                />
              </div>
            </div>
            <div classs="box-align-center">
              <div class="box-row-center">
                <img
                  src="../assets/icon/food/피자.png"
                  alt
                  class="food-image"
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 스타일추천 -->
      <div class="box-row">
        <div class="profile-image-box-small">
          <img class="profile-image-small" src="@/assets/image/김모르.png" />
        </div>
        <div class="box-column yellow-2 text-chat">
          <div class="box-row">스타일 고민이구나! 그럼 이건 어때?</div>
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
</template>
<script>
export default {
  components: {},
};
</script>

<style scoped>
.body {
  height: 105vh;
}
</style>
