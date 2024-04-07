<template>
  <div class=" flex-1 bg-base-100 min-h-screen">
    <div style="border-bottom: 3px solid rgba(172, 171, 171, 0.18);" class=" bg-base-100 z-40 md:w-full w-auto h-auto flex bg-opacity-70 backdrop-blur-sm top-0 flex-wrap sticky border-b">
      <div class="w-full flex flex-nowrap">
      <span class="flex font-sans  h-16 px-8 pt-4  text-2xl flex-1 font-bold tracking-wider">{{ useCenter.getCurrentItemTitle() }}</span>
      
      <div class="flex btn btn-circle" @click="themeStore.changeTheme">
        <Icon v-if="themeStore.light" name="ri:moon-fill" class=" w-8 h-8" />
        <Icon v-else name="material-symbols:wb-sunny" class=" w-8 h-8" />
      </div>
    </div>
    <p style="color: #6c7172;" class=" text-sm  px-10 mb-2  tracking-wide">{{ useCenter.getCurrentItemdescription() }}</p>

    </div>
    <no-ssr placeholder="Loading...">
      <LoginView v-if="cookie === undefined" />
      <!-- <postInput v-if="useCenter.getCurrentItemTitle() == '泡泡广场' && cookie !== undefined"
        class="flex border-b border-base-100 backdrop-blur-sm top-0" /> -->
    </no-ssr>
    <!-- <div style="background-color: #f2f3f58d;"> -->
    <!-- <NuxtPage></NuxtPage></div> -->
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