/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import javafx.geometry.Bounds;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

/**
 * @author ibirc
 *
 */
public class StorePrinterFx extends NodePrinter {
    private Rectangle printRectangle;
    Bounds nodeBound = null;

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
	nodeBound = node.getBoundsInLocal();

	Printer printer = Printer.getDefaultPrinter();
	super.setScale(1);
	super.setPrintRectangle(getPrintRectangle());
	PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT,
		Printer.MarginType.DEFAULT);
	double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInLocal().getWidth();
	double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInLocal().getHeight();
	node.getTransforms().add(new Scale(scaleX, scaleY));
	System.out.printf("Node BoundsInLocal width %.2f and height %.2f\n", node.getBoundsInLocal().getWidth(),
		node.getBoundsInLocal().getHeight());
	System.out.printf("Page Transform Scale X = %.2f, Y = %.2f\n", scaleX, scaleY);

	PrinterJob job = PrinterJob.createPrinterJob();

	if (job != null) {
	    boolean success = super.print(job, true, node);
	    // boolean success = job.printPage(node);
	    if (success) {
		job.endJob();
	    } else {
		System.out.println("Everything was NOT printed");
	    }
	}
    }

    public Rectangle getPrintRectangle() {
	if (printRectangle == null) {
	    printRectangle = new Rectangle(504, 684, null);
	    System.out.printf("Node Bounds: width: %.2f and height %.2f\n", nodeBound.getWidth(),
		    nodeBound.getHeight());
	    printRectangle.setStroke(Color.BLACK);
	}
	return printRectangle;
    }
}
