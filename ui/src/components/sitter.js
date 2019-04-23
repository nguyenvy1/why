import React, { PureComponent } from 'react'

class Sitter extends PureComponent {
  render() {
    const { sitter } = this.props

    return (
      <div className="sitter">
        <button
          type="button"
          className="pokemon__sprite"
          // style={{
          //   backgroundImage: `url(${`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${
          //     pokemon.id
          //   }.png`})`
          // }}
        />
        <p className="pokemon__name">{sitter.name}</p>
      </div>
    )
  }
}

export default Sitter
