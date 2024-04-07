export interface PostDTOData {
  id: number;
  userId: number;
  username: string;
  avatar: string;
  content: string;
  likeCount: number;
  collect: boolean;
  commentCount: number;
  like: boolean;
  createTime: string;
}

export interface PostData {
  userId: number;
  content: string;
}
