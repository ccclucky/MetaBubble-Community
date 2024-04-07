<template>
    <div class="flex flex-col w-full h-max bg-base-200">
        <div class="flex flex-row p-3 w-full">
            <!-- avtar -->
            <div class="flex w-10 h-10 bg-gray-300 rounded-full">
                <img class="rounded-full w-full h-full block m-auto" :src="userInfo.avatar">
            </div>
            <!-- 文本框 -->
            <div class="flex w-full pl-3 " @click="getTheme">
                <el-input class="w-full text-2xl" resize="none" :autosize="{ minRows: 3, maxRows: 30 }" v-model="text"
                    type="textarea" placeholder="新鲜事！" :inputStyle="style" :textareaStyle="style" :containerStyle="style" />
            </div>
        </div>

        <!-- 提交 -->
        <div class="  flex justify-end">
            <button style="letter-spacing: 3px;" class=" opacity-90 text flex mx-4 btn w-28 rounded-xl  h-10 btn-primary   text-xl " @click="post">发 布</button>
        </div>
    </div>
</template>


<script setup>
import { storeToRefs } from 'pinia';
import { ref } from 'vue';
import { usePostStoreHook } from '~/stores/post';
import { useUserStoreHook } from '~/stores/user';

const usePostStore = usePostStoreHook()
const useUserStore = useUserStoreHook()

const text = ref('')

const style = ref({
    background: 'transparent',
    color: "hsl(var(--bc) / var(--tw-text-opacity))"
});

useUserStore.getUserInfo()
const { userInfo } = storeToRefs(useUserStore)

const post = async () => {
    const post = ref({
        userId: userInfo.value.id,
        content: text.value
    })
    usePostStore.post(post).then((res) => {
        text.value = ""
    }).catch((err) => {

    });
}
</script>

<style scoped>
div {
    font-size: 18px;
}
</style>