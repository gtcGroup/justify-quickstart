/*
 * [Licensed per the Open Source "MIT License".]
 *
 * Copyright (c) 2006 - 2018 by
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
package com.gtcgroup.justify.quickstart.de;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gtcgroup.justify.jpa.de.BaseUuidDE;

@Entity
@Table(name = "VACATION")
@AttributeOverride(name = "uuid", column = @Column(name = "VACATION_UUID"))
public class QuickVacationDE extends BaseUuidDE {

	private static final long serialVersionUID = 1L;

	@Column(name = "VACATION_DESTINATION")
	private String destination;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "NOTE_UUID", referencedColumnName = "NOTE_UUID")
	private QuickNoteDE note;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "CUSTOMER_UUID", referencedColumnName = "CUSTOMER_UUID")
	private QuickCustomerDE customer;

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

	public QuickCustomerDE getCustomer() {

		return this.customer;
	}

	public String getDestination() {
		return this.destination;
	}

	public QuickNoteDE getNote() {
		return this.note;
	}

	public void setCustomer(final QuickCustomerDE quickCustomerDE) {

		this.customer = quickCustomerDE;
	}

	public void setDestination(final String destination) {

		this.destination = destination;
	}

	public void setNote(final QuickNoteDE quickNoteDE) {

		this.note = quickNoteDE;
	}
}
