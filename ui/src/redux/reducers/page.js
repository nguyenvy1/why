import {
    GET_SITTERS_REQUEST,
    GET_SITTERS_FAIL,
    GET_SITTERS_SUCCESS,
    FILTER_SITTERS_FAIL,
    FILTER_SITTERS_REQUEST,
    FILTER_SITTERS_SUCCESS,
    SET_SITTERS
} from '../constants/page'

const initialState = {
    isFetched: false,
    error: null,
    sitters: [],
}

export default function(state = initialState, action) {
    switch (action.type) {
        case GET_SITTERS_REQUEST:
            return {
                ...state,
                isFetched: true
            }

        case GET_SITTERS_SUCCESS:
            return {
                ...state,
                isFetched: false
            }

        case GET_SITTERS_FAIL:
            return {
                ...state,
                isFetched: false,
                error: action.payload
            }

        case FILTER_SITTERS_REQUEST:
            return {
                ...state,
                isFetched: true
            }

        case FILTER_SITTERS_SUCCESS:
            return {
                ...state,
                isFetched: false
            }

        case FILTER_SITTERS_FAIL:
            return {
                ...state,
                isFetched: false,
                error: action.payload
            }
        case SET_SITTERS:
            return {
                ...state,
                sitters: action.payload
            }

        default:
            return state
    }
}
