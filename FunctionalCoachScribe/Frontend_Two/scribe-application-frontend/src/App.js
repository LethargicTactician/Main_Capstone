// import logo from './logo.svg';
import './App.css';
import { NavBar } from './components/NavBar';
import {Banner} from './components/Banner';
import {Skills} from './components/Skills';
import {Coaches} from './components/Coaches'
import 'bootstrap/dist/css/bootstrap.min.css';
import {Footer} from './components/Footer';
import {Contact} from './components/Contact';

function App() {
  return (
    <div className="App">
      <NavBar/>
      <Banner/>
      <Skills/>
      <Coaches/>
      <Contact/>
      <Footer/>
    </div>
  );
}

export default App;
