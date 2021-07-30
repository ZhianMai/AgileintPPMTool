import { GET_PROJECTS, GET_PROJECT } from "../actions/types";

const initialState = {
  projectList: [],
  project: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projectList: action.payload,
      };

    case GET_PROJECT:
      return {
        ...state,
        project: action.payload,
      };

    default:
      return state;
  }
}
