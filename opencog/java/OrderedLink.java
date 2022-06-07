/*
 * opencog/java/OrderedLink.java
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

public class OrderedLink extends Link {
	public OrderedLink(AtomSpace atomspace, java.util.List<Atom> outgoing) {
		super(atomspace, Types.ORDERED_LINK, outgoing);
	}

	public OrderedLink(AtomSpace atomspace, Atom a) {
		super(atomspace, Types.ORDERED_LINK, a);
	}

	public OrderedLink(AtomSpace atomspace, Atom a, Atom b) {
		super(atomspace, Types.ORDERED_LINK, a, b);
	}

	public OrderedLink(AtomSpace atomspace, Atom a, Atom b, Atom c) {
		super(atomspace, Types.ORDERED_LINK, a, b, c);
	}

	public OrderedLink(AtomSpace atomspace, Atom a, Atom b, Atom c, Atom d) {
		super(atomspace, Types.ORDERED_LINK, a, b, c, d);
	}
}
