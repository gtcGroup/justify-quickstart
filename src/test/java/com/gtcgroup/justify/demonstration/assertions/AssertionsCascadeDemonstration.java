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
package com.gtcgroup.justify.demonstration.assertions;

import org.junit.Rule;
import org.junit.Test;

import com.gtcgroup.justify.core.rule.JstConfigureUserIdRule;
import com.gtcgroup.justify.core.rulechain.JstRuleChain;
import com.gtcgroup.justify.core.si.JstRuleChainSI;
import com.gtcgroup.justify.demo.de.BookingDE;
import com.gtcgroup.justify.demo.de.CustomerDE;
import com.gtcgroup.justify.demo.de.NoteDE;
import com.gtcgroup.justify.demo.populator.ConstantsDemonstration;
import com.gtcgroup.justify.jpa.assertions.AssertionsJPA;
import com.gtcgroup.justify.jpa.assertions.JstAssertJpaPO;
import com.gtcgroup.justify.jpa.rule.JstConfigureJpaRule;

/**
 * Demonstration Class
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
@SuppressWarnings({ "javadoc", "static-method" })
public class AssertionsCascadeDemonstration {

	private static final String BOOKING_UUID = "bookingUUID";
	private static final String NOTE_UUID = "noteUUID";
	private static final String CUSTOMER_UUID = "customerUUID";

	@Rule
	public JstRuleChainSI ruleChain = JstRuleChain.outerRule(true).bindJulToLog4j(true)
	.around(JstConfigureJpaRule.withPersistenceUnitName(ConstantsDemonstration.JUSTIFY_PU))
	.around(JstConfigureUserIdRule.withUserId());

	@Test
	public void demonstrateCascadeTypesForBooking1() {

		final JstAssertJpaPO assertJpaPO = JstAssertJpaPO
				.withPopulatedEntity(ConstantsDemonstration.JUSTIFY_PU, populateBooking())
				.withCascadeAll(NoteDE.class, "note").withCascadeAllExceptRemove(CustomerDE.class, "customer");

		AssertionsJPA.assertCascadeTypes(assertJpaPO);
	}

	private BookingDE populateBooking() {

		final CustomerDE customerDE = new CustomerDE().setUuid(AssertionsCascadeDemonstration.CUSTOMER_UUID);
		final NoteDE noteDE = new NoteDE().setUuid(AssertionsCascadeDemonstration.NOTE_UUID);

		final BookingDE bookingDE = new BookingDE();
		bookingDE.setUuid(AssertionsCascadeDemonstration.BOOKING_UUID);
		bookingDE.setNote(noteDE);
		bookingDE.setCustomer(customerDE);

		return bookingDE;
	}
}