import {SimpleRole} from './simple/simpleRole';
import {SimpleTheme} from './simple/simpleTheme';
import {SimplePublication} from './simple/simplePublication';

export interface User {
  id: number;
  username: string;
  email: string;
  roles: SimpleRole[];
  preferences: SimpleTheme[];
  publications: SimplePublication[];
  bookmarks: SimplePublication[];

  // constructor(
  //   public id: number,
  //   public username: string,
  //   public email: string,
  //   public roles: SimpleRole[],
  //   public preferences: SimpleTheme[],
  //   public publications: SimplePublication[],
  //   public bookmarks: SimplePublication[]
  // ) {
  // };
}
