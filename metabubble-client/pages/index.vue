<script setup>
import { usePostStoreHook } from "@/stores/post/index"
import { storeToRefs } from "pinia";

const usePostStore = usePostStoreHook()
usePostStore.getList()
const { postList } = storeToRefs(usePostStore)
</script>

<!-- 广场首页 -->
<template>
  <div>
    <PostCard v-for="item in postList" :key="item.id" :foo="item" />
  </div>
</template>
