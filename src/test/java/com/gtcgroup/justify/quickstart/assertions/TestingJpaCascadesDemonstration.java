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

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.OneToOne;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import com.gtcgroup.justify.core.test.extension.JstConfigureTestLogToConsole;
import com.gtcgroup.justify.core.test.extension.JstConfigureTestUserId;
import com.gtcgroup.justify.jpa.test.assertion.AssertionsJPA;
import com.gtcgroup.justify.jpa.test.extension.JstConfigureTestJPA;
import com.gtcgroup.justify.jpa.test.po.JstAssertCascadePO;
import com.gtcgroup.justify.quickstart.de.BookingDE;
import com.gtcgroup.justify.quickstart.de.CustomerDE;
import com.gtcgroup.justify.quickstart.de.NoteDE;
import com.gtcgroup.justify.quickstart.populator.ConfigureJustifyWithPopulatorPO;
import com.gtcgroup.justify.quickstart.populator.ConstantsQuickStart;

/**
 * This test class verifies the {@link BookingDE} cascade values within the
 * {@link OneToOne} annotations.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 */
@JstConfigureTestLogToConsole
@JstConfigureTestUserId
@JstConfigureTestJPA(configureTestJpaPO = ConfigureJustifyWithPopulatorPO.class)
public class TestingJpaCascadesDemonstration {

	private static final String GET_NOTE = "getNote";
	private static final String GET_CUSTOMER = "getCustomer";

	private static BookingDE populateBooking() {

		final CustomerDE customerDE = new CustomerDE();
		final NoteDE noteDE = new NoteDE();

		final BookingDE bookingDE = new BookingDE();
		bookingDE.setNote(noteDE);
		bookingDE.setCustomer(customerDE);

		return bookingDE;
	}

	@Test
	public void testCascadeTypesForBooking_customerCascadeAll_incorrect() {

		assertThrows(AssertionFailedError.class, () -> {
			AssertionsJPA.assertCascadeTypes(JstAssertCascadePO.withPersistenceUnitName(ConstantsQuickStart.JUSTIFY_PU)
					.withPopulatedEntity(populateBooking()).withCascadeAll(GET_NOTE, GET_CUSTOMER)
					.withCleanupAfterVerification(GET_CUSTOMER));
		});
	}

	@Test
	public void testCascadeTypesForBooking_happyPath() {

		final JstAssertCascadePO assertJpaPO = JstAssertCascadePO
				.withPersistenceUnitName(ConstantsQuickStart.JUSTIFY_PU).withPopulatedEntity(populateBooking())
				.withCascadeAll(GET_NOTE).withCascadeAllExceptRemove(GET_CUSTOMER)
				.withCleanupAfterVerification(GET_CUSTOMER);

		AssertionsJPA.assertCascadeTypes(assertJpaPO);
	}

	@Test
	public void testCascadeTypesForBooking_happyPath_explicitPersistAndRemove() {

		final JstAssertCascadePO assertJpaPO = JstAssertCascadePO
				.withPersistenceUnitName(ConstantsQuickStart.JUSTIFY_PU).withPopulatedEntity(populateBooking())
				.withCascadePersist(GET_NOTE).withCascadeRemove(GET_NOTE).withCascadeAllExceptRemove(GET_CUSTOMER)
				.withCleanupAfterVerification(GET_CUSTOMER);

		AssertionsJPA.assertCascadeTypes(assertJpaPO);
	}

}