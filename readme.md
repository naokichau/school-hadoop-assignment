# Hadoop duplicate images detection using Skein Hash

# How to run:
- Install docker
- Follow the steps below:
```
$ cd hadoop-sandbox
$ docker network create hadoop-sandbox
$ docker-compose -p hadoop-sandbox up -d
$ cd ..
```

# FOR Linux & macOS
```
$ docker run -it --rm --name hadoop-shell --network hadoop-sandbox -e "CORE_CONF_fs_defaultFS=hdfs://hadoop-namenode:8020" --mount type=bind,src=$PWD,dst=/assignment -e "CLUSTER_NAME=hadoop-sandbox" -t naokichau/hadoop-shell /bin/bash
```
# FOR Windows
```
$ docker run -it --rm --name hadoop-shell --network hadoop-sandbox -e "CORE_CONF_fs_defaultFS=hdfs://hadoop-namenode:8020" -v "/[YOUR PARTITION LETTER]/[PATH TO THIS FOLDER]:/assignment" -e "CLUSTER_NAME=hadoop-sandbox" -t naokichau/hadoop-shell /bin/bash
```

# In hadoop-shell
```
$ cd assignment
$ hadoop fs -put ./images /images
$ cd src
$ hadoop jar BtoS.jar BinFilesToHadoopSeqFile /images/imageset.txt /input/seq
$ hadoop jar ImgDup.jar ImgDup /input/seq /output
```