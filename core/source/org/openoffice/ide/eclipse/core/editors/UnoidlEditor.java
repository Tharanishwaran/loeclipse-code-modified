/*************************************************************************
 *
 * $RCSfile: UnoidlEditor.java,v $
 *
 * $Revision: 1.3 $
 *
 * last change: $Author: cedricbosdo $ $Date: 2006/08/20 11:55:55 $
 *
 * The Contents of this file are made available subject to the terms of
 * either of the GNU Lesser General Public License Version 2.1
 *
 * Sun Microsystems Inc., October, 2000
 *
 *
 * GNU Lesser General Public License Version 2.1
 * =============================================
 * Copyright 2000 by Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, CA 94303, USA
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 * 
 * The Initial Developer of the Original Code is: Sun Microsystems, Inc..
 *
 * Copyright: 2002 by Sun Microsystems, Inc.
 *
 * All Rights Reserved.
 *
 * Contributor(s): Cedric Bosdonnat
 *
 *
 ************************************************************************/
package org.openoffice.ide.eclipse.core.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;
import org.openoffice.ide.eclipse.core.OOEclipsePlugin;

/**
 * Class handling the UNO-IDL text to render them in an Eclipse editor. In order
 * to fully understand the editor mechanisms, please report to Eclipse plugin
 * developer's guide.
 * 
 * @see UnoidlConfiguration for the viewer configuration
 * @see UnoidlDocumentProvider for the document provider
 * 
 * @author cbosdonnat
 *
 */
public class UnoidlEditor extends TextEditor {
	
	/**
	 * Member that listens to the preferences porperty changes 
	 */
	private IPropertyChangeListener mPropertyListener = new IPropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {
			getSourceViewer().invalidateTextPresentation();
			
		}
	};
	
	/**
	 * The color manager providing the colors for the editor
	 */
	private ColorProvider mColorManager;
	
	/**
	 * Default constructor setting the correct document provider and viewer
	 * configuration.
	 */
	public UnoidlEditor() {
		super();
		
		mColorManager = new ColorProvider();
		setSourceViewerConfiguration(new UnoidlConfiguration(mColorManager));
		setDocumentProvider(new UnoidlDocumentProvider());
		OOEclipsePlugin.getDefault().getPreferenceStore().addPropertyChangeListener(mPropertyListener);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#createSourceViewer(org.eclipse.swt.widgets.Composite, org.eclipse.jface.text.source.IVerticalRuler, int)
	 */
	protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
		return super.createSourceViewer(parent, ruler, styles);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPart#dispose()
	 */
	public void dispose() {
		mColorManager.dispose();
		OOEclipsePlugin.getDefault().getPreferenceStore().removePropertyChangeListener(mPropertyListener);
		super.dispose();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
	}
}