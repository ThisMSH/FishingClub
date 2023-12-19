import { IdentityDocumentType } from "src/app/types/types";

export interface Member {
    number?: number;
    name: string;
    familyName: string;
    accessionDate: string;
    nationality: string;
    identityDocument: IdentityDocumentType;
    identityNumber: string;
}
