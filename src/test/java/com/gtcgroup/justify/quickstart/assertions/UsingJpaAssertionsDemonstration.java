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
package com.gtcgroup.justify.quickstart.assertions;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestUserId;
import com.gtcgroup.justify.jpa.test.assertion.AssertionsJPA;
import com.gtcgroup.justify.jpa.test.extension.JstConfigureTestJPA;
import com.gtcgroup.justify.quickstart.de.NoteDE;
import com.gtcgroup.justify.quickstart.populator.ConfigureJustifyWithPopulatorPO;
import com.gtcgroup.justify.quickstart.populator.ConstantsQuickStart;
import com.gtcgroup.justify.quickstart.populator.NoteDataPopulator;

/**
 * This test class demonstrates JPA convenience assertions using the
 * {@link AssertionsJPA} enum.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
@JstConfigureTestLogToConsole
@JstConfigureTestUserId(userId = "assertionsId")
@JstConfigureTestJPA(configureTestJpaPO = ConfigureJustifyWithPopulatorPO.class)
public class UsingJpaAssertionsDemonstration {

	@Test
	public void testExistsInDatabase_happyPath() {

		assertAll(() -> {
			AssertionsJPA.assertExistsInDatabase(ConstantsQuickStart.JUSTIFY_PU, NoteDataPopulator.getNotePopulated());
			AssertionsJPA.assertExistsInDatabase(ConstantsQuickStart.JUSTIFY_PU, NoteDE.class,
					ConstantsQuickStart.QUICKSTART_NOTE_UUID);
		});
	}

	@Test
	public void testNotExistsInDatabase_happyPath() {

		assertAll(() -> {
			AssertionsJPA.assertNotExistsInDatabase(ConstantsQuickStart.JUSTIFY_PU, new NoteDE().setUuid("fake_UUID"));
			AssertionsJPA.assertNotExistsInDatabase(ConstantsQuickStart.JUSTIFY_PU, NoteDE.class, "fake_IDENTITY");
		});
	}
}