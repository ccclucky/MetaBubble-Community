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
                        <img class="rounded-full w-full h-full block m-auto" :src="userInfo.avatar">
                    </div>
                    <button
                        class="btn btn-circle flex w-32 h-6 border border-indigo-600 absolute top-0 right-0 justify-center items-center text-base">
                        编辑信息
                    </button>
                </div>

                <!-- 昵称等信息 -->
                <div class="flex flex-col w-full h-auto">
                    <div class="flex flex-1 flex-col mt-1 mb-3">
                        <span class="flex text-xl font-bold">{{ userInfo.username }}</span>
                        <span class="flex text-sm font-light">{{ userInfo.email }}</span>
                    </div>
                    <div class="flex w-full h-5 mb-3">
                        <Icon name="material-symbols:calendar-month-outline-sharp" class=" w-4 h-4 mr-1" />
                        <span class="flex text-xs font-normal">{{ userInfo.createTime }} 加入</span>
                    </div>

                    <!-- 关注和粉丝 -->
                    <div class="flex w-full h-8">
                        <NuxtLink class="text-xs font-normal mr-2" to="/focus">关注</NuxtLink>
                        <NuxtLink class="text-xs font-normal ml-2" to="/focus">粉丝</NuxtLink>
                    </div>
                </div>

            </div>
        </div>
        <!-- 数据 -->
        <div class="flex flex-row w-full h-auto border-b border-base-300">
            <NuxtLink
                class="btn btn-ghost flex flex-1 justify-center items-center"
                v-for="item in dataItems"
                :key="item.name"
                :to="item.path"
            >
                <span class="flex w-full h-full justify-center items-center text-xl font-normal">{{ item.name }}</span>
            </NuxtLink>
        </div>
        <NuxtPage />
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useUserStoreHook } from '~/stores/user';
import { useRouter } from 'vue-router';

const dataItems = [
    {
        name: "帖子",
        path: '/home/post'
    },
    {
        name: "回复",
        path: '/home/reply'
    },
    {
        name: "喜欢",
        path: '/home/like'
    }
]

const useUserStore = useUserStoreHook()
const { userInfo } = storeToRefs(useUserStore)

 const router = useRouter();
// 在页面加载时默认选择模块
const defaultModule = '/home/post'; // 你希望的默认模块
router.replace(defaultModule);

</script>
