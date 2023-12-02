import { Col } from "react-bootstrap";

export const StaffCard = ({ userInfo: { firstName, lastName, email }, title, roomNumber, imgUrl }) => {
    return (
        <Col sm={6} md={4}>
            <div className="proj-imgbx">
                <img src={imgUrl} alt="hi" />
                <div className="proj-txtx">
                    <h4>{firstName} {lastName}</h4>
                    <span>{email} </span>
                    <p><b>Title:</b> {title} </p>
                    <p><b>Office Number:</b> {roomNumber}</p>
                </div>
            </div>
        </Col>
    );
};
