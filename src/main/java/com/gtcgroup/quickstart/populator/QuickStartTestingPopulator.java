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
package com.gtcgroup.quickstart.populator;

import java.util.ArrayList;
import java.util.List;

import com.gtcgroup.justify.jpa.testing.populator.JstBaseTestingPopulator;
import com.gtcgroup.quickstart.constants.ConstantsQuickStart;
import com.gtcgroup.quickstart.de.QuickCustomerDE;
import com.gtcgroup.quickstart.de.QuickNoteDE;
import com.gtcgroup.quickstart.de.QuickVacationDE;

/**
 * This Testing Populator class demonstrates typical usage.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
public class QuickStartTestingPopulator extends JstBaseTestingPopulator {

	private static QuickNoteDE note;

	public static QuickNoteDE getNotePopulated() {

		if (null == note) {
			final QuickNoteDE quickNoteDE = new QuickNoteDE();
			quickNoteDE.setUuid(ConstantsQuickStart.QUICKSTART_NOTE_UUID);
			quickNoteDE.setText(ConstantsQuickStart.QUICKSTART_NOTE_TEXT);
			QuickStartTestingPopulator.note = quickNoteDE;
		}
		return note;
	}

	private static QuickVacationDE populateVacation() {

		final QuickCustomerDE quickCustomerDE = new QuickCustomerDE();

		final QuickVacationDE quickVacationDE = new QuickVacationDE();
		quickVacationDE.setNote(getNotePopulated());
		quickVacationDE.setCustomer(quickCustomerDE);

		return quickVacationDE;
	}

	private final List<Object> populatedList = new ArrayList<>();

	/**
	 * @see JstBaseDataPopulator#populateCreateListTM(String)
	 */
	@Override
	public List<Object> populateCreateListTM(final String persistenceUnitName) {

		this.populatedList.add(populateVacation());

		return this.populatedList;
	}
}
