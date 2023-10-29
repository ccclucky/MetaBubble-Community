import { useRoute } from 'vue-router'

export const useCenterStore = defineStore('center', () => {
    const route = useRoute()

    const pathTitle = [
        {
            name: "广场",
            path: "/",
            title: "泡泡广场",
        },
        {
            name: "关注",
            path: "/focus",
            title: "我的关注",
        },
        {
            name: "通知",
            path: "/notify",
            title: "通知",
        },
        {
            name: "个人",
            path: "/home",
            title: "个人主页",
        },
        {
            name: "收藏",
            path: "/collect",
            title: "我的收藏",
        },
        {
            name: "帖子",
            path: "/post/detail",
            title: "帖子",
        }
    ]

    const currentPath = ref(route.path)

    const getCurrentItemTitle = () => {
        currentPath.value = route.path
        const currentItem =  pathTitle.find(item => item.path === currentPath.value)
        return currentItem ? currentItem.title : ''
    }
    return {
        currentPath,
        pathTitle,
        getCurrentItemTitle
    }
})