<script setup>
const menu = [
  {
    name: "主页",
    path: "/home"
  },
  {
    name: "主页",
    path: "/home"
  },
  {
    name: "主页",
    path: "/home"
  },
  {
    name: "主页",
    path: "/home"
  },
]
</script>

<template>
  <div class="w-full min-h-full flex flex-row justify-center">

    <div class="w-full min-h-full flex flex-row justify-center max-w-7xl relative">
      <div class="md:w-64 w-12">
        <div class="md:w-64 w-12 bg-red-500 top-0 h-screen fixed flex flex-col px-4">
          
          <NuxtLink
            class="flex px-8 py-4 w-full rounded-full text-3xl hover:bg-black hover:bg-opacity-20 transition-all duration-500"
            v-for="item in menu" :key="item.name" :to="item.path">
            {{ item.name }}
          </NuxtLink>
        </div>
      </div>
      <div class="flex-1 bg-blue-500 h-[1920px]">
        <div class="w-full h-48 bg-white bg-opacity-40 backdrop-blur-sm top-0 sticky">
          111
          111
          111
        </div>
        55
        <NuxtPage></NuxtPage>
      </div>
      <div class="w-64 sm:flex hidden bg-green-500">
        111
      </div>
    </div>
  </div>
</template>
