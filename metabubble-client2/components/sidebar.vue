<template>
  <div class="flex items-center flex-row w-26 md:w-64">
    <div style="border-right: 4px solid rgba(235, 235, 235, 0.18);
" class="rounded-l-xl md:w-64 w-24 bg-base-200 top-0 h-screen fixed flex flex-col px-4  border-base-300">
      <!-- logo -->
      <NuxtLink class="flex justify-center w-full h-32 mb-2" to="/">
        <Icon name="fluent-emoji:bubbles" class=" w-32 h-32" />
      </NuxtLink>
      <!-- 菜单路由 
        class="flex p-4 md:px-8 md:py-5 w-full rounded-full text-3xl hover:bg-black hover:bg-opacity-20 transition-all duration-500"-->
      <NuxtLink class="btn btn-ghost justify-start text-xl font-normal items-center rounded-full py-4 h-auto"
        v-for="item in menu" :key="item.name" :to="item.path">
        <Icon :name="item.icon" class=" w-6 h-6" />
        <span style="letter-spacing: 0.3em;" v-if="(item.path === ('/' + useCenter.currentPath.split('/')[1]))"
          class=" ml-3 tracking-wide px-3 hidden md:block font-bold">{{
          item.name }}</span>
        <span style="letter-spacing: 0.3em;" v-else class="ml-3 px-3 hidden md:block tracking-wider">{{ item.name
          }}</span>
      </NuxtLink>

      <!-- 发帖 -->
      <NuxtLink
        class="btn text-white btn-primary justify-center text-2xl font-normal items-center rounded-full py-4 mt-3 h-auto "
        onclick="postDialog.showModal()" style="letter-spacing: 0.4em;">
        发帖
      </NuxtLink>
      <!-- <button class="btn " onclick="my_modal_3.showModal()">发帖</button> -->


      <!-- 登录登出 -->
      <div @click="handleLogout"
        class="mt-auto pr-3 py-5 w-full h-auto rounded-full text-3xl hover:bg-black hover:bg-opacity-20 transition-all duration-500">
        <div class="flex flex-row w-full justify-center py-auto">
          <!-- 头像 -->
          <div class="w-12 h-12 bg-gray-300 rounded-full">
            <img class="rounded-full w-full h-full block m-auto" :src="userInfo.avatar">
          </div>
          <!-- 信息 -->
          <div class="md:w-28 w-24 pl-3 hidden md:block">
            <span class="flex text-ellipsis	font-semibold text-base">
              {{ userInfo.username }}
              <!-- <el-popconfirm title="确定退出？" confirm-button-text="是" cancel-button-text="否">
                <template #reference>
                  <div
                    class="flex rounded-full w-6 h-6 m-auto pr-1 hover:bg-black hover:bg-opacity-20 transition-all duration-500 cursor-pointer"
                    type="button">
                    <Icon name="material-symbols:logout" class="rounded-full w-4 h-4 block ml-auto mt-1" />
                  </div>
                </template>
</el-popconfirm> -->
              <div
                class="flex rounded-full w-6 h-6 m-auto pr-1 hover:bg-black cursor-pointer hover:bg-opacity-20 transition-all duration-500"
                type="button">
                <Icon name="material-symbols:logout" class="rounded-full w-4 h-4 block ml-auto mt-1" />
              </div>
              <!-- <button>
                <Icon name="material-symbols:logout" class="rounded-full w-4 h-4 block ml-auto mt-1" />

              </button> -->
            </span>
            <span class="flex text-ellipsis font-light text-base">{{ userInfo.email }}</span>
          </div>
        </div>
      </div>

      <!-- 退出登录 -->
      <dialog ref="logoutForm" class="modal">
        <div class="modal-box">
          <h3 class="font-bold text-lg text-center">退出登录</h3>
          <div class="modal-action flex flex-row w-full justify-center">
            <div class="flex w-1/2 justify-center">
              <button class="btn text-base font-bold" @click="logout">确定</button>
            </div>
            <div class="flex w-1/2 justify-center">
              <form method="dialog" class="">
                <button class="btn text-base font-bold">取消</button>
              </form>
            </div>
          </div>
        </div>
      </dialog>
    </div>
  </div>
</template>

<script setup>
import { useSidebarStore } from '../stores/sidebar'
import { useCenterStore } from '../stores/center'
import { useUserStoreHook } from '~/stores/user'
import { storeToRefs } from 'pinia';

const route = useRoute()
const useSidebar = useSidebarStore()
const useCenter = useCenterStore()
const useUserStore = useUserStoreHook()
const router = useRouter()

const { menu } = storeToRefs(useSidebar)

const logoutForm = ref('logoutForm');
const handleLogout = async () => {
  logoutForm.value.showModal()
}

console.log(useUserStore.isLogin);
// 退出登录逻辑
const logout = () => {
  useUserStore.logout().then((result) => {
    logoutForm.value.close()
    router.push({ path: "/" })
    location.reload();
  }).catch((err) => {
    console.log("dsadsadsadsadsadsadsadas", err);
  });
}

// 获取当前用户信息
const { userInfo } = storeToRefs(useUserStore)
if (userInfo.value == undefined) {
  userInfo.value = {
    username: "游客",
    avatar: "../punlic/sum.png",
    // avatar: "",
    email: "tourists@qq.com"
  }
}

</script>