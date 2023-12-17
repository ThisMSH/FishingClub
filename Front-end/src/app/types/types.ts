export type DarkInfo = { isDark?: boolean };

export type SideBarLinks = { name: string; img: string; link: string };

export type BtnStyle =
    | 'primary'
    | 'secondary'
    | 'danger'
    | 'success'
    | 'warning';

export type InputType = 'text' | 'email' | 'password' | 'number' | 'date' | 'date-time' | 'time' | 'search';

export type SpecialType = 'date' | 'time' | 'datetime';

export type CompetitionFilter = 'ALL' | 'INCOMING' | 'ONGOING' | 'DONE';

export type SortOrder = 'ASC' | 'DESC';

export type PaginationParams = {
    page?: number;
    size?: number;
    sortBy?: string;
    sortOrder?: SortOrder;
    fullName?: string;
    filter?: CompetitionFilter;
};
