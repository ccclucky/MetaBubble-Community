<script setup>
import { usePostStoreHook } from '~/stores/post';

const route = useRoute()
const usePostStore = usePostStoreHook()
const likes = await usePostStore.likeList(route.params.id)
</script>

<template>
    <div>
        <PostCard v-for="item in likes" :key="item.id" :foo="item" />
    </div>
</template>