import {SimplePublication} from './simple/SimplePublication';
import {SimpleTag} from './simple/SimpleTag';

export interface Tag extends SimpleTag {
  publications: SimplePublication[];
}
