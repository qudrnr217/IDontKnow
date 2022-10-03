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
        <div class="input-align-right">{{ user.name }}ddd</div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">E-MAIL</div>
        <div class="input-align-right">{{ user.email }}ddd</div>
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
          <select class="sb-rectangle-short">
            <option selected disabled value="">거주지</option>
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
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">성별</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short">
            <option selected disabled value="">성별</option>
            <option
              v-for="(gender, index) in Gender"
              :key="index"
              :value="gender.value"
            >
              {{ gender.text }}
            </option>
          </select>
        </div>
      </div>
      <div class="box-row input-rectangle-long white blue-3-border">
        <div class="text-h3">연령대</div>
        <div class="input-align-right">
          <select class="sb-rectangle-short">
            <option selected disabled value="">연령대</option>
            <option v-for="(age, index) in Age" :key="index" :value="age.value">
              {{ age.text }}
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
export default {
  name: "UserInfo",
  components: {
    VueConfirmDialog,
  },
  props: ["userId"],
  data() {
    return {
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
      Gender: [
        {
          value: "M",
          text: "남자",
        },
        {
          value: "F",
          text: "여자",
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
      // vue 날리기
      this.$router.push({ name: "userLogout", path: "/profile/logout" });
    },
    passwordChange() {
      this.$router.push({ name: "userPassword", path: "/profile/password" });
    },
    withdrawal() {
      // TODO: dialog 체크 필요
      // 탈퇴하기 api 호출 (vue 날리기)
      // home 으로 이동
    },
  },
};
</script>

<style scoped>
.btn-rectangle-small {
  margin-left: 10px;
}
</style>
