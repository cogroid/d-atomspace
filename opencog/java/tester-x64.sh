#!/bin/bash

JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
JAVA=${JAVA_HOME}/bin/java
JAVA_BIN_DIR=./bin

mkdir -p ${JAVA_BIN_DIR}/../tmp
${JAVA} -cp ${JAVA_BIN_DIR}/../datomspace.jar com.cogroid.atomspace.Tester -j ${JAVA_BIN_DIR}/../tmp

