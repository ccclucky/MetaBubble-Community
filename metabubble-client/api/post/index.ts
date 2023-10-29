import httpRequest from "@/utils/index";
import type { PostData } from "./type";

export const getListAPI = () => {
  return httpRequest.get("/post/list");
};

export const PostAPI = (post: PostData) => {
  return httpRequest.post("/post/add", post);
};
