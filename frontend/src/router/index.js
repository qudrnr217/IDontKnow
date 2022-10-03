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
import UserLogin from "../components/mypage/LoginView1.vue";
import UserLogout from "../components/mypage/LogoutView1.vue";
import UserInfo from "../components/mypage/MypageView1.vue";
import UserPassword from "../components/mypage/PasswordchangeView.vue";
import UserRegist from "../components/mypage/RegistView1.vue";
// chat
import ChatView from "../views/MainView1.vue";
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
        path: "/user/:userId",
        name: "userInfo",
        component: UserInfo,
        props: true,
      },
      {
        path: "/password",
        name: "userPassword",
        component: UserPassword,
        props: true,
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
  // {
  //   path: "/",
  //   name: "home",
  //   component: HomeView,
  //   redirect: () => {
  //     return { path: "/main/clothes" };
  //   },
  // },
  // {
  //   path: "/community",
  //   name: "community",
  //   component: () => import("../views/CommunityView.vue"),
  //   children: [
  //     {
  //       path: "main",
  //       name: "community_home",
  //       component: () => import("../components/community/CommunityPage.vue"),
  //     },
  //     {
  //       path: "votedetail",
  //       name: "votedetail",
  //       component: () => import("../components/community/VoteDetailView.vue"),
  //     },
  //     {
  //       path: "votecreate",
  //       name: "votecreate",
  //       component: () => import("../components/community/CreateVote.vue"),
  //     },
  //   ],
  // },

  // 새 디자인 적용
  // {
  //   path: "/mypage",
  //   name: "mypage",
  //   component: () => import("../views/LoginView.vue"),
  //   children: [
  //     {
  //       path: "login",
  //       name: "login_home",
  //       component: () => import("../components/mypage/LoginView1.vue"),
  //     },
  //     {
  //       path: "regist",
  //       name: "regist",
  //       component: () => import("../components/mypage/RegistView1.vue"),
  //     },
  //     {
  //       path: "logout",
  //       name: "logout",
  //       component: () => import("../components/mypage/LogoutView1.vue"),
  //     },
  //     {
  //       path: "mypage_home",
  //       name: "mypage",
  //       component: () => import("../components/mypage/MypageView1.vue"),
  //     },
  //     {
  //       path: "/mypage/password",
  //       name: "mypage/password",
  //       component: () => import("../components/mypage/PasswordchangeView.vue"),
  //     },
  //   ],
  // },
  // 새 디자인
  // {
  //   path: "/main",
  //   name: "main",
  //   component: () => import("../views/MainView1.vue"),
  //   children: [
  //     {
  //       path: "clothes",
  //       name: "clothes",
  //       component: () => import("../views/MainClothesView.vue"),
  //     },
  //     {
  //       path: "hotspot",
  //       name: "hotspot",
  //       component: () => import("../views/MainHotspotView.vue"),
  //     },
  //     {
  //       path: "food",
  //       name: "food",
  //       component: () => import("../views/MainFoodView.vue"),
  //     },
  //     {
  //       path: "food/detail",
  //       name: "food/detail",
  //       props: true,
  //       component: () => import("../views/MainFoodDetailView.vue"),
  //     },
  //   ],
  // },
  // {
  //   path: "/main/food",
  //   name: "food",
  //   component: () => import("../views/MainFoodView.vue"),
  // },
  // {
  //   path: "/main/food/detail",
  //   name: "food/detail",
  //   component: () => import("../views/MainFoodDetailView.vue"),
  // },
  // {
  //   path: "/main",
  //   name: "main",
  //   component: () => import("../views/MainView1.vue"),
  // },
  // {
  //   path: "/about",
  //   name: "about",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  // },
  // 새 디자인 적용부분 확인
  // {
  //   path: "/home",
  //   name: "newHome",
  //   component: () => import("../views/HomeViewCopy.vue"),
  //   redirect: () => {
  //     return { path: "/home/community/메뉴" };
  //   },
  //   children: [
  //     {
  //       path: "community/:category",
  //       // path: "community",
  //       hidden: true,
  //       component: () => import("../components/home/VoteList.vue"),
  //       name: "voteList",
  //       props: true,
  //     },
  //   ],
  // },
  // {
  //   path: "/vote",
  //   name: "voteCreateView",
  //   component: () => import("../views/VoteCreateView.vue"),
  //   redirect: () => {
  //     return { path: "/vote/create" };
  //   },
  //   children: [
  //     {
  //       path: "create",
  //       name: "voteCreate",
  //       component: () => import("../components/home/VoteCreate.vue"),
  //       props: true,
  //     },
  //   ],
  // },
  // {
  //   path: "/vote",
  //   name: "voteDetailView",
  //   component: () => import("../views/VoteDetailView.vue"),
  //   redirect: () => {
  //     return { path: "/vote/detail/:voteId" };
  //   },
  //   children: [
  //     {
  //       path: "detail/:voteId",
  //       name: "voteDetail",
  //       component: () => import("../components/home/VoteDetail.vue"),
  //       props: true,
  //     },
  //   ],
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
