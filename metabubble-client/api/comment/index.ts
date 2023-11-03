import httpRequest from "~/utils";
import type { CreateCommentData } from "./type";

export const getCommentsAPI = (postId: number) => {
  return httpRequest.get(`/comment/comments/${postId}`);
};

export const addCommentAPI = (comment: CreateCommentData) => {
  return httpRequest.post("/comment/add", comment);
};

export const addReplyAPI = (reply: CreateCommentData) => {
  return httpRequest.post("/comment/reply", reply);
};

export const likeAPI = (commentId: number) => {
  return httpRequest.post(`/comment/like/${commentId}`, commentId);
};
