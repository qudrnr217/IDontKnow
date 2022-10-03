import Vue from "vue";
import VueRouter from "vue-router";
// home
import HomeView from "../views/HomeViewCopy.vue";
import VoteList from "../components/home/VoteList.vue";
// voteCreate
import VoteCreateView from "../views/VoteCreateView.vue";
import VoteCreate from "../components/home/VoteCreate.vue";
// voteDetail
import VoteDetailView from "../views/VoteDetailView.vue";
import VoteDetail from "../components/home/VoteDetail.vue";
// profile
import ProfileView from "../views/LoginView.vue";
import UserLogin from "../components/profile/UserLogin.vue";
import UserLogout from "../components/profile/UserLogout.vue";
import UserInfo from "../components/profile/UserInfo.vue";
import UserPassword from "../components/profile/UserPassword.vue";
import UserRegist from "../components/profile/UserRegist.vue";
// chat
import ChatView from "../views/ChatView.vue";
// record
import RecordView from "../views/RecordView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
    redirect: () => {
      return { path: "/home/메뉴" };
    },
    children: [
      {
        path: "/home/:category",
        name: "voteList",
        component: VoteList,
        hidden: true,
        props: true,
      },
    ],
  },
  {
    path: "/vote",
    name: "voteCreateView",
    component: VoteCreateView,
    redirect: () => {
      return { path: "/vote/create" };
    },
    children: [
      {
        path: "create",
        name: "voteCreate",
        component: VoteCreate,
        props: true,
      },
    ],
  },
  {
    path: "/vote",
    name: "voteDetailView",
    component: VoteDetailView,
    children: [
      {
        path: "/:voteId",
        name: "voteDetail",
        component: VoteDetail,
        props: true,
      },
    ],
  },

  {
    path: "/profile",
    name: "profile",
    component: ProfileView,
    children: [
      {
        path: "login",
        name: "userLogin",
        component: UserLogin,
      },
      {
        path: "regist",
        name: "userRegist",
        component: UserRegist,
      },
      {
        path: "logout",
        name: "userLogout",
        component: UserLogout,
      },
      {
        path: "user/:userId",
        name: "userInfo",
        component: UserInfo,
        props: true,
      },
      {
        path: "password",
        name: "userPassword",
        component: UserPassword,
      },
    ],
  },
  {
    path: "/chat",
    name: "chat",
    component: ChatView,
    // TODO : 컴포넌트로 변경 필요
  },
  {
    path: "/record",
    name: "record",
    component: RecordView,
    // TODO : 컴포넌트로 변경 필요
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
