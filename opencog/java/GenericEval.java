/*
 * opencog/java/GenericEval.java
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

public abstract class GenericEval {
	public long jni_ptr = 0;

	protected String _input_line = "";
	protected String _error_string = "";
	protected boolean _pending_input = false;
	protected boolean _caught_error = false;

	public GenericEval() {
	}

	public boolean input_pending() {
		return _pending_input;
	}

	public void clear_pending() {
		_input_line = "";
		_error_string = "";
		_pending_input = false;
		_caught_error = false;
	}

	public boolean eval_error() {
		return _caught_error;
	}

	public String get_error_string() {
		return _error_string;
	}

	public abstract void begin_eval();

	public abstract void eval_expr(String text);

	public abstract String poll_result();

	public abstract void interrupt();
}
