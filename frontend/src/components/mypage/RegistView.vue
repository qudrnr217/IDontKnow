<template>
  <div>
    <div class="background">
      <div class="body">
        <header-view class="header" />
        <div class="title">회원가입</div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">Name</div>
            <input
              class="content_input"
              type="text"
              id="name"
              v-model="name"
              placeholder="닉네임"
            />
          </div>
        </div>
        <div class="content_check">
          <div class="conten_check_text">사용 가능한 닉네임입니다.</div>
          <button class="button">닉네임 중복 체크</button>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">E-mail</div>
            <input
              class="content_input"
              type="text"
              id="email"
              v-model="email"
              placeholder="이메일"
            />
          </div>
        </div>
        <div class="content_check">
          <div class="conten_check_text">사용 가능한 이메일입니다.</div>
          <button class="button">이메일 중복 체크</button>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">Password</div>
            <input
              class="content_input"
              type="password"
              id="password"
              v-model="confirm_password"
              placeholder="비밀번호"
            />
          </div>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">Password</div>
            <input
              class="content_input"
              type="password"
              id="password"
              v-model="password"
              placeholder="비밀번호 확인"
            />
          </div>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">거주지</div>
            <div class="dropdown">
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
            </div>
          </div>
        </div>
        <div class="content-box1">
          <div class="content" style="justify-content: flex-end">
            <div class="content_title">성별</div>
            <div class="dropdown" style="margin: 0px">
              <control-view-2 :segments="segments2" />
            </div>
          </div>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">연령대</div>
            <div class="dropdown">
              <select class="sel">
                <!-- v-model=""  -->
                <option value="">연령대</option>
                <option
                  v-for="(age, index) in Age"
                  :key="index"
                  :value="age.value"
                >
                  {{ age.text }}
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="content_check">
          <button class="button" @click="data.isShow = true">회원가입</button>
          <vue-confirm-dialog
            :data="data"
            v-if="data.isShow"
          ></vue-confirm-dialog>
        </div>

        <footer-view class="footer" />
      </div>
    </div>
  </div>
</template>

<script>
import HeaderView from "../common/HeaderView.vue";
import FooterView from "../common/FooterView.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
import ControlView2 from "../common/ControlView2.vue";
export default {
  components: {
    HeaderView,
    FooterView,
    VueConfirmDialog,
    ControlView2,
  },

  data() {
    return {
      name: "",
      email: "",
      password: "",
      confirm_password: "",

      data: {
        isShow: false,
        title: "회원가입이 완료되었습니다.",
        message:
          "사이트에 가입하신걸 축하드립니다! 메인페이지로 가셔서 로그인을 하고 사이트를 즐겨보세요!",
        yes: "확인",
      },
      segments2: [
        {
          title: "남자",
          id: "1",
        },
        {
          title: "여자",
          id: "2",
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
    };
  },
};
</script>

<style scoped>
.body {
  width: 390px;
  height: 844px;
}
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}
.content-box1 {
  width: 100%;
  display: flex;
  justify-content: center;
  align-content: center;
}
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  width: 360px;
  height: 40px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content_title {
  /* justify-content: center; */
  flex-grow: 1;
  font-size: 15px;
  font-family: "GmarketSansTTFLight";
  vertical-align: center;
}
.content_input {
  justify-content: flex-end;
  flex-grow: 3;
  border: none;
  font-family: "GmarketSansTTFLight";
  text-align: right;
}
.content_check {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px;
}
.conten_check_text {
  margin: 10px;
  color: #007aff;
  text-align: center;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
}
.button {
  background-color: #007aff;
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  color: white;
  text-align: center;
  text-decoration: none;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
}
.dropdown {
  display: flex;
  justify-content: flex-end;
  align-content: center;
  flex-grow: 3;
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
