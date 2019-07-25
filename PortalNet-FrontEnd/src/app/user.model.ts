import { Authority } from './authority';

export class User {
    public userId: number;
    public userEmail: string;
    public userName: string;
    public role: string;
    jwt?: string;
    authorities: Authority[];

    constructor(userId: number, userEmail: string, userName: string, role: string) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.role = role;
    }
}