// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  ssr: false,
  // 其他配置...
  target: "server", // 如果你想生成静态站点，也可以使用 'static'
  css: ["~/assets/css/main.css"],
  postcss: {
    plugins: {
      tailwindcss: {},
      autoprefixer: {},
    },
  },
  modules: [
    "nuxt-icon",
    "@element-plus/nuxt",
    [
      "@pinia/nuxt",
      {
        autoImports: [
          // 自动引入 `defineStore()`
          "defineStore",
          // 自动引入 `defineStore()` 并重命名为 `definePiniaStore()`
          ["defineStore", "definePiniaStore"],
        ],
      },
    ],
    "@pinia-plugin-persistedstate/nuxt",
  ],
  vite: {
    server: {
      proxy: {
        "/api": {
          target: "http://43.139.169.144:9999", //这里是接口地址
          changeOrigin: true,
        },
      },
    },
  },
  elementPlus: {
    /** Options */
  },
});
