export interface Response<T> {
    data?: T | T[];
    cause?: object;
    message: string;
    status: number;
}
