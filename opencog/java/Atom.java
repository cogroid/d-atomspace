/*
 * opencog/java/Atom.java
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

public abstract class Atom extends Value {
	protected AtomSpace _atomspace;

	public Atom(int type) {
		super(type);
	}

	protected abstract void setAtomSpace(AtomSpace atomspace);
	public AtomSpace getAtomSpace() {
		return _atomspace;
	}

	public long get_hash() {
		return jni_get_hash(jni_ptr);
	}

	private native long jni_get_hash(long jni_ptr);

	public boolean is_atom() {
		return true;
	}

	// public ContentHash get_hash();

	public abstract String get_name();
	public abstract int get_arity();

	public void dispose() {
		jni_dispose(jni_ptr);
		disposed(true);
	}

	private native void jni_dispose(long jni_ptr);

	public abstract java.util.List<Atom> getOutgoingSet();
}
