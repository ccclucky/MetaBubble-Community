<script setup>
import { usePostStoreHook } from "~/stores/post";
import { useUserStoreHook } from "~/stores/user";

const usePostStore = usePostStoreHook()
const useUserStore = useUserStoreHook()
const activeTab = ref('login'); // 默认激活的选项卡是登录
const loginForm = ref('loginForm'); // 默认激活的选项卡是登录
const loginData = reactive({
    schoolId: "2023433070102",
    password: "1234"
})
const signupData = reactive({
    schoolId: "",
    password: "",
    email: "",
    code: ""
})

const resetForm = () => {
    loginData.schoolId = "",
        loginData.password = "",
        signupData.schoolId = "",
        signupData.password = "",
        signupData.email = "",
        signupData.code = ""
}

const handleLogin = () => {
    const dialog = loginForm.value
    activeTab.value = 'login'
    dialog.showModal()
}

const handleSignup = () => {
    const dialog = loginForm.value
    activeTab.value = 'signup'
    dialog.showModal()
}

const setActiveTab = (tab) => {
    activeTab.value = tab; // 设置当前激活的选项卡
}

const login = async () => {
    await useUserStore.login(
        loginData.schoolId, loginData.password
    ).then((res) => {
        useUserStore.getUserInfo()
        usePostStore.getList()
    }).catch((err) => {

    });
    location.reload()
}

const signup = async (signupData) => {
    console.log(signupData);
}
</script>

