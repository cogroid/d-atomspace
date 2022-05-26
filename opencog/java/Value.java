/*
 * opencog/java/Value.java
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

public abstract class Value implements Disposable {
	protected long _jniPtr = 0;
	protected int _type = 0;
	protected boolean _disposed;

	public Value(int type) {
		_type = type;
	}

	public long jniPtr() {
		return _jniPtr;
	}

	protected void jniPtr(long value) {
		_jniPtr = value;
	}

	public boolean disposed() {
		return _disposed;
	}

	protected Disposable disposed(boolean disposed) {
		_disposed = disposed;
		return this;
	}

	public int getType() {
		return _type;
	}

	public abstract boolean isAtom();
	public abstract boolean isNode();
	public abstract boolean isLink();

	public boolean isType(int type) {
		return isType(type, true);
	}

	public boolean isType(int type, boolean subclass) {
		if (!subclass) {
			return getType() == type;
		}
		return jni_is_type(jniPtr(), type);
	}

	private native boolean jni_is_type(long jni_ptr, int type);

	public abstract String toString(String indent);
	public abstract String toShortString(String indent);

	public String toString() {
		return toString("");
	}

	public String toShortString() {
		return toShortString("");
	}

	public abstract boolean equals(Value v);

	public boolean notEquals(Value v) {
		return !equals(v);
	}

	public void dispose() {
		jni_dispose(jniPtr());
		disposed(true);
	}

	private native void jni_dispose(long jni_ptr);
}
