<template>
    <NuxtLink class="flex flex-row w-full h-auto p-4  border-b border-base-300 items-start" :to="{
        name: 'post-detail', // 这里的路由名称应根据你的路由配置来定义
        query: { post: JSON.stringify(post) } // 传递的参数，假设 post 有一个 id 字段
    }">
        <div class="flex mr-3 w-auto">
            <!-- avtar -->
            <div class="flex w-10 h-10 rounded-full">
                <img class="rounded-full w-full h-full block m-auto" :src="post.avatar">
            </div>
        </div>
        <!-- 个人信息、时间、内容 -->
        <div class="flex flex-col flex-1">
            <!-- 信息 -->
            <div class="flex w-auto h-6 pt-2">
                <span>{{ post.username }} · {{ post.createTime }}</span>
            </div>
            <!-- 内容 -->
            <div class="flex pt-2 text-left">
                <p class=" whitespace-pre-wrap max-h-36 truncate break-all">
                    {{ post.content }}
                </p>
            </div>
            <!-- 点赞、收藏、评论信息 -->
            <div class="flex flex-1 flex-row px-20 pt-2 h-full">
                <div class="flex flex-1 justify-start items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <Icon v-if="true" name="icon-park-outline:like" class="w-5 h-5" />
                        <Icon v-else name="icon-park-solid:like" class="w-5 h-5" />
                    </div>
                    <p class="px-2">231</p>
                </div>
                <div class="flex flex-1 justify-center items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <Icon v-if="true" name="majesticons:comment-2-line" class="w-5 h-5" />
                        <Icon v-else name="majesticons:comment-2" class="w-5 h-5" />
                    </div>
                    <p class="px-2">231</p>
                </div>
                <div class="flex flex-1 justify-end items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <Icon v-if="true" name="ep:collection-tag" class="w-5 h-5" />
                        <Icon v-else name="icon-park-solid:like" class="w-5 h-5" />
                    </div>
                    <p class="px-2">231</p>
                </div>
            </div>
        </div>
    </NuxtLink>
</template>

<script setup>
const props = defineProps(['foo'])
const post = props.foo
</script>
