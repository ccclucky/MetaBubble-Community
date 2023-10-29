// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
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
    '@pinia-plugin-persistedstate/nuxt'
  ],
  vite: {
    server: {
      proxy: {
        '/api': {
          target: 'http://localhost:8080',  //这里是接口地址
          changeOrigin: true
        }
      },
    }
  },
  elementPlus: {
    /** Options */
  },
});
