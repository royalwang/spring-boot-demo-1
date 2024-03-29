# Spring Boot MySQL + MyBatis Demo

This module serves as a basic demo for MySQL + MyBatis.

Please refer to [`mysql` module](../mysql/README.md) before reading this module.

## Start MySQL Docker Container

Before running this demo, please install [Docker](https://docs.docker.com/engine/install/)
and start MySQL Docker container:

```bash
# download docker image
> docker pull mysql
# run docker container
> docker run -itd --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql
```

To stop and start the container, run the following:

```bash
# stop
> docker stop mysql
# start
> docker start mysql
```

You can also manage the containers in Docker Desktop.

After starting the container, please make sure that you have `test` database in MySQL.

## Add Data into Database

This will be done automatically when starting this application.
Please note that this will reset `test.student` table.

