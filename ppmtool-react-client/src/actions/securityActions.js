import axios from "axios";
import setJWTToken from "../securityUtils/setJWTToken";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const login = (LoginRequest) => async (dispatch) => {
  try {
    // Post => Login Request
    const res = await axios.post("/api/users/login", LoginRequest);

    // Extract token from res.data
    const { token } = res.data;

    // Store the token in the localStorage
    localStorage.setItem("jwtToken", token);

    // Set token in header *** Important ***
    setJWTToken(token);

    // Decode token on React
    const decoded = jwt_decode(token);

    // Dispatch to security reducer
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded,
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};
