<template>
  <div class="body">
    <div class="box-row-left">
      <div class="text-title text-h1 blue-4-text">회원정보</div>
    </div>
    <div class="box-row-left">
      <div class="text-title text-h4 blue-4-text">
        {{ user.name }}님 환영합니다 !
      </div>
    </div>
    <div class="profile-image-box">
      <img class="profile-image" src="@/assets/image/김게따.png" />
    </div>
    <div class="box-column">
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">Name</div>
        <div class="input-align-right">{{ user.name }}</div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">E-MAIL</div>
        <div class="input-align-right">{{ user.email }}</div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">Password</div>
        <div class="input-align-right">
          <div class="btn-rectangle-long blue-0" @click="passwordChange()">
            비밀번호 변경
          </div>
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">거주지</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short" v-model="user.districtId">
            <option selected disabled value="">거주지</option>
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
            <option
              v-for="(value, index) in genders"
              :key="index"
              :value="value"
            >
              {{ index }}
            </option>
          </select>
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">연령대</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short" v-model="user.age">
            <option selected disabled value="">연령대</option>
            <option v-for="(value, key) in ages" :key="key" :value="value">
              {{ key }}
            </option>
          </select>
        </div>
      </div>
      <div class="box-btn-right">
        <div class="btn-rectangle-small blue-0" @click="logout()">로그아웃</div>
        <div class="btn-rectangle-small red" @click="withdrawal()">
          탈퇴하기
        </div>
        <vue-confirm-dialog
          :data="data"
          v-if="data.isShow"
        ></vue-confirm-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import VueConfirmDialog from "../common/VueConfirmDialog.vue";
import { locations, ages, genders } from "@/const/const.js";
import { mapState, mapMutations } from "vuex";
import { getUserInfo, updateUserInfo, logout } from "@/api/mypage";
export default {
  name: "UserInfo",
  components: {
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
        mode: "5",
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
    ...mapMutations("userStore", ["SET_INIT", "SET_DISTRICT_ID"]),
    logout() {
      logout(
        this.accessToken,
        this.userId,
        (response) => {
          console.log(response.data);
          this.SET_INIT();
          console.log("로그아웃 완료");
          this.$router.push({ name: "userLogout", path: "/profile/logout" });
        },
        (error) => {
          if (error == 401) {
            this.$router.push({ name: "userLogin", path: "/profile/login" });
          }
          console.log(error);
          console.log("로그아웃 실패");
        }
      );
    },
    passwordChange() {
      this.$router.push({ name: "userPassword", path: "/profile/password" });
    },
    withdrawal() {
      // TODO: dialog 체크 필요
      // 탈퇴하기 api 호출 (vue 날리기)
      // home 으로 이동
      this.data.isShow = true;

      // deleteUserInfo(
      //   this.accessToken,
      //   this.user.id,
      //   (response) => {
      //     console.log(response.data);
      //     this.data.isShow = true;
      //     // this.SET_INIT();
      //     // this.$store.state.started = 0;
      //     // this.$router.push({ name: "home", path: "/" });
      //   },
      //   (error) => {
      //     console.log(error);
      //   }
      // );
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
.btn-rectangle-small {
  margin-left: 10px;
}
</style>
