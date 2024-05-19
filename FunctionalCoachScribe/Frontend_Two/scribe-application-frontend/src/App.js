// import logo from './logo.svg';
import './App.css';
import { NavBar } from './components/NavBar';
import { Banner } from './components/Banner';
import { Skills } from './components/Skills';
import { TabsInfo } from './components/TabsInfo';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Footer } from './components/Footer';
import { Contact } from './components/Contact';
import { AIChatbot } from './components/chat-components/AIChatbot';

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ChatRoom } from './components/chat-components/ChatRoom';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <NavBar />
        <Banner />
        <Skills />
        <TabsInfo />
        <ChatRoom />
        <Contact />
        <AIChatbot />
        <Footer />
      </div>
    </BrowserRouter>

  );
}

export default App;
