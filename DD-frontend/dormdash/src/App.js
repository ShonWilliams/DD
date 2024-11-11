import React from 'react';
import ReactDOM from 'react-dom/client';
import { useState } from "react";
import { BrowserRouter as Router, Route, Routes, BrowserRouter } from 'react-router-dom';
import './App.css';
import LoginSignUp from './pages/LoginSignUp';

function App() {
  return(
    <div>
      <BrowserRouter>
        <Routes>
          <Route index element={<LoginSignUp/>}/>
          <Route path="/login" element={<LoginSignUp/>}/>
          {/* <Route path="/signup" element={<SignUp/>}/>
          <Route path="/home" element={<Home/>}/>
          <Route path="/addPost" element={<AddPost/>}/>
          <Route path="/profile" element={<Profile/>} />
          <Route path="/settings" element={<Settings/>} />
          <Route path="*" element={<PageNotFound/>}/> */}
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App;