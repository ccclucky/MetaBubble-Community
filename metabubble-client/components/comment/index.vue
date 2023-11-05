
<template>
    <div class="flex flex-1 flex-col border-b border-base-300 p-4">
        <NuxtLink class="flex flex-row w-full h-auto items-start">
            <div class="flex mr-3 w-auto">
                <!-- avtar -->
                <div class="flex w-10 h-10 rounded-full">
                    <img class="rounded-full w-full h-full block m-auto" :src="comment.avatar">
                </div>
            </div>
            <!-- 个人信息、时间、内容 -->
            <div class="flex flex-col flex-1">
                <!-- 信息 -->
                <div class="flex w-auto h-6 pt-2">
                    <span>{{ comment.username }}</span>
                </div>
                <!-- 内容 -->
                <div class="flex pt-2 text-left">
                    <p class=" whitespace-pre-wrap max-h-36 truncate break-all">
                        {{ comment.content }}
                    </p>
                </div>
            </div>
        </NuxtLink>

        <!-- 时间、点赞、收藏、评论信息 -->
        <div class="flex flex-1 flex-row px-20 pt-2 h-full">
            <div class="flex flex-1 justify-start items-center">
                <p class="px-2">时间·{{ comment.createTime }}</p>
            </div>
            <div class="flex flex-1 justify-center items-center cursor-pointer">
                <div @click="handleReply" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="false" name="majesticons:comment-2" class="w-5 h-5" />
                    <Icon v-else name="majesticons:comment-2-line" class="w-5 h-5" />
                </div>
                <p class="px-2">{{ replies.length }}</p>
            </div>
            <div class="flex flex-1 justify-start items-center cursor-pointer">
                <div @click="likeAndUnlike" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                    <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                </div>
                <p class="px-2">{{ likeCount }}</p>
            </div>
        </div>

        <div class="flex justify-center items-center py-1">
            <p class="w-max cursor-pointer" @click="allReplyHandle">
                全部回复
            </p>
        </div>
        <div class=" w-full h-max" v-if="flag">
            <CommentReply v-for="item in replies" :key="item" :reply="item" :postId="postId" :parentId="comment.id" />
        </div>
    </div>

    <dialog ref="replyDialog" class="modal">
        <div class="modal-box">
            <h3 class="font-bold text-lg text-center">回复</h3>
            <form method="dialog">
                <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="resetForm">✕</button>
            </form>
            <div>
                <PostComment :post-id="postId" :replyUserId="comment.userId" :parent-id="comment.id" />
            </div>
        </div>
    </dialog>
</template>

<script setup>
import { useCommentStoreHook } from '~/stores/comment';

const useCommentStore = useCommentStoreHook()
const props = defineProps(['comment', 'postId'])

const { comment, postId } = toRefs(props);

const replies = ref(comment?.value.replies)

// 显示所有回复
const flag = ref(false)
const allReplyHandle = () => {
    flag.value = !flag.value
    replies.value = comment?.value.replies
}

// 回复评论
watch(comment, () => {
  replies.value = comment?.value.replies
});

// 回复评论的dialog
const replyDialog = ref("replyDialog")

const handleReply = () => {
    replyDialog.value.showModal()
}

// 点赞该评论
// 判断是否点赞
const like = ref(comment.value.like)
const likeCount = ref(comment.value.likeCount)
const likeAndUnlike = () => {
    useCommentStore.likeOrUnlike(comment.value.id).then((res) => {
        like.value = !like.value
        likeCount.value = like.value ? ++likeCount.value : --likeCount.value
        usePostStore.getList()
    }).catch((err) => {

    });
}

</script>