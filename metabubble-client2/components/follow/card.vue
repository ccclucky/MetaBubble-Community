<template >
    <div class=" flex w-full h-20 p-3  border-base-200" v-if="flag">
        <div class="flex items-start w-1/7 ml-7 mr-2">
            <img class="rounded-full w-14 h-14 block m-auto" :src="user.avatar">
        </div>
        <div class="flex items-center justify-start w-1/3 p-3">
            <span style="color: #198bddb1;" class="text-2xl font-black font-mono antialiased">{{ user.username }}</span>
        </div>
        <div class="flex items-center justify-end flex-1 h-full p-8">
            <!-- <button @click="handleToInfo" class="btn btn-sm btn-circle rounded-full w-24 h-6 border border-indigo-600 justify-center items-center text-base mr-5">
                主页
            </button> -->
            <button v-if="showFollow" @click="handleFollow" style="color: #f2f2f6;" class="btn btn-sm btn-primary w-24 h-6 border  justify-center items-center text-base ml-5">
              {{ fucusOrNot > 0 ? "取消关注" : "关注" }}  
            </button>
        </div>
        <!-- <div class="flex items-center justify-start w-1/3 bg-slate-300 p-3">
            <span class="text-3xl font-black font-mono antialiased">{{ user.username }}</span>
        </div> -->
    </div>
</template>

<script setup>
import { useFollowStoreHook } from '~/stores/follow'

const fucusOrNot = ref(1)

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
    // flag.value = false
    fucusOrNot.value *= -1;
}
</script>