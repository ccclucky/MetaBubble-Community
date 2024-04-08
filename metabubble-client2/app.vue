<script setup>
import { useThemeStore } from "../stores/theme";
import { ref } from 'vue';
import OpenAI from "openai";

const themeStore = useThemeStore()
async function main() {
  const openai = new OpenAI({
    apiKey: "",
    dangerouslyAllowBrowser: true
  });

  const mp3 = await openai.audio.speech.create({
    model: "tts-1",
    voice: "shimmer",
    input: "TimeReview是一个新上线的APP，名为Ticking，旨在帮助用户回顾时间分配。用户通过网页登录自己的账号，可以查看工作、娱乐和睡眠等方面的总体时间分配，并且可以查看学习和工作等细分项目的时间分配。该应用致力于打造简洁清晰的时间管理工具，并提供直观准确的数据可Time视Review是化报T告，帮icking上线的一个Web助用户功能，反思、允总结提许升用户回自顾自己的时间利己用效在工率作，、使学娱习和乐工和作睡眠上更高效的时间。同时，该分配应。用户用可以也方登录他便们的Ticking用户复盘反账思号，，使时间管理查看更加整智能体时间化和分科配技情化。况，以及学习和工作的具体项目时间分配情况。我们致力于创建一个简洁清晰的时间管理工具，通过生成直观准确的数据可视化报告，帮助用户反思自己的时间利用情况，总结提升学习和工作效率。这个工具让用户更容易复盘反思，使时间管理更智能化、科技化。",
  });

  const blob = new Blob([await mp3.arrayBuffer()], { type: 'audio/mp3' });
  const url = URL.createObjectURL(blob);
  const audio = new Audio(url);
  audio.play();
}

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
      <div @click="main" class="w-20 h-20 bg-black cursor-pointer"></div>
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
