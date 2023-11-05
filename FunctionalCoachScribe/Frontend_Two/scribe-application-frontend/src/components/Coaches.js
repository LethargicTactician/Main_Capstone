import { Col, Container, Row, Nav, Tab } from "react-bootstrap";
import { CoachCard } from "./CoachCard";
import colorSharp2 from "../assets/img/gradent.jpg"
import coach1 from "../assets/img/coachesPfp/Coach1.png";
import coach2 from "../assets/img/coachesPfp/Coach2.png";
import TrackVisibility from "react-on-screen";
import "animate.css"

export const Coaches =()=>{
    const coaches =[
        {
            name: "Mr. Dawgy",
            bio: "I do math uwu",
            imgUrl: coach1,
        },
        {
            name: "Chad",
            bio: "flex coach",
            imgUrl: coach2,
        },
        {
            name: "Mr. Dawgy",
            bio: "I do math uwu",
            imgUrl: coach1
        },
        {
            name: "Chad",
            bio: "flex coach",
            imgUrl: coach2,
        }, 
        {
            name: "Mr. Dawgy",
            bio: "I do math uwu",
            imgUrl: coach1
        },
        {
            name: "Chad",
            bio: "flex coach",
            imgUrl: coach2,
        },
        // ADD MORE IF YOU WANT IG

    ];
    return(
        <section className="coach" id="coach">
            <Container>
                <Row>
                    <Col>
                    <TrackVisibility>
                        {({isVisible}) =>                        
                            <div className={isVisible ? "animate__animated animate__bounce" : ""}>
                                <h2>Coaches</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                </div>}
                        </TrackVisibility>
                        <Tab.Container id="coach-tabs" defaultActiveKey="first">

                        <Nav variant="pills" className="nav- pills mb-5 justify-content-center align-items-center" id="pills-tab">
                            <Nav.Item>
                                <Nav.Link eventKey="first">Tab One</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="second">Tab Two</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link eventKey="third">
                                Tab Three
                                </Nav.Link>
                            </Nav.Item>
                        </Nav>
                        <Tab.Content>
                            <Tab.Pane eventKey="first">
                                <Row>
                                    {
                                        coaches.map((coach, index) => {
                                            return(
                                                <CoachCard key={index} {...coach}/>                                   
                                            )

                                        })
                                    }

                                </Row>
                            </Tab.Pane>
                            <Tab.Pane eventKey="second">Loren Ipsum</Tab.Pane>
                            <Tab.Pane eventKey="third">Loren Ipsum</Tab.Pane>
                        </Tab.Content>
                        </Tab.Container>

                    </Col>
                </Row>
            </Container>
            <img className="background-image-right" src={colorSharp2} alt="hi"/>
        </section>


    )
}