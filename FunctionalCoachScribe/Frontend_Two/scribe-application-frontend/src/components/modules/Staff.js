// Staff.js
import {Row} from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import { StaffCard } from "./module-cards/StaffCard";
import axios from 'axios';

export const Staff = () => {
  const [staff, setStaff] = useState([]);

  useEffect(() => {
    const fetchUnsplashImage = async (width, height) => {
        try {
          const response = await axios.get('https://api.unsplash.com/photos/random', {
            params: {
              query: 'Adult wearing suit smiling',
              client_id: 'qn6r6zGNpPKxzPWtDaQinEPE9Ve0J4miXzr6JbH2RX4',
              width,
              height,
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

    const fetchStaffData = async () => {
      try {
        const response = await fetch('http://localhost:8081/v1/user/getStaff');
        const data = await response.json();
        const staffWithImages = await Promise.all(
          data.map(async staffMember => {
            const imgUrl = await fetchUnsplashImage(700, 500);
            return {
              userInfo: staffMember.userInfo,
              title: staffMember.title,
              roomNumber: staffMember.roomNumber,
              imgUrl,
            };
          })
        );
        setStaff(staffWithImages);
      } catch (error) {
        console.error('Error fetching staff data:', error);
      }
    };

    fetchStaffData();
  }, []);

  return (
    <div>
      {/* Render StaffCard component when the "Staff" tab is active */}
      {staff.length > 0 && (
        <Row>
          {staff.map((staffMember, index) => (
            <StaffCard key={index} {...staffMember}                           
            imgWidth={500} 
            imgHeight={500} />
          ))}
        </Row>
      )}
    </div>
  );
};
