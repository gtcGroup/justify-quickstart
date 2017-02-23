//****************************************************************
//* Copyright (c) 2016 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.gtcgroup.justify.demo.de;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class is a Domain Entity.
 *
 * @since jab-v7.0
 */
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
