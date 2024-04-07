<template>
  <div class="flex flex-col w-full h-max bg-base-200 p-3 rounded-lg">
    <!-- close -->
    <div class="flex justify-end">
      <form method="dialog">
        <button class="btn btn-circle flex w-12 h-12 hover:bg-base-300 transition-all duration-500">
          <Icon name="mdi:close" class="w-6 h-6" />
        </button>
      </form>
    </div>
    <!-- 输入框和发布按钮 -->
    <form method="dialog">
      <PostInput />
    </form>
  </div>
</template>
  