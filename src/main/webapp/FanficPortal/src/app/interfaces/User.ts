import { SimpleRole } from './simple/SimpleRole';
import { SimpleTheme } from './simple/SimpleTheme';
import { SimplePublication } from './simple/SimplePublication';
import { SimpleUser } from './simple/SimpleUser';

export interface User extends SimpleUser {
  roles: SimpleRole[];
  preferences: SimpleTheme[];
  publications: SimplePublication[];
  bookmarks: SimplePublication[];
}
