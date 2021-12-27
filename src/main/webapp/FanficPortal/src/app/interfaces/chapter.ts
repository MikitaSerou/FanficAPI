import { Publication } from './simple/publication';

export interface Chapter {
  id: number;
  name: string;
  text: string;
  imageReference: string;
  publication: Publication;
}
