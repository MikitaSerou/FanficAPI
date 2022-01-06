import { SimplePublication } from './simple/simplePublication';
import { SimpleTag } from './simple/simpleTag';

export interface Theme {
  id: number;
  name: string;
  imageUrl: string;
  publications: SimplePublication[];
  countOfSubscribers: number;
  tags: SimpleTag[];
}
