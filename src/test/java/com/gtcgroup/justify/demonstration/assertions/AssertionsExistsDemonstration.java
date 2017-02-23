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

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;

import com.gtcgroup.justify.core.rule.JstConfigureUserIdRule;
import com.gtcgroup.justify.core.rulechain.JstRuleChain;
import com.gtcgroup.justify.core.si.JstRuleChainSI;
import com.gtcgroup.justify.demo.de.SampleDE;
import com.gtcgroup.justify.demo.populator.ConstantsDemonstration;
import com.gtcgroup.justify.demo.populator.SampleDataPopulator;
import com.gtcgroup.justify.jpa.assertions.AssertionsJPA;
import com.gtcgroup.justify.jpa.helper.JstEntityManagerUtilHelper;
import com.gtcgroup.justify.jpa.rm.JstQueryJpaRM;
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
@SuppressWarnings("all")
public class AssertionsExistsDemonstration {

	@Rule
	public JstRuleChainSI ruleChain = JstRuleChain.outerRule(false)
			.around(JstConfigureJpaRule.withDataPopulator(ConstantsDemonstration.JUSTIFY_PU, SampleDataPopulator.class))
			.around(JstConfigureUserIdRule.withUserId());

	@Test
	public void demonstrateExistsInDatabaseWithEntityIdentities() {

		AssertionsJPA.assertExistsInDatabaseWithEntityIdentities(ConstantsDemonstration.JUSTIFY_PU, SampleDE.class,
				ConstantsDemonstration.SAMPLE_DE_UUID);
	}

	@Test
	public void demonstrateExistsInDatabaseWithPopulatedEntities() {

		AssertionsJPA.assertExistsInDatabaseWithEntities(ConstantsDemonstration.JUSTIFY_PU,
				SampleDataPopulator.sampleDE);
	}

	@Test
	public void demonstrateExistsInPersistenceContextWithModifiableEntities() {

		final boolean existsInPersistenceContext = JstEntityManagerUtilHelper
				.existsInPersistenceContextWithManagedEntities(
						AssertionsJPA.getEntityManager(ConstantsDemonstration.JUSTIFY_PU), findModifiableSampleDE());

		Assertions.assertThat(existsInPersistenceContext).isTrue();
	}

	@Test
	public void demonstrateExistsInPersistenceContextWithReadOnlyEntities() {

		final boolean existsInPersistenceContext = JstEntityManagerUtilHelper
				.existsInPersistenceContextWithManagedEntities(
						AssertionsJPA.getEntityManager(ConstantsDemonstration.JUSTIFY_PU), findReadOnlySampleDE());

		Assertions.assertThat(existsInPersistenceContext).isFalse();
	}

	@Test
	public void demonstrateExistsInSharedCacheWithEntityIdentities() {

		AssertionsJPA.assertExistsInSharedCacheWithEntityIdentities(ConstantsDemonstration.JUSTIFY_PU, SampleDE.class,
				ConstantsDemonstration.SAMPLE_DE_UUID);
	}

	@Test
	public void demonstrateExistsInSharedCacheWithPopulatedEntities() {

		AssertionsJPA.assertExistsInSharedCacheWithEntities(ConstantsDemonstration.JUSTIFY_PU,
				SampleDataPopulator.sampleDE);
	}

	private SampleDE findModifiableSampleDE() {

		final JstQueryJpaRM queryRM = new JstQueryJpaRM(
				AssertionsJPA.getEntityManager(ConstantsDemonstration.JUSTIFY_PU));

		return queryRM.findModifiableSingleOrException(SampleDE.class, ConstantsDemonstration.SAMPLE_DE_UUID);
	}

	private SampleDE findReadOnlySampleDE() {

		final JstQueryJpaRM queryRM = new JstQueryJpaRM(
				AssertionsJPA.getEntityManager(ConstantsDemonstration.JUSTIFY_PU));

		return queryRM.findReadOnlySingleOrException(SampleDE.class, ConstantsDemonstration.SAMPLE_DE_UUID);
	}
}
