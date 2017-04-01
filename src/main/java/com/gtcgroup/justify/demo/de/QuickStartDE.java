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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

/**
 * Test Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
@Entity
@Table(name = "QUICK_START_DE")
@AttributeOverrides({ @AttributeOverride(name = "createUser", column = @Column(name = "QUICK_START_DE_CREATE_USER_C")),
		@AttributeOverride(name = "createTime", column = @Column(name = "QUICK_START_DE_CREATE_S")),
		@AttributeOverride(name = "updateUser", column = @Column(name = "QUICK_START_DE_LAST_UPDT_USER_C")),
		@AttributeOverride(name = "updateTime", column = @Column(name = "QUICK_START_DE_LAST_UPDT_S")),
		@AttributeOverride(name = "uuid", column = @Column(name = "QUICKSTART_DE_UUID")) })
@Cache(size = 100, alwaysRefresh = true, refreshOnlyIfNewer = true)
@NamedQuery(name = "queryQuickStartList", query = "SELECT s FROM QuickStartDE s")
public class QuickStartDE extends BaseUuidDE {

	private static final long serialVersionUID = 1L;

	@Column(name = "QUICK_START_DE_TEXT")
	private String text;

	/**
	 * @return {@link String}
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param text
	 * @return NoteDE
	 */
	public QuickStartDE setText(final String text) {
		this.text = text;
		return this;
	}
}