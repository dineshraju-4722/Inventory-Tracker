import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: null,
  isAuthenticated: false,
  loading: false,
  error: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setLoading(state, action) {
      state.loading = action.payload;
    },
    loginSuccess(state, action) {
      state.user = action.payload;
      state.isAuthenticated = true;
      state.loading = false;
      state.error = null;
    },
    loginError(state, action) {
      state.error = action.payload;
      state.loading = false;
      state.isAuthenticated = false;
    },
    signupSuccess(state, action) {
      state.error = null;
      state.loading = false;
    },
    signupError(state, action) {
      state.error = action.payload;
      state.loading = false;
    },
    logout(state) {
      state.user = null;
      state.isAuthenticated = false;
      state.error = null;
      state.loading = false;
    },
    clearError(state) {
      state.error = null;
    },
  },
});

export const {
  setLoading,
  loginSuccess,
  loginError,
  signupSuccess,
  signupError,
  logout,
  clearError,
} = userSlice.actions;

export default userSlice.reducer;
