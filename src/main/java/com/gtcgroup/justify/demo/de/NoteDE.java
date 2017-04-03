/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2017 by
 * Global Technology Consulting Group, Inc. at
 * http://gtcGroup.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.gtcgroup.justify.demo.de;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "NOTE")
@AttributeOverrides({ @AttributeOverride(name = "uuid", column = @Column(name = "NOTE_UUID")) })
@SuppressWarnings("javadoc")
@NamedQueries({ @NamedQuery(name = "queryNoteList", query = "SELECT note FROM NoteDE note"),
		@NamedQuery(name = "queryNoteSingle", query = "SELECT note FROM NoteDE note WHERE note.text = 'test1Text' "), })
public class NoteDE extends BaseUuidDE {

	private static final long serialVersionUID = 1L;

	public static String STRING = "string";

	@Column(name = "NOTE_TEXT")
	private String text;

	@Transient
	private final String string = NoteDE.STRING;

	public String getString() {

		return this.string;
	}

	public String getText() {

		return this.text;
	}

	@SuppressWarnings("static-method")
	public String retrieveException() {

		throw new RuntimeException();
	}

	public NoteDE setText(final String text) {

		this.text = text;
		return this;
	}

}
