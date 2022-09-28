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

  {
    path: "/regist",
    name: "regist",
    component: () => import("../views/Regist1View.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/Login1View.vue"),
  },
  {
    path: "/logout",
    name: "logout",
    component: () => import("../views/Logout1View.vue"),
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("../views/MypageView.vue"),
  },
  {
    path: "/mypage/password",
    name: "mypage/password",
    component: () => import("../views/PasswordchangeView.vue"),
  },
  {
    path: "/main",
    name: "main",
    component: () => import("../views/HomeView.vue"),
    children: [
      {
        path: "clothes",
        name: "clothes",
        component: () => import("../views/MainClothesView.vue"),
      },
      {
        path: "hotspot",
        component: () => import("../views/MainHotspotView.vue"),
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
  // {
  //   path: "/about",
  //   name: "about",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
