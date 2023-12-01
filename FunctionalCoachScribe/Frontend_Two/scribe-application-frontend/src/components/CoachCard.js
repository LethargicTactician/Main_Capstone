import { Col } from "react-bootstrap";

export const CoachCard = ({ coachid: { firstName, lastName, email }, hours, coachingDays, course, imgUrl }) => {
    return (
        <Col sm={6} md={4}>
            <div className="proj-imgbx">
                <img src={imgUrl} alt="hi" />
                <div className="proj-txtx">
                    <h4>{firstName} {lastName}</h4>
                    <span>{email}</span>
                    <p><b>Hours:</b> {hours}</p>
                    <p><b>Coaching Days:</b> {coachingDays}</p>
                    <p><b>Course:</b> {course}</p>
                </div>
            </div>
        </Col>
    );
};
