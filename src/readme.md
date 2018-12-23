javac -classpath $(hadoop classpath) -d BtoS/ BinFilesToHadoopSeqFile.java
jar -cvf BtoS.jar -C BtoS/ .
hadoop jar BtoS.jar BinFilesToHadoopSeqFile /images/imageset.txt /input/seq

javac -classpath $(hadoop classpath) -d ImgDup/ ImgDup.java ImgDupMapper.java ImgDupReducer.java Skein512.java
jar -cvf ImgDup.jar -C ImgDup/ .
hadoop jar ImgDup.jar ImgDup /input/seq /output