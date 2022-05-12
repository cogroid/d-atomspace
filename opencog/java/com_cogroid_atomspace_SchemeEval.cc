#include "com_cogroid_atomspace_SchemeEval.h"
#include <opencog/atomspace/AtomSpace.h>
#include <opencog/guile/SchemeEval.h>
#include "SPW.h"

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_init
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1init
  (JNIEnv *env, jobject thisObj, jlong as_jni_ptr) {
	opencog::AtomSpace *asp = NULL;
	if (as_jni_ptr != 0) {
		cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(as_jni_ptr);
		asp = spw_asp->get();
	}
	cogroid::SPW<opencog::SchemeEval> *spw_se = new cogroid::SPW<opencog::SchemeEval>(asp);
	return spw_se->instance();
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_init_scheme
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1init_1scheme
  (JNIEnv *env, jclass clz) {
	opencog::SchemeEval::init_scheme();
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_set_scheme_as
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1set_1scheme_1as
  (JNIEnv *env, jclass clz, jlong as_jni_ptr) {
	opencog::AtomSpace *asp = NULL;
	if (as_jni_ptr != 0) {
		cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(as_jni_ptr);
		asp = spw_asp->get();
	}
	opencog::SchemeEval::set_scheme_as(asp);
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_get_evaluator
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1get_1evaluator
  (JNIEnv *env, jclass clz) {
	opencog::SchemeEval *se = opencog::SchemeEval::get_evaluator();
	std::shared_ptr<opencog::SchemeEval> sp_se(se);
	cogroid::SPW<opencog::SchemeEval> *spw_se = new cogroid::SPW<opencog::SchemeEval>(sp_se);
	return spw_se->instance();
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_get_evaluator_as
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1get_1evaluator_1as
  (JNIEnv *env, jclass clz, jlong as_jni_ptr) {
	opencog::AtomSpace *asp = NULL;
	if (as_jni_ptr != 0) {
		cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(as_jni_ptr);
		asp = spw_asp->get();
	}
	opencog::SchemeEval *se = opencog::SchemeEval::get_evaluator(asp);
	std::shared_ptr<opencog::SchemeEval> sp_se(se);
	cogroid::SPW<opencog::SchemeEval> *spw_se = new cogroid::SPW<opencog::SchemeEval>(sp_se);
	return spw_se->instance();
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_begin_eval
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1begin_1eval
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::SchemeEval> *spw_se = cogroid::SPW<opencog::SchemeEval>::get(jni_ptr);
	opencog::SchemeEval *se = spw_se->get();
	se->begin_eval();
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_eval_expr
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1eval_1expr
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring text) {
	cogroid::SPW<opencog::SchemeEval> *spw_se = cogroid::SPW<opencog::SchemeEval>::get(jni_ptr);
	opencog::SchemeEval *se = spw_se->get();
	const char *ctext = env->GetStringUTFChars(text, NULL);
	std::string stext(ctext);
	se->eval_expr(stext);
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_poll_result
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1poll_1result
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::SchemeEval> *spw_se = cogroid::SPW<opencog::SchemeEval>::get(jni_ptr);
	opencog::SchemeEval *se = spw_se->get();
	std::string stext = se->poll_result();
	return env->NewStringUTF(stext.c_str());
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_interrupt
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1interrupt
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::SchemeEval> *spw_se = cogroid::SPW<opencog::SchemeEval>::get(jni_ptr);
	opencog::SchemeEval *se = spw_se->get();
	se->interrupt();
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_eval_as
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1eval_1as
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring text) {
	cogroid::SPW<opencog::SchemeEval> *spw_se = cogroid::SPW<opencog::SchemeEval>::get(jni_ptr);
	opencog::SchemeEval *se = spw_se->get();
	const char *ctext = env->GetStringUTFChars(text, NULL);
	std::string stext(ctext);
	opencog::AtomSpace *asp = se->eval_as(stext);
	return (long)asp;
}

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_recursing
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1recursing
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::SchemeEval> *spw_se = cogroid::SPW<opencog::SchemeEval>::get(jni_ptr);
	opencog::SchemeEval *se = spw_se->get();
	bool rs = se->recursing();
	return rs;
}
