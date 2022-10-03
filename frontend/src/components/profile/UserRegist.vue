<template>
  <div class="bgd-common">
    <div class="body">
      <div class="title">모르에게 물어봐</div>
      <div class="title-description">회원가입</div>
      <div class="box-regist" style="background: transparent; height: 10px" />
      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">Name</div>
          <input
            class="input-regist"
            type="text"
            id="name"
            v-model="name"
            placeholder="닉네임"
          />
        </div>
      </div>

      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
        <div class="text-regist">사용 가능한 닉네임입니다.</div>
        <button class="btn-rectangle-small" style="background-color: #58ccff">
          닉네임 중복 체크
        </button>
      </div>

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">E-Mail</div>
          <input
            class="input-regist"
            type="email"
            id="email"
            v-model="email"
            placeholder="이메일"
          />
        </div>
      </div>

      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
        <div class="text-regist">사용 가능한 이메일입니다.</div>
        <button class="btn-rectangle-small" style="background-color: #58ccff">
          이메일 중복 체크
        </button>
      </div>

      <div class="box-regist">
        <div class="content-regist">
          <div class="title-regist">Password</div>
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
          <div class="title-regist">Password</div>
          <input
            class="input-regist"
            type="password"
            id="confirm_password"
            v-model="confirm_password"
            placeholder="비밀번호 확인"
          />
        </div>
      </div>

      <div
        class="box-regist"
        style="justify-content: flex-end; background: transparent"
      >
        <div class="text-regist">입력한 비밀번호가 다릅니다.</div>
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
          style="background-color: #58ccff"
          @click="data.isShow = true"
        >
          회원가입
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
  components: {
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
};
</script>

<style scoped>
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
  justify-content: space-between;
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
.text-regist {
  font-family: "ONEMobileTitle";
  font-size: 12px;
  display: flex;
  align-items: center;
  margin-right: 10px;
}
.btn-rectangle-small {
  font-family: "ONEMobileTitle";
  font-size: 8px;
  display: flex;
  width: 120px;
  height: 30px;
  border-radius: 10px;
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
