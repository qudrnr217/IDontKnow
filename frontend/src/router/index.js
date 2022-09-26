import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/community",
    name: "community",
    component: () => import("../views/CommunityView.vue"),
    children: [
      {
        path: "createvote",
        name: "createvote",
        component: () => import("@/components/community/CreateVoteView.vue"),
      },
    ],
  },
  {
    path: "/createvote",
    name: "createvote",
    component: () => import("@/components/community/CreateVoteView.vue"),
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
