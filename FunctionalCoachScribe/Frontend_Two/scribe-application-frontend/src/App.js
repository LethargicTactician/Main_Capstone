// import logo from './logo.svg';
import './App.css';
import { NavBar } from './components/NavBar';
import {Banner} from './components/Banner';
import {Skills} from './components/Skills';
import {Coaches} from './components/Coaches'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <NavBar/>
      <Banner/>
      <Skills/>
      <Coaches/>
    </div>
  );
}

export default App;
