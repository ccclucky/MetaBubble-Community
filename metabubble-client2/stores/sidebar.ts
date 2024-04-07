import { getUserInfoAPI } from '~/api/user';
import { useUserStore } from './user/index';
import { useRoute } from 'vue-router'
import { useUserStoreHook } from '~/stores/user'

export const useSidebarStore = defineStore('sidebar', () => {
    const route = useRoute()
    const menuList = [
        {
            name: "关注",
            path: "/focus",
            title: "我的关注",
            icon: "material-symbols:center-focus-strong"
        },
        {
            name: "通知",
            path: "/notify",
            title: "通知",
            icon: "ic:round-notifications"
        },
        {
            name: "个人",
            path: "/home",
            title: "个人主页",
            icon: "material-symbols:house-rounded"
        },
        {
            name: "收藏",
            path: "/collect",
            title: "我的收藏",
            icon: "icon-park-solid:file-collection"
        }
    ]
    
    const menu = ref([
        {
            name: "广场",
            path: "/",
            title: "泡泡广场",
            icon: "material-symbols:edit-square"
        }
    ])

    const cookie = useCookie('token')
    if (cookie.value !== undefined) {
        menuList.forEach(element => {
            menu.value.push(element)
        });

    }

    const currentPath = ref(route.path)

    const getCurrentItemTitle = () => {
        currentPath.value = route.path
        const currentItem = menuList.find(item => item.path === currentPath.value)
        return currentItem ? currentItem.title : ''
    }
    return {
        menu,
        getCurrentItemTitle
    }
})