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
                    <button @click="handleFollow"
                        class="btn btn-circle flex w-32 h-6 border border-indigo-600 absolute top-0 right-0 justify-center items-center text-base">
                        <span v-if="follow">已关注</span>
                        <span v-else>关注</span>
                    </button>
                </div>

                <!-- 昵称等信息 -->
                <div class="flex flex-col w-full h-auto">
                    <div class="flex flex-1 flex-col mt-1 mb-3">
                        <span class="flex text-xl font-bold">{{ user.username }}</span>
                        <span class="flex text-sm font-light">{{ user.email }}</span>
                    </div>
                    <div class="flex w-full h-5 mb-3">
                        <Icon name="material-symbols:calendar-month-outline-sharp" class=" w-4 h-4 mr-1" />
                        <span class="flex text-xs font-normal">{{ user.createTime }} 加入</span>
                    </div>

                    <!-- 关注和粉丝 -->
                    <div class="flex w-full h-8">
                        <!-- 显示数量 -->
                        <NuxtLink class="text-xs font-normal mr-2"
                            :to="{ name: 'follow-id', params: { id: user?.id } }">关注
                            {{ followCount }}</NuxtLink>
                        <NuxtLink class="text-xs font-normal ml-2" :to="{ name: 'fans-id', params: { id: user?.id } }">
                            粉丝 {{
                            fansCount }}
                        </NuxtLink>
                    </div>
                </div>

            </div>
        </div>
        <!-- 数据 -->
        <div class="flex flex-row w-full h-auto border-b border-base-300">
            <NuxtLink class="btn btn-ghost flex flex-1 justify-center items-center" v-for="item in dataItems"
                :key="item.name" :to="{ name: item.path, params: { id: user.id } }">
                <span class="flex w-full h-full justify-center items-center text-xl font-normal">{{ item.name }}</span>
            </NuxtLink>
        </div>
        <NuxtPage />
    </div>
</template>

<script setup>
import { useUserStoreHook } from '~/stores/user'
import { useFollowStoreHook } from '~/stores/follow'
import { storeToRefs } from 'pinia';

const route = useRoute()
const id = route.params.id
const useUserStore = useUserStoreHook()

const dataItems = [
    {
        name: "帖子",
        path: 'info-id-post-id'
    },
    {
        name: "喜欢",
        path: 'info-id-like-id'
    }
]

const user = await useUserStore.getUserInfoById(id)

const router = useRouter();
// 在页面加载时默认选择模块
const defaultModule = '/info-' + user.id + '/post-' + user.id; // 你希望的默认的默认模块
router.replace(defaultModule);

// 执行关注
const useFollowStore = useFollowStoreHook()
// 获取当前用户的所有关注用户
const curUserId = useUserStore.userInfo.id
const allFollow = ref([])


// 显示是否已关注
const follow = ref(false)
// const follow = ref(false)
const handleFollow = async () => {
    await useFollowStore.follow(parseInt(id))
    follow.value = !follow.value
}

watch(allFollow, () => {
    follow.value = allFollow.value.some(item => item.id === parseInt(id))
})

const followCount = ref(0)
const fansCount = ref(0)

onMounted(async () => {
    const res = await useFollowStore.getAllFollow(curUserId)
    allFollow.value = res


    // 获取关注数和粉丝数
    const count1 = await useFollowStore.getAllFollow(parseInt(id))
    followCount.value = count1.length
    const count2 = await useFollowStore.getAllFans(parseInt(id))
    fansCount.value = count2.length
})

</script>