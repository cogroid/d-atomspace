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

    private String _logFile;

    public Tester(String logFile) {
	_logFile = logFile;
    }

    public void writeLog(String text) {
	Loader.me().writeLog(_logFile, text);
	System.out.println("\n" + text);
    }

    public static void main(String[] args) {
	System.out.println("\n---------");

	try {
		if (args.length < 2) {
			System.out.println("\nTester.class <-f|-j|-l> <libFolder|tmpFolder|logFolder> \n");
			return;
		}
		if ("-f".equals(args[0])) {
			Loader.me().loadFromFolder(args[1]);
		} else if ("-j".equals(args[0])) {
			Loader.me().loadFromJar(args[1]);
		} else if ("-l".equals(args[0])) {
			Loader.me().loadWithLog(args[1]);
		} else {
			System.out.println("\nTester.class <-f|-j|-l> <libFolder|tmpFolder|logFolder> \n");
			return;
		}

		String logFile = args[1] + "/testing.txt";

		Tester tester = new Tester(logFile);
		tester.testPseudoValue();
		tester.testAtomSpace();
	} catch (Throwable e) {
		e.printStackTrace();
	}

	System.out.println("\n---------");
    }

    public void testPseudoValue() {
	String log = "\n===== PseudoValue =====\n";
	writeLog(log);
	PseudoValue pv = new PseudoValue(1);
	log = "is_atom: " + pv.is_atom();
	writeLog(log);
	log = "is_node: " + pv.is_node();
	writeLog(log);
	log = "is_link: " + pv.is_link();
	writeLog(log);
	log = "is_type: " + pv.is_type(1);
	writeLog(log);
	pv.dispose();
	log = "disposed: " + pv.disposed();
	writeLog(log);
    }

    public void testAtomSpace() {
	String log = "\n===== AtomSpace =====\n";
	writeLog(log);
	AtomSpace pv = new AtomSpace();
	log = "is_atom: " + pv.is_atom();
	writeLog(log);
	log = "is_node: " + pv.is_node();
	writeLog(log);
	log = "is_link: " + pv.is_link();
	writeLog(log);
	log = "is_type: " + pv.is_type(1);
	writeLog(log);
	log = "hash: " + pv.get_hash();
	writeLog(log);
	log = "to_string_indent: " + pv.to_string("indent");
	writeLog(log);
	log = "to_short_string_indent: " + pv.to_short_string("indent");
	writeLog(log);
	pv.getOutgoingSet();
	pv.dispose();
	log = "disposed: " + pv.disposed();
	writeLog(log);
    }
}
