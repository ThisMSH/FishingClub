import { HuntingRequest } from "../hunting/hunting-request";
import { RankingRequest } from "../ranking/ranking-request";
import { Member } from "./member";

export interface MemberResponse extends Member {
    rankings: RankingRequest[];
    huntings: HuntingRequest[];
}
