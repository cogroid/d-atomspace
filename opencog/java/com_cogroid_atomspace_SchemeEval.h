/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_cogroid_atomspace_SchemeEval */

#ifndef _Included_com_cogroid_atomspace_SchemeEval
#define _Included_com_cogroid_atomspace_SchemeEval
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_init
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1init
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_init_scheme
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1init_1scheme
  (JNIEnv *, jclass);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_set_scheme_as
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1set_1scheme_1as
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_get_evaluator
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1get_1evaluator
  (JNIEnv *, jclass);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_get_evaluator_as
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1get_1evaluator_1as
  (JNIEnv *, jclass, jlong);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_begin_eval
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1begin_1eval
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_eval_expr
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1eval_1expr
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_poll_result
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1poll_1result
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_interrupt
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1interrupt
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_eval_as
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1eval_1as
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_cogroid_atomspace_SchemeEval
 * Method:    jni_recursing
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_SchemeEval_jni_1recursing
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif