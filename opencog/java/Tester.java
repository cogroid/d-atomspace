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
    private boolean _runTestSchemeEval = false;

    public Tester(String logFile) {
	_logFile = logFile;
    }

    public boolean runTestSchemeEval() {
	return _runTestSchemeEval;
    }

    public Tester runTestSchemeEval(boolean value) {
	_runTestSchemeEval = value;
	return this;
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
		if (args.length < 3) {
			System.out.println("\nTester.class <-f|-j|-l> <-x64|-i386|-armv7> <libFolder|tmpFolder|logFolder> \n");
			return;
		}
		if ("-x64".equals(args[1])) {
			Loader.me().machine("x64");
		} else if ("-i386".equals(args[1])) {
			Loader.me().machine("i386");
		} else if ("-armv7".equals(args[1])) {
			Loader.me().machine("armv7");
		}
		if ("-f".equals(args[0])) {
			Loader.me().loadFromFolder(args[2]);
		} else if ("-j".equals(args[0])) {
			Loader.me().loadFromJar(args[2]);
		} else if ("-l".equals(args[0])) {
			Loader.me().loadWithLog(args[2]);
		} else {
			System.out.println("\nTester.class <-f|-j|-l> <-x64|-i386|-armv7> <libFolder|tmpFolder|logFolder> \n");
			return;
		}

		String logFile = args[2] + "/testing.txt";

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
		if (Loader.me().is_armv7()) {
			if (runTestSchemeEval()) {
				testSchemeEval();
			}
		} else {
			testSchemeEval();
		}
		testNode();
		testConceptNode();
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }

    public void testPseudoValue() {
	try {
		String log = "\n===== PseudoValue =====\n";
		writeLog(log);
		PseudoValue pv = new PseudoValue(1);
		log = "isAtom: " + pv.isAtom();
		writeLog(log);
		log = "isNode: " + pv.isNode();
		writeLog(log);
		log = "isLink: " + pv.isLink();
		writeLog(log);
		log = "isType: " + pv.isType(1);
		writeLog(log);
		pv.dispose();
		log = "disposed: " + pv.disposed();
		writeLog(log);
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }

    public void testNode() {
	try {
		String log = "\n===== Node =====\n";
		writeLog(log);
		AtomSpace as = new AtomSpace();
		Node pv = new Node(as, Types.CONCEPT_NODE, "dream");
		log = "isAtom: " + pv.isAtom();
		writeLog(log);
		log = "isNode: " + pv.isNode();
		writeLog(log);
		log = "isLink: " + pv.isLink();
		writeLog(log);
		log = "getName: " + pv.getName();
		writeLog(log);
		log = "getArity: " + pv.getArity();
		writeLog(log);
		log = "toString: " + pv.toString();
		writeLog(log);
		log = "toShortString: " + pv.toShortString();
		writeLog(log);
		pv.dispose();
		log = "disposed: " + pv.disposed();
		writeLog(log);
	} catch (Throwable e) {
		writeLog(Loader.me().stackTrace(e));
	}
    }

    public void testConceptNode() {
	try {
		String log = "\n===== Concept Node =====\n";
		writeLog(log);
		AtomSpace as = new AtomSpace();
		ConceptNode pv = new ConceptNode(as, "dream");
		ConceptNode pv_b = new ConceptNode(as, "dream");
		log = "isAtom: " + pv.isAtom();
		writeLog(log);
		log = "isNode: " + pv.isNode();
		writeLog(log);
		log = "isLink: " + pv.isLink();
		writeLog(log);
		log = "getName: " + pv.getName();
		writeLog(log);
		log = "getArity: " + pv.getArity();
		writeLog(log);
		log = "toString: " + pv.toString();
		writeLog(log);
		log = "toShortString: " + pv.toShortString();
		writeLog(log);
		log = "equals(true, dream): " + pv.equals(pv);
		writeLog(log);
		log = "equals(false, dream): " + pv.equals(pv_b);
		writeLog(log);
		log = "equals(false, atomspace): " + pv.equals(as);
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
		log = "isAtom: " + pv.isAtom();
		writeLog(log);
		log = "isNode: " + pv.isNode();
		writeLog(log);
		log = "isLink: " + pv.isLink();
		writeLog(log);
		log = "isType: " + pv.isType(1);
		writeLog(log);
		log = "getHash: " + pv.getHash();
		writeLog(log);
		log = "toString(indent): " + pv.toString("indent");
		writeLog(log);
		log = "toShortString(indent): " + pv.toShortString("indent");
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
		try {
			SchemeEval.initScheme();
		} catch (Throwable e) {
			writeLog(Loader.me().stackTrace(e));
		}
		AtomSpace pv = new AtomSpace();
		SchemeEval se = new SchemeEval(pv);
		String tmpFolder = new java.io.File(_logFile).getParentFile().getAbsolutePath();
		extractScmFiles();
		java.util.List<String> files = scmFiles();
		for (int i = 0; i < files.size(); i++) {
			String fn = files.get(i);
			String text = readTextFile(fn, tmpFolder);
			try {
				writeLog("----- Eval: " + fn + " -----");
				String rs = "";
				se.beginEval();
				writeLog("beginEval();");
				se.evalExpr(text);
				writeLog("evalExpr();");
				rs = se.pollResult();
				writeLog("pollResult();");
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
