<template>
    <div class="flex bg-base-300 flex-col w-full h-max px-8 min-h-screen pb-20">
        <div class="w-full tracking-widest font-bold  h-16 flex justify-start items-center"><span>活动通知</span></div>
        <!-- 头部信息 -->
        <div class="flex flex-row pb-1 pt-3">
            <!-- avtar -->
            <div class="flex w-10 h-10 rounded-full mr-1">
                <img class="rounded-full w-full h-full block m-auto" :src="post.avatar">
            </div>
            <!-- 昵称 -->
            <div class=" ml-5 flex flex-1 flex-col">
                <span style="color: #6c84bb;" class=" font-thin tracking-wide flex text-base ">{{ post.username
                    }}</span>
                <!-- <span class="flex text-sm font-light">{{ post.email }}</span> -->
                <span style="color: #868996;" class="tracking-wide">{{ post.createTime }}</span>
            </div>
        </div>

        <!-- main -->
        <div class="flex flex-1 flex-col h-max mt-6 mb-6">

            <!-- 文本内容 -->
            <div class="flex">
                <p style="letter-spacing: 4px;" class="tracking-wide  whitespace-pre-wrap text-xl  truncate break-all">
                    {{ post.content }}
                </p>
            </div>

            <!-- 发布时间，浏览数 -->
            <div class="flex flex-row my-4 justify-end">
                <div class="flex">&nbsp;&nbsp;</div>
                <div style="color: #868996;" class="flex text-sm font-normal">231</div>
                <div style="color: #868996;" class="flex text-sm font-normal text-white"> 查看</div>
            </div>

            <!-- 点赞、收藏、评论 -->
            <div class="w-full flex justify-between flex-row px-2 py-2 h-full border-b-2   pb-6">
                <div class="ai-summary   ">


                    <!-- <div v-if="showBoard" class="chat chat-start min-w-80 w-[700px] absolute bottom-2 left-5  ">
                        <div class="chat-bubble">
                            总结：{{ aitext }}
                        </div>
                    </div> -->
                    <!-- <div onclick="handleShowTip" class="tooltip tooltip-right" :data-tip="aitext"> -->
                    <div :class="{ 'tooltip-open': showTip }" class="tooltip tooltip-right relative" :data-tip="aitext">
                        <img @click="handleAI" src="/AI.png" class="w-8 h-8 rounded-full relative cursor-pointer" />
                        <button v-if="showTip" @click="main"
                            class="w-8 h-8 rounded-full top-3px bg-white flex justify-center items-center">
                            <svg t="1712554624290" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                xmlns="http://www.w3.org/2000/svg" p-id="1576" width="16" height="16"
                                data-spm-anchor-id="a313x.search_index.0.i4.95bf3a81TPXRb6">
                                <path
                                    d="M874.017052 799.776769c-7.426863 7.625291-19.615989 7.795371-27.24128 0.368508-7.653637-7.426863-7.795371-19.615989-0.396855-27.24128 36.425645-37.44613 65.736242-81.893921 85.749087-131.160668 19.247481-47.367512 29.849186-99.185472 29.849186-153.554646 0-54.340826-10.601705-106.187133-29.849186-153.526298-20.012845-49.266748-49.323441-93.714539-85.749087-131.160669-7.398516-7.625291-7.256782-19.842764 0.396855-27.269627 7.625291-7.398516 19.814417-7.228435 27.24128 0.396856 39.912302 41.046174 72.029233 89.689292 93.88462 143.519876 21.090023 51.931348 32.712213 108.653305 32.712214 168.039862 0 59.414904-11.62219 116.136862-32.712214 168.039863-21.855387 53.85893-53.972318 102.502048-93.88462 143.548223z m-121.239287-132.861477c-7.426863 7.625291-19.615989 7.795371-27.24128 0.368508s-7.795371-19.615989-0.368508-27.24128c19.417562-19.956151 35.036652-43.65408 45.723397-69.931569 10.233197-25.257004 15.902558-52.895139 15.902558-81.922268 0-28.998782-5.669361-56.636917-15.902558-81.893921-10.686746-26.277489-26.305835-49.975418-45.723397-69.931569-7.426863-7.625291-7.256782-19.842764 0.368508-27.24128 7.625291-7.426863 19.814417-7.256782 27.24128 0.368509 22.932566 23.556195 41.357989 51.449452 53.887278 82.319123 12.104086 29.792493 18.765585 62.334625 18.765585 96.379138s-6.661499 66.614993-18.765585 96.407486c-12.557635 30.869671-30.954712 58.762928-53.887278 82.319123zM353.994906 269.351345H78.123796v437.73137h275.87111c4.337061 0 8.362308 1.41734 11.565497 3.826819l235.703687 149.217584V116.278596l-237.00764 150.039641c-3.174842 2.012623-6.74654 2.976415-10.261544 2.976415v0.056693zM74.778873 230.686303h273.716753l235.646994-149.189237c5.017385-3.146495 10.743439-5.045731 16.639575-5.612668 5.726055-0.566936 11.593843 0.113387 17.064777 2.069317v0.056694l0.08504 0.028346c6.236297 2.239398 11.395416 5.811095 15.108847 10.289891 4.450448 5.357546 6.859927 11.820618 6.859927 18.878972v761.990477c0 7.058355-2.409478 13.493079-6.859927 18.878973-3.344923 4.025246-7.880412 7.313476-13.294651 9.58122-0.62363 0.283468-1.247259 0.538589-1.899236 0.765364-5.470933 1.95593-11.338722 2.636253-17.064777 2.097663-5.896136-0.566936-11.62219-2.466172-16.639575-5.612667l-235.646994-149.189237H74.778873c-8.730816 0-16.866349-2.891374-23.017606-7.596944-7.596944-5.811095-12.302514-14.258443-12.302514-23.953051V262.20795c0-9.694607 4.733917-18.141955 12.330861-23.924703 6.12291-4.677223 14.28679-7.596944 22.989259-7.596944z"
                                    fill="" p-id="1577"></path>
                            </svg>
                        </button>
                    </div>

                </div>
                <div class="flex">
                    <div class="flex   justify-start items-center">
                        <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                            <!-- <button @click="likeAndUnlike">
                            <Icon v-if="like" name="icon-park-solid:like" class="w-5 h-5" />
                            <Icon v-else name="icon-park-outline:like" class="w-5 h-5" />
                        </button> -->

                            <svg v-if="like" t="1701929971704" @click="likeAndUnlike" class="icon"
                                viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="10483"
                                width="20" height="20">
                                <path
                                    d="M948.4 407.2c-29.2-35.5-76.9-35.5-92.6-35.5H730c10.2-55.2 18.9-119.4 0.2-187.1-12.8-46.6-36.3-79.7-72-101.1-18.7-11.2-38.1-16.9-57.8-16.9-51.8 0-90.6 38.4-96.4 95.7-2.2 21.4-4.2 41.7-9.3 59.1-19 63.9-65.4 112.7-108.3 151.8-16 14.4-33.1 40.2-33.3 69.2-0.6 77.6-0.7 155.5-0.7 235.1l-0.1 141.4c-0.2 47.3 25 85.4 67 101.7 22.2 9 45.7 14 70.1 14.7 38.8 0.5 77.8 0.5 114.3 0.5h56.9c37.2 0 74.4 0 111.8 0.4h1.2c43.5 0 77.7-21.7 93.9-59.5l4.8-11.1c11.3-26 22.9-52.9 30.1-82.8 22-90.9 44.9-188.2 63.4-283.8 7.4-37.9 1.6-68.8-17.4-91.8zM216.1 374.5h-11.9c-56.2 0-101.9 45.7-101.9 101.9v348.4c0 56.2 45.7 101.9 101.9 101.9h11.9c56.2 0 101.9-45.7 101.9-101.9V476.4c0.1-56.2-45.7-101.9-101.9-101.9z"
                                    fill="#dd6572" p-id="10484"></path>
                            </svg>

                            <svg v-else @click="likeAndUnlike" t="1701929074092" class="icon" viewBox="0 0 1024 1024"
                                version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7281" width="20" height="20">
                                <path
                                    d="M598.354747 67.542626c-48.148687 0-90.130101 32.905051-98.960808 79.437576 0 0-14.312727 72.882424-21.798787 99.090101-12.308687 43.196768-55.363232 90.944646-86.522829 106.188283-23.531313 11.636364-110.99798 11.765657-116.350707 11.765656H155.707475c-32.762828 0-59.384242 26.479192-59.384243 59.384243v475.022222c0 32.762828 26.479192 59.384242 59.384243 59.384242h548.033939c88.126061 0 163.025455-64.452525 176.135758-151.647676l45.873131-305.713132c10.834747-71.809293-44.8-136.274747-117.423838-136.274747H673.254141s20.066263-66.469495 30.228687-178.669899c5.081212-56.837172-35.167677-110.99798-94.280404-117.152323-3.620202-0.54303-7.227475-0.814545-10.847677-0.814546zM333.705051 898.288485V421.533737c38.917172-2.534141 66.999596-8.016162 83.574949-16.316767 43.726869-21.669495 99.633131-81.040808 117.281616-143.088485 7.899798-27.681616 21.39798-96.155152 23.001212-104.184243 3.47798-17.92 20.596364-31.159596 40.649697-31.159596 1.603232 0 3.206465 0.129293 4.822627 0.271516 28.211717 2.947879 43.326061 29.698586 41.32202 52.686868-9.360808 103.912727-27.823838 166.503434-28.082425 166.904243l-23.130505 76.489697h215.182223c17.519192 0 33.564444 7.356768 45.071515 20.596363 11.507071 13.239596 16.316768 30.228687 13.640404 47.618586L821.294545 797.052121c-8.830707 58.569697-58.181818 101.094141-117.423838 101.094142h-370.165656v0.142222z m-177.997576 0v-475.022222h118.626262v475.022222H155.707475z m0 0"
                                    p-id="7282" data-spm-anchor-id="a313x.search_index.0.i34.42e13a811JZrVu"
                                    fill="#acb0b3" class="selected"></path>
                            </svg>

                        </div>
                        <p class="px-2 w-12 text-sm tracking-widest">{{ likeCount == 0 ? '点赞' : likeCount }}</p>
                    </div>
                    <div class="flex ml-8  justify-center items-center">
                        <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                            <svg t="1701929016232" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                xmlns="http://www.w3.org/2000/svg" p-id="6131" width="20" height="20">
                                <path d="M343.8 473.1m-50.3 0a50.3 50.3 0 1 0 100.6 0 50.3 50.3 0 1 0-100.6 0Z"
                                    fill="#e1e4e8" p-id="6132"
                                    data-spm-anchor-id="a313x.search_index.0.i26.42e13a811JZrVu" class="selected">
                                </path>
                                <path d="M520.8 473.1m-50.3 0a50.3 50.3 0 1 0 100.6 0 50.3 50.3 0 1 0-100.6 0Z"
                                    fill="#acb0b3" p-id="6133"
                                    data-spm-anchor-id="a313x.search_index.0.i25.42e13a811JZrVu" class="selected">
                                </path>
                                <path d="M697.9 473.1m-50.3 0a50.3 50.3 0 1 0 100.6 0 50.3 50.3 0 1 0-100.6 0Z"
                                    fill="#acb0b3" p-id="6134"
                                    data-spm-anchor-id="a313x.search_index.0.i24.42e13a811JZrVu" class="selected">
                                </path>
                                <path
                                    d="M138.4 1004.4l14.9-72.4c0.1-0.5 9.7-47.3 16.8-98.1 11.9-86 6.5-109 4.8-113.7-77.5-74-120.2-169.9-120.2-270.3 0-54.4 12.3-107.2 36.5-156.9 23.3-47.7 56.5-90.4 98.8-127.1 42-36.4 90.9-65 145.2-84.9 56.1-20.6 115.6-31 176.9-31 61.3 0 120.8 10.4 176.9 31 54.3 19.9 103.2 48.5 145.2 84.9 42.3 36.6 75.5 79.4 98.8 127.1 24.2 49.7 36.5 102.4 36.5 156.9S957.2 557.1 933 606.8c-23.3 47.7-56.5 90.4-98.8 127.1-42 36.4-90.9 65-145.2 84.9-56.1 20.6-115.6 31-176.9 31-49.7 0-98.6-6.9-145.3-20.6-6 1.8-29.1 11.5-95.6 63.4-40.6 31.7-76.6 63-77 63.3l-55.8 48.5zM512.1 100c-224.7 0-407.4 157-407.4 349.9 0 87.2 37.7 170.8 106 235.3 15.8 14.9 23.5 45.7 9.9 147.9-1.9 14-3.9 27.8-6 40.6 9.1-7.4 18.9-15.2 29-23 81.5-63.3 108.4-71.9 123.9-71.9 3.6 0 7 0.5 10.3 1.5 43.1 12.9 88.2 19.5 134.3 19.5 224.6 0 407.4-156.9 407.4-349.9C919.4 257 736.7 100 512.1 100z"
                                    fill="#acb0b3" p-id="6135"
                                    data-spm-anchor-id="a313x.search_index.0.i23.42e13a811JZrVu" class="selected">
                                </path>
                            </svg>

                        </div>
                        <p class="px-2 text-sm tracking-widest">{{ commentList.length == 0 ? '评论' : commentList.length
                            }}
                        </p>
                    </div>
                    <div class="flex ml-8  justify-end items-center">
                        <div class="flex justify-center items-center w-7 h-7 rounded-full hover:bg-base-300">
                            <svg v-if="collect" t="1701929744606" @click="CollectAndUnCollect" class="icon"
                                viewBox="0 0 1025 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9139"
                                width="18" height="18">
                                <path
                                    d="M1024.049737 393.508571a36.571429 36.571429 0 0 0-29.257143-24.868571l-311.588571-44.617143L544.232594 42.422857a37.302857 37.302857 0 0 0-64.365714 0l-138.971429 281.6L30.769737 365.714286a36.571429 36.571429 0 0 0-29.257143 24.868571 36.571429 36.571429 0 0 0 9.508572 36.571429l224.548571 219.428571-53.394286 311.588572a36.571429 36.571429 0 0 0 14.628572 35.108571 35.108571 35.108571 0 0 0 21.211428 6.582857 33.645714 33.645714 0 0 0 16.091429-4.388571l277.942857-146.285715 277.942857 146.285715a34.377143 34.377143 0 0 0 37.302857 0 36.571429 36.571429 0 0 0 14.628572-35.108572l-53.394286-309.394285 224.548572-219.428572a36.571429 36.571429 0 0 0 10.971428-38.034286z"
                                    fill="#ff713c" p-id="9140"></path>
                            </svg>

                            <svg v-else t="1701929182257" @click="CollectAndUnCollect" class="icon"
                                viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8295"
                                data-spm-anchor-id="a313x.search_index.0.i39.42e13a811JZrVu" width="20" height="20">
                                <path
                                    d="M281.53303 928.39895c-25.624986 0-50.501018-12.174029-66.544026-32.565043-14.54295-18.485965-19.864986-41.554022-14.602035-63.289958 3.644006-15.03703 35.325952-142.825984 50.326016-203.30496-46.522982-40.027955-143.173018-123.188019-147.206042-126.69399-37.387981-32.427008-41.397965-65.25696-38.167962-87.080038 5.347021-36.103987 30.872986-62.679962 66.617037-69.364019l2.381005-0.44503 214.680986-19.841024 78.381978-180.89001c6.044979-16.124006 14.564966-29.507994 25.342976-39.805952 16.134042-15.417037 36.653978-23.565005 59.34295-23.565005l0.67799 0.002048c23.29897 0.172032 45.211955 10.169958 61.714022 28.150989 10.638029 11.590963 16.735027 23.882035 19.664998 29.790003l0.752026 1.516954 76.652954 183.51104 216.03799 20.608 3.168973 0.706048c13.595034 3.030016 31.045018 8.171008 45.446042 21.139968 10.863002 9.784013 23.813018 27.567002 23.813018 56.727962 0 32.011981-17.926042 52.00599-31.015014 66.604954-1.735987 1.935974-3.569971 3.979981-5.480038 6.175027l-1.154048 1.325978-1.239962 1.246003c-1.007002 1.010995-1.055027 1.060045-143.350989 129.17801 14.619034 69.505024 41.413018 197.642957 43.625984 213.520998 3.145011 22.42304-2.69097 43.50505-16.440013 59.317965-10.422989 11.987046-30.005965 26.276045-64.108954 26.276045-8.816026 0-20.312986-1.517978-44.534989-12.530995l-2.336973-1.063014L512.531046 808.66601c-56.04096 32.335974-177.973965 102.542029-195.439002 111.326003C305.964032 925.577011 294.013952 928.39895 281.53303 928.39895zM196.490957 444.796006c36.530995 31.446016 105.230029 90.554982 146.699981 126.232986l24.365978 20.963021-7.740006 31.195955c-0.377037 1.521971-27.010048 108.873011-44.128051 178.034995 47.157043-26.827981 120.028979-68.775014 170.746982-98.092032l26.159002-15.120998 194.361037 112.572006c-10.519962-51.373978-26.318029-126.87401-37.578035-180.240998l-6.213018-29.449011 22.367027-20.139008c1.239962-1.115955 95.806976-86.260019 140.576051-126.571008l-226.801971-21.63497-87.554048-209.610957-91.506995 211.180954L196.490957 444.796006z"
                                    p-id="8296" data-spm-anchor-id="a313x.search_index.0.i38.42e13a811JZrVu"
                                    fill="#acb0b3" class="selected"></path>
                            </svg>
                        </div>
                        <p class="w-12 px-2 text-sm tracking-widest">{{ collectCount == 0 ? '收藏' : collectCount }}</p>
                    </div>
                </div>

            </div>
        </div>

        <!-- footer -->
        <div class="flex flex-col" ref="postComment">
            <!-- 你的回复 -->
            <div class="flex border-b border-base-300">
                <!-- 回复 -->
                <PostComment :postId="post.id" :replyUserId="post.userId" class=" pb-2" />
            </div>
            <!-- 评论信息 -->
            <div class=" text-xl  font-bold flex w-full h-auto pt-2 px-4">全部评论 </div>

            <div class="flex flex-1 flex-col ">
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
import OpenAI from "openai";
async function main() {
    const openai = new OpenAI({
        apiKey: "",
        dangerouslyAllowBrowser: true
    });

    const mp3 = await openai.audio.speech.create({
        model: "tts-1",
        voice: "shimmer",
        input: aitext.value,
    });

    const blob = new Blob([await mp3.arrayBuffer()], { type: 'audio/mp3' });
    const url = URL.createObjectURL(blob);
    const audio = new Audio(url);
    audio.play();
}

const route = useRoute();
const post = JSON.parse(route.query.post); // 获取 postId 参数
const showBoard = ref(false)
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
const aitext = ref('')
//点击ai建议
const handleAI = () => {
    showBoard.value = !showBoard.value
    if (showBoard) {
        const currentTimeMillis = Date.now();

        callModel('帮我总结一下下面的内容，用简短的语言，字数不超过400' + post.content, currentTimeMillis)
    }
    showTip.value = !showTip.value
}

const showTip = ref(false)
const appendLastMessageContent = (content) => {
    // let outcontent=content.replace(/\n/g, '<br>')
    aitext.value += content;

};
const callModel = async (message, userId) => {
    const res = await fetch(`https://www.oboard.eu.org/api/gpt?prompt=${message}&userId=${userId}`, {
        method: 'GET',
    });

    if (!res.body) return;
    const reader = res.body.pipeThrough(new TextDecoderStream()).getReader();
    while (true) {
        var { value, done } = await reader.read();
        if (done) {
            break
        }

        let outvalue = value?.replace('undefined', '') || '';
        appendLastMessageContent(outvalue);
    }
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

const commentList = ref([])
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