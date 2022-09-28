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
                <!-- <div class="dropdown"><dropdown-location /></div> -->
              </th>
            </tr>
            <tr>
              <td>성별</td>
            </tr>
            <tr>
              <td>연령대</td>
              <td>
                <div class="dropdown">
                  <!-- <dropdown-age /> -->
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
// import DropdownLocation from "../regist/DropdownLocation.vue";
// import DropdownAge from "../regist/DropdownAge.vue";
import VueConfirmDialog from "../common/VueConfirmDialog.vue";

export default {
  components: {
    HeaderView,
    FooterView,
    // DropdownLocation,
    // DropdownAge,
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
</style>
