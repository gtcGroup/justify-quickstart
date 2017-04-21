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

package com.gtcgroup.justify.demo.assertions;

import org.junit.Rule;
import org.junit.Test;

import com.gtcgroup.justify.core.rulechain.JstRuleChain;
import com.gtcgroup.justify.core.si.JstRuleChainSI;
import com.gtcgroup.justify.demo.de.QuickStartDE;
import com.gtcgroup.justify.demo.populator.ConstantsQuickStart;
import com.gtcgroup.justify.demo.populator.QuickStartDataPopulator;
import com.gtcgroup.justify.jpa.assertions.AssertionsJPA;
import com.gtcgroup.justify.jpa.rule.JstConfigureJpaRule;

/**
 * This demonstration class contains a test verifying the anticipated use of the
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2017 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since v3.0
 */
@SuppressWarnings("all")
public class AssertionsExistsDemonstration {

	@Rule
	public JstRuleChainSI ruleChain = JstRuleChain.outerRule(false).around(JstConfigureJpaRule
			.withPersistence(ConstantsQuickStart.JUSTIFY_PU).withDataPopulators(QuickStartDataPopulator.class));

	@Test
	public void demonstrateExistsInDatabaseWithEntities() {

		AssertionsJPA.assertExistsInDatabaseWithEntities(ConstantsQuickStart.JUSTIFY_PU,
				QuickStartDataPopulator.quickStartDE);
	}

	@Test
	public void demonstrateExistsInDatabaseWithEntityIdentity() {

		AssertionsJPA.assertExistsInDatabaseWithEntityIdentities(ConstantsQuickStart.JUSTIFY_PU, QuickStartDE.class,
				ConstantsQuickStart.QUICKSTART_DE_UUID);
	}
}
