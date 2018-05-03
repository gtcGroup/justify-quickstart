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
package com.gtcgroup.quickstart.demo.jpa;

import static org.junit.jupiter.api.Assertions.assertAll;

import javax.persistence.OneToOne;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.testing.extension.JstConfigureTestingLogToConsole;
import com.gtcgroup.justify.core.testing.extension.JstConfigureTestingUserId;
import com.gtcgroup.justify.jpa.testing.assertion.AssertionsJPA;
import com.gtcgroup.justify.jpa.testing.extension.JstConfigureTestingJPA;
import com.gtcgroup.quickstart.constants.ConstantsQuickStart;
import com.gtcgroup.quickstart.de.QuickNoteDE;
import com.gtcgroup.quickstart.de.QuickVacationDE;
import com.gtcgroup.quickstart.po.ConfigureTestingJpaPO;
import com.gtcgroup.quickstart.populator.QuickStartTestingPopulator;

/**
 * This demonstration class verifies the {@link QuickVacationDE} cascade values
 * within the {@link OneToOne} annotations.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
@JstConfigureTestingLogToConsole
@JstConfigureTestingUserId(userId = "assertionsId")
@JstConfigureTestingJPA(configureTestingJpaPO = ConfigureTestingJpaPO.class)
public class AssertionsJpaExistsDemonstration {

	@Test
	public void testExistsInDatabase_happyPath() {

		assertAll(() -> {
			AssertionsJPA.assertExistsInDatabase(ConstantsQuickStart.QUICKSTART_PU,
					QuickStartTestingPopulator.getNotePopulated());
			AssertionsJPA.assertExistsInDatabase(ConstantsQuickStart.QUICKSTART_PU, QuickNoteDE.class,
					ConstantsQuickStart.QUICKSTART_NOTE_UUID);
		});
	}

	@Test
	public void testNotExistsInDatabase_happyPath() {

		final QuickNoteDE note = new QuickNoteDE();
		note.setUuid("fake_UUID");

		assertAll(() -> {
			AssertionsJPA.assertNotExistsInDatabase(ConstantsQuickStart.QUICKSTART_PU, note);
			AssertionsJPA.assertNotExistsInDatabase(ConstantsQuickStart.QUICKSTART_PU, QuickNoteDE.class, "fake_IDENTITY");
		});
	}
}