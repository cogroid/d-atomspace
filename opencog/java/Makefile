JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
GPP=g++
JAVAC=javac
JAVA=java
JAVA_SRC_DIR=.
JAVA_BIN_DIR=../../build/opencog/java/bin
JAVA_ROOT_SRC_DIR=../../
JAVA_ROOT_BIN_DIR=../../build

all: clean build

build: init copy_so libdatomspace.so Value.class PseudoValue.class Atom.class__AtomSpace.class Tester.class

init:
	mkdir -p ${JAVA_BIN_DIR}
	cp ${JAVA_SRC_DIR}/tester.sh ${JAVA_BIN_DIR}/../

copy_so:
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atomspace/libatomspace.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/libatombase.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/atom_types/libatom_types.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/libatomcore.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/execution/libexecution.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/libatomflow.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/foreign/libforeign.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/libgrounded.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/join/libjoin.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/parallel/libparallel.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/libpattern.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/libclearbox.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/libtruthvalue.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/libvalue.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/libexec.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/libguile-uuid.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/liblogger.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/librandgen.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/libtype-utils.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/guile/libsmob.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/api/libpersist.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/json/libjson.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/libload_scm.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/libpersist-file.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/libsexpr.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/storage/libstorage-types.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/persist/tlb/libtlb.so ${JAVA_BIN_DIR}
	cp ${JAVA_ROOT_BIN_DIR}/opencog/query/libquery-engine.so ${JAVA_BIN_DIR}

com_cogroid_atomspace_Value.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_Value.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atoms/value -I${JAVA_ROOT_SRC_DIR}/opencog/atoms/atom_types -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_Value.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_Value.o

com_cogroid_atomspace_Atom.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_Atom.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atoms/base -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_Atom.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_Atom.o

com_cogroid_atomspace_PseudoValue.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_PseudoValue.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atomspace -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_PseudoValue.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_PseudoValue.o

com_cogroid_atomspace_AtomSpace.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_AtomSpace.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atomspace -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_AtomSpace.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_AtomSpace.o

libdatomspace.so: com_cogroid_atomspace_Value.o com_cogroid_atomspace_PseudoValue.o com_cogroid_atomspace_AtomSpace.o com_cogroid_atomspace_Atom.o
	${GPP} -shared -fPIC -o ${JAVA_BIN_DIR}/libdatomspace.so \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_Value.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_Atom.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_PseudoValue.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_AtomSpace.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atomspace/libatomspace.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/libatombase.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/atom_types/libatom_types.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/libatomcore.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/execution/libexecution.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/libatomflow.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/foreign/libforeign.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/libgrounded.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/join/libjoin.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/parallel/libparallel.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/libpattern.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/libclearbox.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/libtruthvalue.so \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/libvalue.so \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/libexec.so \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/libguile-uuid.so \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/liblogger.so \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/librandgen.so \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/libtype-utils.so \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/libsmob.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/api/libpersist.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/json/libjson.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/libload_scm.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/libpersist-file.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/libsexpr.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/storage/libstorage-types.so \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/tlb/libtlb.so \
	${JAVA_ROOT_BIN_DIR}/opencog/query/libquery-engine.so \

Value.class: ${JAVA_SRC_DIR}/Value.java
	${JAVAC} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Value.java ${JAVA_SRC_DIR}/Disposable.java

PseudoValue.class: ${JAVA_SRC_DIR}/PseudoValue.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/PseudoValue.java

Atom.class__AtomSpace.class: ${JAVA_SRC_DIR}/Atom.java ${JAVA_SRC_DIR}/AtomSpace.java ${JAVA_SRC_DIR}/Types.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Atom.java ${JAVA_SRC_DIR}/AtomSpace.java ${JAVA_SRC_DIR}/Types.java

Tester.class: ${JAVA_SRC_DIR}/Tester.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Tester.java

run:
	${JAVA} -cp ${JAVA_BIN_DIR} -Djava.library.path=${JAVA_BIN_DIR} com.cogroid.atomspace.Tester

clean:
	rm -rf ${JAVA_BIN_DIR}
	rm -f ${JAVA_BIN_DIR}/../tester.sh
