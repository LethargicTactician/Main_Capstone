import { Navbar, Container, Nav } from "react-bootstrap";
import { useEffect, useState, } from "react";
import logo from "../assets/img/logo.png";
import navIcon1 from '../assets/img/navigation-2.svg';
import navIcon2 from '../assets/img/nav-icon2.svg';
import navIcon3 from '../assets/img/nav-icon3.svg';
import chat from '../components/chat-components/ChatRoom';
import { useNavigate, Link, Router } from 'react-router-dom';


//import React from "react"
export const NavBar = () => {
    const [activateLink, setActiveLink] = useState('home');
    const [scrolled, setScrolled] = useState(false);
    const navigate = useNavigate();

    const handleChatRouteClick = () => navigate(chat);
    

    //when scrolling down on the window you change the background
    useEffect(() => { 
    const onScroll = ()=>{
        if(window.scrollY > 50){
            setScrolled(true);
        } else{
            setScrolled(false);
        }
    }
    window.addEventListener("scroll", onScroll);
    }, [])

    //when section is pressed 
    const onUpdateActiveLink =(value)=>{
        setActiveLink(value);
    }
    return(
        <Navbar expand="lg" className={scrolled ? "scrolled": ""}>
            <Container>
                {/* CHANGE THE LOGO LATER  */}
                <Navbar.Brand href="#home">
                    <img src={logo} alt="Logo" className="navbar-logo"/>
                </Navbar.Brand>

                <Navbar.Toggle aria-controls="basic-navbar-nav">
                    <span className="navbar-toggler-icon"></span>
                </Navbar.Toggle>


                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                    {/* NAVLINKS */}
                        <Nav.Link href="#home" className={activateLink === 'home' ? 'active navbar-link': 'navbar-link'} onClick={() => onUpdateActiveLink('home')}>Home</Nav.Link>
                        <Nav.Link href="#coach-info" className={activateLink === 'coaches' ? 'active navbar-link': 'navbar-link'} onClick={() => onUpdateActiveLink('coaches')}>Coaches</Nav.Link>
                        <Nav.Link href="#feedback" className={activateLink === 'purpose' ? 'active navbar-link': 'navbar-link'} onClick={() => onUpdateActiveLink('purpose')}>Feedback</Nav.Link>



                    </Nav>
                    <span className="navbar-text">
                        {/* CHANGE ICONS LATER TOO */}
                        <div className="social-icon">
                            <a href="#home"><img src={navIcon1} alt=""/> </a>
                            <a href="#home"><img src={navIcon2} alt=""/> </a>
                            <a href="#home"><img src={navIcon3} alt=""/> </a>

                        </div>
                        
                        <button className="vvd" onClick={handleChatRouteClick} href="#chat">
                            <span>Let's Chat!</span>
                            </button>
                            {/* <Link to={chat}>testinggggg</Link> */}
                    </span>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}