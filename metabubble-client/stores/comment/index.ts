import { CommentData } from './../../api/comment/type/index';
import {
  RepliesAPI,
  addCommentAPI,
  addReplyAPI,
  getAllNotifiesAPI,
  getCommentsAPI,
  likeAPI,
} from "./../../api/comment/index";
import store from "@/stores";
import { defineStore } from "pinia";
import type {
  CommentData,
  CreateCommentData,
  ReplyData,
} from "~/api/comment/type";

export const useCommentStore = defineStore("comment", () => {
  const comments = ref<CommentData[]>([]);
  const getComments = async (postId: number) => {
    const res: any = await getCommentsAPI(postId);
    comments.value = res.data;
  };

  const addComment = async (comment: CreateCommentData) => {
    await addCommentAPI(comment);
    await getComments(comment.postId);
  };

  const addReply = async (reply: CreateCommentData) => {
    await addReplyAPI(reply);
    await getComments(reply.postId);
  };

  const likeOrUnlike = async (commentId: number) => {
    await likeAPI(commentId);
  };

  const replies = ref<ReplyData[]>([]);
  const getAllReplies = async () => {
    const res: any = await RepliesAPI();
    replies.value = res.data;
  };

  const notifies = ref<CommentData[]>([]);
  const getNotifies = async () => {
    const res: any = await getAllNotifiesAPI();
    notifies.value = res.data;
  };

  return {
    comments,
    replies,
    notifies,
    getComments,
    addComment,
    addReply,
    likeOrUnlike,
    getAllReplies,
    getNotifies,
  };
});

export function useCommentStoreHook() {
  return useCommentStore(store);
}
