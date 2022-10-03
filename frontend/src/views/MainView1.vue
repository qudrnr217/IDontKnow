<template>
  <div class="bgd-common">
    <div class="body">
      <div class="title">모르에게 물어봐</div>
      <div class="title-description">당신의 고민을 함께 해요</div>
      <!-- 오늘의 날씨 -->
      <div class="box-chat">
        <div class="img-chat">
          <img class="cat" src="../assets/image/김모르.png" />
        </div>
        <div class="content-chat">
          오늘의 날씨는 기온은 18.9도, <br />
          습도는 73%, 풍속은 약한 바람, <br />
          강수확률은 10%! 결정에 참고해~ <br />
        </div>
      </div>
      <!-- 질문 선택  -->
      <div class="box-chat">
        <div class="img-chat">
          <img class="cat" src="../assets/image/김모르.png" />
        </div>
        <div class="content-chat">
          <div class="text-chat">오늘은 어떤 고민이 있어서 왔어?</div>
          <div class="btn-box">
            <button class="btn-rectangle-small yellow-1" id="1">
              뭐먹을까?
            </button>
            <button class="btn-rectangle-small purple-1" id="2">
              뭐입을까?
            </button>
            <button class="btn-rectangle-small green-1" id="3">
              어디가지?
            </button>
          </div>
        </div>
      </div>
      <!-- 메뉴추천 -->
      <div class="box-chat">
        <div class="img-chat">
          <img class="cat" src="../assets/image/김모르.png" />
        </div>
        <div class="content-chat">
          <div class="text-chat">메뉴 고민이구나! 그럼 이건 어때?</div>
          <div class="box-menu">
            <div classs="content-menu">
              <div class="img-menu">
                <img
                  src="../assets/icon/food/중식.png"
                  alt
                  class="food1_img"
                  style="width: 80px; height: 80px"
                />
              </div>
              <div class="btn-menu">
                <div
                  class="btn-rectangle-small"
                  style="background-color: #fff7e2"
                >
                  중식
                </div>
              </div>
            </div>
            <div classs="content-menu">
              <div class="img-menu">
                <img
                  src="../assets/icon/food/피자.png"
                  alt
                  class="food2_img"
                  style="width: 80px; height: 80px"
                />
              </div>
              <div class="btn-menu">
                <div
                  class="btn-rectangle-small"
                  style="background-color: #fff7e2"
                >
                  피자
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 장소추천 -->
      <div class="box-chat">
        <div class="img-chat">
          <img class="cat" src="../assets/image/김모르.png" />
        </div>
        <div class="content-chat">
          <div class="text-chat">장소 고민이구나! 그럼 이건 어때?</div>
          <div class="box-menu">
            <div class="img-location"></div>
            <div class="text-content"></div>
          </div>
          <div class="box-menu">
            <div class="img-menu"></div>
            <div class="text-content"></div>
          </div>
        </div>
      </div>
      <!-- 스타일추천 -->
      <div class="box-chat">
        <div class="img-chat">
          <img class="cat" src="../assets/image/김모르.png" />
        </div>
        <div class="content-chat">
          <div class="text-chat">스타일 고민이구나! 그럼 이건 어때?</div>
          <div class="img-content">
            <img
              src="../assets/image/clothes/12~16.png"
              alt
              class="clothes_img"
            />
          </div>
          <div class="text-content">
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
.bgd-common {
  background: #ffffff;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  margin: auto;
}
.body {
  padding: 10px;
}
.title {
  font-family: "ONEMobileTitle";
  font-size: 24px;
  text-align: left;
}
.title-description {
  font-family: "ONEMobileTitle";
  font-size: 20px;
  text-align: left;
}
.box-chat {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding: 10px;
}
.img-chat {
  margin-right: 20px;
}
.cat {
  width: 60px;
  height: 60px;
}
.content-chat {
  font-family: "ONEMobileTitle";
  font-size: 16px;
  background-image: url(../assets/image/yellow-bubble.png);
  background-size: 100% 100%;
  padding: 10px 10px 10px 20px;
  min-width: 260px;
}
.box-menu {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  padding: 10px;
}
.content-menu {
  display: flex;
  justify-content: center;
}
.img-menu {
  display: flex;
}
.btn-menu {
  font-family: "ONEMobileTitle";
  display: flex;
  justify-content: center;
}
.text-chat {
  font-family: "ONEMobileTitle";
  font-size: 16px;
  padding-top: 10px;
  padding-bottom: 10px;
}
.btn-box {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.btn-rectangle-small {
  font-family: "ONEMobileTitle";
  font-size: 8px;
  display: flex;
  width: 60px;
  height: 30px;
  border-radius: 10px;
  align-items: center;
  justify-content: center;
}
.img-content {
}
.text-content {
  font-family: "ONEMobileTitle";
  font-size: 16px;
  padding-left: 15px;
}
</style>
