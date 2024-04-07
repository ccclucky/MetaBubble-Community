export interface CommentData {
  id: number;
  userId: number;
  username: string;
  avatar: string;
  postId: number;
  parentId: number;
  createTime: string;
  like: boolean;
  likeCount: number;
  replies: ReplyData[];
}

export interface ReplyData {
  id: number;
  userId: number;
  username: string;
  replyUsername: string;
  avatar: string;
  createTime: string;
  like: boolean;
  likeCount: number;
}

export interface CreateCommentData {
  postId: number;
  parentId?: number;
  replyUserId: number;
  content: string;
}
