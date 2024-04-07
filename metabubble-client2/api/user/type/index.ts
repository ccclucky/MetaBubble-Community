export interface UserData {
  id: number
  schoolId: string;
  username: string;
  email: string;
  phone: string;
  avatar: string;
  roleName: string[];
  createTime: string;
}

export interface UpdateUserData {
  id: number;
  schoolId?: string;
  username?: string;
  email?: string;
  phone?: string;
  avatar?: string;
  password?: string;
}