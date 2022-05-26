/*
 * opencog/java/AtomSpace.java
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

public class AtomSpace extends Atom {
	public AtomSpace(long jni_ptr) {
		super(Types.ATOM_SPACE);
		jniPtr(jni_ptr);
	}

	public AtomSpace() {
		super(Types.ATOM_SPACE);
		jniPtr(jni_init());
	}

	private native long jni_init();

	protected void setAtomSpace(AtomSpace atomspace) {
		_atomspace = atomspace;
	}

	public boolean isAtom() {
		return true;
	}

	public boolean isNode() {
		return false;
	}

	public boolean isLink() {
		return false;
	}

	public String toString(String indent) {
		return jni_to_string(jniPtr(), indent);
	}

	private native String jni_to_string(long jni_ptr, String indent);

	public String toShortString(String indent) {
		return jni_to_short_string(jniPtr(), indent);
	}

	private native String jni_to_short_string(long jni_ptr, String indent);

	public boolean equals(Value v) {
		return getType() == v.getType();
	}

	public void dispose() {
		jni_dispose(jniPtr());
		disposed(true);
	}

	private native void jni_dispose(long jni_ptr);

	// Please implement
	public String getName() {
		return "AtomSpace";
	}

	// Please implement
	public int getArity() {
		return 0;
	}

	// Please implement

	public java.util.List<Atom> getOutgoingSet() {
		java.util.List<Atom> result = new java.util.ArrayList<Atom>();
		int size = jni_getOutgoingSet_size(jniPtr());
		System.out.println("outgoingSet size: " + size);
		for (int i = 0; i < size; i++) {
			int type = jni_getOutgoingSet_item_type(jniPtr(), i);
			System.out.println("outgoingSet item type [" + i + "]: " + type);
			if (type == Types.ATOM_SPACE) {
				long ptr = jni_getOutgoingSet_item_pointer(jniPtr(), i);
				System.out.println("outgoingSet item pointer [" + i + "]: " + ptr);
				AtomSpace as = new AtomSpace(ptr);
				result.add(as);
			}
		}
		return result;
	}

	private native int jni_getOutgoingSet_size(long jni_ptr);
	private native int jni_getOutgoingSet_item_type(long jni_ptr, int idx);
	private native long jni_getOutgoingSet_item_pointer(long jni_ptr, int idx);

}
