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

package com.gtcgroup.quickstart.multicore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import com.gtcgroup.justify.core.testing.helper.internal.LogTestConsoleUtilHelper;
import com.gtcgroup.quickstart.de.QuickMulticoreDE;

/**
 * This {@link RecursiveTask} class demonstrates typical usage.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
public class QuickStartRecursiveTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private final int actualNumberOfElements;
	private final List<QuickMulticoreDE> multicoreList;

	private final int minimumNumberOfElements;
	private final int start;
	private final int end;

	public QuickStartRecursiveTask(final List<QuickMulticoreDE> multicoreList, final int minimumNumberOfElements) {

		this.multicoreList = multicoreList;
		this.minimumNumberOfElements = minimumNumberOfElements;
		this.start = 0;
		this.end = multicoreList.size() - 1;
		this.actualNumberOfElements = multicoreList.size();
	}

	protected QuickStartRecursiveTask(final List<QuickMulticoreDE> multicoreList, final int minimumNumberOfElements,
			final int start, final int end, final int actualNumberOfElements) {

		this.multicoreList = multicoreList;
		this.minimumNumberOfElements = minimumNumberOfElements;
		this.start = start;
		this.end = end;
		this.actualNumberOfElements = actualNumberOfElements;
	}

	@SuppressWarnings("boxing")
	@Override
	protected Integer compute() {

		if (this.minimumNumberOfElements <= this.actualNumberOfElements) {

			return ForkJoinTask.invokeAll(splitSubtasks()).stream().mapToInt(ForkJoinTask::join).sum();

		}
		return processList();
	}

	protected int processList() {

		// Simulate processing to force use of multiple worker threads.
		try {
			Thread.sleep(1);
		} catch (@SuppressWarnings("unused") final InterruptedException e) {
			// Ignore
		}

		LogTestConsoleUtilHelper.logRedToConsole("\t\tProcess [" + (this.end + 1 - this.start) + "] domain entities: "
				+ Thread.currentThread().getName());

		return 1; // Represents the number of tasks processed.
	}

	private Collection<QuickStartRecursiveTask> splitSubtasks() {

		final List<QuickStartRecursiveTask> taskList = new ArrayList<>();

		final int actualElementNumber = (this.end - this.start) / 2 - 1;
		final int middle = actualElementNumber + this.start;

		taskList.add(new QuickStartRecursiveTask(this.multicoreList, this.minimumNumberOfElements, this.start, middle,
				actualElementNumber));

		taskList.add(new QuickStartRecursiveTask(this.multicoreList, this.minimumNumberOfElements, middle + 1, this.end,
				actualElementNumber));

		return taskList;
	}
}