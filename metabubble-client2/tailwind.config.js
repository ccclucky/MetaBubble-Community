/** @type {import('tailwindcss').Config} */
// import { useThemeStore } from "../stores/theme";
// const themeStore = useThemeStore()
// themeStore.changetheme(1)
const themeone = {
  "color-scheme": "light",
  "primary": "#2b44e7",
  "secondary": "#f287a4",
  "accent": "#fffbbf",
  "neutral": "#26243d",
  "info": "#75a8f0",
  "success": "#13aa82",
  "warning": "#fab938",
  "error": "#f24421",
  "base-200": "#cacafa",
  "base-100": "#d4d4f7",
  "base-300": "#e8e8ff",
  "--rounded-box": "0.2rem",
  "--rounded-btn": "0.2rem",
  "--rounded-badge": "0.2rem",
  "--tab-radius": "0.2rem",
}
const themetwo = {
  "color-scheme": "light",
  "primary": "#1F3A5F",
  "secondary": "#f287a4",
  "accent": "#3D5A80",
  "neutral": "#26243d",
  "info": "#75a8f0",
  "success": "#13aa82",
  "warning": "#fab938",
  "error": "#f24421",
  "base-200": "#1f2b3e",
  "base-100": "#1f2b3e",
  "base-300": "#374357",

  "--rounded-box": "0.2rem",
  "--rounded-btn": "0.2rem",
  "--rounded-badge": "0.2rem",
  "--tab-radius": "0.2rem",
}

const themethree = {
  "color-scheme": "light",
  "primary": "#8B5FBF",
  "secondary": "#f287a4",
  "accent": "#D6C6E1",
  "neutral": "#26243d",
  "info": "#75a8f0",
  "success": "#13aa82",
  "warning": "#fab938",
  "error": "#f24421",
  "base-200": "#E9E4ED",
  "base-100": "#E9E4ED",
  "base-300": "#D6C6E1",

  "--rounded-box": "0.2rem",
  "--rounded-btn": "0.2rem",
  "--rounded-badge": "0.2rem",
  "--tab-radius": "0.2rem",
}
module.exports = {
  content: [
    "./components/**/*.{js,vue,ts}",
    "./layouts/**/*.vue",
    "./pages/**/*.vue",
    "./plugins/**/*.{js,ts}",
    "./nuxt.config.{js,ts}",
    "./app.vue",
  ],
  theme: {
    extend: {},
  },
  daisyui: {
    themes: [{
      base: themeone
    }, { dark: themetwo }, { retro: themethree }, "black", "light"],
  },
  plugins: [require("@tailwindcss/typography"), require("daisyui")],
}