<script setup>
import { storeToRefs } from 'pinia';
import { usePostStoreHook } from '~/stores/post';

const route = useRoute()
const usePostStore = usePostStoreHook()
const posts = await usePostStore.AllPosts(route.params.id)
</script>

<template>
    <div>
        <PostCard v-for="item in posts" :key="item.id" :foo="item" />
    </div>
</template>