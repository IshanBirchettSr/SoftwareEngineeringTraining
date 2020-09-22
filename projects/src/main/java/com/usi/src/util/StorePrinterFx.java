/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import java.util.List;

import javafx.geometry.Bounds;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
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
     * @param list The scene node to be printed.
     */
    public void print(final List<Node> list) {

	Printer printer = Printer.getDefaultPrinter();
	nodeBound = list.get(0).getBoundsInLocal();
	super.setPrintRectangle(getPrintRectangle());
	PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT,
		Printer.MarginType.DEFAULT);
//	for (Node node : list) {
//	    nodeBound = node.getBoundsInLocal();
//
//	    // double scaleX = pageLayout.getPrintableWidth() /
//	    // node.getBoundsInLocal().getWidth();
//	    // double scaleY = pageLayout.getPrintableHeight() /
//	    // node.getBoundsInLocal().getHeight();
//	    double scaleX = 1.00;
//	    double scaleY = 1.00;
//	    node.getTransforms().add(new Scale(scaleX, scaleY));
//	    System.out.printf("Page Transform Scale X = %.2f, Y = %.2f\n", scaleX, scaleY);
//	}
//	// System.out.printf("Page Transform Scale X = %.2f, Y = %.2f\n", scaleX,
//	// scaleY);
	super.setScale(1);
	PrinterJob job = PrinterJob.createPrinterJob();
	if (job != null) {
	    boolean success = print(job, true, list);
	    if (success) {
		job.endJob();
	    } else {
		System.out.println("Everything was NOT printed");
	    }
	}
    }

    public Rectangle getPrintRectangle() {
	if (printRectangle == null) {
	    printRectangle = new Rectangle(nodeBound.getWidth(), nodeBound.getHeight(), null);
	    printRectangle.setStroke(Color.BLACK);
	}
	return printRectangle;
    }
}
