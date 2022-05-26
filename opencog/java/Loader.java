/*
 * opencog/java/Loader.java
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

public class Loader {
	private static Loader __me;
	private String _machine = "armv7";

	public static Loader me() {
		if (__me == null) {
			__me = new Loader();
		}
		return __me;
	}

	public Loader() { }

	public String machine() {
		return _machine;
	}

	public Loader machine(String value) {
		_machine = value;
		return this;
	}

	public boolean is_armv7() {
		return "armv7".equals(_machine);
	}

	public boolean is_x64() {
		return "x64".equals(_machine);
	}

	public boolean is_i386() {
		return "i386".equals(_machine);
	}

	public java.util.List<String> requires() {
		java.util.List<String> libs = new java.util.ArrayList<String>();
		if (is_armv7()) {
			libs.add("fixing");
			libs.add("ltdl");
			libs.add("ffi");
			libs.add("gmp");
			libs.add("gc");
			libs.add("c++");
			libs.add("boost_system");
			libs.add("boost_filesystem");
			libs.add("boost_thread");
			libs.add("boost_program_options");
			libs.add("charset");
			libs.add("iconv");
			libs.add("guile-2.2");
			libs.add("cogutil");
			libs.add("datomspace");
		}
		if (is_x64()) {
			libs.add("gc");
			libs.add("boost_system");
			libs.add("boost_filesystem");
			libs.add("guile-2.2");
			libs.add("cogutil");
			libs.add("datomspace");
		}
		if (is_i386()) {
			libs.add("ffi");
			libs.add("gc");
			libs.add("boost_system");
			libs.add("boost_filesystem");
			libs.add("boost_thread");
			libs.add("boost_program_options");
			libs.add("guile-2.2");
			libs.add("cogutil");
			libs.add("datomspace");
		}
		return libs;
	}

	public Loader loadWithLog(String logFolder) {
		java.util.List<String> libs = requires();
		String logFile = new java.io.File(new java.io.File(logFolder), "datomspace-load.txt").getAbsolutePath();
		writeLog(logFile, "Loading dAtomSpace's native libraries ...");

		for (int i = 0; i < libs.size(); i++) {
			String libname = libs.get(i);
			try {
				System.loadLibrary(libname);
				writeLog(logFile, "lib" + libname + ".so loaded ...");
			} catch (Throwable e) {
				e.printStackTrace();
				writeLog(logFile, "Failed to load lib" + libname + ".so:\n" + stackTrace(e));
			}
		}

		return this;
	}

	public Loader loadFromFolder(String libFolder) {
		java.util.List<String> libs = requires();
		String logFile = new java.io.File(new java.io.File(libFolder), "datomspace-load.txt").getAbsolutePath();
		writeLog(logFile, "Loading dAtomSpace's native libraries ...");

		java.io.File parent = new java.io.File(libFolder);
		for (int i = 0; i < libs.size(); i++) {
			String libname = libs.get(i);
			try {
				java.io.File f = new java.io.File(parent, "lib" + libname + ".so");
				if (!f.exists()) {
					f = new java.io.File(parent, "lib" + libname + ".a");
				}
				System.load(f.getAbsolutePath());
				writeLog(logFile, "lib" + libname + ".so loaded ...");
			} catch (Throwable e) {
				e.printStackTrace();
				writeLog(logFile, "Failed to load lib" + libname + ".so:\n" + stackTrace(e));
			}
		}

		return this;
	}

	public Loader copyFileFromJar(String filename, String tmpFolder) {
		try {
			java.io.File tagFile = new java.io.File(new java.io.File(tmpFolder), filename.substring(1));
			tagFile.getParentFile().mkdirs();
			String resourceName = filename;
			java.io.InputStream stream = Loader.class.getResourceAsStream(resourceName);
			int readBytes;
			byte[] buffer = new byte[4096];
			java.io.OutputStream resStreamOut = new java.io.FileOutputStream(tagFile);
			while ((readBytes = stream.read(buffer, 0, buffer.length)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
			stream.close();
			resStreamOut.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return this;
	}

	public Loader copyLibFromJar(String libName, String tmpFolder) {
		try {
			java.io.File tagFile = new java.io.File(new java.io.File(tmpFolder), "lib" + libName + ".so");
			String resourceName = "/lib" + libName + ".so";
			java.io.InputStream stream = Loader.class.getResourceAsStream(resourceName);
			int readBytes;
			byte[] buffer = new byte[4096];
			try {
				if ((readBytes = stream.read(buffer, 0, buffer.length)) == 0) {
					throw new Exception("");
				}
				stream = Loader.class.getResourceAsStream(resourceName);
			} catch (Throwable e) {
				resourceName = "/lib" + libName + ".a";
				stream = Loader.class.getResourceAsStream(resourceName);
				tagFile = new java.io.File(new java.io.File(tmpFolder), "lib" + libName + ".a");
			}
			java.io.OutputStream resStreamOut = new java.io.FileOutputStream(tagFile);
			while ((readBytes = stream.read(buffer, 0, buffer.length)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
			stream.close();
			resStreamOut.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return this;
	}

	public Loader writeLog(String logFile, String text) {
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(new java.io.File(logFile), true);
			fos.write(("\n" + text + "\n").getBytes("UTF-8"));
			fos.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return this;
	}

	public String stackTrace(Throwable e) {
		java.io.StringWriter sw = new java.io.StringWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public Loader loadFromJar(String tmpFolder) {
		String logFile = new java.io.File(new java.io.File(tmpFolder), "datomspace-load.txt").getAbsolutePath();
		java.util.List<String> libs = requires();
		writeLog(logFile, "Loading dAtomSpace's native libraries ...");
		java.io.File parent = new java.io.File(tmpFolder);
		for (int i = 0; i < libs.size(); i++) {
			String libname = libs.get(i);
			try {
				copyLibFromJar(libname, tmpFolder);
				java.io.File f = new java.io.File(parent, "lib" + libname + ".so");
				if (!f.exists()) {
					f = new java.io.File(parent, "lib" + libname + ".a");
				}
				System.load(f.getAbsolutePath());
				writeLog(logFile, "lib" + libname + ".so loaded ...");
			} catch (Throwable e) {
				e.printStackTrace();
				writeLog(logFile, "Failed to load lib" + libname + ".so:\n" + stackTrace(e));
			}
		}

		return this;
	}
}
