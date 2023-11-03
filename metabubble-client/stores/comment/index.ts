import { addCommentAPI, addReplyAPI, getCommentsAPI, likeAPI } from './../../api/comment/index';
import store from "@/stores";
import { defineStore } from "pinia";
import type { CommentData, CreateCommentData } from '~/api/comment/type';

export const useCommentStore = defineStore("comment", () => {

    const comments = ref<CommentData[]>([]);
    const getComments = async (postId: number) => {
        const res: any = await getCommentsAPI(postId)
        comments.value = res.data
    }

    const addComment = async (comment: CreateCommentData) => {
        await addCommentAPI(comment)
        await getComments(comment.postId)
    }

    const addReply = async (reply: CreateCommentData) => {
        await addReplyAPI(reply)
        await getComments(reply.postId)
    }

    const likeOrUnlike = async (commentId: number) => {
        await likeAPI(commentId)
    }

    return {
        comments,
        getComments,
        addComment,
        addReply,
        likeOrUnlike
    }
});

export function useCommentStoreHook() {
  return useCommentStore(store);
}