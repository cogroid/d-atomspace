/*
 * opencog/java/com_cogroid_atomspace_PseudoValue.cc
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

#include "com_cogroid_atomspace_PseudoValue.h"
#include <AtomSpace.h>
#include "SPW.h"

/*
 * Class:     com_cogroid_atomspace_PseudoValue
 * Method:    jni_init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_cogroid_atomspace_PseudoValue_jni_1init
  (JNIEnv *env, jobject thisObj) {
	cogroid::SPW<opencog::AtomSpace> *spw_asp = new cogroid::SPW<opencog::AtomSpace>();
	return spw_asp->instance();
}
