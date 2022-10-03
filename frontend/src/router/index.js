import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
    redirect: () => {
      return { path: "/main/clothes" };
    },
  },
  {
    path: "/community",
    name: "community",
    component: () => import("../views/CommunityView.vue"),
    children: [
      {
        path: "main",
        name: "community_home",
        component: () => import("../components/community/CommunityPage.vue"),
      },
      {
        path: "votedetail",
        name: "votedetail",
        component: () => import("../components/community/VoteDetailView.vue"),
      },
      {
        path: "votecreate",
        name: "votecreate",
        component: () => import("../components/community/CreateVote.vue"),
      },
    ],
  },
  {
    path: "/votes",
    name: "votes",
    component: () => import("../views/VoteView.vue"),
    children: [
      {
        path: "main",
        name: "vote_home",
        component: () => import("../components/vote/VotePage.vue"),
      },
    ],
  },

  // 로그인
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("../views/LoginView.vue"),
    children: [
      {
        path: "login",
        name: "login_home",
        component: () => import("../components/mypage/LoginView1.vue"),
      },
      {
        path: "regist",
        name: "regist",
        component: () => import("../components/mypage/RegistView1.vue"),
      },
      {
        path: "logout",
        name: "logout",
        component: () => import("../components/mypage/LogoutView1.vue"),
      },
      {
        path: "mypage_home",
        name: "mypage",
        component: () => import("../components/mypage/MypageView1.vue"),
      },
      {
        path: "/mypage/password",
        name: "mypage/password",
        component: () => import("../components/mypage/PasswordchangeView.vue"),
      },
    ],
  },

  {
    path: "/main",
    name: "main",
    component: () => import("../views/MainView1.vue"),
    children: [
      {
        path: "clothes",
        name: "clothes",
        component: () => import("../views/MainClothesView.vue"),
      },
      {
        path: "hotspot",
        name: "hotspot",
        component: () => import("../views/MainHotspotView.vue"),
      },
      {
        path: "food",
        name: "food",
        component: () => import("../views/MainFoodView.vue"),
      },
      {
        path: "food/detail",
        name: "food/detail",
        props: true,
        component: () => import("../views/MainFoodDetailView.vue"),
      },
    ],
  },
  {
    path: "/main/food",
    name: "food",
    component: () => import("../views/MainFoodView.vue"),
  },
  {
    path: "/main/food/detail",
    name: "food/detail",
    component: () => import("../views/MainFoodDetailView.vue"),
  },
  {
    path: "/main",
    name: "main",
    component: () => import("../views/MainView1.vue"),
  },
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
  {
    path: "/home",
    name: "newHome",
    component: () => import("../views/HomeViewCopy.vue"),
    redirect: () => {
      return { path: "/home/community" };
    },
    children: [
      {
        path: "community/:category",
        // path: "community",
        hidden: true,
        component: () => import("../components/home/VoteList.vue"),
        name: "voteList",
        props: true,
      },
    ],
  },
  {
    path: "/vote",
    name: "voteCreateView",
    component: () => import("../views/VoteCreateView.vue"),
    redirect: () => {
      return { path: "/vote/create" };
    },
    children: [
      {
        path: "create",
        name: "voteCreate",
        component: () => import("../components/home/VoteCreate.vue"),
        props: true,
      },
    ],
  },
  {
    path: "/vote",
    name: "voteDetailView",
    component: () => import("../views/VoteDetailView.vue"),
    redirect: () => {
      return { path: "/vote/detail/:voteId" };
    },
    children: [
      {
        path: "detail/:voteId",
        name: "voteDetail",
        component: () => import("../components/home/VoteDetail.vue"),
        props: true,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
