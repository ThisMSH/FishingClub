import { FishRequest } from "../fish/fish-request";
import { Level } from "./level";

export interface LevelResponse extends Level {
    fishes: FishRequest[];
}
