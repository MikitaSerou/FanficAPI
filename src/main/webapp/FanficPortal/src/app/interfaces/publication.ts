import {SimpleUser} from './simple/simpleUser';
import {SimpleTheme} from './simple/simpleTheme';
import {SimpleChapter} from './simple/simpleChapter';
import {SimpleTag} from './simple/simpleTag';

export interface Publication {
  id: number;
  name: string;
  description: string;
  author: SimpleUser;
  theme: SimpleTheme;
  chapters: SimpleChapter[];
  usersWhoDidBookmark: SimpleUser[];
  usersWhoLiked: SimpleUser[];
  tags: SimpleTag[];
}
