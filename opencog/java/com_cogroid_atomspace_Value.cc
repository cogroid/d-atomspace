/*
 * opencog/java/com_cogroid_atomspace_Value.cc
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

#include "com_cogroid_atomspace_Value.h"
#include <Value.h>
#include <types.h>

/*
 * Class:     com_cogroid_atomspace_Value
 * Method:    jni_is_type
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_cogroid_atomspace_Value_jni_1is_1type
  (JNIEnv *env, jobject thisObj, jlong jni_ptr, jint type) {
	opencog::Type v_type = (opencog::Type)type;
	opencog::Value *v_value = (opencog::Value *)jni_ptr;
	return v_value->is_type(v_type);
}

/*
 * Class:     com_cogroid_atomspace_Value
 * Method:    jni_dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_cogroid_atomspace_Value_jni_1dispose
  (JNIEnv *env, jobject thisObj, jlong jni_ptr) {
	opencog::Value *v_value = (opencog::Value *)jni_ptr;
	delete v_value;
}
