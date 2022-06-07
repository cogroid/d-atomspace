/*
 * opencog/java/Link.java
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

public class Link extends Atom {

	public static interface Callback {
		public boolean exec(Atom a);
	}

	protected java.util.List<Atom> _outgoing;

	protected void init(AtomSpace atomspace, int type) {
		setAtomSpace(atomspace);
		long[] outgoing_ptrs = new long[_outgoing.size()];
		for (int i = 0; i < _outgoing.size(); i++) {
			outgoing_ptrs[i] = _outgoing.get(i).jniPtr();
		}
		jniPtr(jni_init(atomspace.jniPtr(), Types.refineNodeType(type), outgoing_ptrs));
	}

	public Link(AtomSpace atomspace, int type, java.util.List<Atom> outgoing) {
		super(Types.refineLinkType(type));
		_outgoing = outgoing;
		init(atomspace, type);
	}

	public Link(AtomSpace atomspace, int type, Atom a) {
		super(Types.refineLinkType(type));
		_outgoing = new java.util.ArrayList<Atom>();
		_outgoing.add(a);
		init(atomspace, type);
	}

	public Link(AtomSpace atomspace, int type, Atom a, Atom b) {
		super(Types.refineLinkType(type));
		_outgoing = new java.util.ArrayList<Atom>();
		_outgoing.add(a);
		_outgoing.add(b);
		init(atomspace, type);
	}

	public Link(AtomSpace atomspace, int type, Atom a, Atom b, Atom c) {
		super(Types.refineLinkType(type));
		_outgoing = new java.util.ArrayList<Atom>();
		_outgoing.add(a);
		_outgoing.add(b);
		_outgoing.add(c);
		init(atomspace, type);
	}

	public Link(AtomSpace atomspace, int type, Atom a, Atom b, Atom c, Atom d) {
		super(Types.refineLinkType(type));
		_outgoing = new java.util.ArrayList<Atom>();
		_outgoing.add(a);
		_outgoing.add(b);
		_outgoing.add(c);
		_outgoing.add(d);
		init(atomspace, type);
	}

	private native long jni_init(long as_jni_ptr, int type, long[] outgoing_jni_ptrs);

	protected void setAtomSpace(AtomSpace atomspace) {
		_atomspace = atomspace;
	}

	public String getName() {
		return "";
	}

	public boolean isNode() {
		return false;
	}

	public boolean isLink() {
		return true;
	}

	public int getArity() {
		return _outgoing.size();
	}

	public int size() {
		return _outgoing.size();
	}

	public java.util.List<Atom> getOutgoingSet() {
		return _outgoing;
	}

	public Atom getOutgoingAtom(int pos) {
		return _outgoing.get(pos);
	}

	public boolean foreachOutgoing(Callback cb) {
		for (int i = 0; i < _outgoing.size(); i++) {
			if (cb.exec(_outgoing.get(i))) return true;
		}
		return false;
	}

	public void dispose() {
		jni_dispose(jniPtr());
		disposed(true);
	}

	private native void jni_dispose(long jni_ptr);

	public boolean equals(Value v) {
		return jni_equals(jniPtr(), v.jniPtr());
	}

	private native boolean jni_equals(long jni_ptr, long value_jni_ptr);

	public boolean lessThan(Atom a) {
		return jni_less_than(jniPtr(), a.jniPtr());
	}

	private native boolean jni_less_than(long jni_ptr, long atom_jni_ptr);

	public String toString(String indent) {
		return jni_to_string(jniPtr(), indent);
	}

	private native String jni_to_string(long jni_ptr, String indent);

	public String toShortString(String indent) {
		return jni_to_short_string(jniPtr(), indent);
	}

	private native String jni_to_short_string(long jni_ptr, String indent);
}
