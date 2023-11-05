import httpRequest from "@/utils/index";
import type { UpdateUserData } from "./type";

export const getUserInfoAPI = () => {
  return httpRequest.get("/user/info");
};

export const UpdateUserInfoAPI = (user: UpdateUserData) => {
  return httpRequest.Put("/user/update", user);
};