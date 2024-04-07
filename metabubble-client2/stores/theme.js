export const useThemeStore = defineStore('theme', () => {
    const light = ref(true)

    const changeTheme = () => {
        let html = document.querySelector("html");
        const theme = html.getAttribute("data-theme") == "black" ? "wireframe" : "black";
        html.setAttribute("data-theme", theme);

        light.value = !light.value;
    }
    return {
        light,
        changeTheme
    }
})