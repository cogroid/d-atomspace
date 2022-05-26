/*
 * opencog/java/Types.java
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

public class Types {
	public static int ATOM_SPACE = 1;
	public static int CONCEPT_NODE = 2;

	public static boolean hasNodeType(int type) {
		int[] types = {CONCEPT_NODE};
		for (int i = 0; i < types.length; i++) {
			if (types[i] == type) {
				return true;
			}
		}
		return false;
	}

	public static int refineNodeType(int type) {
		if (hasNodeType(type)) {
			return type;
		}
		return CONCEPT_NODE;
	}
}
