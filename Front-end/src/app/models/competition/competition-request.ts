import { RankingKey } from "../ranking/ranking-key";
import { Competition } from "./competition";

export interface CompetitionRequest extends Competition {
    rankingKeys?: RankingKey[];
    huntingIds?: number[];
}
