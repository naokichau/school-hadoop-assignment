docker network create hadoop-sandbox

docker-compose -p hadoop-sandbox up -d

docker run -it --rm --name hadoop-shell --network hadoop-sandbox -e "CORE_CONF_fs_defaultFS=hdfs://hadoop-namenode:8020" --mount type=bind,src=$PWD/assignment,dst=/assignment -e "CLUSTER_NAME=hadoop-sandbox" -t naokichau/hadoop-shell /bin/bash
