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
	public long jni_ptr = 0;
	protected int _type = 0;
	protected boolean _disposed;

	public Value(int type) {
		_type = type;
	}

	public boolean disposed() {
		return _disposed;
	}

	protected Disposable disposed(boolean disposed) {
		_disposed = disposed;
		return this;
	}

	public int get_type() {
		return _type;
	}

	public abstract boolean is_atom();
	public abstract boolean is_node();
	public abstract boolean is_link();

	public boolean is_type(int type) {
		return is_type(type, true);
	}

	public boolean is_type(int type, boolean subclass) {
		if (!subclass) {
			return get_type() == type;
		}
		return jni_is_type(jni_ptr, type);
	}

	private native boolean jni_is_type(long jni_ptr, int type);

	public abstract String to_string(String indent);
	public abstract String to_short_string(String indent);

	public String to_string() {
		return to_string("");
	}

	public String to_short_string() {
		return to_short_string("");
	}

	public abstract boolean equals(Value v);

	public boolean not_equals(Value v) {
		return !equals(v);
	}

	public void dispose() {
		jni_dispose(jni_ptr);
		disposed(true);
	}

	private native void jni_dispose(long jni_ptr);
}
