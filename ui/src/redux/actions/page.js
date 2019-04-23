import {
    GET_SITTERS_REQUEST,
    GET_SITTERS_FAIL,
    GET_SITTERS_SUCCESS,
    FILTER_SITTERS_FAIL,
    FILTER_SITTERS_REQUEST,
    FILTER_SITTERS_SUCCESS,
    SET_SITTERS
} from '../constants/page'

function setSitters(data) {
    const sitters = data.results

    return {
        type: SET_SITTERS,
        payload: sitters
    }
}

export function searchSitters() {
    return dispatch => {
        dispatch({
            type: GET_SITTERS_REQUEST
        })

        return fetch(`http://localhost:8080/searchSitters?size=300`)
            .then(response => {
                if (response.ok) {
                    return response.json()
                }

                throw new Error(`${response.status}: ${response.statusText}`)
            })
            .then(data => {
                dispatch({
                    type: GET_SITTERS_SUCCESS
                })
                dispatch(setSitters(data))
            })
            .catch(error => {
                dispatch({
                    type: GET_SITTERS_FAIL,
                    payload: error.message
                })
            })
    }
}

export function filterSitters(value) {
    return dispatch => {
        dispatch({
            type: FILTER_SITTERS_REQUEST
        })

        return fetch(`http://localhost:8080/filterByRating?size=300&greaterThan=${value}`)
            .then(response => {
                console.log(response.toString())
                if (response.ok) {
                    return response.json()
                }

                throw new Error(`${response.status}: ${response.statusText}`)
            })
            .then(data => {
                dispatch({
                    type: FILTER_SITTERS_SUCCESS
                })
                dispatch(setSitters(data))
            })
            .catch(error => {
                dispatch({
                    type: FILTER_SITTERS_FAIL,
                    payload: error.message
                })
            })
    }
}