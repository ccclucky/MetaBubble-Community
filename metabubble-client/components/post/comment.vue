<template>
    <div class="flex flex-col w-full h-max">
        <div class="flex flex-row p-3 w-full">
            <!-- avtar -->
            <div class="flex w-10 h-10 bg-gray-300 rounded-full">
                <img class="rounded-full w-full h-full block m-auto" :src="useUserStore.userInfo.avatar">
            </div>
            <!-- 文本框 -->
            <div class="flex w-full pl-3" @click="toggleButton">
                <el-input class="w-full text-2xl" resize="none" :autosize="{ minRows: 3, maxRows: 30 }" v-model="text"
                    type="textarea" placeholder="留下你的评论吧！" :inputStyle="style" :textareaStyle="style"
                    :containerStyle="style" />
            </div>
        </div>

        <!-- 提交按钮 -->
        <div class="flex justify-end">
            <!-- 回复按钮 -->
            <button @click="commentHandle" v-show="showButton"
                class="flex btn btn-ghost btn-circle w-16 b-8 text-base hover:bg-base-300">
                回复
            </button>

            <!-- 取消按钮 -->
            <button v-show="showButton" @click="cancelButton"
                class="flex btn btn-ghost btn-circle w-16 b-8 text-base hover:bg-base-300">
                取消
            </button>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import { useCommentStoreHook } from '~/stores/comment';
import { useUserStoreHook } from '~/stores/user';
const text = ref('');
const style = ref({
    background: 'transparent',
    color: "hsl(var(--bc) / var(--tw-text-opacity))"
});
const showButton = ref(false);

const toggleButton = () => {
    showButton.value = true;
};

const cancelButton = () => {
    showButton.value = false;
};

const props = defineProps(['postId', 'replyUserId', 'parentId'])
const postId = props.postId
const replyUserId = props.replyUserId
const parentId = props.parentId

const useUserStore = useUserStoreHook()
const useCommentStore = useCommentStoreHook()
const commentHandle = () => {
    if (parentId) {
        console.log("parentId", parentId);
        useCommentStore.addReply({
            postId: postId,
            parentId: parentId,
            replyUserId: replyUserId,
            content: text.value
        })
    } else {
        console.log("replyUserId", replyUserId);
        useCommentStore.addComment({
            postId: postId,
            replyUserId: replyUserId,
            content: text.value
        })
    }
    text.value = ""
}
</script>
  
<style scoped>
div {
    font-size: 18px;
}
</style>
  