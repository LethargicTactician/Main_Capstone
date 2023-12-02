import { Col, Container, Row, Nav, Tab } from "react-bootstrap";
import {Staff} from "./modules/Staff";
import {Coaches} from "./modules/Coaches";
import {Professors} from "./modules/Professor"
import TrackVisibility from "react-on-screen";
import "animate.css"

export const TabsInfo =()=>{
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
                        <Coaches/>
                      </Row>
                    </Tab.Pane>

                    <Tab.Pane eventKey="second">
                      <Professors/>
                    </Tab.Pane>

                    <Tab.Pane eventKey="third">
                      <Row>
                        <Staff/>
                      </Row>
                    </Tab.Pane>
                  </Tab.Content>
                </Tab.Container>
              </Col>
            </Row>
          </Container>
        </section>
      );
    };