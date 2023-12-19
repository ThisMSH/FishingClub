import { HuntingResponse } from "../hunting/hunting-response";
import { RankingResponse } from "../ranking/ranking-response";
import { Competition } from "./competition";

export interface CompetitionResponse extends Competition {
    rankings: RankingResponse[];
    huntings: HuntingResponse[];
}
