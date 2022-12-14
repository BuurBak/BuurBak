import './OfferResults.css'
import TrailerCard from '../trailerCard/TrailerCard'
import NoResults from '../../trailers/trailerCard/NoResults.jsx'
import ReactPaginate from 'react-paginate'
import { useState } from 'react'

export default function OfferRestults({ filteredTrailers, loading }) {
  const [pageNumber, setPageNumber] = useState(0)
  // Display 20 items per page,
  const itemsPerPage = 20
  const pagesVisited = pageNumber * itemsPerPage
  const displayItems = filteredTrailers
    .slice(pagesVisited, pagesVisited + itemsPerPage)
    .map((filteredTrailers) => {
      return (
        <TrailerCard key={filteredTrailers.id} trailer={filteredTrailers} />
      )
    })
  const pageCount = Math.ceil(filteredTrailers.length / itemsPerPage)
  const changePage = ({ selected }) => {
    setPageNumber(selected)
  }

  if (!filteredTrailers) return

  return (
    <>
      <div className="offerResultsContainer">
        {loading ? (
          <p>Loading...</p>
        ) : (
          <>
            {filteredTrailers.length > 0 ? <>{displayItems}</> : <NoResults />}
          </>
        )}
      </div>
      <ReactPaginate
        previousLabel={'Vorige'}
        nextLabel={'Volgende'}
        pageCount={pageCount}
        onPageChange={changePage}
        containerClassName={'paginationBtns'}
        previousLinkClassName={'previousBtn'}
        nextLinkClassName={'nextBtn'}
        activeClassName={'paginationActive'}
        pageRangeDisplayed={1}
      />
    </>
  )
}
