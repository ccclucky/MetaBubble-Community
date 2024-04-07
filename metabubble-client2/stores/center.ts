import { useRoute } from "vue-router";
import { useUserStoreHook } from "~/stores/user";

export const useCenterStore = defineStore("center", () => {
  const route = useRoute();
  const useUserStore = useUserStoreHook();

  const pathTitle = [
    {
      name: "广场",
      path: "/",
      // title: "泡泡广场",
      title: "莞思社区",
      // description:'发现新事物 发现新事物 发现新事物 发现新事物 发现新事物'
      description: "获取最新内部消息",
    },
    {
      name: "关注",

      path: "/focus",
      title: "我的关注",
      //   description: "关注",
    },
    {
      name: "通知",
      path: "/notify",
      title: "通知",
      description: "",
    },
    {
      name: "个人",
      path: "/home",
      title: "个人主页",
      description: "",
    },
    {
      name: "收藏",
      path: "/collect",
      title: "我的收藏",
      description: "收藏了等会再看",
    },
    {
      name: "帖子",
      path: "/post/detail",
      title: "帖子",
      description: "",
    },
  ];

  const currentPath = ref(route.path);

  const getCurrentItemTitle = () => {
    if (route.name === "focus-id") {
      currentPath.value = "/focus";
    } else {
      currentPath.value = route.matched[0].path;
    }

    const currentItem = pathTitle.find(
      (item) => item.path === currentPath.value
    );
    return currentItem ? currentItem.title : "信息";
  };
  const getCurrentItemdescription = () => {
    if (route.name === "focus-id") {
      currentPath.value = "/focus";
    } else {
      currentPath.value = route.matched[0].path;
    }

    const currentItem = pathTitle.find(
      (item) => item.path === currentPath.value
    );
    return currentItem ? currentItem.description : "信息";
  };
  return {
    currentPath,
    pathTitle,
    getCurrentItemTitle,
    getCurrentItemdescription,
  };
});
