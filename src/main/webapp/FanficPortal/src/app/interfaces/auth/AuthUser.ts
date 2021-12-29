export interface AuthUser {
  id: number;
  username: string;
  email: string;
  token: string;
  refreshToken: string;
  roles: string[];
}
