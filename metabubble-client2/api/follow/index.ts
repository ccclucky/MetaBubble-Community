import httpRequest from "@/utils/index";

export const followAPI = (followId: number) => {
  return httpRequest.post(`/follow/${followId}`, followId);
};

export const allFollowAPI = (userId: number) => {
  return httpRequest.get(`/follow/allFollow/${userId}`);
};

export const allFansAPI = (userId: number) => {
  return httpRequest.get(`/follow/allFans/${userId}`);
};