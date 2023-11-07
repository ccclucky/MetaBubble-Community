<template>
    <div class="flex w-full h-28 p-3 border-b border-base-300" v-if="flag">
        <div class="flex items-start w-1/6">
            <img class="rounded-full w-20 h-20 block m-auto" :src="user.avatar">
        </div>
        <div class="flex items-center justify-start w-1/3 p-3">
            <span class="text-3xl font-black font-mono antialiased">{{ user.username }}</span>
        </div>
        <div class="flex items-center justify-end flex-1 h-full p-8">
            <button @click="handleToInfo" class="btn btn-circle w-24 h-6 border border-indigo-600 justify-center items-center text-base mr-3">
                主页
            </button>
            <button v-if="showFollow" @click="handleFollow" class="btn btn-circle w-24 h-6 border border-indigo-600 justify-center items-center text-base ml-3">
                取消关注
            </button>
        </div>
        <!-- <div class="flex items-center justify-start w-1/3 bg-slate-300 p-3">
            <span class="text-3xl font-black font-mono antialiased">{{ user.username }}</span>
        </div> -->
    </div>
</template>

<script setup>
import { useFollowStoreHook } from '~/stores/follow'

const flag = ref(true)

const props = defineProps(['user'])
const user = props.user

// 跳转主页
const router = useRouter()
const handleToInfo = () => {
    router.push({ name: 'info-id', params: { id: user.id } })
}

// 是否显示取消关注
const route = useRoute()
const showFollow = route.fullPath.startsWith("/fans") ? false : true

// 取消关注
const useFollowStore = useFollowStoreHook()
const handleFollow = () => {
    useFollowStore.follow(parseInt(user.id))
    flag.value = false
}
</script>