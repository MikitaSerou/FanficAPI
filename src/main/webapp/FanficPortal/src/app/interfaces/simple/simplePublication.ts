import {SimpleTheme} from "./simpleTheme";
import {SimpleUser} from "./simpleUser";
import {SimpleTag} from "./simpleTag";

export interface SimplePublication {
  id: number,
  name: string,
  description: string,
  author: SimpleUser,
  theme: SimpleTheme,
  tags: SimpleTag[]

}
