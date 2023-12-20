import { Hunting } from "./hunting";

export interface HuntingRequest extends Hunting {
    weight?: number;
    memberNumber: number;
    competitionCode: string;
    fishName: string;
}
