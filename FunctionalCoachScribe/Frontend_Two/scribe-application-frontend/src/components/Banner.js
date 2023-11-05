import { useState, useEffect } from 'react';
import { Container, Row, Col } from "react-bootstrap";
import { ArrowRightCircle } from "react-bootstrap-icons";
import headerImg from "../assets/img/Xiao_Filler_Img2.png";
import 'animate.css';
import TrackVisibility from 'react-on-screen';




export const Banner = ()=>{
    //---------FOR ANIMATION-------
    const [loopNum, setLoopNum] = useState(0);
    const [isDeleting, setIsDeleting ] = useState(false);
    const toRotate = [ "an adeptus", "Xiao", "your favorite coach"];
    const [text, setText] = useState('');
    const [delta, setDelta] = useState(300 - Math.random() * 100);
    const period = 2000;

    
    useEffect(() => {
        let ticker = setInterval(() => {
            tick();
        }, delta)

        return () => { clearInterval(ticker)};
    }, [text])

    const tick = () => {
        let i = loopNum % toRotate.length;
        let fullText = toRotate[i];
        let updatedText = isDeleting ? fullText.substring(0, text.length -1) : fullText.substring(0, text.length +1)

        setText(updatedText);

        if(isDeleting){
            setDelta(prevDelta => prevDelta /2)
        }

        if(!isDeleting && updatedText === fullText){
            setIsDeleting(true);
            setDelta(period);

        } else if(isDeleting && updatedText === ''){
            setIsDeleting(false);
            setLoopNum(loopNum + 1);
            setDelta(500);

        }

    }


    return(
        <section className="banner" id="home">
            <Container>
                <Row className="align-items-center">
                    <Col xs={12} md={5} xl={7}>
                        <TrackVisibility>
                        {({isVisible}) =>                        
                            <div className={isVisible ? "animate__animated animate__fadeIn" : ""}>
                            <span className="tagline" >Welcome to this thing</span>
                                <h1>{"Hi! I'm "}<span className="wrap">{text}</span></h1>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                <button onClick={() => console.log('connect')}>Let's chat <ArrowRightCircle size={25}/></button>     

                            </div>  }                      
                        </TrackVisibility>                    
                    </Col>
                    <Col xa={12} md={12} xl={5}>
                        <img src={headerImg} alt="Header Img"></img>
                    </Col>
                </Row>
            </Container>

        </section>
    )
}