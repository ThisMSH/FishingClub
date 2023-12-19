import { CompetitionRequest } from '../competition/competition-request';
import { MemberRequest } from '../member/member-request';
import { Ranking } from './ranking';

export interface RankingResponse extends Ranking {
    competition: CompetitionRequest;
    member: MemberRequest;
}
