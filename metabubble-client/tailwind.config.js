/** @type {import('tailwindcss').Config} */
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
      "wireframe": {
        "color-scheme": "light",
        "primary": "#e89dc7",
        "secondary": "#f287a4",
        "accent": "#fffbbf",
        "neutral": "#26243d",
        "info": "#75a8f0",
        "success": "#13aa82",
        "warning": "#fab938",
        "error": "#f24421",
        "base-100": "#e7e7f4",
        "base-200": "#c4c4e4",
        "base-300": "#a1a1d4",
        "--rounded-box": "0.2rem",
        "--rounded-btn": "0.2rem",
        "--rounded-badge": "0.2rem",
        "--tab-radius": "0.2rem",
      }
    }, "black"],
  },
  plugins: [require("@tailwindcss/typography"), require("daisyui")],
}