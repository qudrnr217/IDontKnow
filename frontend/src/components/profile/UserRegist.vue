<template>
  <div class="body">
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">회원가입</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        결정왕 김모르에 오신 것을 환영합니다 !
      </div>
    </div>
    <div class="profile-image-box">
      <img class="profile-image" src="@/assets/image/김게따.png" />
    </div>
    <div class="box-column">
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">Name</div>
        <input
          class="input-align-right"
          type="text"
          id="name"
          style="font-size: 16px; border-radius: 10px"
          v-model="user.name"
          placeholder="닉네임"
        />
      </div>
      <div class="box-btn-right">
        <div
          class="text-discription text-h5 blue-4-text"
          v-if="!data.checkNameResult && data.nameCheckClicked"
        >
          사용 가능한 닉네임입니다.
        </div>
        <div
          class="text-discription text-h5 red-text"
          v-if="data.checkNameResult && data.nameCheckClicked"
        >
          사용 불가능한 닉네임입니다.
        </div>
        <div
          id="checkNickname"
          class="btn-rectangle-short blue-2 text-h4"
          @click="
            checkName();
            data.nameCheckClicked = true;
          "
        >
          중복 체크
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">E-Mail</div>
        <input
          class="input-align-right"
          type="email"
          id="email"
          style="font-size: 16px; border-radius: 10px"
          v-model="user.email"
          placeholder="이메일"
        />
      </div>
      <div class="box-btn-right">
        <div
          class="text-discription text-h5 blue-4-text"
          v-if="!data.checkEmailResult && data.emailCheckClicked"
        >
          사용 가능한 이메일입니다.
        </div>
        <div
          class="text-discription text-h5 red-text"
          v-else-if="data.checkEmailResult && data.emailCheckClicked"
        >
          사용 불가능한 이메일입니다.
        </div>
        <div
          id="checkEmail"
          class="btn-rectangle-short blue-2 text-h4"
          @click="
            checkEmail();
            data.emailCheckClicked = true;
          "
        >
          중복 체크
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">Password</div>
        <input
          class="input-align-right"
          type="password"
          id="password"
          style="font-size: 16px; border-radius: 10px"
          v-model="data.password"
          placeholder="비밀번호"
        />
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">Password</div>
        <input
          class="input-align-right"
          type="password"
          id="confirmPassword"
          style="font-size: 16px; border-radius: 10px"
          v-model="data.confirmPassword"
          placeholder="비밀번호 확인"
        />
      </div>
      <!-- TODO: v-if로 처리 필요 -->
      <div class="box-btn-right" v-if="!checkPassword()">
        <div class="text-h4 red-text">입력한 비밀번호가 다릅니다.</div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">거주지</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short" v-model="user.districtId">
            <option selected disabled value="0">거주지</option>
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
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">성별</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short" v-model="user.gender">
            <option selected disabled value="">성별</option>
            <option v-for="(value, key) in genders" :key="key" :value="value">
              {{ key }}
            </option>
          </select>
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">연령대</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short" v-model="user.age">
            <option selected disabled value="0">연령대</option>
            <option v-for="(value, key) in ages" :key="key" :value="value">
              {{ key }}
            </option>
          </select>
        </div>
      </div>
    </div>

    <div class="box-btn-right">
      <div
        class="btn-rectangle-medium blue-2"
        @click="confirm(), (data.isShow = true)"
      >
        회원가입
      </div>
      <vue-confirm-dialog :data="data" v-if="data.isShow"></vue-confirm-dialog>
    </div>
  </div>
</template>

<script>
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
import { locations, ages, genders } from "@/const/const.js";
import {
  regist,
  checkEmailDuplication,
  checkNameDuplication,
} from "@/api/user";
export default {
  components: {
    VueConfirmDialog,
  },

  data() {
    return {
      user: {
        name: "",
        email: "",
        password: "",
        districtId: 0,
        gender: "",
        age: 0,
      },

      ages: { ...ages },
      locations: locations,
      genders: { ...genders },
      data: {
        password: "",
        confirmPassword: "",
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
    };
  },
  methods: {
    confirm() {
      regist(
        this.user,
        (response) => {
          console.log(response.data);
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
    checkPassword() {
      if (this.data.password === this.data.confirmPassword) {
        this.user.password = this.data.confirmPassword;
        return true;
      }
      return false;
    },
    movePage() {
      this.$router.push({ name: "userLogin" });
    },
  },
};
</script>

<style scoped>
.text-discription {
  margin: 7px;
}
</style>
