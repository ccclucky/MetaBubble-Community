<script setup>
import { storeToRefs } from 'pinia';
import { useCommentStoreHook } from '~/stores/comment';

const useCommentStore = useCommentStoreHook()
await useCommentStore.getAllReplies()
const { replies } = storeToRefs(useCommentStore)

</script>

<template>
    <div class=" w-full h-max">
        <CommentReply v-for="item in replies" :key="item" :reply="item" />
    </div>
</template>