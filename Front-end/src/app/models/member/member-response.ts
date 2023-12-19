import { HuntingResponse } from "../hunting/hunting-response";
import { RankingRequest } from "../ranking/ranking-request";
import { Member } from "./member";

export interface MemberResponse extends Member {
    rankings: RankingRequest[];
    huntings: HuntingResponse[];
}
