import { HuntingRequest } from "../hunting/hunting-request";
import { RankingRequest } from "../ranking/ranking-request";
import { Competition } from "./competition";

export interface CompetitionResponse extends Competition {
    rankings: RankingRequest[];
    huntings: HuntingRequest[];
}
