import { CompetitionRequest } from '../competition/competition-request';
import { MemberResponse } from '../member/member-response';
import { Ranking } from './ranking';

export interface RankingResponse extends Ranking {
    competition: CompetitionRequest;
    member: MemberResponse;
}
