<script setup>
import { useThemeStore } from "../stores/theme";
import { ref } from 'vue';
 
const themeStore = useThemeStore()

const container = ref(null);
let startX, startY, startWidth, startHeight;

function startResize(event) {
  event.preventDefault();
  startX = event.clientX;
  startY = event.clientY;
  startWidth = parseInt(document.defaultView.getComputedStyle(container.value).width, 10);
  startHeight = parseInt(document.defaultView.getComputedStyle(container.value).height, 10);
  document.body.style.cursor = 'col-resize'; // 设置鼠标样式为grabbing
  document.addEventListener('mousemove', resize);
  document.addEventListener('mouseup', stopResize);
}

function resize(event) {
  const deltaX = event.clientX - startX;
  // const deltaY = event.clientY - startY;
  container.value.style.width = startWidth + deltaX + 'px';
  // container.value.style.height = startHeight + deltaY + 'px';
}

function stopResize() {
  document.removeEventListener('mousemove', resize);
  document.removeEventListener('mouseup', stopResize);
  document.body.style.cursor = 'default'; // 设置鼠标样式为grabbing
}
</script>

<template>
  <div class="w-full min-h-full flex flex-row justify-center main">
    <div ref="container" @mousedown="startResize" :data-theme='themeStore.nowTheme'
      class="rounded-xl contain md:8/12 w-2/3 min-h-full flex flex-row justify-center min-w-7xl relative resizable-container">
      <!-- 侧边栏 -->
      <sidebar />

      <dialog id="postDialog" class="modal flex items-start justify-center w-full h-full top-24">
        <div class="flex w-1/3 h-1/3">
          <postDialog />
        </div>
      </dialog>

      <!-- 中间栏 -->
      <center />

      <!-- 右边栏 -->
      <right />
      <Live2d />
    </div>
  </div>
</template>

<style>
.contain {

  /* backdrop-filter: blur(3px); */

}

main {
  background: #323232 !important;

}

.resizable-container {
  resize: horizontal;
  /* 允许水平和垂直调整大小 */
  overflow: auto;
  /* 当内容超出容器大小时显示滚动条 */
  /* 添加边框样式，以便更清晰地看到容器 */
}

main {
  background: #323232 !important;

}
</style>
