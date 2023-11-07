<template>
    <div class="flex flex-col">
        <!--  展示 -->
        <div class="flex flex-col w-full h-max">
            <!-- 背景图 -->
            <div class="flex w-full h-52">
                <img class="w-full h-full block m-auto" src="../public/sum.png">
            </div>

            <!-- 基本展示信息 -->
            <div class="flex flex-col w-full h-auto mb-4 px-4 pt-3">
                <!-- 头像、编辑按钮 -->
                <div class="flex w-full relative h-14">
                    <div class="flex w-32 h-32 bg-blcak rounded-full absolute bottom-4 ">
                        <img class="rounded-full w-full h-full block m-auto" :src="userInfo?.avatar">
                    </div>
                    <button @click="handleUpdateInfo"
                        class="btn btn-circle flex w-32 h-6 border border-indigo-600 absolute top-0 right-0 justify-center items-center text-base">
                        编辑信息
                    </button>
                </div>

                <!-- 昵称等信息 -->
                <div class="flex flex-col w-full h-auto">
                    <div class="flex flex-1 flex-col mt-1 mb-3">
                        <span class="flex text-xl font-bold">{{ userInfo?.username }}</span>
                        <span class="flex text-sm font-light">{{ userInfo?.email }}</span>
                    </div>
                    <div class="flex w-full h-5 mb-3">
                        <Icon name="material-symbols:calendar-month-outline-sharp" class=" w-4 h-4 mr-1" />
                        <span class="flex text-xs font-normal">{{ userInfo?.createTime }} 加入</span>
                    </div>

                    <!-- 关注和粉丝 -->
                    <div class="flex w-full h-8">
                        <NuxtLink class="text-xs font-normal mr-2" to="/focus">关注</NuxtLink>
                        <NuxtLink class="text-xs font-normal ml-2" :to="{ name: 'fans-id', params: { id: userInfo?.id } }">粉丝
                        </NuxtLink>
                    </div>
                </div>

            </div>
        </div>
        <!-- 数据 -->
        <div class="flex flex-row w-full h-auto border-b border-base-300">
            <NuxtLink class="btn btn-ghost flex flex-1 justify-center items-center" v-for="item in dataItems"
                :key="item.name" :to="{ name: item.path, params: { id: userInfo?.id } }">
                <span class="flex w-full h-full justify-center items-center text-xl font-normal">{{ item.name }}</span>
            </NuxtLink>
        </div>
        <NuxtPage />
    </div>

    <dialog ref="userInfoDialog" class="modal">
        <div class="modal-box w-1/3 max-w-5xl p-6">
            <h3 class="font-bold text-lg text-center">个人信息</h3>
            <form method="dialog">
                <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="resetForm">✕</button>
            </form>
            <div class="flex flex-col justify-center items-center w-full p-3">
                <div class="flex justify-center items-center pb-3">
                    <el-upload class="avatar-uploader" action="http://110.41.160.186:6789/systemConfig/upload"
                        :show-file-list="false" :on-success="handleAvatarSuccess">
                        <img v-if="avatar" :src="avatar" class="avatar w-48 h-48 rounded-full" />
                    </el-upload>
                </div>
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="userInfoUpdate.schoolId" type="text" placeholder="学号"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="userInfoUpdate.username" type="text" placeholder="昵称"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="userInfoUpdate.email" type="text" placeholder="邮箱"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="userInfoUpdate.phone" type="text" placeholder="电话"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center w-full">
                    <input v-model="userInfoUpdate.password" type="text" placeholder="密码"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
            </div>
            <div class="flex justify-center items-center">
                <button @click="updateUserInfo" class="btn btn-circle w-32 font-bold text-lg mr-6">
                    <span>修改</span>
                </button>
                <button class="btn w-32 btn-circle font-bold text-lg ml-6">
                    <span>取消</span>
                </button>
            </div>
        </div>
    </dialog>
</template>

<script lang="ts" setup>
import { storeToRefs } from 'pinia';
import { useUserStoreHook } from '~/stores/user';
import { useRouter } from 'vue-router';

import type { UploadProps } from 'element-plus'

const dataItems = [
    {
        name: "帖子",
        path: 'home-post-id'
    },
    {
        name: "回复",
        path: 'home-reply-id'
    },
    {
        name: "喜欢",
        path: 'home-like-id'
    }
]

const useUserStore = useUserStoreHook()
const { userInfo } = storeToRefs(useUserStore)

const router = useRouter();
// 在页面加载时默认选择模块
const defaultModule = '/home/post-' + userInfo.value?.id; // 你希望的默认模块
router.replace(defaultModule);

// 更新用户信息
const userInfoDialog = ref('userInfoDialog')
const userInfoUpdate = reactive({
    schoolId: "",
    username: "",
    email: "",
    phone: "",
    password: ""
})
const handleUpdateInfo = () => {
    userInfoUpdate.schoolId = userInfo.value!.schoolId,
        userInfoUpdate.username = userInfo.value!.username,
        userInfoUpdate.email = userInfo.value!.email,
        userInfoUpdate.phone = userInfo.value!.phone,
        (userInfoDialog.value as any).showModal();
}

const resetForm = () => {
    userInfoUpdate.schoolId = "",
        userInfoUpdate.username = "",
        userInfoUpdate.email = "",
        userInfoUpdate.phone = ""
}

const updateUserInfo = () => {
    useUserStore.updateInfo({
        id: userInfo.value!.id,
        avatar: avatar.value || undefined,
        schoolId: userInfoUpdate.schoolId || undefined,
        username: userInfoUpdate.username || undefined,
        email: userInfoUpdate.email || undefined,
        phone: userInfoUpdate.phone || undefined,
        password: userInfoUpdate.password || undefined,
    }).then((res) => {
        console.log(res);
        (userInfoDialog.value as any).close()
    }).catch((err) => {

    });
}

const avatar = ref('https://minio.cclucky.top/cclucky-blog/avatar/66fa0f9e7bd2c29dae746bf578795b45.png')

const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response,
    uploadFile
) => {
    avatar.value = URL.createObjectURL(uploadFile.raw!)
}
</script>