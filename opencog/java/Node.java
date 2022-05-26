/*
 * opencog/java/Node.java
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

public class Node extends Atom {
	protected String _name;

	public Node(AtomSpace atomspace, int type, String name) {
		super(Types.refineNodeType(type));
		_name = name;
		setAtomSpace(atomspace);
		jniPtr(jni_init(atomspace.jniPtr(), Types.refineNodeType(type), name));
	}

	private native long jni_init(long as_jni_ptr, int type, String name);

	protected void setAtomSpace(AtomSpace atomspace) {
		_atomspace = atomspace;
	}

	public boolean isNode() {
		return true;
	}

	public boolean isLink() {
		return false;
	}

	public String getName() {
		return jni_get_name(jniPtr());
	}

	private native String jni_get_name(long jni_ptr);

	public int getArity() {
		return jni_get_arity(jniPtr());
	}

	private native int jni_get_arity(long jni_ptr);

	public void dispose() {
		jni_dispose(jniPtr());
		disposed(true);
	}

	private native void jni_dispose(long jni_ptr);

	public boolean equals(Value v) {
		return jni_equals(jniPtr(), v.jniPtr());
	}

	private native boolean jni_equals(long jni_ptr, long value_jni_ptr);

	public String toString(String indent) {
		return jni_to_string(jniPtr(), indent);
	}

	private native String jni_to_string(long jni_ptr, String indent);

	public String toShortString(String indent) {
		return jni_to_short_string(jniPtr(), indent);
	}

	private native String jni_to_short_string(long jni_ptr, String indent);

	public java.util.List<Atom> getOutgoingSet() {
		return new java.util.ArrayList<Atom>();
	}
}
