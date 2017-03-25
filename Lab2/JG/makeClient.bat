javac -cp .;../lib/protobuf-java-2.4.1.jar;../lib/jgroups-3.6.13.Final.jar -d . ../src/pl/edu/agh/dsrg/sr/protos/Main.java
java -cp .;;../lib/protobuf-java-2.4.1.jar;../lib/jgroups-3.6.13.Final.jar pl.edu.agh.dsrg.sr.protos.Main %1 %2
