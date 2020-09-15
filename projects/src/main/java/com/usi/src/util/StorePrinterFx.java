/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

/**
 * @author ibirc
 *
 */
public class StorePrinterFx extends NodePrinter {

    /**
     * 
     */
    public StorePrinterFx() {

    }

    /**
     * Scales the node based on the standard letter, portrait paper to be printed.
     * 
     * @param node The scene node to be printed.
     */
    public void print(final Node node) {
	Printer printer = Printer.getDefaultPrinter();
	super.setScale(1);
	PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT,
		Printer.MarginType.DEFAULT);
	double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
	double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
	node.getTransforms().add(new Scale(scaleX, scaleY));

	PrinterJob job = PrinterJob.createPrinterJob();

	if (job != null) {
	    // if (job.showPrintDialog(null) == true) {
	    boolean success = super.print(job, true, node);
	    // boolean success = job.printPage(node);
	    if (success) {
		job.endJob();
	    }
	    // }
	}
    }
}
