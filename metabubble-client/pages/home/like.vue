<script setup>
import { storeToRefs } from 'pinia';
import { usePostStoreHook } from '~/stores/post';

const usePostStore = usePostStoreHook()
usePostStore.likeList()
const { like } = storeToRefs(usePostStore)
console.log(like.value);
</script>

<template>
    <div>
        <PostCard v-for="item in like" :key="item.id" :foo="item" />
    </div>
</template>