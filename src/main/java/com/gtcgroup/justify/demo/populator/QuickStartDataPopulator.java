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
package com.gtcgroup.justify.demo.populator;

import java.util.ArrayList;
import java.util.List;

import com.gtcgroup.justify.demo.de.QuickStartDE;
import com.gtcgroup.justify.jpa.helper.JstBaseDataPopulator;

/**
 * This Helper class provides support for populating test data.
 */
@SuppressWarnings("javadoc")
public class QuickStartDataPopulator extends JstBaseDataPopulator {

	public static Object ENTITY_IDENTITY;

	public static QuickStartDE quickStartDE;

	public static List<Object> quickStartList;

	/**
	 * @see JstBaseDataPopulator#populateCreateListTM(JstQueryRM)
	 */
	@Override
	public List<Object> populateCreateListTM(final String persistenceUnitName) {

		QuickStartDataPopulator.quickStartDE = new QuickStartDE().setUuid(ConstantsQuickStart.QUICKSTART_DE_UUID);

		QuickStartDataPopulator.ENTITY_IDENTITY = QuickStartDataPopulator.quickStartDE.getUuid();

		QuickStartDataPopulator.quickStartList = new ArrayList<>();

		QuickStartDataPopulator.quickStartList.add(QuickStartDataPopulator.quickStartDE);

		return QuickStartDataPopulator.quickStartList;
	}
}