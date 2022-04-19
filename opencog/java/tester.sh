#!/bin/bash

JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
JAVA=java
JAVA_BIN_DIR=./bin

${JAVA} -cp ${JAVA_BIN_DIR} -Djava.library.path=${JAVA_BIN_DIR} com.cogroid.atomspace.Tester

