<template>
    <div class="flex flex-col w-full h-max px-4">
        <!-- 头部信息 -->
        <div class="flex flex-row pb-1 pt-3">
            <!-- avtar -->
            <div class="flex w-10 h-10 rounded-full mr-1">
                <img class="rounded-full w-full h-full block m-auto" :src="post.avatar">
            </div>
            <!-- 昵称 -->
            <div class="flex flex-1 flex-col">
                <span class="flex text-base font-bold">{{ post.username }}</span>
                <span class="flex text-sm font-light">{{ post.email }}</span>
            </div>
        </div>

        <!-- main -->
        <div class="flex flex-1 flex-col h-max">
            <!-- 文本内容 -->
            <div class="flex">
                <p class=" whitespace-pre-wrap  truncate break-all">
                    {{ post.content }}
                </p>
            </div>

            <!-- 发布时间，浏览数 -->
            <div class="flex flex-row my-4">
                <div class="flex text-sm font-normal">
                    <span>{{ post.createTime }}</span>
                </div>
                <div class="flex">·</div>
                <div class="flex text-base font-medium font-sans">231</div>
                <div class="flex text-sm font-normal"> 查看</div>
            </div>

            <!-- 点赞、收藏、评论 -->
            <div class="flex flex-1 flex-row px-20 py-2 h-full border-y border-base-300">
                <div class="flex flex-1 justify-start items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <button>
                            <Icon v-if="true" name="icon-park-outline:like" class="w-5 h-5" />
                            <Icon v-else name="icon-park-solid:like" class="w-5 h-5" />
                        </button>
                    </div>
                    <p class="px-2">231</p>
                </div>
                <div class="flex flex-1 justify-center items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <button>
                            <Icon v-if="true" name="majesticons:comment-2-line" class="w-5 h-5" />
                            <Icon v-else name="majesticons:comment-2" class="w-5 h-5" />
                        </button>
                    </div>
                    <p class="px-2">231</p>
                </div>
                <div class="flex flex-1 justify-end items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <button>
                            <Icon v-if="true" name="ep:collection-tag" class="w-5 h-5" />
                            <Icon v-else name="icon-park-solid:like" class="w-5 h-5" />
                        </button>
                    </div>
                    <p class="px-2">231</p>
                </div>
            </div>


        </div>

        <!-- footer -->
        <div class="flex flex-col">
            <div class="flex w-full h-auto pt-2 px-4">评论</div>
            <!-- 你的回复 -->
            <div class="flex border-b border-base-300">
                <!-- 回复 -->
                <PostComment class=" pb-2" />
            </div>
            <!-- 评论信息 -->
            <div class="flex">
                <PostCart />
            </div>
        </div>
    </div>
</template>

<script setup>
const route = useRoute();
const post = JSON.parse(route.query.post); // 获取 postId 参数
</script>