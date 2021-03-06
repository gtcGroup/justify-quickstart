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
package com.gtcgroup.quickstart.de;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gtcgroup.justify.jpa.de.BaseUuidDE;

/**
 * This Domain {@link Entity} class demonstrates typical usage.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
@Entity
@Table(name = "CUSTOMER")
@AttributeOverride(name = "uuid", column = @Column(name = "CUSTOMER_UUID"))
public class QuickCustomerDE extends BaseUuidDE {

	private static final long serialVersionUID = 1L;

	@Column(name = "CUSTOMER_EMAIL_ADDRESS")
	private String emailAddress;

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public QuickCustomerDE setEmailAddress(final String emailAddress) {

		this.emailAddress = emailAddress;
		return this;
	}
}
