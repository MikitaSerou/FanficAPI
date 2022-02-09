import {User} from './User';
import {SimpleRole} from './simple/SimpleRole';

export interface Role extends SimpleRole {
  users: User[];
}
