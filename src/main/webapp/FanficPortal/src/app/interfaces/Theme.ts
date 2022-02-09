import {SimplePublication} from './simple/SimplePublication';
import {SimpleTag} from './simple/SimpleTag';
import {SimpleTheme} from './simple/SimpleTheme';

export interface Theme extends SimpleTheme {
  publications: SimplePublication[];
  tags: SimpleTag[];
}
