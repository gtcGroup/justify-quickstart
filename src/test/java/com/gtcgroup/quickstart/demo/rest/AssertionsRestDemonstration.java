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
package com.gtcgroup.quickstart.demo.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.OneToOne;

import org.junit.jupiter.api.Test;

import com.gtcgroup.justify.core.testing.extension.JstConfigureTestingLogToConsole;
import com.gtcgroup.justify.jpa.testing.extension.JstConfigureTestingJPA;
import com.gtcgroup.justify.rest.testing.assertion.AssertionsREST;
import com.gtcgroup.justify.rest.testing.assertion.JstAssertRestPO;
import com.gtcgroup.justify.rest.testing.extension.JstConfigureTestingREST;
import com.gtcgroup.quickstart.constants.ConstantsQuickStart;
import com.gtcgroup.quickstart.de.QuickVacationDE;
import com.gtcgroup.quickstart.po.ConfigureTestingJpaPO;
import com.gtcgroup.quickstart.po.ConfigureTestingRestPO;
import com.gtcgroup.quickstart.to.NoteResponseTO;
import com.sun.research.ws.wadl.HTTPMethods;

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
@JstConfigureTestingREST(configureTestingRestPO = ConfigureTestingRestPO.class)
@JstConfigureTestingJPA(configureTestingJpaPO = ConfigureTestingJpaPO.class)
public class AssertionsRestDemonstration {

	@Test
	public void testList_happyPath_default_200() {

		AssertionsREST.assertList(JstAssertRestPO.withHttpMethod(HTTPMethods.GET.toString()).withPath("entity")
				.withRequestLogging().withResponseLogging());

	}

	@Test
	public void testSingle_404_FAKE_PATH() {

		AssertionsREST.assertSingle(NoteResponseTO.class, JstAssertRestPO.withHttpMethod(HTTPMethods.GET.toString())
				.withPath("FAKE_PATH").withRequestLogging().withResponseLogging().withValidHttpStatusCodes(404));
	}

	@Test
	public void testSingle_happyPath_default_200() {

		final NoteResponseTO note = AssertionsREST.assertSingle(NoteResponseTO.class,
				JstAssertRestPO.withHttpMethod(HTTPMethods.GET.toString())
						.withPath("entity/" + ConstantsQuickStart.QUICKSTART_NOTE_UUID).withRequestLogging()
						.withResponseLogging());

		assertEquals(ConstantsQuickStart.QUICKSTART_NOTE_TEXT, note.getText());

	}
}