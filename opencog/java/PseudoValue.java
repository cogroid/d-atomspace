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
		jni_ptr = jni_init();
	}

	private native long jni_init();

	public boolean is_atom() {
		return get_type() == 1;
	}

	public boolean is_node() {
		return get_type() == 2;
	}

	public boolean is_link() {
		return get_type() == 3;
	}

	public String to_string(String indent) {
		return indent + " : [" + get_type() + " ]";
	}

	public String to_short_string(String indent) {
		return indent + ":" + get_type();
	}

	public boolean equals(Value v) {
		return get_type() == v.get_type();
	}
}
