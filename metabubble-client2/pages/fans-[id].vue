<template>
    <div v-if="allFans.length !== 0">
        <FollowCard v-for="item in allFans" :user="item" :key="item.id" />
    </div>
    <div v-else class="flex justify-center items-center h-96">
        <span>暂无粉丝</span>
    </div>
</template>

<script setup>
import { useFollowStoreHook } from '~/stores/follow'

const route = useRoute()

const useFollowStore = useFollowStoreHook()
const allFans = ref([])

onMounted(async () => {
    const res = await useFollowStore.getAllFans(route.params.id)
    allFans.value = res
})
</script>