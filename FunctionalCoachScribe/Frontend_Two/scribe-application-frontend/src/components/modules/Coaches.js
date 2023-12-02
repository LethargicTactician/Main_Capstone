import { Row } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import { CoachCard } from "./module-cards/CoachCard";
import axios from 'axios';

export const Coaches = () =>{
    const [coaches, setCoaches] = useState([]);    

    useEffect(() => {
        const fetchUnsplashImage = async (width, height) => {
            try {
                const response = await axios.get('https://api.unsplash.com/photos/random', {
                    params: {
                        query: 'college student with laptop',
                        client_id: 'qn6r6zGNpPKxzPWtDaQinEPE9Ve0J4miXzr6JbH2RX4', 
                        width: width,
                        height: height,
                    },
                });
                       
                if (response.data && response.data.urls && response.data.urls.regular) {
                    const imageUrl = response.data.urls.regular;
                    return imageUrl;
                } else {
                    console.error('Invalid Unsplash API response:', response.data);
                    return '';
                }
            } catch (error) {
                console.error('Error fetching Unsplash image:', error);
                return '';
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
    }, []);

    return (
        <div>
            {/* Render CoachCard component when the "Coaches" tab is active */}
            {coaches.length > 0 && (
                <Row>
                    {coaches.map((coach, index) => (
                        <CoachCard key={index} {...coach}                           
                            imgWidth={500} 
                            imgHeight={500} />
                    ))}
                </Row>
            )}
        </div>
    );
};
