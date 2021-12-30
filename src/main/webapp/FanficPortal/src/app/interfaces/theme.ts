import { SimplePublication } from './simple/simplePublication';
import { SimpleUser } from './simple/simpleUser';

export interface Theme {
  id: number;
  name: string;
  imageUrl: string;
  publications: SimplePublication[];
  subscribers: SimpleUser[];
}
