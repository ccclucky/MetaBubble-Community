import { UpdateUserInfoAPI, getUserInfoAPI, getUserInfoByIdAPI } from "~/api/user";
import type { UpdateUserData, UserData } from "~/api/user/type";
import { loginAPI, logoutAPI } from "@/api/login/index";
import store from "..";

export const useUserStore = defineStore("user", () => {
  // 用户登录
  const login = async (schoolId: string, password: string) => {
    const cookie = useCookie("token");
    const res: any = await loginAPI({
      schoolId: schoolId,
      password: password,
    });
    cookie.value = res.data.token;
    isLogin.value = true
  };

  // 获取登录用户信息
  const userInfo = ref<UserData>();
  const getUserInfo = async () => {
    const res: any = await getUserInfoAPI();
    userInfo.value = res.data;
  };

  // 根据id获取登录用户信息
  const getUserInfoById = async (userId: number): Promise<any> => {
    const res: any = await getUserInfoByIdAPI(userId);
    return res.data
  };

  // 用户退出登录
  const logout = async () => {
    const cookie = useCookie("token");
    userInfo.value = undefined;
    await logoutAPI();
    cookie.value  = null
    isLogin.value = false
  };
  
  const updateInfo = async (user: UpdateUserData) => {
    await UpdateUserInfoAPI(user);
    getUserInfo()
  };

  const isLogin = ref(false)

  return {
    userInfo,
    isLogin,
    login,
    logout,
    getUserInfo,
    updateInfo,
    getUserInfoById
  };
});

export function useUserStoreHook() {
  return useUserStore(store);
}
