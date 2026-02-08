import type { ApiError } from "../types/ApiError";

export class ApiException extends Error {
  public readonly status: number;
  public readonly code: string;
  public readonly path: string;

  constructor(apiError: ApiError) {
    super(apiError.message);

    this.status = apiError.status;
    this.code = apiError.error;
    this.path = apiError.path;
  }
}