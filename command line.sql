-- Maven build jar
mvn clean install
mvn package

-- Build docker
docker build -t fun-chat-service .
docker run --name fun-chat-service -p 8080:8080 fun-chat-service	-- Run docker image at localhost only

-- Push to heroku
heroku container:login
docker tag 541bd8beebc3 registry.heroku.com/my-funchat/web			-- 7af02a24804c: This is image_id of the builded docker image
docker push registry.heroku.com/my-funchat/web
-- Deploy
heroku container:release web -a my-funchat
-- View logs
heroku logs -a my-funchat
-- Remove untagged docker images
docker rmi $(docker images -f “dangling=true” -q)

heroku run rails console -a my-funchat

docker run --name registry.heroku.com/my-funchat/web -p 8080:8080 fun-chat-service