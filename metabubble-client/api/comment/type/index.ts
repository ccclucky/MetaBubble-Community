export interface CommentData {
  id: number;
  userId: number;
  username: string;
  avatar: string;
  createTime: string;
  replies: ReplyData[];
}

export interface ReplyData {
  id: number;
  userId: number;
  username: string;
  replyUsername: string;
  avatar: string;
  createTime: string;
}

export interface CreateCommentData {
  postId: number;
  parentId?: number;
  replyUserId: number;
  content: string;
}
