<template>
    <div class="mt main w-full flex justify-center flex-wrap">
        <div class="task">
            <div class="tags">
                <span class="tag">活动通知</span>
                <button class="options">
                    <svg xml:space="preserve" viewBox="0 0 41.915 41.916" xmlns:xlink="http://www.w3.org/1999/xlink"
                        xmlns="http://www.w3.org/2000/svg" id="Capa_1" version="1.1" fill="#000000">
                        <g stroke-width="0" id="SVGRepo_bgCarrier"></g>
                        <g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g>
                        <g id="SVGRepo_iconCarrier">
                            <g>
                                <g>
                                    <path
                                        d="M11.214,20.956c0,3.091-2.509,5.589-5.607,5.589C2.51,26.544,0,24.046,0,20.956c0-3.082,2.511-5.585,5.607-5.585 C8.705,15.371,11.214,17.874,11.214,20.956z">
                                    </path>
                                    <path
                                        d="M26.564,20.956c0,3.091-2.509,5.589-5.606,5.589c-3.097,0-5.607-2.498-5.607-5.589c0-3.082,2.511-5.585,5.607-5.585 C24.056,15.371,26.564,17.874,26.564,20.956z">
                                    </path>
                                    <path
                                        d="M41.915,20.956c0,3.091-2.509,5.589-5.607,5.589c-3.097,0-5.606-2.498-5.606-5.589c0-3.082,2.511-5.585,5.606-5.585 C39.406,15.371,41.915,17.874,41.915,20.956z">
                                    </path>
                                </g>
                            </g>
                        </g>
                    </svg>
                </button>
            </div>
            <div @click="handlePush">
                <p style="letter-spacing: 2px; color: #4e4e4e;"
                    class="tracking-wide whitespace-pre-wrap max-h-36 truncate break-all">
                    {{ post.content }}
                </p>
            </div>
            <div class="stats overflow-hidden">
                <div>
                    <div>
                        <svg t="1701928731393" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="4300" width="20" height="20">
                            <path
                                d="M512 64c-256 0-460.8 208-460.8 460.8s208 460.8 460.8 460.8 460.8-208 460.8-460.8S768 64 512 64zM512 940.8c-227.2 0-412.8-185.6-412.8-412.8s185.6-412.8 412.8-412.8 412.8 185.6 412.8 412.8S742.4 940.8 512 940.8z"
                                p-id="4301" fill="#acb0b3"></path>
                            <path
                                d="M809.6 544l-278.4 0 0-281.6c0-12.8-9.6-22.4-22.4-22.4-12.8 0-22.4 9.6-22.4 22.4l0 307.2c0 12.8 9.6 22.4 22.4 22.4 0 0 3.2 0 3.2 0l297.6 0c12.8 0 22.4-9.6 22.4-22.4C832 553.6 822.4 544 809.6 544z"
                                p-id="4302" fill="#acb0b3"></path>
                        </svg>
                        {{ post.createTime }}
                    </div>
                    <div>
                        <svg t="1701929016232" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="6131" width="20" height="20">
                            <path d="M343.8 473.1m-50.3 0a50.3 50.3 0 1 0 100.6 0 50.3 50.3 0 1 0-100.6 0Z"
                                fill="#e1e4e8" p-id="6132" data-spm-anchor-id="a313x.search_index.0.i26.42e13a811JZrVu"
                                class="selected"></path>
                            <path d="M520.8 473.1m-50.3 0a50.3 50.3 0 1 0 100.6 0 50.3 50.3 0 1 0-100.6 0Z"
                                fill="#acb0b3" p-id="6133" data-spm-anchor-id="a313x.search_index.0.i25.42e13a811JZrVu"
                                class="selected"></path>
                            <path d="M697.9 473.1m-50.3 0a50.3 50.3 0 1 0 100.6 0 50.3 50.3 0 1 0-100.6 0Z"
                                fill="#acb0b3" p-id="6134" data-spm-anchor-id="a313x.search_index.0.i24.42e13a811JZrVu"
                                class="selected"></path>
                            <path
                                d="M138.4 1004.4l14.9-72.4c0.1-0.5 9.7-47.3 16.8-98.1 11.9-86 6.5-109 4.8-113.7-77.5-74-120.2-169.9-120.2-270.3 0-54.4 12.3-107.2 36.5-156.9 23.3-47.7 56.5-90.4 98.8-127.1 42-36.4 90.9-65 145.2-84.9 56.1-20.6 115.6-31 176.9-31 61.3 0 120.8 10.4 176.9 31 54.3 19.9 103.2 48.5 145.2 84.9 42.3 36.6 75.5 79.4 98.8 127.1 24.2 49.7 36.5 102.4 36.5 156.9S957.2 557.1 933 606.8c-23.3 47.7-56.5 90.4-98.8 127.1-42 36.4-90.9 65-145.2 84.9-56.1 20.6-115.6 31-176.9 31-49.7 0-98.6-6.9-145.3-20.6-6 1.8-29.1 11.5-95.6 63.4-40.6 31.7-76.6 63-77 63.3l-55.8 48.5zM512.1 100c-224.7 0-407.4 157-407.4 349.9 0 87.2 37.7 170.8 106 235.3 15.8 14.9 23.5 45.7 9.9 147.9-1.9 14-3.9 27.8-6 40.6 9.1-7.4 18.9-15.2 29-23 81.5-63.3 108.4-71.9 123.9-71.9 3.6 0 7 0.5 10.3 1.5 43.1 12.9 88.2 19.5 134.3 19.5 224.6 0 407.4-156.9 407.4-349.9C919.4 257 736.7 100 512.1 100z"
                                fill="#acb0b3" p-id="6135" data-spm-anchor-id="a313x.search_index.0.i23.42e13a811JZrVu"
                                class="selected"></path>
                        </svg>
                        {{ commentCount }}
                    </div>
                    <div>
                        <svg v-if="like" t="1701929971704" @click="likeAndUnlike" class="icon" viewBox="0 0 1024 1024"
                            version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10483" width="20" height="20">
                            <path
                                d="M948.4 407.2c-29.2-35.5-76.9-35.5-92.6-35.5H730c10.2-55.2 18.9-119.4 0.2-187.1-12.8-46.6-36.3-79.7-72-101.1-18.7-11.2-38.1-16.9-57.8-16.9-51.8 0-90.6 38.4-96.4 95.7-2.2 21.4-4.2 41.7-9.3 59.1-19 63.9-65.4 112.7-108.3 151.8-16 14.4-33.1 40.2-33.3 69.2-0.6 77.6-0.7 155.5-0.7 235.1l-0.1 141.4c-0.2 47.3 25 85.4 67 101.7 22.2 9 45.7 14 70.1 14.7 38.8 0.5 77.8 0.5 114.3 0.5h56.9c37.2 0 74.4 0 111.8 0.4h1.2c43.5 0 77.7-21.7 93.9-59.5l4.8-11.1c11.3-26 22.9-52.9 30.1-82.8 22-90.9 44.9-188.2 63.4-283.8 7.4-37.9 1.6-68.8-17.4-91.8zM216.1 374.5h-11.9c-56.2 0-101.9 45.7-101.9 101.9v348.4c0 56.2 45.7 101.9 101.9 101.9h11.9c56.2 0 101.9-45.7 101.9-101.9V476.4c0.1-56.2-45.7-101.9-101.9-101.9z"
                                fill="#dd6572" p-id="10484"></path>
                        </svg>

                        <svg v-else @click="likeAndUnlike" t="1701929074092" class="icon" viewBox="0 0 1024 1024"
                            version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7281" width="20" height="20">
                            <path
                                d="M598.354747 67.542626c-48.148687 0-90.130101 32.905051-98.960808 79.437576 0 0-14.312727 72.882424-21.798787 99.090101-12.308687 43.196768-55.363232 90.944646-86.522829 106.188283-23.531313 11.636364-110.99798 11.765657-116.350707 11.765656H155.707475c-32.762828 0-59.384242 26.479192-59.384243 59.384243v475.022222c0 32.762828 26.479192 59.384242 59.384243 59.384242h548.033939c88.126061 0 163.025455-64.452525 176.135758-151.647676l45.873131-305.713132c10.834747-71.809293-44.8-136.274747-117.423838-136.274747H673.254141s20.066263-66.469495 30.228687-178.669899c5.081212-56.837172-35.167677-110.99798-94.280404-117.152323-3.620202-0.54303-7.227475-0.814545-10.847677-0.814546zM333.705051 898.288485V421.533737c38.917172-2.534141 66.999596-8.016162 83.574949-16.316767 43.726869-21.669495 99.633131-81.040808 117.281616-143.088485 7.899798-27.681616 21.39798-96.155152 23.001212-104.184243 3.47798-17.92 20.596364-31.159596 40.649697-31.159596 1.603232 0 3.206465 0.129293 4.822627 0.271516 28.211717 2.947879 43.326061 29.698586 41.32202 52.686868-9.360808 103.912727-27.823838 166.503434-28.082425 166.904243l-23.130505 76.489697h215.182223c17.519192 0 33.564444 7.356768 45.071515 20.596363 11.507071 13.239596 16.316768 30.228687 13.640404 47.618586L821.294545 797.052121c-8.830707 58.569697-58.181818 101.094141-117.423838 101.094142h-370.165656v0.142222z m-177.997576 0v-475.022222h118.626262v475.022222H155.707475z m0 0"
                                p-id="7282" data-spm-anchor-id="a313x.search_index.0.i34.42e13a811JZrVu" fill="#acb0b3"
                                class="selected"></path>
                        </svg>
                        {{ likeCount }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <svg v-if="collect" t="1701929744606" @click="CollectAndUnCollect" class="icon"
                            viewBox="0 0 1025 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9139"
                            width="18" height="18">
                            <path
                                d="M1024.049737 393.508571a36.571429 36.571429 0 0 0-29.257143-24.868571l-311.588571-44.617143L544.232594 42.422857a37.302857 37.302857 0 0 0-64.365714 0l-138.971429 281.6L30.769737 365.714286a36.571429 36.571429 0 0 0-29.257143 24.868571 36.571429 36.571429 0 0 0 9.508572 36.571429l224.548571 219.428571-53.394286 311.588572a36.571429 36.571429 0 0 0 14.628572 35.108571 35.108571 35.108571 0 0 0 21.211428 6.582857 33.645714 33.645714 0 0 0 16.091429-4.388571l277.942857-146.285715 277.942857 146.285715a34.377143 34.377143 0 0 0 37.302857 0 36.571429 36.571429 0 0 0 14.628572-35.108572l-53.394286-309.394285 224.548572-219.428572a36.571429 36.571429 0 0 0 10.971428-38.034286z"
                                fill="#ff713c" p-id="9140"></path>
                        </svg>

                        <svg v-else t="1701929182257" @click="CollectAndUnCollect" class="icon" viewBox="0 0 1024 1024"
                            version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8295"
                            data-spm-anchor-id="a313x.search_index.0.i39.42e13a811JZrVu" width="20" height="20">
                            <path
                                d="M281.53303 928.39895c-25.624986 0-50.501018-12.174029-66.544026-32.565043-14.54295-18.485965-19.864986-41.554022-14.602035-63.289958 3.644006-15.03703 35.325952-142.825984 50.326016-203.30496-46.522982-40.027955-143.173018-123.188019-147.206042-126.69399-37.387981-32.427008-41.397965-65.25696-38.167962-87.080038 5.347021-36.103987 30.872986-62.679962 66.617037-69.364019l2.381005-0.44503 214.680986-19.841024 78.381978-180.89001c6.044979-16.124006 14.564966-29.507994 25.342976-39.805952 16.134042-15.417037 36.653978-23.565005 59.34295-23.565005l0.67799 0.002048c23.29897 0.172032 45.211955 10.169958 61.714022 28.150989 10.638029 11.590963 16.735027 23.882035 19.664998 29.790003l0.752026 1.516954 76.652954 183.51104 216.03799 20.608 3.168973 0.706048c13.595034 3.030016 31.045018 8.171008 45.446042 21.139968 10.863002 9.784013 23.813018 27.567002 23.813018 56.727962 0 32.011981-17.926042 52.00599-31.015014 66.604954-1.735987 1.935974-3.569971 3.979981-5.480038 6.175027l-1.154048 1.325978-1.239962 1.246003c-1.007002 1.010995-1.055027 1.060045-143.350989 129.17801 14.619034 69.505024 41.413018 197.642957 43.625984 213.520998 3.145011 22.42304-2.69097 43.50505-16.440013 59.317965-10.422989 11.987046-30.005965 26.276045-64.108954 26.276045-8.816026 0-20.312986-1.517978-44.534989-12.530995l-2.336973-1.063014L512.531046 808.66601c-56.04096 32.335974-177.973965 102.542029-195.439002 111.326003C305.964032 925.577011 294.013952 928.39895 281.53303 928.39895zM196.490957 444.796006c36.530995 31.446016 105.230029 90.554982 146.699981 126.232986l24.365978 20.963021-7.740006 31.195955c-0.377037 1.521971-27.010048 108.873011-44.128051 178.034995 47.157043-26.827981 120.028979-68.775014 170.746982-98.092032l26.159002-15.120998 194.361037 112.572006c-10.519962-51.373978-26.318029-126.87401-37.578035-180.240998l-6.213018-29.449011 22.367027-20.139008c1.239962-1.115955 95.806976-86.260019 140.576051-126.571008l-226.801971-21.63497-87.554048-209.610957-91.506995 211.180954L196.490957 444.796006z"
                                p-id="8296" data-spm-anchor-id="a313x.search_index.0.i38.42e13a811JZrVu" fill="#acb0b3"
                                class="selected"></path>
                        </svg>
                        {{ collectCount }}
                    </div>
                </div>
                <div style="border: 0;" class="viewer">
                    <!-- <span><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                            <g stroke-width="0" id="SVGRepo_bgCarrier"></g>
                            <g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g>
                            <g id="SVGRepo_iconCarrier">
                                <path stroke-width="2" stroke="#ffffff"
                                    d="M17 8C17 10.7614 14.7614 13 12 13C9.23858 13 7 10.7614 7 8C7 5.23858 9.23858 3 12 3C14.7614 3 17 5.23858 17 8Z">
                                </path>
                                <path stroke-linecap="round" stroke-width="2" stroke="#ffffff"
                                    d="M3 21C3.95728 17.9237 6.41998 17 12 17C17.58 17 20.0427 17.9237 21 21"></path>
                            </g>
                        </svg></span>
                    <span><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                            <g stroke-width="0" id="SVGRepo_bgCarrier"></g>
                            <g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g>
                            <g id="SVGRepo_iconCarrier">
                                <path stroke-width="2" stroke="#ffffff"
                                    d="M17 8C17 10.7614 14.7614 13 12 13C9.23858 13 7 10.7614 7 8C7 5.23858 9.23858 3 12 3C14.7614 3 17 5.23858 17 8Z">
                                </path>
                                <path stroke-linecap="round" stroke-width="2" stroke="#ffffff"
                                    d="M3 21C3.95728 17.9237 6.41998 17 12 17C17.58 17 20.0427 17.9237 21 21"></path>
                            </g>
                        </svg></span>
                    <span><svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                            <g stroke-width="0" id="SVGRepo_bgCarrier"></g>
                            <g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g>
                            <g id="SVGRepo_iconCarrier">
                                <path stroke-width="2" stroke="#ffffff"
                                    d="M17 8C17 10.7614 14.7614 13 12 13C9.23858 13 7 10.7614 7 8C7 5.23858 9.23858 3 12 3C14.7614 3 17 5.23858 17 8Z">
                                </path>
                                <path stroke-linecap="round" stroke-width="2" stroke="#ffffff"
                                    d="M3 21C3.95728 17.9237 6.41998 17 12 17C17.58 17 20.0427 17.9237 21 21"></path>
                            </g>
                        </svg></span> -->
                    <NuxtLink :to="{ name: 'info-id', params: { id: post.userId } }" class="flex  w-auto">
                        <!-- avatar -->


                        <div class="flex w-5 h-5 rounded-full">
                            <img class="rounded-full w-full ml-2 h-full block" :src="post.avatar" />
                        </div>
                    </NuxtLink>
                    <span>{{ post.username }}</span>
                </div>
            </div>
        </div>

        <div>
            <!-- <div class="border-base-300 p-4"> -->
            <!-- <div class="flex flex-row w-full h-auto items-start">
                    <NuxtLink :to="{ name: 'info-id', params: { id: post.userId } }" class="flex mr-3 w-auto">
                    </NuxtLink>

                    <div class="flex flex-col flex-1">
                        <div class="flex w-auto h-6 pt-2 felx justify-between tracking-wide text-base">
                            <span style="color: #8c8a8b">{{ post.username }} </span>
                            <span style="color: #868996" class="mt-3 mb-2 text-sm tracking-wide">{{ post.createTime
                            }}</span>
                        </div>
                        <div @click="handlePush" class="mt-2 flex pt-2 text-left cursor-pointer">
                            <p class="tracking-wide whitespace-pre-wrap max-h-36 truncate break-all">
                                {{ post.content }}
                            </p>
                        </div>
                    </div>
                </div> -->

            <!-- <div class="flex flex-1 flex-row px-20 pt-2 h-full">
                    <div class="flex flex-1 justify-start items-center cursor-pointer">
                        <div @click="likeAndUnlike"
                            class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                            <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                            <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                        </div>
                        <p class="px-2">{{ likeCount }}</p>
                    </div>
                    <div class="flex flex-1 justify-center items-center cursor-pointer">
                        <div @click="handleComment"
                            class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
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
                </div> -->
        </div>
        <!-- </div> -->
    </div>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { usePostStoreHook } from "~/stores/post";

const props = defineProps(["foo"]);
const post = props.foo;
const router = useRouter();
// 点击跳转事件
const handlePush = () => {
    router.push({
        name: "post-detail", // 这里的路由名称应根据你的路由配置来定义
        query: { post: JSON.stringify(post) }, // 传递的参数，假设 post 有一个 id 字段
    });
};

const usePostStore = usePostStoreHook();
// 点赞事件
const like = ref(post.like);
const likeCount = ref(post.likeCount);
const likeAndUnlike = async () => {
    await usePostStore.likeOrUnlike(post.id);
    like.value = !like.value;
    likeCount.value = like.value ? ++likeCount.value : --likeCount.value;
    post.like = like.value;
    post.likeCount = likeCount.value;
};

// 评论事件
const handleComment = () => {
    router.push({
        name: "post-detail", // 这里的路由名称应根据你的路由配置来定义
        query: { post: JSON.stringify(post), comment: true }, // 传递的参数，假设 post 有一个 id 字段
    });
};

// 收藏事件
const collect = ref(post.collect);
const collectCount = ref(post.collectCount);
const CollectAndUnCollect = async () => {
    await usePostStore.CollectOrUnCollect(post.id);
    collect.value = !collect.value;
    collectCount.value = collect.value
        ? ++collectCount.value
        : --collectCount.value;
    post.collect = collect.value;
    post.collectCount = collectCount.value;
};

// 获取评论数量
const { postList } = storeToRefs(usePostStore);
const curPost = postList.value.find((item) => item.id === post.id);
const commentCount = ref(0);
watch(postList, () => {
    commentCount.value = postList.value.find(
        (item) => item.id === post.id
    ).commentCount;
});
</script>
<style>
.task {
    z-index: 0;
    margin-top: 10px;
    position: relative;
    color: #2e2e2f;
    cursor: pointer;
    background-color: #e8e8ff;
    padding: 1rem;
    border-radius: 8px;
    box-shadow: rgba(99, 99, 99, 0.1) 0px 2px 8px 0px;
    margin-bottom: 1rem;
    border: 3px dashed transparent;
    /* max-width: 450px; */
    width: 94%;
    margin-bottom: 20px;
}

.task:hover {
    box-shadow: rgba(99, 99, 99, 0.3) 0px 2px 8px 0px;
    border-color: rgba(162, 179, 207, 0.2) !important;
}

.task p {
    font-size: 15px;
    padding: 1.2rem 0;
    margin-bottom: 1.2rem;
}

.tag {
    border-radius: 10px;
    padding: 4px 13px;
    font-size: 18px;
    color: #545454;
    font-weight: 1000;
    /* background-color: #1389eb; */
}

.tags {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.options {
    background: transparent;
    border: 0;
    color: #c4cad3;
    font-size: 17px;
}

.options svg {
    fill: #9fa4aa;
    width: 20px;
}

.stats {
    position: relative;
    width: 100%;
    color: #acb0b3;
    font-size: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: #e8e8ff;
}

.stats div {
    margin-right: 1rem;
    height: 20px;
    display: flex;
    align-items: center;
    cursor: pointer;
}

.stats svg {
    margin-right: 5px;
    height: 100%;
    stroke: #9fa4aa;
}

.viewer span {
    height: 30px;
    width: auto;
    /* background-color: rgb(28, 117, 219); */
    /* margin-right: 10px; */
    border-radius: 50%;
    display: grid;
    align-items: center;
    text-align: center;
    font-weight: bold;
    /* color: #fff; */
    padding: 2px;
}

.viewer span svg {
    stroke: #fff;
}
</style>
