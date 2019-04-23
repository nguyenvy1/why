import React, { Component } from 'react'
import Sitter from './sitter'
import Search from '../components/search'

class Page extends Component {
  componentDidMount() {
    this.props.searchSitters()
  }

  handleFilter(event) {
    this.props.filterSitters(10)
  }

  render() {
    let { sitters, isFetched, error } = this.props

    let sitterDisplay = sitters.map(sitter => {
      return (
        <li className="pokemons__item" key={sitter.id}>
          <Sitter sitter={sitter} />
        </li>
      )
    })

    return (
      <div className="page">
        {error && <div className="page__error">{error}</div>}
        <div className="page__search">
          <Search onChange={this.handleFilter.bind(this)} />
        </div>
        {isFetched ? (
          <p>Loading...</p>
        ) : (
          <ul className="sitters">{sitterDisplay}</ul>
        )}
      </div>
    )
  }
}

export default Page
