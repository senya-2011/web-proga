import { createSlice } from "@reduxjs/toolkit";

const pointsSlice = createSlice({
  name: "points",
  initialState: {
    points: [], 
  },
  reducers: {
    setPoints(state, action) {
      state.points = action.payload;
    },
  },
});

export const { setPoints} = pointsSlice.actions;
export default pointsSlice.reducer;
