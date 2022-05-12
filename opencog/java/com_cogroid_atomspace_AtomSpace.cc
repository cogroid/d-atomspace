/*
 * opencog/java/com_cogroid_atomspace_AtomSpace.cc
 *
 * Copyright (C) 2022 Dinh Thoai Tran
 * All Rights Reserved
 *
 * Written by Dinh Thoai Tran <progrocus@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License v3 as
 * published by the Free Software Foundation and including the exceptions
 * at http://opencog.org/wiki/Licenses
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to:
 * Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

#include "com_cogroid_atomspace_AtomSpace.h"
#include "AtomSpace.h"
#include "SPW.h"

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1init
  (JNIEnv *env, jobject thisObj) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = new cogroid::SPW<opencog::AtomSpace>();
	return spw_asp->instance();
}

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1dispose
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::AtomSpace>::dispose(jni_ptr);
}

// Please implement

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_getOutgoingSet_size
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1getOutgoingSet_1size
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	return (int)asp->getOutgoingSet().size();
}

// Please implement

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_getOutgoingSet_item_type
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1getOutgoingSet_1item_1type
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jint idx) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	if (asp->getOutgoingSet()[idx].get()->get_type() == opencog::ATOM_SPACE) {
		return 1;
	}
	return 0;
}

// Please implement

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_getOutgoingSet_item_pointer
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1getOutgoingSet_1item_1pointer
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jint idx) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	return (long)asp->getOutgoingSet()[idx].get();
}

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_to_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1to_1string
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring indent) {
	const char *cindent = env->GetStringUTFChars(indent, NULL);
	std::string sindent(cindent);
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	std::string result = asp->to_string(sindent);
	return env->NewStringUTF(result.c_str());
}

/*
 * Class:     com_cogroid_atomspace_AtomSpace
 * Method:    jni_to_short_string
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cogroid_atomspace_AtomSpace_jni_1to_1short_1string
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jstring indent) {
	const char *cindent = env->GetStringUTFChars(indent, NULL);
	std::string sindent(cindent);
	cogroid::SPW<opencog::AtomSpace> *spw_asp = cogroid::SPW<opencog::AtomSpace>::get(jni_ptr);
	opencog::AtomSpace *asp = spw_asp->get();
	std::string result = asp->to_short_string(sindent);
	return env->NewStringUTF(result.c_str());
}

