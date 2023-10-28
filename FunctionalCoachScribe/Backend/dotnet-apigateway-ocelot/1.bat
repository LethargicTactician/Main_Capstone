CLS

docker stop PRO290APIGatewayOcelot
docker rm PRO290APIGatewayOcelot
docker rmi pro290ocelotgatewayapi:1
docker build --no-cache -t pro290ocelotgatewayapi:1 .
docker run -d -p 5041:80 --name PRO290APIGatewayOcelot --net netPRO290 pro290ocelotgatewayapi:1