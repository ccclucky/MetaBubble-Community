import { allFansAPI, allFollowAPI, followAPI } from "~/api/follow";
import store from "..";
import type { UserData } from "~/api/user/type";
import { useUserStore, useUserStoreHook } from "../user";

export const useFollowStore = defineStore("follow", () => {
  // 关注用户
  const follow = async (followId: number) => {
    await followAPI(followId);
    const useUserStore = useUserStoreHook()
    getAllFollow(useUserStore.userInfo!.id)
  };

  // 所有关注
  const getAllFollow = async (userId: number) : Promise<any> => {
    const res: any = await allFollowAPI(userId);
    return res.data
  }

  // 所有粉丝
  const getAllFans = async (userId: number) : Promise<any> => {
    const res: any = await allFansAPI(userId);
    return res.data
  }

  return {
    follow,
    getAllFollow,
    getAllFans
  };
});

export function useFollowStoreHook() {
  return useFollowStore(store);
}
