import React, { useEffect, useState } from 'react'
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
// import './LoginForm.css';
import 'bootstrap';

var stompClient =null;


export const ChatRoom = () => {
    const [privateChats, setPrivateChats] = useState(new Map());     
    const [publicChats, setPublicChats] = useState([]); 
    const [passwordError, setPasswordError] = useState('');
    const [tab, setTab] = useState("CHATROOM");
    const [userData, setUserData] = useState({
        username: '',
        receivername: '',
        connected: false,
        message: ''
    });
    useEffect(() => {
      console.log(userData);
    }, [userData]);

    //--------------- USER CONNECTION ------------>
    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/ws');  // Use port 8080 for host
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }
    const onConnected = () => {
        setUserData({...userData,"connected": true});
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('/user/'+userData.username+'/private', onPrivateMessage);
        userJoin();
    }

    const userJoin=()=>{
          let chatMessage = {
            senderName: userData.username,
            // receivername: tab,
            status:"JOIN"
          };
          stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    }

    //--------------- REGISTER ------------------>
    const registerUser=()=>{
        connect();
    }

    //--------------- PASSWORD------------------->

    const validatePassword = (password) => {
        if (password.length < 8) {
            return 'Password must be at least 8 characters long';
        }      
        return ''; 
    };

    const handlePassword = (event) => {
        const password = event.target.value;
        const error = validatePassword(password);
        setUserData({ ...userData, password });
        setPasswordError(error);
    };
    

    //----------- RECEIVE MESSAGES --------------->
    const onMessageReceived = (payload)=>{
        var payloadData = JSON.parse(payload.body);
        switch(payloadData.status){
            case "JOIN":
                if(!privateChats.get(payloadData.senderName)){
                    privateChats.set(payloadData.senderName,[]);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
        }
    }    
    //---receive private message----
    const onPrivateMessage = (payload)=>{
        console.log(payload);
        let payloadData = JSON.parse(payload.body);
        if(privateChats.get(payloadData.senderName)){
            privateChats.get(payloadData.senderName).push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else{
            let list =[];
            list.push(payloadData);
            privateChats.set(payloadData.senderName,list);
            setPrivateChats(new Map(privateChats));
        }
    }

    //--------- SENDING MESSAGES ------------------->
    const sendValue=()=>{
            if (stompClient) {
              let chatMessage = {
                senderName: userData.username,
                message: userData.message,
                status:"MESSAGE"
              };
              console.log(chatMessage);
              stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
              setUserData({...userData,"message": ""});
            }
    }

    const sendPrivateValue=()=>{
        if (stompClient) {
          let chatMessage = {
            senderName: userData.username,
            receiverName:tab,
            message: userData.message,
            status:"MESSAGE"
          };
          
          
          if(userData.username !== tab){
            privateChats.get(tab).push(chatMessage);
            //privateChats.set(tab).push(chatMessage);
            setPrivateChats(new Map(privateChats));
          }
          stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
          setUserData({...userData,"message": ""});
        }
    }

    //-------------- BUGS/ HANDLING ---------------->

    const handleMessage =(event)=>{
        const {value}=event.target;
        setUserData({...userData,"message": value});
    }

    const handleUsername=(event)=>{
        const {value}=event.target;
        setUserData({...userData,"username": value});
    }

    const onError = (err) => {
        console.log(err);
        
    }      

    // -------------REACT stuff------------------->
    return (
    <div className="container" id="chat">
        {/* <nav className="navbar">
        <ul className="nav-list">
            <li className="nav-item">
            <a href="/">Home</a>
            </li>
            <li className="nav-item">
            <a href="/about">About</a>
            </li>
            <li className="nav-item">
            <a href="/services">Services</a>
            </li>
            <li className="nav-item">
            <a href="/contact">Contact</a>
            </li>
        </ul>
        </nav> */}
        {userData.connected?        
        <div className="chat-box">
            <div className="member-list">
                <ul>
                    <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Public Chatroom</li>
                    {[...privateChats.keys()].map((name,index)=>(
                        <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
                    ))}
                </ul>
            </div>
            {tab==="CHATROOM" && <div className="chat-content">
                <ul className="chat-messages">
                    {publicChats.map((chat,index)=>(
                        <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                            {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                            <div className="message-data">{chat.message}</div>
                            {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                        </li>
                    ))}
                </ul>

                <div className="send-message">
                    <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} /> 
                    <button type="button" className="send-button" onClick={sendValue}>send</button>
                </div>
            </div>}
            {/* // Check if the active tab is a private chat */}
            {tab!=="CHATROOM" && <div className="chat-content"> 
                <ul className="chat-messages">
                    {[...privateChats.get(tab)].map((chat,index)=>( 
                        <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                            {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                            <div className="message-data">{chat.message}</div>
                            {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                        </li>
                    ))}
                </ul>

                <div className="send-message">
                    <input type="text" className="input-message" placeholder={`send private message to ${tab}`} value={userData.message} onChange={handleMessage} /> 
                    <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                </div>
            </div>}
        </div>
        :
        // ---------------------------------------REGISTER---------------------------------------------
        <form>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            placeholder="Enter your username"
            value={userData.username}
            onChange={handleUsername}
            required
          />
        </div>
                <div className="form-group">
            <label htmlFor="password">Password:</label>
            <input
                type="password"
                id="password"
                name="password"
                placeholder="Enter your password"
                value={userData.password}
                onChange={handlePassword}
                required
        />
        </div>
{passwordError && <div className="error">{passwordError}</div>}
        <button type="button" onClick={registerUser} className="btn">
          Login
        </button>
      </form>       
        
        }
    </div>
    )
}

export default ChatRoom