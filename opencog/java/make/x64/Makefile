JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
GPP=g++
JAVAC=${JAVA_HOME}/bin/javac
JAVA=${JAVA_HOME}/bin/java
JAR=${JAVA_HOME}/bin/jar
JAVA_SRC_DIR=../../
JAVA_BIN_DIR=../../../../build/opencog/java/x64/bin
JAVA_ROOT_SRC_DIR=../../../../
JAVA_ROOT_BIN_DIR=../../../../build

all: clean build

build: init libdatomspace.so Value.class PseudoValue.class Atom.class__AtomSpace.class GenericEval.class SchemeEval.class Node.class ConceptNode.class Link.class OrderedLink.class Tester.class datomspace.jar

init:
	mkdir -p ${JAVA_BIN_DIR}
	cp -f ${JAVA_SRC_DIR}/tester-x64.sh ${JAVA_BIN_DIR}/../tester.sh
	cp -rf ${JAVA_SRC_DIR}/lib/x64/* ${JAVA_BIN_DIR}/
	mkdir -p ${JAVA_BIN_DIR}/tests
	cp -rf ${JAVA_SRC_DIR}/tests/* ${JAVA_BIN_DIR}/tests/

com_cogroid_atomspace_Value.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_Value.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atoms/value -I${JAVA_ROOT_SRC_DIR}/opencog/atoms/atom_types -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_Value.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_Value.o

com_cogroid_atomspace_Atom.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_Atom.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atoms/base -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_Atom.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_Atom.o

com_cogroid_atomspace_PseudoValue.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_PseudoValue.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atomspace -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_PseudoValue.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_PseudoValue.o

com_cogroid_atomspace_AtomSpace.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_AtomSpace.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR}/opencog/atomspace -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_AtomSpace.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_AtomSpace.o

com_cogroid_atomspace_SchemeEval.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_SchemeEval.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR} -I/home/cogroid/local/guile/x64/include/guile/2.2 -DHAVE_GUILE=1 -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_SchemeEval.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_SchemeEval.o

com_cogroid_atomspace_Node.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_Node.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR} -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_Node.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_Node.o

com_cogroid_atomspace_Link.o: ${JAVA_SRC_DIR}/com_cogroid_atomspace_Link.cc
	${GPP} -c -fPIC -std=gnu++17 -I${JAVA_ROOT_SRC_DIR} -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux ${JAVA_SRC_DIR}/com_cogroid_atomspace_Link.cc -o ${JAVA_BIN_DIR}/com_cogroid_atomspace_Link.o

libdatomspace.so: com_cogroid_atomspace_Value.o com_cogroid_atomspace_PseudoValue.o com_cogroid_atomspace_AtomSpace.o com_cogroid_atomspace_Atom.o com_cogroid_atomspace_SchemeEval.o com_cogroid_atomspace_Node.o com_cogroid_atomspace_Link.o
	${GPP} -shared -fPIC -lpthread -Wall -fstack-protector -Wno-variadic-macros -fopenmp -std=gnu++17 -O3 -g -Wl,--no-as-needed \
	-o ${JAVA_BIN_DIR}/libdatomspace.so \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_Value.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_Atom.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_PseudoValue.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_AtomSpace.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_SchemeEval.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_Node.o \
	${JAVA_BIN_DIR}/com_cogroid_atomspace_Link.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/atom_types/CMakeFiles/atom_types.dir/atom_types_init.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/atom_types/CMakeFiles/atom_types.dir/NameServer.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/CMakeFiles/atombase.dir/Atom.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/CMakeFiles/atombase.dir/ClassServer.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/CMakeFiles/atombase.dir/Handle.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/CMakeFiles/atombase.dir/Link.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/CMakeFiles/atombase.dir/Node.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/base/CMakeFiles/atombase.dir/Valuation.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/AbsentLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/ArityLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/Checkers.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/CondLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/Context.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/DefineLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/DeleteLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/FindUtils.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/FreeLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/FreeVariables.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/FunctionLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/ImplicationScopeLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/LambdaLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/MapLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/NumberNode.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/PrenexLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/PresentLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/PutLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/Quotation.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/RandomChoice.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/Replacement.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/RewriteLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/ScopeLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/SleepLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/StateLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TimeLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TypeChoice.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TypedAtomLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TypedVariableLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TypeIntersectionLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TypeNode.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/TypeUtils.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/UniqueLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/UnorderedLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/Variables.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/VariableList.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/core/CMakeFiles/atomcore.dir/VariableSet.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/execution/CMakeFiles/execution.dir/Force.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/execution/CMakeFiles/execution.dir/EvaluationLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/execution/CMakeFiles/execution.dir/ExecutionOutputLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/execution/CMakeFiles/execution.dir/Instantiator.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/CMakeFiles/atomflow.dir/PredicateFormulaLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/CMakeFiles/atomflow.dir/SetTVLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/CMakeFiles/atomflow.dir/SetValueLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/CMakeFiles/atomflow.dir/StreamValueOfLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/CMakeFiles/atomflow.dir/TruthValueOfLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/flow/CMakeFiles/atomflow.dir/ValueOfLink.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/foreign/CMakeFiles/foreign.dir/ForeignAST.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/foreign/CMakeFiles/foreign.dir/SexprAST.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/CMakeFiles/grounded.dir/DLScheme.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/CMakeFiles/grounded.dir/GroundedPredicateNode.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/CMakeFiles/grounded.dir/GroundedSchemaNode.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/CMakeFiles/grounded.dir/LibraryManager.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/CMakeFiles/grounded.dir/LibraryRunner.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/grounded/CMakeFiles/grounded.dir/SCMRunner.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/join/CMakeFiles/join.dir/JoinLink.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/parallel/CMakeFiles/parallel.dir/ExecuteThreadedLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/parallel/CMakeFiles/parallel.dir/ParallelLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/parallel/CMakeFiles/parallel.dir/ThreadJoinLink.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/BindLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/DualLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/GetLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/MeetLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/PatternJit.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/PatternLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/PatternTerm.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/PatternUtils.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/Pattern.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/QueryLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/pattern/CMakeFiles/pattern.dir/SatisfactionLink.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/AccumulateLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/ArithmeticLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/DivideLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/FoldLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/HeavisideLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/Log2Link.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/MaxLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/MinLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/MinusLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/NumericFunctionLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/PlusLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/PowLink.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/RandomNumber.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/reduct/CMakeFiles/clearbox.dir/TimesLink.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/CountTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/EvidenceCountTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/FormulaTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/FuzzyTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/IndefiniteTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/ProbabilisticTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/SimpleTruthValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/truthvalue/CMakeFiles/truthvalue.dir/TruthValue.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/Value.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/FloatValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/FormulaStream.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/LinkStreamValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/LinkValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/QueueValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/RandomStream.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/StreamValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/StringValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/ValueFactory.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atoms/value/CMakeFiles/value.dir/VoidValue.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/atomspace/CMakeFiles/atomspace.dir/AtomSpace.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atomspace/CMakeFiles/atomspace.dir/AtomTable.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atomspace/CMakeFiles/atomspace.dir/Transient.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/atomspace/CMakeFiles/atomspace.dir/TypeIndex.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/CMakeFiles/exec.dir/ExecSCM.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/CMakeFiles/guile-uuid.dir/UuidSCM.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/CMakeFiles/logger.dir/LoggerSCM.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/CMakeFiles/randgen.dir/RandGenSCM.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/guile/modules/CMakeFiles/type-utils.dir/TypeUtilsSCM.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeEval.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeModule.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemePrimitive.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmob.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobAtom.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobAS.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobGC.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobNew.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobPrint.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobTV.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobValue.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/guile/CMakeFiles/smob.dir/SchemeSmobLogger.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/api/CMakeFiles/persist.dir/BackingQuery.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/api/CMakeFiles/persist.dir/BackingStore.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/api/CMakeFiles/persist.dir/PersistSCM.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/api/CMakeFiles/persist.dir/StorageNode.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/json/CMakeFiles/json.dir/DecodeJson.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/json/CMakeFiles/json.dir/EncodeJson.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/json/CMakeFiles/json.dir/JSCommands.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/json/CMakeFiles/json.dir/JsonEval.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/load_scm.dir/fast_load.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/persist-file.dir/FileStorage.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/persist-file.dir/PersistFileSCM.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/sexpr.dir/AtomSexpr.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/sexpr.dir/Commands.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/sexpr.dir/FrameSexpr.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/sexpr.dir/SexprEval.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/persist/sexpr/CMakeFiles/sexpr.dir/ValueSexpr.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/storage/CMakeFiles/storage-types.dir/storage_types_init.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/persist/tlb/CMakeFiles/tlb.dir/TLB.cc.o \
\
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/ContinuationMixin.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/InitiateSearchMixin.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/NextSearchMixin.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/PatternMatchEngine.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/Recognizer.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/RewriteMixin.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/Satisfier.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/SatisfyMixin.cc.o \
	${JAVA_ROOT_BIN_DIR}/opencog/query/CMakeFiles/query-engine.dir/TermMatchMixin.cc.o \
\
	${JAVA_ROOT_SRC_DIR}/opencog/java/lib/x64/libcogutil.so \
	${JAVA_ROOT_SRC_DIR}/opencog/java/lib/x64/libguile-2.2.so \

	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_Value.o
	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_Atom.o
	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_PseudoValue.o
	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_AtomSpace.o
	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_SchemeEval.o
	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_Node.o
	rm -f ${JAVA_BIN_DIR}/com_cogroid_atomspace_Link.o

Value.class: ${JAVA_SRC_DIR}/Value.java
	${JAVAC} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Value.java ${JAVA_SRC_DIR}/Disposable.java

PseudoValue.class: ${JAVA_SRC_DIR}/PseudoValue.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/PseudoValue.java

Atom.class__AtomSpace.class: ${JAVA_SRC_DIR}/Atom.java ${JAVA_SRC_DIR}/AtomSpace.java ${JAVA_SRC_DIR}/Types.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Atom.java ${JAVA_SRC_DIR}/AtomSpace.java ${JAVA_SRC_DIR}/Types.java

Tester.class: ${JAVA_SRC_DIR}/Tester.java ${JAVA_SRC_DIR}/Loader.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Tester.java ${JAVA_SRC_DIR}/Loader.java

GenericEval.class: ${JAVA_SRC_DIR}/GenericEval.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/GenericEval.java

SchemeEval.class: ${JAVA_SRC_DIR}/SchemeEval.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/SchemeEval.java

Node.class: ${JAVA_SRC_DIR}/Node.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Node.java

ConceptNode.class: ${JAVA_SRC_DIR}/ConceptNode.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/ConceptNode.java

Link.class: ${JAVA_SRC_DIR}/Link.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/Link.java

OrderedLink.class: ${JAVA_SRC_DIR}/OrderedLink.java
	${JAVAC} -Xlint:unchecked -cp ${JAVA_BIN_DIR} -d ${JAVA_BIN_DIR} ${JAVA_SRC_DIR}/OrderedLink.java

datomspace.jar:
	${JAR} cvf ${JAVA_BIN_DIR}/../datomspace.jar -C ${JAVA_BIN_DIR} .

run:
	mkdir -p ${JAVA_BIN_DIR}/../tmp
	${JAVA} -cp ${JAVA_BIN_DIR}/../datomspace.jar com.cogroid.atomspace.Tester -j -x64 ${JAVA_BIN_DIR}/../tmp

clean:
	rm -f ${JAVA_BIN_DIR}/../tester.sh
	rm -f ${JAVA_BIN_DIR}/../datomspace.jar
	rm -rf ${JAVA_BIN_DIR}/../tmp
	rm -rf ${JAVA_BIN_DIR}/../*
