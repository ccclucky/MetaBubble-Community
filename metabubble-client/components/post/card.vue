<template>
    <div class=" border-b border-base-300 p-4">
        <div class="flex flex-row w-full h-auto items-start">
            <NuxtLink :to="{ name: 'info-id', params: { id: post.userId } }" class="flex mr-3 w-auto">
                <!-- avatar -->
                <div class="flex w-10 h-10 rounded-full">
                    <img class="rounded-full w-full h-full block m-auto" :src="post.avatar">
                </div>
            </NuxtLink>
            <!-- 个人信息、时间、内容 -->
            <div class="flex flex-col flex-1">
                <!-- 信息 -->
                <div class="flex w-auto h-6 pt-2">
                    <span>{{ post.username }} · {{ post.createTime }}</span>
                </div>
                <!-- 内容 -->
                <div @click="handlePush" class="flex pt-2 text-left cursor-pointer">
                    <p class=" whitespace-pre-wrap max-h-36 truncate break-all">
                        {{ post.content }}
                    </p>
                </div>
            </div>
        </div>

        <!-- 点赞、收藏、评论信息 -->
        <div class="flex flex-1 flex-row px-20 pt-2 h-full">
            <div class="flex flex-1 justify-start items-center cursor-pointer">
                <div @click="likeAndUnlike" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                    <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                </div>
                <p class="px-2">{{ likeCount }}</p>
            </div>
            <div class="flex flex-1 justify-center items-center cursor-pointer">
                <div @click="handleComment" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="false" name="majesticons:comment-2" class="w-5 h-5" />
                    <Icon v-else name="majesticons:comment-2-line" class="w-5 h-5" />
                </div>
                <p class="px-2">{{ commentCount }}</p>
            </div>
            <div class="flex flex-1 justify-end items-center cursor-pointer">
                <div @click="CollectAndUnCollect"
                    class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="collect" name="icon-park-solid:file-collection" class="w-5 h-5" />
                    <Icon v-else name="icon-park-outline:file-collection" class="w-5 h-5" />
                </div>
                <p class="px-2">{{ collectCount }}</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { usePostStoreHook } from '~/stores/post';

const props = defineProps(['foo'])
const post = props.foo
const router = useRouter()
// 点击跳转事件
const handlePush = () => {
    router.push({
        name: 'post-detail', // 这里的路由名称应根据你的路由配置来定义
        query: { post: JSON.stringify(post) } // 传递的参数，假设 post 有一个 id 字段
    })
}

const usePostStore = usePostStoreHook()
// 点赞事件
const like = ref(post.like)
const likeCount = ref(post.likeCount)
const likeAndUnlike = async () => {
    await usePostStore.likeOrUnlike(post.id)
    like.value = !like.value
    likeCount.value = like.value ? ++likeCount.value : --likeCount.value
    post.like = like.value
    post.likeCount = likeCount.value
}

// 评论事件
const handleComment = () => {
    router.push({
        name: 'post-detail', // 这里的路由名称应根据你的路由配置来定义
        query: { post: JSON.stringify(post), comment: true } // 传递的参数，假设 post 有一个 id 字段
    })
}

// 收藏事件
const collect = ref(post.collect)
const collectCount = ref(post.collectCount)
const CollectAndUnCollect = async () => {
    await usePostStore.CollectOrUnCollect(post.id)
    collect.value = !collect.value
    collectCount.value = collect.value ? ++collectCount.value : --collectCount.value
    post.collect = collect.value
    post.collectCount = collectCount.value
}

// 获取评论数量
const { postList } = storeToRefs(usePostStore)
const curPost = postList.value.find(item => item.id === post.id)
const commentCount = ref(0)
watch(postList, () => {
    commentCount.value = postList.value.find(item => item.id === post.id).commentCount
})
</script>
