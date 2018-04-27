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

package com.gtcgroup.quickstart.ic;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gtcgroup.justify.core.base.JstBaseIC;
import com.gtcgroup.quickstart.bf.QuickStartBF;
import com.gtcgroup.quickstart.de.QuickNoteDE;
import com.gtcgroup.quickstart.to.NoteTO;

/**
 * An I/O Controller class used for demonstration.
 *
 * <p style="font-family:Verdana; font-size:10px; font-style:italic">
 * Copyright (c) 2006 - 2018 by Global Technology Consulting Group, Inc. at
 * <a href="http://gtcGroup.com">gtcGroup.com </a>.
 * </p>
 *
 * @author Marvin Toll
 * @since 8.5
 */
@Path("/entity")
public class QuickStartRestIC extends JstBaseIC {

	@GET
	@Path("/{uuid}")
	public Response getNote(@PathParam("uuid") final String uuid) {

		final NoteTO note = new NoteTO();
		note.setText(QuickStartBF.retrieveNote(uuid).getText());

		return Response.ok(note).type(MediaType.APPLICATION_JSON).build();
	}

	@GET
	public Response getNoteList() {

		final GenericEntity<List<QuickNoteDE>> genericEntity = new GenericEntity<List<QuickNoteDE>>(
				QuickStartBF.retrieveNoteList()) {
			// Empty Block
		};

		return Response.ok(genericEntity).type(MediaType.APPLICATION_JSON).build();
	}
}