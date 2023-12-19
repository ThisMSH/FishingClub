import { RankingKey } from '../ranking/ranking-key';
import { Member } from './member';

export interface MemberRequest extends Member {
    rankingKeys?: RankingKey[];
    huntingIds?: number[];
}
