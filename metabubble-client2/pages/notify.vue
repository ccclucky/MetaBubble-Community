<template>
    <div v-for="item in notifies">
        <Comment v-if="item.parentId === null" :key="item" :comment="item" :postId="item.postId"/>
        <CommentReply v-else :key="item.id" :reply="item" :postId="item.postId" />
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useCommentStoreHook } from '~/stores/comment';

const useCommmentStore = useCommentStoreHook()
const { notifies } = storeToRefs(useCommmentStore)
const flag = ref(true)
// if () {
//     flag.value = false
// }

onMounted(async () => {
    await useCommmentStore.getNotifies()
    console.log("-----", notifies.value);
})
</script>