import httpRequest from "@/utils/index";
import type { PostData } from "./type";

export const getListAPI = () => {
  return httpRequest.get("/post/list");
};

export const PostInfoAPI = (postId: number) => {
  return httpRequest.get(`/post/list/${postId}`);
};

export const CollectPostAPI = () => {
  return httpRequest.get(`/post/allCollect`);
};

export const LikePostAPI = (userId: number) => {
  return httpRequest.get(`/post/allLike/${userId}`);
};

export const AllPostAPI = (userId: number) => {
  return httpRequest.get(`/post/allPost/${userId}`);
};

export const PostAPI = (post: PostData) => {
  return httpRequest.post("/post/add", post);
};

export const LikeOrUnlikeAPI = (postId: number) => {
  return httpRequest.post(`/post/like/${postId}`, postId);
};

export const CollectOrUnCollectAPI = (postId: number) => {
  return httpRequest.post(`/post/collect/${postId}`, postId);
};

