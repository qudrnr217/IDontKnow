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
              <th>{{ user.name }}</th>
            </tr>
            <tr>
              <td>이메일</td>
              <td>{{ user.email }}</td>
            </tr>
            <tr>
              <td colspan="2">비밀번호 변경</td>
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
                <select class="sel" v-model="user.districtId">
                  <option
                    v-for="(value, index) in locations"
                    :key="index"
                    :value="index + 1"
                  >
                    {{ locations[index] }}
                  </option>
                </select>
              </th>
            </tr>
            <tr>
              <td>성별</td>
              <td>
                <select class="sel" v-model="user.gender">
                  <!-- v-model=""  -->
                  <option
                    v-for="(value, index) in genders"
                    :key="index"
                    :value="value"
                  >
                    {{ genders[index] }}
                  </option>
                </select>
              </td>
            </tr>
            <tr>
              <td>연령대</td>
              <td>
                <div class="dropdown">
                  <select class="sel" v-model="user.age">
                    <!-- v-model=""  -->
                    <option
                      v-for="(value, key) in ages"
                      :key="key"
                      :value="value"
                    >
                      {{ key }}
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
import { locations, ages, genders } from "../../const/const.js";
import { mapState, mapMutations } from "vuex";
import { getUserInfo, updateUserInfo } from "@/api/mypage";
export default {
  components: {
    HeaderView,
    FooterView,
    VueConfirmDialog,
  },

  data() {
    return {
      ages: { ...ages },
      locations: locations,
      genders: genders,
      user: {
        id: 0,
        name: "",
        email: "",
        districtId: 0,
        districtName: "",
        gender: "",
        age: 0,
      },
      data: {
        isShow: false,
        title: "탈퇴하시겠습니까?",
        message: "회원 탈퇴시 게시글 확인이 어려울 수 있습니다.",
        yes: "탈퇴하기",
        no: "취소",
      },
    };
  },
  mounted() {
    this.user.id = this.userId;
    this.getUser();
  },

  methods: {
    ...mapMutations("userStore", [
      "SET_ACCESS_TOKEN",
      "SET_REFRESH_TOKEN",
      "SET_DISTRICT_ID",
    ]),
    logout() {
      this.SET_ACCESS_TOKEN("");
      this.SET_REFRESH_TOKEN("");
      this.$router.push({ path: "/mypage/login" });
    },
    getUser() {
      getUserInfo(
        this.accessToken,
        this.user.id,
        (response) => {
          this.user.id = response.data.id;
          this.user.name = response.data.name;
          this.user.email = response.data.email;
          this.user.districtId = response.data.districtId;
          this.user.districtName = response.data.districtName;
          this.user.gender = response.data.gender;
          this.user.age = response.data.age;
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    ...mapState("userStore", ["userId", "name", "districtId", "accessToken"]),
    districtId() {
      return this.user.districtId;
    },
    gender() {
      console.log(this.user.gender);
      return this.user.gender;
    },
    age() {
      return this.user.age;
    },
  },
  watch: {
    districtId() {
      console.log(this.user.districtId);
      if (this.user.districtId == 0) return;
      updateUserInfo(
        this.accessToken,
        this.userId,
        this.user.districtId,
        this.user.gender,
        this.user.age,
        (response) => {
          console.log(response.data);
          this.SET_DISTRICT_ID(response.data.districtId);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    age() {
      console.log(this.user.age);
      if (this.user.age == 0) return;
      updateUserInfo(
        this.accessToken,
        this.userId,
        this.user.districtId,
        this.user.gender,
        this.user.age,
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    gender() {
      console.log(this.user.gender);
      if (this.user.gender == "") return;
      updateUserInfo(
        this.accessToken,
        this.userId,
        this.user.districtId,
        this.user.gender,
        this.user.age,
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
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
