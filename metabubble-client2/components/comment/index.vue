<template>
    <div class=" flex  flex-1 flex-col  border-base-300 p-4">
        <NuxtLink class="flex flex-row w-full h-auto items-start ">
            <div class="flex mr-3 w-auto">
                <!-- avtar -->
                <div class="flex mt-3 w-14 h-14 rounded-full">
                    <img class="rounded-full w-full h-full block m-auto" :src="comment.avatar">
                </div>
            </div>
            <!-- 个人信息、时间、内容 -->
            <div class="  flex flex-col flex-1">
                <!-- 信息 -->
                <div class="flex w-auto h-6 pt-4 mb-2  justify-between">


                    <div>
                        <span style="color: #6c84bb;" class=" tracking-widest mr-3" v-if="!isNotify">{{
                        comment.username }}</span>
                        <span style="color: #000;font-weight: 1000;" class=" tracking-wide me-3" v-else>{{
                        comment.username
                    }}</span>
                        <!-- <span style="color: #000;"> 评论了你的帖子</span> -->

                    </div>


                </div>

                <!-- 内容 -->
                <div class="w-full justify-between flex">
                    <div class="flex pt-2 text-left  flex-1 ">
                        <p class=" whitespace-pre-wrap tracking-wider max-h-36 truncate break-all">
                            {{ comment.content }}
                        </p>
                    </div>
                    <div class="pt-2 flex  justify-start items-center cursor-pointer w-5">


                    </div>

                </div>
                <div class="info h-10 flex flex-nowrap w-full justify-end items-center">
                    <svg t="1701928731393" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="4300" width="20" height="20">
                        <path
                            d="M512 64c-256 0-460.8 208-460.8 460.8s208 460.8 460.8 460.8 460.8-208 460.8-460.8S768 64 512 64zM512 940.8c-227.2 0-412.8-185.6-412.8-412.8s185.6-412.8 412.8-412.8 412.8 185.6 412.8 412.8S742.4 940.8 512 940.8z"
                            p-id="4301" fill="#acb0b3"></path>
                        <path
                            d="M809.6 544l-278.4 0 0-281.6c0-12.8-9.6-22.4-22.4-22.4-12.8 0-22.4 9.6-22.4 22.4l0 307.2c0 12.8 9.6 22.4 22.4 22.4 0 0 3.2 0 3.2 0l297.6 0c12.8 0 22.4-9.6 22.4-22.4C832 553.6 822.4 544 809.6 544z"
                            p-id="4302" fill="#acb0b3"></path>
                    </svg>

                    <span style="color: #b1b5bf;line-height:2.5rem;"
                        class=" ml-2 h-full text-base mr-4 tracking-wide">{{
                        comment.createTime
                    }}</span>
                    <div class="flex flex-1 justify-end items-center cursor-pointer">
                        <div @click="handleReply"
                            class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                            <Icon v-if="false" name="majesticons:comment-2" class="w-5 h-5" />
                            <Icon v-else name="majesticons:comment-2-line" class="w-5 h-5" />
                        </div>
                        <p class="px-3 mr-6"> 回复 </p>

                        <svg @click="likeAndUnlike" v-if="like" t="1701929971704" class="icon" viewBox="0 0 1024 1024"
                            version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10483" width="20" height="20">
                            <path
                                d="M948.4 407.2c-29.2-35.5-76.9-35.5-92.6-35.5H730c10.2-55.2 18.9-119.4 0.2-187.1-12.8-46.6-36.3-79.7-72-101.1-18.7-11.2-38.1-16.9-57.8-16.9-51.8 0-90.6 38.4-96.4 95.7-2.2 21.4-4.2 41.7-9.3 59.1-19 63.9-65.4 112.7-108.3 151.8-16 14.4-33.1 40.2-33.3 69.2-0.6 77.6-0.7 155.5-0.7 235.1l-0.1 141.4c-0.2 47.3 25 85.4 67 101.7 22.2 9 45.7 14 70.1 14.7 38.8 0.5 77.8 0.5 114.3 0.5h56.9c37.2 0 74.4 0 111.8 0.4h1.2c43.5 0 77.7-21.7 93.9-59.5l4.8-11.1c11.3-26 22.9-52.9 30.1-82.8 22-90.9 44.9-188.2 63.4-283.8 7.4-37.9 1.6-68.8-17.4-91.8zM216.1 374.5h-11.9c-56.2 0-101.9 45.7-101.9 101.9v348.4c0 56.2 45.7 101.9 101.9 101.9h11.9c56.2 0 101.9-45.7 101.9-101.9V476.4c0.1-56.2-45.7-101.9-101.9-101.9z"
                                fill="#dd6572" p-id="10484"></path>
                        </svg>

                        <svg @click="likeAndUnlike" v-else t="1701929074092" class="icon" viewBox="0 0 1024 1024"
                            version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7281" width="20" height="20">
                            <path
                                d="M598.354747 67.542626c-48.148687 0-90.130101 32.905051-98.960808 79.437576 0 0-14.312727 72.882424-21.798787 99.090101-12.308687 43.196768-55.363232 90.944646-86.522829 106.188283-23.531313 11.636364-110.99798 11.765657-116.350707 11.765656H155.707475c-32.762828 0-59.384242 26.479192-59.384243 59.384243v475.022222c0 32.762828 26.479192 59.384242 59.384243 59.384242h548.033939c88.126061 0 163.025455-64.452525 176.135758-151.647676l45.873131-305.713132c10.834747-71.809293-44.8-136.274747-117.423838-136.274747H673.254141s20.066263-66.469495 30.228687-178.669899c5.081212-56.837172-35.167677-110.99798-94.280404-117.152323-3.620202-0.54303-7.227475-0.814545-10.847677-0.814546zM333.705051 898.288485V421.533737c38.917172-2.534141 66.999596-8.016162 83.574949-16.316767 43.726869-21.669495 99.633131-81.040808 117.281616-143.088485 7.899798-27.681616 21.39798-96.155152 23.001212-104.184243 3.47798-17.92 20.596364-31.159596 40.649697-31.159596 1.603232 0 3.206465 0.129293 4.822627 0.271516 28.211717 2.947879 43.326061 29.698586 41.32202 52.686868-9.360808 103.912727-27.823838 166.503434-28.082425 166.904243l-23.130505 76.489697h215.182223c17.519192 0 33.564444 7.356768 45.071515 20.596363 11.507071 13.239596 16.316768 30.228687 13.640404 47.618586L821.294545 797.052121c-8.830707 58.569697-58.181818 101.094141-117.423838 101.094142h-370.165656v0.142222z m-177.997576 0v-475.022222h118.626262v475.022222H155.707475z m0 0"
                                p-id="7282" data-spm-anchor-id="a313x.search_index.0.i34.42e13a811JZrVu" fill="#000000"
                                class="selected"></path>
                        </svg>

                        <span @click="likeAndUnlike" style=" letter-spacing: 6px; font-size: 16px;"
                            class=" cursor-pointer">&nbsp;{{ likeCount == 0 ? '点赞' : likeCount }}</span>&nbsp;


                    </div>


                    <!-- <div @click="likeAndUnlike"
                            class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                            <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                            <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                        </div> -->
                    <!-- <p class="px-2">{{ likeCount }}</p> -->


                    <div>
                    </div>




                </div>

            </div>

        </NuxtLink>


        <!-- 时间、点赞、收藏、评论信息 -->
        <div v-if="!isNotify" class="flex flex-1 flex-row px-20 pt-2 h-full">

            <div class="flex flex-1 justify-center items-center cursor-pointer">
                <!-- <div @click="handleReply" class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                    <Icon v-if="false" name="majesticons:comment-2" class="w-5 h-5" />
                    <Icon v-else name="majesticons:comment-2-line" class="w-5 h-5" />
                </div> -->
                <!-- <p class="px-2">{{ replies?.length }}</p> -->
            </div>

        </div>


        <div class=" w-full h-max">

            <CommentReply v-for="(item, index) in replies" :key="item" :flag="flag" :reply="item" :index="index"
                :postId="postId" :parentId="comment.id" />
        </div>
        <!-- <div class=" w-full h-max"  v-else-if="flag==false&&replies.length==1">
            <CommentReply v-for="(item,index) in replies" :key="item" :flag="flag" :reply="item" :index="index" :postId="postId" :parentId="comment.id" />
        </div> -->
        <div v-if="!isNotify && replies.length > 1" class="flex justify-start p-32 items-center py-1">
            <p style="color: #5285add4;" class="w-max cursor-pointer" @click="allReplyHandle">
                <span></span>{{ flag ? "收起" : "展开" }}&nbsp{{ replies.length }} 条回复
            </p>
        </div>
    </div>

    <dialog ref="replyDialog" class="modal">
        <div class="modal-box">
            <h3 class="font-bold text-lg text-center">回复</h3>
            <form method="dialog">
                <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="resetForm">✕</button>
            </form>
            <div>
                <PostComment :post-id="postId" :replyUserId="comment.userId" :parent-id="comment.id" />
            </div>
        </div>
    </dialog>
</template>

<script setup>
import { useCommentStoreHook } from '~/stores/comment';

const useCommentStore = useCommentStoreHook()
const props = defineProps(['comment', 'postId'])

const { comment, postId } = toRefs(props);

const replies = ref(comment?.value.replies)

// 显示所有回复
const flag = ref(false)
const allReplyHandle = () => {
    flag.value = !flag.value
    replies.value = comment?.value.replies
}

// 回复评论
watch(comment, () => {
    replies.value = comment?.value.replies
});

// 回复评论的dialog
const replyDialog = ref("replyDialog")

const handleReply = () => {
    replyDialog.value.showModal()
}

// 点赞该评论
// 判断是否点赞
const like = ref(comment.value.like)
const likeCount = ref(comment.value.likeCount)
const likeAndUnlike = () => {
    useCommentStore.likeOrUnlike(comment.value.id).then((res) => {
        like.value = !like.value
        likeCount.value = like.value ? ++likeCount.value : --likeCount.value
        usePostStore.getList()
    }).catch((err) => {

    });
}

// 判断是否是通知页
const isNotify = ref(false)
const route = useRoute()
isNotify.value = route.matched[0].path === '/notify'
</script>
<style></style>