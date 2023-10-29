export interface PostVoData {
  id: number;
  username: string;
  avatar?: string;
  content: string;
  createTime?: string;
}

export interface PostData {
  userId: number;
  content: string;
}
