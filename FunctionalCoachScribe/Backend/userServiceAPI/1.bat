docker stop UserServiceAPI
docker rm UserServiceAPI
docker rmi userserviceapi:1
docker build -t userserviceapi:1 .
docker run -d -p 8081:8080 --name UserService_API --net netCapstone userserviceapi:1