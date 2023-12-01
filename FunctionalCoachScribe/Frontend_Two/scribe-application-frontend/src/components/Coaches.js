import { Col, Container, Row, Nav, Tab } from "react-bootstrap";
import { CoachCard } from "./CoachCard";
import colorSharp2 from "../assets/img/gradent.jpg"
import coach1 from "../assets/img/coachesPfp/Coach1.png";
import coach2 from "../assets/img/coachesPfp/Coach2.png";
import TrackVisibility from "react-on-screen";
import React, {useState, useEffect} from 'react';
import "animate.css"
// import { useState } from "react";
import axios from 'axios';

export const Coaches =()=>{
    const [coaches, setCoaches] = useState([]);

    useEffect(() => {
        const fetchUnsplashImage = async (width, height) => {
            try {
                const response = await axios.get('https://api.unsplash.com/photos/random', {
                    params: {
                        query: 'college student',
                        client_id: 'qn6r6zGNpPKxzPWtDaQinEPE9Ve0J4miXzr6JbH2RX4', 
                        width: width,
                        height: height,
                    },
                });
        
                // Check if response.data.urls is defined
                if (response.data && response.data.urls && response.data.urls.regular) {
                    const imageUrl = response.data.urls.regular;
                    return imageUrl;
                } else {
                    console.error('Invalid Unsplash API response:', response.data);
                    return ''; // Return a fallback or handle the error
                }
            } catch (error) {
                console.error('Error fetching Unsplash image:', error);
                return ''; // Return a fallback or handle the error
            }
        };

        const fetchCoaches = async () => {
          try {
            const response = await fetch('http://localhost:8081/v1/user/getCoaches');
            const data = await response.json();
            const coachesWithImages = await Promise.all(
              data.map(async coach => {
                const imgUrl = await fetchUnsplashImage(700, 500);
                return {
                  coachid: coach.coachid,
                  hours: coach.hours + ", ",
                  coachingDays: coach.coachingDays + ", ",
                  course: coach.course + ", ",
                  imgUrl,
                };
              })
            );
            setCoaches(coachesWithImages);
          } catch (error) {
            console.error('Error fetching coach data:', error);
          }
        };
  
      fetchCoaches();
    }, []); // Remove setCoaches from the dependency array


    // const coaches =[
    //     {
    //         name: "That Math kid",
    //         bio: "I do math uwu",
    //         imgUrl: coach1,
    //     },
    //     {
    //         name: "Chad",
    //         bio: "flex coach",
    //         imgUrl: coach2,
    //     },
    //     {
    //         name: "The Math Kid's Twin brother",
    //         bio: "I do not do math uwu",
    //         imgUrl: coach1
    //     },
    //     // ADD MORE IF YOU WANT IG

    // ];
    return (
        <section className="coach" id="coach-info">
          <Container>
            <Row>
              <Col>
                <TrackVisibility>
                  {({ isVisible }) => (
                    <div className={isVisible ? 'animate__animated animate__bounce' : ''}>
                      <h2>Coaches</h2>
                      <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                        culpa qui officia deserunt mollit anim id est laborum.
                      </p>
                    </div>
                  )}
                </TrackVisibility>
                <Tab.Container id="coach-tabs" defaultActiveKey="first">
                  <Nav
                    variant="pills"
                    className="nav- pills mb-5 justify-content-center align-items-center"
                    id="pills-tab"
                  >
                    <Nav.Item>
                      <Nav.Link eventKey="first">Coaches</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                      <Nav.Link eventKey="second">Professors</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                      <Nav.Link eventKey="third">Staff</Nav.Link>
                    </Nav.Item>
                  </Nav>
                  <Tab.Content>
                    <Tab.Pane eventKey="first">
                      <Row>
                        {coaches.map((coach, index) => (
                          <CoachCard key={index} {...coach} 
                          imgWidth={500} 
                          imgHeight={500} 
                          />
                          
                        ))}
                      </Row>
                    </Tab.Pane>
                    <Tab.Pane eventKey="second">Loren Ipsum</Tab.Pane>
                    <Tab.Pane eventKey="third">Loren Ipsum</Tab.Pane>
                  </Tab.Content>
                </Tab.Container>
              </Col>
            </Row>
          </Container>
          <img className="background-image-right" src={colorSharp2} alt="hi" />
        </section>
      );
    };
// export default Coaches;