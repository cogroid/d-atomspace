#include "com_cogroid_atomspace_Link.h"
#include <opencog/atomspace/AtomSpace.h>
#include <opencog/atoms/base/Node.h>
#include <opencog/atoms/base/Link.h>
#include <opencog/atoms/base/Handle.h>
#include <opencog/atoms/base/Atom.h>
#include <opencog/atoms/value/Value.h>
#include <opencog/atoms/atom_types/types.h>
#include "SPW.h"
#include "Tool.h"

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_init
 * Signature: (JI[J)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_Link_jni_1init
  (JNIEnv *env, jobject thisObj, jlong as_jni_ptr, jint type, jlongArray outgoing_jni_ptrs) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(as_jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	opencog::Type ltype = cogroid::Tool::link_type(type);
	jsize len = env->GetArrayLength(outgoing_jni_ptrs);
	jboolean *is_copy = new jboolean(true);
	jlong *body = env->GetLongArrayElements(outgoing_jni_ptrs, is_copy);
	opencog::HandleSeq oset;
	for (int i = 0; i < len; i++) {
		jlong atom_jni_ptr = body[i];
		cogroid::SPW<opencog::Atom> *spw_atom = cogroid::SPW<opencog::Atom>::get(atom_jni_ptr);
		oset.push_back((opencog::Handle)spw_atom->object());
	}
	cogroid::SPW<opencog::Link> *spw_link = new cogroid::SPW<opencog::Link>(oset, ltype);
	asp->add_atom(spw_link->object());
	return spw_link->instance();
}

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_Link_jni_1dispose
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::Link>::dispose(jni_ptr);
}

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_equals
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_Link_jni_1equals
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jlong value_jni_ptr) {
	cogroid::SPW<opencog::Link> *spw_link = cogroid::SPW<opencog::Link>::get(jni_ptr);
	opencog::Link *link = spw_link->get();
	cogroid::SPW<opencog::Value> *spw_v = cogroid::SPW<opencog::Value>::get(value_jni_ptr);
	opencog::Value *v_value = spw_v->get();
	return (link == v_value);
}

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_less_than
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_Link_jni_1less_1than
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jlong atom_jni_ptr) {
	cogroid::SPW<opencog::Link> *spw_link = cogroid::SPW<opencog::Link>::get(jni_ptr);
	opencog::Link *link = spw_link->get();
	cogroid::SPW<opencog::Atom> *spw_atom = cogroid::SPW<opencog::Atom>::get(atom_jni_ptr);
	opencog::Atom *atom = spw_atom->get();
	return (link < atom);
}

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_to_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Link_jni_1to_1string
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring indent) {
	const char *cindent = env->GetStringUTFChars(indent, NULL);
	std::string sindent(cindent);
	cogroid::SPW<opencog::Link> *spw_link = cogroid::SPW<opencog::Link>::get(jni_ptr);
	opencog::Link *link = spw_link->get();
	std::string result = link->to_string(sindent);
	return env->NewStringUTF(result.c_str());
}

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_to_short_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Link_jni_1to_1short_1string
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring indent) {
	const char *cindent = env->GetStringUTFChars(indent, NULL);
	std::string sindent(cindent);
	cogroid::SPW<opencog::Link> *spw_link = cogroid::SPW<opencog::Link>::get(jni_ptr);
	opencog::Link *link = spw_link->get();
	std::string result = link->to_short_string(sindent);
	return env->NewStringUTF(result.c_str());
}

