<template>
    <div  class="flex flex-col w-full h-max"   >
        <div class="flex flex-row p-3 w-full" @click.self="cancelButton" >
            <!-- avtar -->

            <!-- 文本框 -->
            <div class="flex w-full pl-3" @click="toggleButton">
                <!-- <el-input class="bg-white w-full text-2xl" resize="none" :autosize="{ minRows: 3, maxRows: 30 }"
                    v-model="text" type="textarea" placeholder="留下你的评论吧！" :inputStyle="style" :textareaStyle="style"
                    :containerStyle="style" /> -->
                <el-input  style="letter-spacing: 3px;" :inputStyle="style" :textareaStyle="style"
                    :containerStyle="style"  class="textarea_comment" resize="none" :autosize="{ minRows: 3, maxRows: 30 }" v-model="text" type="textarea"
                    placeholder="留下你的评论吧！"  >
                    ddd
                    <!-- :inputStyle="style" :textareaStyle="style" :containerStyle="style -->
                    <!-- <template slot="prepend">Http://</template> -->
                </el-input>
            </div>
        </div>

        <!-- 提交按钮 -->
        <div class="flex justify-end">
            <!-- <button v-show="showButton" @click="cancelButton"
            
                class="flex btn btn-neutral btn-circle w-16 b-8 text-base hover:bg-base-300">
                取消
            </button> -->
            <!-- 回复按钮 -->
            <button @click="commentHandle" style="letter-spacing: 3px;"
                class="text-white flex btn btn-primary btn-circle btn- w-24 b-8 text-base ">
                评&nbsp论
            </button>

          
         
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useCommentStoreHook } from "~/stores/comment";
import { useUserStoreHook } from "~/stores/user";
import { usePostStoreHook } from "~/stores/post";
const text = ref("");
const style = ref({
    color: "hsl(var(--bc) / var(--tw-text-opacity))",


});
const showButton = ref(false);

const toggleButton = () => {
    showButton.value = true;
};

const cancelButton = () => {
    showButton.value = false;
};

const props = defineProps(["postId", "replyUserId", "parentId"]);
const postId = props.postId;
const replyUserId = props.replyUserId;
const parentId = props.parentId;

const useUserStore = useUserStoreHook();
const useCommentStore = useCommentStoreHook();
const usePostStore = usePostStoreHook();
const commentHandle = () => {
    if (parentId) {
        useCommentStore.addReply({
            postId: postId,
            parentId: parentId,
            replyUserId: replyUserId,
            content: text.value,
        });
    } else {
        useCommentStore.addComment({
            postId: postId,
            replyUserId: replyUserId,
            content: text.value,
        });
    }
    text.value = "";
};









</script>

<style scoped>
div {
    font-size: 18px;
}
.el-textarea__inner
{
}


/* Optional: Animation on focus */

</style>
