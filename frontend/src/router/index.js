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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
