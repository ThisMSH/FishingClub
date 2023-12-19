import { Fish } from "./fish";

export interface FishRequest extends Fish {
    levelCode: number;
    huntingIds: number[];
}
