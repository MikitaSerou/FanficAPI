import {SimpleUser} from './simpleUser';
import {SimpleTheme} from './simpleTheme';
import {SimpleChapter} from './simpleChapter';
import {SimpleTag} from './simpleTag';

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
