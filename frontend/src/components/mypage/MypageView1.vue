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
          <div class="title-regist">Name</div>
          <div class="text-regist">{{ name }} weejee</div>
        </div>
      </div>
      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">EMAIL</div>
          <div class="text-regist">{{ email }} abc123@gmail.com</div>
        </div>
      </div>
      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">Password</div>
          <button
            class="btn-rectangle-small"
            @click="passwordchange()"
            style="background-color: #b8e9ff; margin-top: 6px"
          >
            비밀번호 변경
          </button>
        </div>
      </div>

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">거주지</div>
          <div class="input-regist">
            <select class="sb-rectangle-big">
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

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">성별</div>
          <div class="input-regist" style="margin-top: 6px">
            <control-view-2 :segments="segments2" />
          </div>
        </div>
      </div>

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">연령대</div>
          <div class="input-regist">
            <select class="sb-rectangle-big">
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

      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
        <button
          class="btn-rectangle-small"
          @click="logout()"
          style="background-color: #18a8f1"
        >
          로그아웃
        </button>
        <button
          class="btn-rectangle-small"
          @click="login()"
          style="background-color: #feccbe"
        >
          탈퇴하기
        </button>
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
import ControlView2 from "../common/ControlView2.vue";
export default {
  name: "UserInfo",
  components: {
    VueConfirmDialog,
    ControlView2,
  },
  props: ["userId"],
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
      this.$router.push({ path: "/mypage/logout" });
    },
    passwordchange() {
      this.$router.push({ path: "/mypage/password" });
    },
  },
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
  flex-grow: 2;
}
.text-regist {
  font-family: "ONEMobileTitle";
  font-size: 12px;
  display: flex;
  flex-grow: 3;
  align-items: center;
  margin-right: 10px;
  padding: 10px;
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
.sb-rectangle-big {
  width: 200px;
  height: 40px;
  border-radius: 10px;
  border-style: none;
  font-family: "ONEMobileTitle";
  font-size: 12px;
  text-align: center;
}
</style>
