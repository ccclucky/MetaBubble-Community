<script setup>
import { usePostStoreHook } from "~/stores/post";
import { useUserStoreHook } from "~/stores/user";

const usePostStore = usePostStoreHook()
const useUserStore = useUserStoreHook()
const activeTab = ref('login'); // 默认激活的选项卡是登录
const loginForm = ref('loginForm'); // 默认激活的选项卡是登录
const loginData = reactive({
    schoolId: "",
    password: ""
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
    <div class="flex flex-col w-full justify-center items-center h-max bg-base-200 p-4 border-b border-base-300">
        <span class="font-bold opacity-75">登陆后解锁更多精彩内容</span>
        <div class="flex flex-row pt-3 flex-1">
            <button class="btn btn-sm w-20 h-12 btn-circle mr-2 border border-base-300" @click="handleLogin">登录</button>
            <button class="btn btn-sm w-20 h-12 btn-circle ml-2 border border-base-300" @click="handleSignup">注册</button>
        </div>
    </div>
    <dialog ref="loginForm" class="modal">
        <div class="modal-box w-3/12 max-w-5xl">
            <form method="dialog">
                <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="resetForm">✕</button>
            </form>
            <div class="tabs flex justify-center items-center w-full pb-3">
                <a id="login" class="tab tab-lifted tab-lg text-xl font-bold"
                    :class="{ 'tab-active': activeTab === 'login' }" @click="setActiveTab('login')">登录</a>
                <a id="signup" class="tab tab-lifted tab-lg text-xl font-bold"
                    :class="{ 'tab-active': activeTab === 'signup' }" @click="setActiveTab('signup')">注册</a>
            </div>
            <!--登录页信息 -->
            <div class="flex flex-col justify-center items-center w-full p-3" v-if="activeTab === 'login'">
                <div class="flex justify-center items-center pb-3 w-full">
                    <input v-model="loginData.schoolId" type="text" placeholder="学号"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
                <div class="flex justify-center items-center w-full">
                    <input v-model="loginData.password" type="text" placeholder="密码"
                        class="input input-bordered input-primary w-full max-w-xs" />
                </div>
            </div>

            <!-- 注册信息页 -->
            <div class="flex flex-col w-full p-3" v-else>
                <div class="flex justify-center items-center pb-3 w-full">
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
                </div>
            </div>

            <div class="flex justify-center items-center">
                <button v-if="activeTab === 'login'" class="btn btn-wide btn-circle font-bold text-lg" @click="login">
                    <span>登录</span>
                </button>
                <button v-else class="btn btn-wide btn-circle font-bold text-lg">
                    <span>注册</span>
                </button>
            </div>
        </div>
    </dialog>
</template>