<template>
    <div class="flex flex-col w-full justify-center items-center h-max bg-base-100  border-b border-base-300 relative">

        <div class=" bg-black ">
            <img class=" w-full opacity-80" src="../../public/indexbg.svg">

        </div>
        <div class="nologin-text absolute text-white top-28 flex justify-center flex-wrap">
            <p style="letter-spacing: 6px; font-weight: 700;" class="text-5xl mb-5 ">Metabubble在线社区</p>
            <span style="letter-spacing: 7px;" class="font-bold opacity-75">登陆后解锁更多精彩内容</span>
        </div>
        <div class="  top-72 absolute flex flex-row w-full justify-center pt-3 flex-1">
            <!-- <button style="letter-spacing: 0.1em;" class="text-white   btn btn-sm w-20 h-12 btn-primary rounded-3xl mr-2 border border-base-100" @click="handleLogin">登 录</button> -->
            <button @click="handleLogin"
                class="mr-14 hover:brightness-110 hover:animate-pulse font-bold py-3 px-6 rounded-full bg-gradient-to-r from-blue-500 to-pink-500 text-white">登录</button>
            <button
                class="ml-14 cursor-pointer font-semibold overflow-hidden relative z-100 border border-green-500 group px-8 py-2 rounded-md">
                <span class="relative z-10 text-green-500 group-hover:text-white text-xl duration-500">关于我们</span>
                <span
                    class="absolute w-full h-full bg-green-500 -left-32 top-0 -rotate-45 group-hover:rotate-0 group-hover:left-0 duration-500"></span>
                <span
                    class="absolute w-full h-full bg-green-500 -right-32 top-0 -rotate-45 group-hover:rotate-0 group-hover:right-0 duration-500"></span>
            </button>
            <!-- <button style="color: #2b44e7;border-color: #2b44e7;background-color: #fff; letter-spacing: 0.1em;" class=" btn btn-sm w-20 h-12 rounded-3xl ml-2 border " @click="handleSignup">注 册</button> -->
        </div>
    </div>
    <dialog ref="loginForm" class="modal">
        <div style="background-color: #0d1b2a;" class="modal-box  max-w-xl">
            <form method="dialog">
                <button style="border-color: aliceblue; color: #fff;"
                    class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="resetForm">✕</button>
            </form>
            <div class="tabs flex justify-center items-center w-full pb-3">
                <button id="login" class="btn  tab tab-lifted tab-lg text-xl font-bold"
                    :class="{ 'tab-active': activeTab === 'login' }" @click="setActiveTab('login')"
                    v-if="activeTab === 'login'">登录</button>


                <button id="login" class=" tab text-white tab-lifted tab-lg text-xl font-bold"
                    :class="{ 'tab-active': activeTab === 'login' }" @click="setActiveTab('login')" v-else>登录</button>


                <button id="signup" class="tab  text-black  tab-lifted tab-lg text-xl font-bold"
                    :class="{ 'tab-active': activeTab === 'signup' }" @click="setActiveTab('signup')"
                    v-if="activeTab === 'signup'">注册</button>


                <button id="signup" class="tab  text-white  tab-lifted tab-lg text-xl font-bold"
                    :class="{ 'tab-active': activeTab === 'signup' }" @click="setActiveTab('signup')" v-else>注册</button>
            </div>
            <!--登录页信息 -->
            <div class="flex flex-col justify-center items-center w-full p-3" v-if="activeTab === 'login'">
                <!-- <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="loginData.schoolId" type="text" placeholder="学号"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center w-full">
                    <input v-model="loginData.password" type="text" placeholder="密码"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div> -->


                <div class=" w-full mx-auto bg-[#0d1b2a] rounded-lg shadow-md p-4">
                    <form>
                        <div class="mb-4">
                            <label class="block mb-2 text-gray-50" for="name"> 学号 </label>
                            <input v-model="loginData.schoolId" placeholder="请输入你的学号"
                                class="text-white w-full p-2 border-b-2 border-yellow-400 bg-transparent outline-none focus:border-b-2 focus:border-yellow-600"
                                type="text" />
                        </div>
                        <div class="mb-4">
                            <label class="block mb-2 text-gray-50" for="email"> 密码 </label>
                            <input v-model="loginData.password" placeholder="请输入你的密码"
                                class="w-full text-white p-2 border-b-2 border-yellow-400 bg-transparent outline-none focus:border-b-2 focus:border-yellow-600"
                                name="email" id="email" type="email" />
                        </div>

                        <div class="mb-4">

                        </div>
                    </form>
                </div>




            </div>

            <!-- 注册信息页 -->
            <div class="flex flex-col w-full p-3" v-else>
                <!-- <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="signupData.schoolId" type="text" placeholder="学号"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="signupData.password" type="text" placeholder="密码"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="signupData.email" type="text" placeholder="邮箱"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center w-full flex-row">
                    <div class="flex w-full max-w-xs h-12">
                        <div class=" flex justify-start">
                            <input type="text" placeholder="验证码" v-model="signupData.code"
                                class="input input-bordered input-primary max-w-xs w-11/12" />
                        </div>
                        <div class=" flex justify-center">
                            <button class="btn text-lg">发送</button>
                        </div>
                    </div>
                </div>
                <div class="flex justify-start items-center w-full">
                </div> -->


                <div class=" w-full mx-auto bg-[#0d1b2a] rounded-lg shadow-md p-4">
                    <form>
                        <div class="mb-4">
                            <label class="block mb-2 text-gray-50" for="name"> 学号 </label>
                            <input v-model="signupData.schoolId" placeholder="请输入学号"
                                class="w-full p-2 border-b-2 border-yellow-400 bg-transparent outline-none focus:border-b-2 focus:border-yellow-600"
                                type="text" />
                        </div>
                        <div class="mb-4">
                            <label class="block mb-2 text-gray-50" for="email"> 密码 </label>
                            <input v-model="signupData.password" placeholder="设置密码"
                                class="w-full p-2 border-b-2 border-yellow-400 bg-transparent outline-none focus:border-b-2 focus:border-yellow-600"
                                name="email" id="email" type="email" />
                        </div>
                        <div class="mb-4">
                            <label class="block mb-2 text-gray-50" for="email"> 邮箱 </label>
                            <input v-model="signupData.email" placeholder="请输入邮箱"
                                class="w-full p-2 border-b-2 border-yellow-400 bg-transparent outline-none focus:border-b-2 focus:border-yellow-600"
                                name="email" id="email" type="email" />
                        </div>
                        <div class="mb-4 flex ">
                            <div class="flex-1">
                                <label class="block mb-2 text-gray-50" for="message"> 验证码 </label>
                                <input v-model="signupData.code" placeholder="请输入验证码"
                                    class="w-full p-2 border-b-2 border-yellow-400 bg-transparent outline-none focus:border-b-2 focus:border-yellow-600"
                                    rows="4" name="message" id="message" />
                            </div>
                            <div class=" flex justify-center items-end ml-20">
                                <button style="width: 100px;" class=" btn btn-secondary text-lg">发送</button>
                            </div>
                        </div>
                        <div class="mb-4">
                            <!-- <button
                                class="w-full bg-[#F7AB0A] text-gray-700 font-semibold p-2 rounded transition-all hover:bg-[#FFC857]"
                                type="submit">
                                注册
                            </button> -->
                        </div>
                    </form>
                </div>
            </div>

            <div class="flex justify-center items-center">
                <button v-if="activeTab === 'login'"
                    class="bg-[#F7AB0A] p-2 rounded transition-all hover:bg-[#FFC857] btn btn-wide btn-circle font-bold text-lg"
                    @click="login">
                    <span>登录</span>
                </button>
                <button v-else
                    class="bg-[#F7AB0A] p-2 rounded transition-all hover:bg-[#FFC857] btn btn-wide btn-circle font-bold text-lg">
                    <span>注册</span>
                </button>
            </div>
        </div>
    </dialog>
</template>