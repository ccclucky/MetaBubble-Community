<script setup>
import { storeToRefs } from 'pinia';
import { usePostStoreHook } from '~/stores/post';

const usePostStore = usePostStoreHook()
usePostStore.AllPosts()
const { posts } = storeToRefs(usePostStore)

</script>

<template>
    <div>
        <PostCard v-for="item in posts" :key="item.id" :foo="item" />
    </div>
</template>