import { CollectOrUnCollectAPI, LikeOrUnlikeAPI, PostAPI, getListAPI, PostInfoAPI } from "@/api/post/index";
import type { PostData, PostDTOData } from "@/api/post/type";
import store from "@/stores";
import { defineStore } from "pinia";

export const usePostStore = defineStore("post", () => {
  const postList = ref<PostDTOData[]>([]);
  const getList = async () => {
    const res: any = await getListAPI();
    postList.value = res.data
    
  };

  const post = async (body: PostData) => {
    await PostAPI(body)
    await getList()
  }

  const likeOrUnlike = async (postId : number) => {
    await LikeOrUnlikeAPI(postId)
  }

  const CollectOrUnCollect = async (postId : number) => {
    await CollectOrUnCollectAPI(postId)
  }

  return {
    postList,
    getList,
    post,
    likeOrUnlike,
    CollectOrUnCollect
  };
});

export function usePostStoreHook() {
  return usePostStore(store);
}
