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
              v-model="user.name"
              placeholder="닉네임"
            />
          </div>
        </div>
        <div class="content_check">
          <div
            class="conten_check_text"
            v-if="!data.checkNameResult && data.nameCheckClicked"
          >
            사용 가능한 닉네임입니다.
          </div>
          <div
            class="conten_check_text"
            v-if="data.checkNameResult && data.nameCheckClicked"
            style="color: #be0000"
          >
            사용 불 가능한 닉네임입니다.
          </div>
          <button
            class="button"
            @click="
              checkName();
              data.nameCheckClicked = true;
            "
          >
            닉네임 중복 체크
          </button>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">E-mail</div>
            <input
              class="content_input"
              type="text"
              id="email"
              v-model="user.email"
              placeholder="morl@idontknow.com"
            />
          </div>
        </div>
        <div class="content_check">
          <div
            class="conten_check_text"
            v-if="!data.checkEmailResult && data.emailCheckClicked"
          >
            사용 가능한 이메일입니다.
          </div>
          <div
            class="conten_check_text"
            v-else-if="data.checkEmailResult && data.emailCheckClicked"
            style="color: #be0000"
          >
            사용 불가능한 이메일입니다.
          </div>
          <button
            class="button"
            @click="
              checkEmail();
              data.emailCheckClicked = true;
            "
          >
            이메일 중복 체크
          </button>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">Password</div>
            <input
              class="content_input"
              type="password"
              id="password"
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
              id="passwordCheck"
              v-model="user.password"
              placeholder="비밀번호 확인"
            />
          </div>
        </div>
        <div class="content-box1">
          <div class="content">
            <div class="content_title">거주지</div>
            <div class="dropdown">
              <select class="sel" v-model="user.districtId">
                <!-- v-model=""  -->
                <option value="">거주지</option>
                <option
                  v-for="(value, index) in locations"
                  :key="index"
                  :value="index + 1"
                >
                  {{ locations[index] }}
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
              <select class="sel" v-model="user.age">
                <!-- v-model=""  -->
                <option value="">연령대</option>
                <option v-for="(value, key) in ages" :key="key" :value="value">
                  {{ key }}
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="content_check">
          <button class="button" @click="confirm">회원가입</button>
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
import { ages, locations } from "../../const/const.js";
import {
  regist,
  checkEmailDuplication,
  checkNameDuplication,
} from "../../api/user";
export default {
  name: "RegistView",
  components: {
    HeaderView,
    FooterView,
    VueConfirmDialog,
    ControlView2,
  },

  data() {
    return {
      user: {
        name: "",
        email: "",
        password: "",
        districtId: 0,
        gender: "M",
        age: 0,
      },
      ages: { ...ages },
      locations: locations,
      data: {
        checkEmailResult: false,
        checkNameResult: false,
        emailCheckClicked: false,
        nameCheckClicked: false,
        isShow: false,
        title: "회원가입이 완료되었습니다.",
        message:
          "사이트에 가입하신걸 축하드립니다! 메인페이지로 가셔서 로그인을 하고 사이트를 즐겨보세요!",
        yes: "확인",
      },
      segments2: [
        {
          title: "남자",
          id: "M",
        },
        {
          title: "여자",
          id: "F",
        },
      ],
    };
  },
  methods: {
    confirm() {
      if (this.data.checkEmailResult || this.data.checkNameResult) {
        this.isShow = true;
      }
      regist(
        this.user,
        (response) => {
          console.log(response.data);
          this.movePage();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    checkEmail() {
      if (this.user.email == "") {
        this.data.checkEmailResult = true;
        return;
      }
      checkEmailDuplication(
        this.user.email,
        (response) => {
          console.log(response.data);
          this.data.checkEmailResult = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    checkName() {
      if (this.user.name == "") {
        this.data.checkNameResult = true;
        return;
      }
      checkNameDuplication(
        this.user.name,
        (response) => {
          console.log(response.data);
          this.data.checkNameResult = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    movePage() {
      this.$router.push({ name: "login_home" });
    },
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
