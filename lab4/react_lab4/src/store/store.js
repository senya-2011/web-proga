import { configureStore } from '@reduxjs/toolkit';
import coordinatesReducer from './coordinateSlice.js';
import pointsReducer from './pointSlice.js'
import authReducer from './authSlice.js'

const store = configureStore({
  reducer: {
    coordinates: coordinatesReducer, 
    points: pointsReducer,
    auth: authReducer,
  },
});

export default store;