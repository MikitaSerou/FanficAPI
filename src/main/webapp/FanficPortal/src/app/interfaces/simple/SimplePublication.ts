import {SimpleTheme} from './SimpleTheme';
import {SimpleUser} from './SimpleUser';
import {SimpleTag} from './SimpleTag';

export interface SimplePublication {
  id: number;
  name: string;
  imageUrl: string;
  description: string;
  author: SimpleUser;
  theme: SimpleTheme;
  tags: SimpleTag[];
  creationDate: Date;
  updateDate: Date;
}
