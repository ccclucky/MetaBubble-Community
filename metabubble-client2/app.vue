<script setup></script>

<template>
  <div class="w-full min-h-full flex flex-row justify-center main">
    <div
      class="rounded-xl contain md:8/12 w-full min-h-full flex flex-row justify-center max-w-7xl relative">
      <!-- 侧边栏 -->
      <sidebar />

      <dialog
        id="postDialog"
        class="modal flex items-start justify-center w-full h-full top-24">
        <div class="flex w-1/3 h-1/3">
          <postDialog />
        </div>
      </dialog>

      <!-- 中间栏 -->
      <center />

      <!-- 右边栏 -->
      <right />
    </div>
  </div>
</template>
<style>
.contain {

  /* backdrop-filter: blur(3px); */
   
}
main{
  background: #323232!important;
  
}
</style>
