<template>
    <div v-if="allFollow.length !== 0">
        <FollowCard v-for="item in allFollow" :user="item" :key="item.id" />
    </div>
    <div v-else class="flex justify-center items-center h-96">
        <span>暂无关注</span>
    </div>
</template>

<script setup>
import { useFollowStoreHook } from '~/stores/follow'
import { useUserStoreHook } from '~/stores/user'

const route = useRoute()
const useFollowStore = useFollowStoreHook()
const useUserStore = useUserStoreHook()
const allFollow = ref([])

onMounted(async () => {
    const res = await useFollowStore.getAllFollow(useUserStore.userInfo.id)
    allFollow.value = res
})
</script>