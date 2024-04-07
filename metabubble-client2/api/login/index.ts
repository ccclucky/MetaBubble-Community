import httpRequest from "@/utils/index";
import type { LoginInfo } from "./type";

export const loginAPI = (loginInfo: LoginInfo) => {
  return httpRequest.post("/login", loginInfo);
};

export const logoutAPI = () => {
  return httpRequest.post("/user/logout");
};
