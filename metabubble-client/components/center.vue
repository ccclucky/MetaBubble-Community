<template>
  <div class="flex-1 bg-base-200 min-h-[1920px]">
    <div class="md:w-full w-auto h-auto flex bg-base-200 bg-opacity-40 backdrop-blur-sm top-0 sticky border-b border-base-300">
      <span class="flex font-sans m-full h-16 px-8 py-4 text-2xl flex-1">{{ useCenter.getCurrentItemTitle() }}</span>
      <div class="flex btn btn-circle" @click="themeStore.changeTheme">
        <Icon v-if="themeStore.light" name="ri:moon-fill" class=" w-8 h-8" />
        <Icon v-else name="material-symbols:wb-sunny" class=" w-8 h-8" />
      </div>
    </div>
    <no-ssr placeholder="Loading...">
      <LoginView v-if="cookie === undefined" />
      <postInput v-if="useCenter.getCurrentItemTitle() == '泡泡广场' && cookie !== undefined"
        class="flex border-b border-base-300 backdrop-blur-sm top-0" />
    </no-ssr>
    <NuxtPage></NuxtPage>
  </div>
</template>

<script setup>
import { useThemeStore } from "../stores/theme";
import { useCenterStore } from "../stores/center";

const useCenter = useCenterStore()

const themeStore = useThemeStore()

const cookie = useCookie('token')
</script>

