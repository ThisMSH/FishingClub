import { HuntingRequest } from "../hunting/hunting-request";
import { LevelRequest } from "../level/level-request";
import { Fish } from "./fish";

export interface FishResponse extends Fish {
    level: LevelRequest;
    huntings: HuntingRequest[];
}
