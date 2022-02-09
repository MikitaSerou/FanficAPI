import {SimpleUser} from './simple/SimpleUser';
import {SimpleChapter} from './simple/SimpleChapter';
import {SimplePublication} from './simple/SimplePublication';

export interface Publication extends SimplePublication {
  chapters: SimpleChapter[];
  usersWhoDidBookmark: SimpleUser[];
  usersWhoLiked: SimpleUser[];
}
