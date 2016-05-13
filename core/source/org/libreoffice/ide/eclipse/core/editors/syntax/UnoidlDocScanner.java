/*************************************************************************
 *
 * $RCSfile: UnoidlDocScanner.java,v $
 *
 * $Revision: 1.4 $
 *
 * last change: $Author: cedricbosdo $ $Date: 2007/11/25 20:32:26 $
 *
 * The Contents of this file are made available subject to the terms of
 * the GNU Lesser General Public License Version 2.1
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
package org.libreoffice.ide.eclipse.core.editors.syntax;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.libreoffice.ide.eclipse.core.editors.idl.Colors;
import org.libreoffice.ide.eclipse.core.editors.utils.ColorProvider;

/**
 * UNO-IDL comment scanner. Used by the UNO-IDL viewer configuration. In order to fully understand the editor
 * mechanisms, please report to Eclipse plugin developer's guide.
 *
 *
 */
public class UnoidlDocScanner extends RuleBasedScanner {

    /**
     * Default constructor initializing the color manager to colorize.
     *
     * @param pColorManager
     *            the color manager used to provide the colors
     */
    public UnoidlDocScanner(ColorProvider pColorManager) {

        IToken body = new Token(new TextAttribute(pColorManager.getColor(Colors.C_AUTODOC_COMMENT)));

        IToken tag = new Token(new TextAttribute(pColorManager.getColor(Colors.C_AUTODOC_COMMENT), null, SWT.BOLD));
        IToken xmlTag = new Token(new TextAttribute(pColorManager.getColor(Colors.C_XML_TAG)));

        IRule[] rules = new IRule[2];
        rules[0] = new SingleLineRule("<", ">", xmlTag); //$NON-NLS-1$ //$NON-NLS-2$
        rules[1] = new RegexRule("@[a-zA-Z]+", tag); //$NON-NLS-1$

        setRules(rules);

        setDefaultReturnToken(body);
    }
}
