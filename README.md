# CoachScribe_Capstone

# Notes
Languages: 
Java
C#
MongoDB
JavaScript
Eureka
SpringBoot
.Net Framework
ReactJs
Bootstrap CSS
HTML
Web Socket
Voiceflow
GPT 4

> Users: Stores user account information.
> Teachers: Stores user account information & schedule.
> Staff: Stores user account information & schedule.
> Coaches: Stores user account information & schedule.
> Chat Service: Web Socket that allows a chat from user to user

Users Collection - default/parent collection
Teacher Collection
Staff Collection
Coach Collection

User ID - uuid
Teacher ID - uuid
Staff ID - uuid
Coach ID - uuid

# Users Collection
User ID
First Name
Last Name
Email
Password Hash (for authentication)

# Staff Collection
{same as user}
Room Number 
Available hours []

# Teacher Collection
{same as user}
Room Number 
Schedule []
Office hours []

# Coach Collection
{same as user}
Room Number
Schedule []
