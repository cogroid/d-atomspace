/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_cogroid_atomspace_Link */

#ifndef _Included_com_cogroid_atomspace_Link
#define _Included_com_cogroid_atomspace_Link
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_init
 * Signature: (JI[J)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_Link_jni_1init
  (JNIEnv *, jobject, jlong, jint, jlongArray);

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_Link_jni_1dispose
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_equals
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_Link_jni_1equals
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_less_than
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_Link_jni_1less_1than
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_to_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Link_jni_1to_1string
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_cogroid_atomspace_Link
 * Method:    jni_to_short_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_Link_jni_1to_1short_1string
  (JNIEnv *, jobject, jlong, jstring);

#ifdef __cplusplus
}
#endif
#endif