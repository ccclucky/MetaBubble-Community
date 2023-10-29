import httpRequest from "@/utils/index";

export const getUserInfoAPI = () => {
  return httpRequest.get("/user/info");
};