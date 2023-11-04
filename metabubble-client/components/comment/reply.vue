<template>
    <div class="flex flex-1 flex-col p-4">
        <div class="flex flex-row w-full h-auto items-start">
            <div class="flex mr-3 w-auto">
                <!-- avtar -->
                <div class="flex w-10 h-10 rounded-full">
                    <img class="rounded-full w-full h-full block m-auto" :src="reply.avatar">
                </div>
            </div>
            <!-- 个人信息、时间、内容 -->
            <div class="flex flex-col flex-1">
                <!-- 信息 -->
                <div class="flex w-auto h-6 pt-2">
                    <span>{{ reply.username }} > {{ reply.replyUsername }}</span>
                </div>
                <!-- 内容 -->
                <div class="flex pt-2 text-left">
                    <p class=" whitespace-pre-wrap max-h-36 truncate break-all">
                        {{ reply.content }}
                    </p>
                </div>
            </div>
        </div>

        <!-- 时间、点赞、收藏、评论信息 -->
        <div class="flex flex-1 flex-row px-20 pt-2 h-full" v-if="parentId">
            <div class="flex flex-1 justify-start items-center">
                <p class="px-2">时间 · {{ reply.createTime }}</p>
            </div>
            <div class="flex flex-1 justify-center items-center cursor-pointer">
                <div @click="handleComment" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="false" name="majesticons:comment-2" class="w-5 h-5" />
                    <Icon v-else name="majesticons:comment-2-line" class="w-5 h-5" />
                </div>
            </div>
            <div class="flex flex-1 justify-start items-center cursor-pointer">
                <div @click="likeAndUnlike" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                    <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                </div>
                <p class="px-2">{{ likeCount }}</p>
            </div>
        </div>
    </div>

    <dialog ref="replyDialog" class="modal">
        <div class="modal-box">
            <h3 class="font-bold text-lg text-center">回复</h3>
            <form method="dialog">
                <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="resetForm">✕</button>
            </form>
            <div>
                <PostComment :post-id="postId" :replyUserId="reply.userId" :parent-id="parentId" />
            </div>
        </div>
    </dialog>
</template>

<script setup>
import { useCommentStoreHook } from '~/stores/comment';

const props = defineProps(['reply', "postId", 'parentId'])
const { reply, postId, parentId } = toRefs(props)
const replyDialog = ref('replyDialog')
const handleComment = () => {
    replyDialog.value.showModal()
}

// 点赞该评论
// 判断是否点赞
const useCommentStore = useCommentStoreHook()
const like = ref(reply.value.like)
const likeCount = ref(reply.value.likeCount)
const likeAndUnlike = () => {
    useCommentStore.likeOrUnlike(reply.value.id).then((res) => {
        like.value = !like.value
        likeCount.value = like.value ? ++likeCount.value : --likeCount.value
        usePostStore.getList()
    }).catch((err) => {

    });
}
</script>