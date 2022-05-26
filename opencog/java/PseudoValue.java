/*
 * opencog/java/PseudoValue.java
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

package com.cogroid.atomspace;

public class PseudoValue extends Value {
	public PseudoValue(int type) {
		super(type);
		jniPtr(jni_init());
	}

	private native long jni_init();

	public boolean isAtom() {
		return getType() == 1;
	}

	public boolean isNode() {
		return getType() == 2;
	}

	public boolean isLink() {
		return getType() == 3;
	}

	public String toString(String indent) {
		return indent + " : [" + getType() + " ]";
	}

	public String toShortString(String indent) {
		return indent + ":" + getType();
	}

	public boolean equals(Value v) {
		return getType() == v.getType();
	}
}
