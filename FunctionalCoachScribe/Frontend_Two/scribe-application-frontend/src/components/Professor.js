import { Row } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import { ProfessorCard } from "./ProfessorCard";
import axios from 'axios';

export const Professors = () => {
    const [professors, setProfessors] = useState([]);

    useEffect(() => {
        const fetchUnsplashImage = async (width, height) => {
            try {
                const response = await axios.get('https://api.unsplash.com/photos/random', {
                    params: {
                        query: 'college professor in classroom',
                        client_id: 'qn6r6zGNpPKxzPWtDaQinEPE9Ve0J4miXzr6JbH2RX4',
                        width: width,
                        height: height,
                    },
                });

                const { data } = response;

                if (data && data.urls && data.urls.regular) {
                  return data.urls.regular;
                } else {
                  console.error('Invalid Unsplash API response:', data);
                  return '';
                }
              } catch (error) {
                console.error('Error fetching Unsplash image:', error);
                return '';
              }
        };

        const fetchProfessors = async () => {
            try {
                const response = await fetch('http://localhost:8081/v1/user/getTeacher');
                const data = await response.json();
                const professorsWithImages = await Promise.all(
                    data.map(async professor => {
                        const imgUrl = await fetchUnsplashImage(700, 500);
                        return {
                            userInfo: professor.userInfo,
                            officeHours: professor.officeHours,
                            officeNum: professor.officeNum,
                            teacherCourses: professor.teacherCourses,
                            imgUrl,
                        };
                    })
                );
                setProfessors(professorsWithImages);
            } catch (error) {
                console.error('Error fetching professor data:', error);
            }
        };

        fetchProfessors();
    }, []);

    return (
        <div>
            {professors.length > 0 && (
                <Row>
                    {professors.map((professor, index) => (
                        <ProfessorCard key={index} {...professor}
                            imgWidth={500}
                            imgHeight={500} />
                    ))}
                </Row>
            )}
        </div>
    );
};
