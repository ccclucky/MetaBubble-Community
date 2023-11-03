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
                <div class="flex">&nbsp;·&nbsp;</div>
                <div class="flex text-sm font-normal">231</div>
                <div class="flex text-sm font-normal"> 查看</div>
            </div>

            <!-- 点赞、收藏、评论 -->
            <div class="flex flex-1 flex-row px-20 py-2 h-full border-y border-base-300">
                <div class="flex flex-1 justify-start items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <button @click="likeAndUnlike">
                            <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                            <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                        </button>
                    </div>
                    <p class="px-2">{{ likeCount }}</p>
                </div>
                <div class="flex flex-1 justify-center items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <button>
                            <Icon v-if="true" name="majesticons:comment-2-line" class="w-5 h-5" />
                            <Icon v-else name="majesticons:comment-2" class="w-5 h-5" />
                        </button>
                    </div>
                    <p class="px-2">{{ commentList.length }}</p>
                </div>
                <div class="flex flex-1 justify-end items-center">
                    <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                        <button @click="CollectAndUnCollect">
                            <Icon v-if="collect" name="icon-park-solid:file-collection" class="w-5 h-5" />
                            <Icon v-else name="icon-park-outline:file-collection" class="w-5 h-5" />
                        </button>
                    </div>
                    <p class="px-2">{{ collectCount }}</p>
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="flex flex-col" ref="postComment">
            <div class="flex w-full h-auto pt-2 px-4">评论</div>
            <!-- 你的回复 -->
            <div class="flex border-b border-base-300">
                <!-- 回复 -->
                <PostComment :postId="post.id" :replyUserId="post.userId" class=" pb-2" />
            </div>
            <!-- 评论信息 -->
            <div class="flex flex-1 flex-col">
                <PostCart />
                <Comment v-for="item in commentList" :key="item.id" :comment="item" :postId="post.id" />
            </div>
        </div>
    </div>
</template>

<script setup>
import { usePostStoreHook } from '~/stores/post';
import { useCommentStoreHook } from '~/stores/comment';
import { storeToRefs } from 'pinia';

const route = useRoute();
const post = JSON.parse(route.query.post); // 获取 postId 参数

const usePostStore = usePostStoreHook()

const like = ref(post.like)
const likeCount = ref(post.likeCount)
const likeAndUnlike = () => {
    usePostStore.likeOrUnlike(post.id).then((res) => {
        like.value = !like.value
        likeCount.value = like.value ? ++likeCount.value : --likeCount.value
        usePostStore.getList()
    }).catch((err) => {

    });
}

// 收藏事件
const collect = ref(post.collect)
const collectCount = ref(post.collectCount)
const CollectAndUnCollect = () => {
    usePostStore.CollectOrUnCollect(post.id).then((res) => {
        collect.value = !collect.value
        collectCount.value = collect.value ? ++collectCount.value : --collectCount.value
        usePostStore.getList()
    }).catch((err) => {

    });
}

// 获取评论信息
const useCommentStore = useCommentStoreHook()
useCommentStore.getComments(post.id)

const commentList  = ref([])
const { comments } = storeToRefs(useCommentStore)

watch(comments, () => {
  // 在 comment 变化时执行操作
  // 在这里执行更新 Comment 组件的操作，例如重新渲染或其他操作
  commentList.value = comments.value
});

const postComment = ref(null);  // 修正此行，应该使用相同的引用名
const router = useRouter()
const flag = ref(false)
onMounted(() => {
    // 定位到评论的效果
    if (route.query.comment === "true") {
        flag.value = true
    } else {
        flag.value = false
    }
    if (flag.value) {
        setTimeout(() => {
            window.scrollTo({
                top: postComment.value.getBoundingClientRect().y - 64,
                behavior: "smooth"
            });
        }, 100);
        router.push({
            query: {
                post: JSON.stringify(post)
            }
        })
    }
});
</script>