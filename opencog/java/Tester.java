/*
 * opencog/java/Tester.java
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

public class Tester {

    static {
	try {
		java.util.List<String> libs = new java.util.ArrayList<String>();
		libs.add("datomspace");

		/*
		libs.add("atomspace");
		libs.add("atombase");
		libs.add("atom_types");
		libs.add("atomcore");
		libs.add("execution");
		libs.add("atomflow");
		libs.add("foreign");
		libs.add("grounded");
		libs.add("join");
		libs.add("parallel");
		libs.add("pattern");
		libs.add("clearbox");
		libs.add("truthvalue");
		libs.add("value");
		libs.add("exec");
		libs.add("guile-uuid");
		libs.add("logger");
		libs.add("randgen");
		libs.add("type-utils");
		libs.add("smob");
		libs.add("persist");
		libs.add("json");
		libs.add("load_scm");
		libs.add("persist-file");
		libs.add("sexpr");
		libs.add("storage-types");
		libs.add("tlb");
		libs.add("query-engine");
		*/

		for (int i = 0; i < libs.size(); i++) {
			String libname = libs.get(i);
			try {
				System.loadLibrary(libname);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	} catch (Throwable e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	System.out.println("\n---------");

	try {
		testPseudoValue();
		testAtomSpace();
	} catch (Throwable e) {
		e.printStackTrace();
	}

	System.out.println("\n---------");
    }

    private static void testPseudoValue() {
	System.out.println("\n===== PseudoValue =====\n");
	PseudoValue pv = new PseudoValue(1);
	System.out.println("is_atom: " + pv.is_atom());
	System.out.println("is_node: " + pv.is_node());
	System.out.println("is_link: " + pv.is_link());
	System.out.println("is_type: " + pv.is_type(1));
	pv.dispose();
	System.out.println("disposed: " + pv.disposed());
    }

    private static void testAtomSpace() {
	System.out.println("\n===== AtomSpace =====\n");
	AtomSpace as = new AtomSpace();
	System.out.println("is_atom: " + as.is_atom());
	System.out.println("is_node: " + as.is_node());
	System.out.println("is_link: " + as.is_link());
	System.out.println("is_type: " + as.is_type(1));
	System.out.println("hash: " + as.get_hash());
	as.dispose();
	System.out.println("disposed: " + as.disposed());
    }
}
