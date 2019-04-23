import { connect } from 'react-redux'
import * as pageActions from '../redux/actions/page'
import Page from '../components/page'

function mapStateToProps(state) {
  const { sitters, isFetched, error } = state.page

  return {
    sitters,
    isFetched,
    error
  }
}

const mapDispatchToProps = {
  searchSitters: pageActions.searchSitters(),
  filterSitters: pageActions.filterSitters()
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Page)
