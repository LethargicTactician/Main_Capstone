import React from 'react';
import { Col } from 'react-bootstrap';

export const ProfessorCard = ({ userInfo: { firstName, lastName, email }, officeHours, officeNum, teacherCourses, imgUrl }) => {
    return (
        <Col sm={6} md={4}>
            <div className="proj-imgbx">
                <img src={imgUrl} alt="Professor" />
                <div className="proj-txtx">
                    <h4>{firstName} {lastName}</h4>
                    <span>{email} </span>
                    <p><b>Office Hours:</b> {officeHours} </p>
                    <p><b>Office Number:</b> {officeNum}</p>
                    <p><b>Courses:</b> {teacherCourses}</p>
                </div>
            </div>
        </Col>
    );
};
