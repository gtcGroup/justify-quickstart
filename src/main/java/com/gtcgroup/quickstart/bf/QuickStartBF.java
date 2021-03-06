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

package com.gtcgroup.quickstart.bf;

import java.util.List;
import java.util.Optional;

import com.gtcgroup.justify.jpa.po.JstFindSinglePO;
import com.gtcgroup.justify.jpa.po.JstQueryAllJPO;
import com.gtcgroup.justify.jpa.rm.JstQueryFindRM;
import com.gtcgroup.quickstart.constants.ConstantsQuickStart;
import com.gtcgroup.quickstart.de.QuickNoteDE;
import com.gtcgroup.quickstart.helper.QuickStartUtilHelper;

/**
 * This Business Facade class demonstrates typical usage.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
public enum QuickStartBF {

	INSTANCE;

	public static QuickNoteDE retrieveNote(final String uuid) {

		final Optional<QuickNoteDE> optionalNote = JstQueryFindRM
				.findSingle(JstFindSinglePO.withPersistenceUnitName(ConstantsQuickStart.QUICKSTART_PU)
						.withEntityClass(QuickNoteDE.class).withEntityIdentity(uuid));

		return QuickStartUtilHelper.unpackOptional(optionalNote, "The UUID [" + uuid + "] could not be found.");
	}

	public static List<QuickNoteDE> retrieveNoteList() {

		final Optional<List<QuickNoteDE>> optionalList = JstQueryFindRM.queryAll(JstQueryAllJPO
				.withPersistenceUnitName(ConstantsQuickStart.QUICKSTART_PU).withEntityClass(QuickNoteDE.class));

		return QuickStartUtilHelper.unpackOptional(optionalList, "A note list could not be found.");

	}
}