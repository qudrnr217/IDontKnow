<template>
  <div class="bgd-common">
    <div class="body">
      <div class="title">모르에게 물어봐</div>
      <div class="title-description">회원정보</div>
      <div class="box-regist" style="background: transparent; height: 10px" />
      <div class="myprofile">
        <img class="cat" src="@/assets/image/김게따.png" />
      </div>
      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">현재 비밀번호</div>
          <input
            class="input-regist"
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호"
          />
        </div>
      </div>
      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">
            새로운 <br />
            비밀번호
          </div>
          <input
            class="input-regist"
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호"
          />
        </div>
      </div>
      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">
            새로운 <br />
            비밀번호 확인
          </div>
          <input
            class="input-regist"
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호"
          />
        </div>
      </div>
      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
        <button class="btn-rectangle-small" style="background-color: #b8e9ff">
          비밀번호 변경
        </button>
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
.cat {
  width: 100px;
  height: 100px;
}
.bgd-common {
  background-image: url("~@/assets/image/background/blue.png");
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
.myprofile {
  display: flex;
  justify-content: center;
  margin: 10px;
}
.box-regist {
  display: flex;
  justify-content: space-around;
  background-color: #ffffff;
  border-radius: 10px;
  margin: 0px 10px 10px 10px;
  padding: 0px 10px 0px 10px;
}
.content-regist {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-content: center;
  width: 100%;
}
.title-regist {
  font-family: "ONEMobileTitle";
  font-size: 16px;
  vertical-align: center;
  padding: 10px;
}
.input-regist {
  justify-content: flex-end;
  border: none;
  font-family: "ONEMobileTitle";
  font-size: 16px;
  text-align: right;
}
.btn-rectangle-small {
  font-family: "ONEMobileTitle";
  font-size: 8px;
  display: flex;
  width: 80px;
  height: 30px;
  border-radius: 10px;
  margin-left: 10px;
  align-items: center;
  justify-content: center;
}
</style>
