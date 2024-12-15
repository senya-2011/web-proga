import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    isAuthenticated: false, 
    login: '',
  },
  reducers: {
    login: (state, action) => {
      state.isAuthenticated = true;
      state.login = action.payload;
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.login = ''; 
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;