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
package com.gtcgroup.quickstart.demo.assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.OneToOne;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import com.gtcgroup.justify.core.testing.extension.JstConfigureTestLogToConsole;
import com.gtcgroup.justify.jpa.testing.assertion.AssertionsJPA;
import com.gtcgroup.justify.jpa.testing.assertion.JstAssertCascadePO;
import com.gtcgroup.justify.jpa.testing.extension.JstConfigureTestingJPA;
import com.gtcgroup.quickstart.de.QuickCustomerDE;
import com.gtcgroup.quickstart.de.QuickNoteDE;
import com.gtcgroup.quickstart.de.QuickVacationDE;
import com.gtcgroup.quickstart.populator.ConfigureTestingJpaPO;
import com.gtcgroup.quickstart.populator.ConstantsQuickStart;

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
 */
@JstConfigureTestLogToConsole
@JstConfigureTestingJPA(configureTestJpaPO = ConfigureTestingJpaPO.class)
public class JpaCascadeTypesDemonstration {

	private static final String GET_NOTE = "getNote";
	private static final String GET_CUSTOMER = "getCustomer";

	private static QuickVacationDE populateVacation() {

		final QuickCustomerDE quickCustomerDE = new QuickCustomerDE();
		final QuickNoteDE quickNoteDE = new QuickNoteDE();

		final QuickVacationDE quickVacationDE = new QuickVacationDE();
		quickVacationDE.setNote(quickNoteDE);
		quickVacationDE.setCustomer(quickCustomerDE);

		return quickVacationDE;
	}

	@Test
	public void testCascadeTypesForVacation_customerCascadeAll_incorrect() {

		assertThrows(AssertionFailedError.class, () -> {
			AssertionsJPA.assertCascadeTypes(JstAssertCascadePO.withPersistenceUnitName(ConstantsQuickStart.JUSTIFY_PU)
					.withPopulatedEntity(populateVacation()).withCascadeAll(GET_NOTE, GET_CUSTOMER)
					.withCleanupAfterVerification(GET_CUSTOMER));
		});
	}

	@Test
	public void testCascadeTypesForVacation_happyPath_usingConvenienceMethodForCascadeAll() {

		final JstAssertCascadePO assertJpaPO = JstAssertCascadePO
				.withPersistenceUnitName(ConstantsQuickStart.JUSTIFY_PU).withPopulatedEntity(populateVacation())
				.withCascadeAll(GET_NOTE).withCascadeAllExceptRemove(GET_CUSTOMER)
				.withCleanupAfterVerification(GET_CUSTOMER);

		AssertionsJPA.assertCascadeTypes(assertJpaPO);
	}

	@Test
	public void testCascadeTypesForVacation_happyPath_usingExplicitPersistAndRemoveMethods() {

		final JstAssertCascadePO assertJpaPO = JstAssertCascadePO
				.withPersistenceUnitName(ConstantsQuickStart.JUSTIFY_PU).withPopulatedEntity(populateVacation())
				.withCascadePersist(GET_NOTE).withCascadeRemove(GET_NOTE).withCascadeAllExceptRemove(GET_CUSTOMER)
				.withCleanupAfterVerification(GET_CUSTOMER);

		AssertionsJPA.assertCascadeTypes(assertJpaPO);
	}

}