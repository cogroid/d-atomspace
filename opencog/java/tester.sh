#!/bin/bash

JAVA_HOME=/home/cogroid/local/jdk8
JAVA=${JAVA_HOME}/bin/java
JAVA_BIN_DIR=./bin

mkdir -p ${JAVA_BIN_DIR}/../tmp
${JAVA} -cp ${JAVA_BIN_DIR}/../datomspace.jar com.cogroid.atomspace.Tester -j -i386 ${JAVA_BIN_DIR}/../tmp

