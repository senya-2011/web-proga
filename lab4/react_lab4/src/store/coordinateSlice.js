import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  x: 0,
  y: 0,
  r: 1,
};

const coordinatesSlice = createSlice({
  name: 'coordinates',
  initialState,
  reducers: {
    setX(state, action) {
      state.x = action.payload;
    },
    setY(state, action) {
      state.y = action.payload;
    },
    setR(state, action) {
      state.r = action.payload;
    },
  },
});

export const { setX, setY, setR } = coordinatesSlice.actions;

export default coordinatesSlice.reducer;
