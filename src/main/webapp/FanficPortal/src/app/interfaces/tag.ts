import { SimplePublication } from './simple/simplePublication';

export interface Tag {
  id: number;
  name: string;
  publications: SimplePublication[];
}
