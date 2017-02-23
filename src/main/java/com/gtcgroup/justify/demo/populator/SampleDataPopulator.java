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

import com.gtcgroup.justify.demo.de.SampleDE;
import com.gtcgroup.justify.jpa.helper.JstBaseDataPopulator;
import com.gtcgroup.justify.jpa.rm.JstQueryJpaRM;

/**
 * @since v. 1.0
 */
@SuppressWarnings("javadoc")
public class SampleDataPopulator extends JstBaseDataPopulator {

	public static Object ENTITY_IDENTITY;

	public static SampleDE sampleDE;

	/**
	 * @see JstBaseDataPopulator#populateCreateListTM(JstQueryRM)
	 */
	@Override
	public List<Object> populateCreateListTM(final JstQueryJpaRM queryRM) {

		SampleDataPopulator.sampleDE = new SampleDE().setUuid(ConstantsDemonstration.SAMPLE_DE_UUID);

		SampleDataPopulator.ENTITY_IDENTITY = SampleDataPopulator.sampleDE.getUuid();

		final List<Object> deList = new ArrayList<Object>();

		deList.add(SampleDataPopulator.sampleDE);

		return deList;
	}
}