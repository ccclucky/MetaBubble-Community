import httpRequest from "@/utils/index";
import type { UpdateUserData } from "./type";

export const getUserInfoAPI = () => {
  return httpRequest.get("/user/info");
};

export const getUserInfoByIdAPI = (userId: number) => {
  return httpRequest.get(`/user/info/${userId}`);
};

export const UpdateUserInfoAPI = (user: UpdateUserData) => {
  return httpRequest.Put("/user/update", user);
};