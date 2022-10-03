<template>
  <div class="background">
    <div class="body">
      <header-view />
      <div class="title">마이페이지</div>
      <div class="myprofile">
        <!-- <img src="../assets/image/profile.png" /> -->
      </div>
      <div class="content_title">회원정보</div>
      <div class="content-box1">
        <div class="content">
          <table class="mypage">
            <tr>
              <th>닉네임</th>
            </tr>
            <tr>
              <td>이메일</td>
            </tr>
            <tr>
              <td>비밀번호 변경</td>
            </tr>
          </table>
        </div>
      </div>
      <div class="content_title">거주지, 성별, 연령대</div>
      <div class="content-box2">
        <div class="content">
          <table class="mypage">
            <tr>
              <th>거주지</th>
              <th>
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
              </th>
            </tr>
            <tr>
              <td>성별</td>
              <td>
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
              </td>
            </tr>
            <tr>
              <td>연령대</td>
              <td>
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
              </td>
            </tr>
          </table>
        </div>
      </div>

      <div class="content_check">
        <div>
          <button class="mypagebutton" @click="logout()">로그아웃</button>
          <button
            class="mypagebutton"
            style="background-color: #ff3b30"
            @click="data.isShow = true"
          >
            탈퇴하기
          </button>
          <vue-confirm-dialog
            :data="data"
            v-if="data.isShow"
          ></vue-confirm-dialog>
        </div>
      </div>

      <footer-view />
    </div>
  </div>
</template>

<script>
import HeaderView from "../common/HeaderView.vue";
import FooterView from "../common/FooterView.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";

export default {
  components: {
    HeaderView,
    FooterView,
    VueConfirmDialog,
  },

  data() {
    return {
      data: {
        isShow: false,
        title: "탈퇴하시겠습니까?",
        message: "회원 탈퇴시 게시글 확인이 어려울 수 있습니다.",
        yes: "탈퇴하기",
        no: "취소",
      },
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
          text: "서울시 성동구",
        },
        {
          value: 17,
          text: "서울시 성북구",
        },
        {
          value: 18,
          text: "서울시 송파구",
        },
        {
          value: 19,
          text: "서울시 양천구",
        },
        {
          value: 20,
          text: "서울시 영등포구",
        },
        {
          value: 21,
          text: "서울시 용산구",
        },
        {
          value: 22,
          text: "서울시 은평구",
        },
        {
          value: 23,
          text: "서울시 종로구",
        },
        {
          value: 24,
          text: "서울시 중구",
        },
        {
          value: 25,
          text: "서울시 중랑구",
        },
      ],
    };
  },

  methods: {
    logout() {
      this.$router.push({ path: "/mypage/login" });
    },
  },
};
</script>

<style scoped>
.title {
  font-weight: 1000;
  font-size: 34px;
  line-height: 36px;
  font-family: "GmarketSansTTFLight";
  font-style: normal;
  /* background-color: red; */
}
.myprofile {
  margin: auto;
  width: 100px;
  height: 100px;
}
.content-box1,
.content-box2 {
  width: 100%;
  display: flex;
  justify-content: center;
  align-content: center;
}
.content {
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #ffffff;
  width: 300px;
  border: none;
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
  font-family: "GmarketSansTTFLight";
}
.content_title {
  justify-content: center;
  flex-grow: 1;
  font-size: 15px;
  font-family: "GmarketSansTTFMedium";
  vertical-align: center;
  margin-left: 30px;
}
.password_change_button {
  background-color: transparent;
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
table {
  border-collapse: collapse;
  width: 100%;
}
th,
td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
.content_check {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px;
}
.mypagebutton {
  background-color: #007aff;
  border: none;
  border-radius: 6px;
  width: 100px;
  height: 30px;
  margin: 10px;
  color: white;
  text-align: center;
  text-decoration: none;
  font-size: 8px;
  font-family: "GmarketSansTTFLight";
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
