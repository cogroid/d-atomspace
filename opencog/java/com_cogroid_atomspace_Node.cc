#include "com_cogroid_atomspace_Node.h"
#include <opencog/atomspace/AtomSpace.h>
#include <opencog/atoms/base/Node.h>
#include <opencog/atoms/value/Value.h>
#include <opencog/atoms/atom_types/types.h>
#include "SPW.h"
#include "Tool.h"

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_init
 * Signature: (JILjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_Node_jni_1init
  (JNIEnv *env, jobject thisObj, jlong as_jni_ptr, jint type, jstring name) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(as_jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	opencog::Type ntype = cogroid::Tool::node_type(type);
	const char *cname = env->GetStringUTFChars(name, NULL);
	std::string sname(cname);
	cogroid::SPW<opencog::Node> *spw_node = new cogroid::SPW<opencog::Node>(ntype, sname);
	asp->add_atom(spw_node->object());
	return spw_node->instance();
}

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_get_name
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Node_jni_1get_1name
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::Node> *spw_node = cogroid::SPW<opencog::Node>::get(jni_ptr);
	opencog::Node *node = spw_node->get();
	std::string result = node->get_name();
	return env->NewStringUTF(result.c_str());
}

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_get_arity
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_cogroid_atomspace_Node_jni_1get_1arity
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::Node> *spw_node = cogroid::SPW<opencog::Node>::get(jni_ptr);
	opencog::Node *node = spw_node->get();
	return node->get_arity();
}

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_Node_jni_1dispose
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::Node>::dispose(jni_ptr);
}

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_equals
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_Node_jni_1equals
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jlong value_jni_ptr) {
	cogroid::SPW<opencog::Node> *spw_node = cogroid::SPW<opencog::Node>::get(jni_ptr);
	opencog::Node *node = spw_node->get();
	cogroid::SPW<opencog::Value> *spw_v = cogroid::SPW<opencog::Value>::get(value_jni_ptr);
	opencog::Value *v_value = spw_v->get();
	return (node == v_value);
}

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_to_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Node_jni_1to_1string
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring indent) {
	const char *cindent = env->GetStringUTFChars(indent, NULL);
	std::string sindent(cindent);
	cogroid::SPW<opencog::Node> *spw_node = cogroid::SPW<opencog::Node>::get(jni_ptr);
	opencog::Node *node = spw_node->get();
	std::string result = node->to_string(sindent);
	return env->NewStringUTF(result.c_str());
}

/*
 * Class:     com_cogroid_atomspace_Node
 * Method:    jni_to_short_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Node_jni_1to_1short_1string
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring indent) {
	const char *cindent = env->GetStringUTFChars(indent, NULL);
	std::string sindent(cindent);
	cogroid::SPW<opencog::Node> *spw_node = cogroid::SPW<opencog::Node>::get(jni_ptr);
	opencog::Node *node = spw_node->get();
	std::string result = node->to_short_string(sindent);
	return env->NewStringUTF(result.c_str());
}

