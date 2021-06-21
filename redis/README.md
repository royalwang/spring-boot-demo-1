# Spring Boot MySQL + Redis + MyBatis Demo

This module serves as a basic demo for MySQL + Redis + MyBatis.

Please refer to [`mysql` module](../mysql/README.md) before reading this module.

## Start MySQL and Redis Docker Container

Before running this demo, please install [Docker](https://docs.docker.com/engine/install/)
and start MySQL and Redis Docker container: 

```bash
# download mysql docker image
> docker pull mysql
# run mysql docker container
> docker run -itd --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql
# download redis docker image
> docker pull redis
# run redis docker container
> docker run -itd --name myredis -p 6379:6379 redis --requirepass "root"
```

To stop and start the container, run the following:

```bash
# stop
> docker stop mysql
> docker stop myredis
# start
> docker start mysql
> docker start myredis
```

You can also manage the containers in Docker Desktop.

After starting the containers, please make sure that you have `test` database in MySQL.

Then check if Redis is alive and functional:

```
> docker exec -it myredis redis-cli
127.0.0.1:6379> auth root
OK
127.0.0.1:6379> keys *
(empty array)
127.0.0.1:6379> exit
```

## Add Data into Database

This will be done automatically when starting this application. 
Please note that this will reset `test.student` table in MySQL 
and cache `student_id::*`, `student_name::*` and `student_username::*` in Redis.

