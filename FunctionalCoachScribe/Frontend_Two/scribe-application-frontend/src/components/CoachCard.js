import {Col} from "react-bootstrap";


export const CoachCard = ({name, bio, imgUrl}) => {
    return(
        <Col sm={6} md={4}>
            <div className="proj-imgbx" >
                <img src={imgUrl} alt="hi"/>
                <div className="proj-txtx" >
                    <h4>{name}</h4>
                    <span>{bio}</span>
                </div>
            </div>
        </Col>
    )

}