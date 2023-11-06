// import logo from './logo.svg';
import './App.css';
import { NavBar } from './components/NavBar';
import {Banner} from './components/Banner';
import {Skills} from './components/Skills';
import {Coaches} from './components/Coaches'
import 'bootstrap/dist/css/bootstrap.min.css';
import {Footer} from './components/Footer';
import {Contact} from './components/Contact';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ChatRoom } from './components/chat-components/ChatRoom';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <NavBar/>
        <Routes>
          <Route path="/chat" element={<ChatRoom/>}/>
          <Route/>
        </Routes>

        <Banner/>
        <Skills/>
        <Coaches/>
        <Contact/>
        <Footer/>
    </div>
    </BrowserRouter>

  );
}

export default App;
