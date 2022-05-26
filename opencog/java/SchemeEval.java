/*
 * opencog/java/SchemeEval.java
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

public class SchemeEval extends GenericEval {
	public SchemeEval() {
		super();
		jniPtr(jni_init(0));
	}

	public SchemeEval(long jni_ptr) {
		jniPtr(jni_ptr);
	}

	public SchemeEval(AtomSpace as) {
		super();
		jniPtr(jni_init(as.jniPtr()));
	}

	private native long jni_init(long as_jni_ptr);

	// Call before first use.
	public static void initScheme() {
		jni_init_scheme();
	}

	private static native void jni_init_scheme();

	// Set per-thread global
	public static void setSchemeAs(AtomSpace as) {
		jni_set_scheme_as(as.jniPtr());
	}

	private static native void jni_set_scheme_as(long as_jni_ptr);

	// Return per-thread, per-atomspace singleton
	public static SchemeEval getEvaluator() {
		long jni_ptr = jni_get_evaluator();
		if (jni_ptr == 0) {
			return null;
		} else {
			return new SchemeEval(jni_ptr);
		}
	}

	private static native long jni_get_evaluator();

	public static SchemeEval getEvaluator(AtomSpace as) {
		long jni_ptr = jni_get_evaluator_as(as.jniPtr());
		if (jni_ptr == 0) {
			return null;
		} else {
			return new SchemeEval(jni_ptr);
		}
	}

	private static native long jni_get_evaluator_as(long as_jni_ptr);

	// The async-output interface.
	public void beginEval() {
		jni_begin_eval(jniPtr());
	}

	private native void jni_begin_eval(long jni_ptr);

	public void evalExpr(String text) {
		jni_eval_expr(jniPtr(), text);
	}

	private native void jni_eval_expr(long jni_ptr, String text);

	public String pollResult() {
		return jni_poll_result(jniPtr());
	}

	private native String jni_poll_result(long jni_ptr);

	public void interrupt() {
		jni_interrupt(jniPtr());
	}

	private native void jni_interrupt(long jni_ptr);

	// The synchronous-output interfaces.
	public String eval(String expr) { 
		beginEval(); 
		evalExpr(expr); 
		return pollResult(); 
	}

	/*
		// Evaluate expression, returning value.
		ValuePtr eval_v(const std::string&);
		ValuePtr eval_v(const std::stringstream& ss) { return eval_v(ss.str()); }

		// Evaluate expression, returning handle.
		Handle eval_h(const std::string& str) { return HandleCast(eval_v(str)); }
		Handle eval_h(const std::stringstream& ss) { return eval_h(ss.str()); }

		// Evaluate expression, returning TV.
		TruthValuePtr eval_tv(const std::string& str) { return TruthValueCast(eval_v(str)); }
		TruthValuePtr eval_tv(const std::stringstream& ss) { return eval_tv(ss.str()); }


		// Apply expression to args, returning Handle or TV
		virtual ValuePtr apply_v(const std::string& func, Handle varargs);
		Handle apply(const std::string& func, Handle varargs) {
			return HandleCast(apply_v(func, varargs)); }
		TruthValuePtr apply_tv(const std::string& func, Handle varargs) {
			return TruthValueCast(apply_v(func, varargs)); }

	*/

	// Evaluate expression, returning AtomSpace.
	public AtomSpace evalAs(String text) {
		long jni_ptr = jni_eval_as(jniPtr(), text);
		if (jni_ptr == 0) {
			return null;
		} else {
			return new AtomSpace(jni_ptr);
		}
	}

	private native long jni_eval_as(long jni_ptr, String text);

	// Nested invocations
	public boolean recursing() { 
		return jni_recursing(jniPtr()); 
	}

	private native boolean jni_recursing(long jni_ptr);

}
