import { PostAPI, getListAPI } from "@/api/post/index";
import type { PostData, PostVoData } from "@/api/post/type";
import store from "@/stores";
import { defineStore } from "pinia";

export const usePostStore = defineStore("post", () => {
  const postList = ref<PostVoData[]>([]);
  const getList = async () => {
    const res: any = await getListAPI();
    postList.value = res.data
  };

  const post = async (body: PostData) => {
    await PostAPI(body)
    await getList()
  }

  return {
    postList,
    getList,
    post
  };
});

export function usePostStoreHook() {
  return usePostStore(store);
}
