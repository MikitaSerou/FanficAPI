import { Publication } from './Publication';
import { SimpleChapter } from './simple/SimpleChapter';

export interface Chapter extends SimpleChapter {
  text: string;
  publication: Publication;
}
