CLS

docker stop CAPAPIGatewayOcelot
docker rm CAPAPIGatewayOcelot
docker rmi capocelotgatewayapi:1
docker build --no-cache -t capocelotgatewayapi:1 .
docker run -d -p 5041:80 --name CAPAPIGatewayOcelot --net netCapstone capocelotgatewayapi:1