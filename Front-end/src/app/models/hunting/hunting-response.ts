import { CompetitionRequest } from "../competition/competition-request";
import { FishRequest } from "../fish/fish-request";
import { MemberRequest } from "../member/member-request";
import { Hunting } from "./hunting";

export interface HuntingResponse extends Hunting {
    member: MemberRequest;
    competition: CompetitionRequest;
    fish: FishRequest;
}
