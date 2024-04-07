<script setup>
import { usePostStoreHook } from "@/stores/post/index"
import { storeToRefs } from "pinia";
import { useUserStoreHook } from "~/stores/user";

const usePostStore = usePostStoreHook()
const useUserStore = useUserStoreHook()
const user = useUserStore.userInfo
usePostStore.collectList(user.id)
const { collect } = storeToRefs(usePostStore)
</script>

<template>
    <div>
        <PostCard v-for="item in collect" :key="item.id" :foo="item" />
    </div>
</template>