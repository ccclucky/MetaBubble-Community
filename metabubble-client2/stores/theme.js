export const useThemeStore = defineStore('theme', () => {
    const light = ref(true)
    const nowTheme = ref('base')
    const changeTheme = () => {
        let html = document.querySelector("html");
        const theme = html.getAttribute("data-theme") == "black" ? "wireframe" : "black";
        html.setAttribute("data-theme", theme);
        light.value = !light.value;
    }
    // const changetheme = (themeNum) => {
    //     selecttheme.value = themeNum
    // 
    const changetheme = (themeName) => {
        nowTheme.value = themeName.value
        console.log(themeName.value)
    }
    return {
        light,
        nowTheme,
        changeTheme,
        changetheme,
    }
})