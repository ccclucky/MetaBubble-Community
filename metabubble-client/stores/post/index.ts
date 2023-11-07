import {
  CollectOrUnCollectAPI,
  LikeOrUnlikeAPI,
  PostAPI,
  getListAPI,
  CollectPostAPI,
  LikePostAPI,
  AllPostAPI,
} from "@/api/post/index";
import type { PostData, PostDTOData } from "@/api/post/type";
import store from "@/stores";
import { defineStore } from "pinia";

export const usePostStore = defineStore("post", () => {
  const postList = ref<PostDTOData[]>([]);
  const getList = async () => {
    const res: any = await getListAPI();
    postList.value = res.data;
  };

  const post = async (body: PostData) => {
    await PostAPI(body);
    await getList();
  };

  const likeOrUnlike = async (postId: number) => {
    await LikeOrUnlikeAPI(postId);
  };

  const CollectOrUnCollect = async (postId: number) => {
    await CollectOrUnCollectAPI(postId);
  };

  const collect = ref<PostDTOData[]>([]);
  const collectList = async () => {
    const res: any = await CollectPostAPI();
    collect.value = res.data;
  };

  const like = ref<PostDTOData[]>([]);
  const likeList = async (userId: number): Promise<any> => {
    const res: any = await LikePostAPI(userId);
    return res.data;
  };

  const posts = ref<PostDTOData[]>([]);
  const AllPosts = async (userId: number): Promise<any> => {
    const res: any = await AllPostAPI(userId);
    return res.data;
  };
  return {
    postList,
    collect,
    like,
    posts,
    getList,
    post,
    likeOrUnlike,
    CollectOrUnCollect,
    collectList,
    AllPosts,
    likeList,
  };
});

export function usePostStoreHook() {
  return usePostStore(store);
}
