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
	protected long _jniPtr = 0;

	protected String _inputLine = "";
	protected String _errorString = "";
	protected boolean _pendingInput = false;
	protected boolean _caughtError = false;

	public GenericEval() {
	}

	public long jniPtr() {
		return _jniPtr;
	}

	protected void jniPtr(long value) {
		_jniPtr = value;
	}

	public boolean inputPending() {
		return _pendingInput;
	}

	public void clearPending() {
		_inputLine = "";
		_errorString = "";
		_pendingInput = false;
		_caughtError = false;
	}

	public boolean evalError() {
		return _caughtError;
	}

	public String getErrorString() {
		return _errorString;
	}

	public abstract void beginEval();

	public abstract void evalExpr(String text);

	public abstract String pollResult();

	public abstract void interrupt();
}
