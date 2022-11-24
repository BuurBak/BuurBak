export interface PaginatedResponse<T> {
  totalPages: number
  totalElements: number
  size: number
  content: T[]
  number: number
  sort: Sort
  first: boolean
  numberOfElements: number
  pageable: Pageable
  last: boolean
  empty: boolean
}

export interface Pageable {
  offset: number
  sort: Sort
  pageNumber: number
  pageSize: number
  paged: boolean
  unpaged: boolean
}

export interface Sort {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}
