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
    private String _testsFolder = null;

    public Tester(String logFile) {
	_logFile = logFile;
    }

    public Tester testsFolder(String folder) {
	_testsFolder = folder;
	return this;
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
		tester.testAll();
	} catch (Throwable e) {
		e.printStackTrace();
	}

	System.out.println("\n---------");
    }

    public java.util.List<String> scmFiles() {
	java.util.List<String> files = new java.util.ArrayList<String>();
	files.add("/tests/scm/ConceptNode.scm");
	return files;
    }

    public void extractScmFiles() {
	if (_testsFolder != null) return;
	String tmpFolder = new java.io.File(_logFile).getParentFile().getAbsolutePath();
	java.util.List<String> files = scmFiles();
	for (int i = 0; i < files.size(); i++) {
		String fn = files.get(i);
		com.cogroid.atomspace.Loader.me().copyFileFromJar(fn, tmpFolder);
	}
    }

    public String readTextFile(String filename, String tmpFolder) {
	try {
		String textFile = new java.io.File(new java.io.File(tmpFolder), filename.substring(1)).getAbsolutePath();
		java.io.FileInputStream fis = new java.io.FileInputStream(textFile);
		java.lang.StringBuilder sb = new java.lang.StringBuilder();
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(fis));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\n");
		}
		fis.close();
		br.close();
		return sb.toString();
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
	return "";
    }

    public void testAll() {
	try {
		testPseudoValue();
		testAtomSpace();
		testSchemeEval();
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }

    public void testPseudoValue() {
	try {
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
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }

    public void testAtomSpace() {
	try {
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
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }

    public void testSchemeEval() {
	try {
		String log = "\n===== SchemeEval =====\n";
		writeLog(log);
		AtomSpace pv = new AtomSpace();
		SchemeEval se = new SchemeEval(pv);
		String tmpFolder = new java.io.File(_logFile).getParentFile().getAbsolutePath();
		extractScmFiles();
		java.util.List<String> files = scmFiles();
		try {
			SchemeEval.init_scheme();
		} catch (Throwable e) {
			writeLog(Loader.me().stackTrace(e));
		}
		for (int i = 0; i < files.size(); i++) {
			String fn = files.get(i);
			String text = readTextFile(fn, tmpFolder);
			try {
				writeLog("----- Eval: " + fn + " -----");
				String rs = "";
				se.begin_eval();
				writeLog("begin_eval();");
				se.eval_expr(text);
				writeLog("eval_expr();");
				rs = se.poll_result();
				writeLog("poll_result();");
				//String rs = se.eval(text);
				writeLog(rs);
			} catch (Throwable e) {
				writeLog(Loader.me().stackTrace(e));
			}
		}
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }
}
