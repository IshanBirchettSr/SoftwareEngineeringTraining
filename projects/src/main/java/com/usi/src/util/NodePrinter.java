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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * Prints any given area of a node to multiple pages
 */
public class NodePrinter {
    private static final double SCREEN_TO_PRINT_DPI = 72d / 96d;

    private double scale = 1.0f;

    /**
     * This rectangle determines the portion to print in the world coordinate
     * system.
     */
    private Rectangle printRectangle;

    /**
     * Prints the given node.
     * 
     * @param job             The printer job which has the configurations for the
     *                        page layout etc. and does the actual printing.
     * @param showPrintDialog Whether or not the print dialog needs to be shown
     *                        prior to printing.
     * @param pageNodes       The content to print.
     * @return <code>true</code> if everything was printed, <code>false</code>
     *         otherwise
     */
    public boolean print(PrinterJob job, boolean showPrintDialog, List<Node> pageNodes) {

	Window window = pageNodes.get(0).getScene() != null ? pageNodes.get(0).getScene().getWindow() : null;

	if (!showPrintDialog || job.showPrintDialog(window)) {
	    boolean success = true;
	    System.out.printf("PageNodes %d\n", pageNodes.size());

	    for (int i = 0; i < pageNodes.size(); i++) {
		PageLayout pageLayout = job.getJobSettings().getPageLayout();
		System.out.printf("Node #%s\n", pageNodes.get(i).getId());
		success &= job.printPage(pageLayout, pageNodes.get(i));
//		
//		double pageWidth = pageLayout.getPrintableWidth();
//		double pageHeight = pageLayout.getPrintableHeight();
//		PrintInfo printInfo = getPrintInfo(pageLayout);
//		printRectangle = getPrintRectangle();
//		double printRectX = this.printRectangle.getX();
//		double printRectY = this.printRectangle.getY();
//		double printRectWidth = this.printRectangle.getWidth();
//		double printRectHeight = this.printRectangle.getHeight();
//
//		Node oldClip = node.getClip();
//		List<Transform> oldTransforms = new ArrayList<>(node.getTransforms());
//
//		// set the printingRectangle bounds as clip
//		node.setClip(new javafx.scene.shape.Rectangle(printRectX, printRectY, printRectWidth, printRectHeight));
//
//		int columns = printInfo.getColumnCount();
//		int rows = printInfo.getRowCount();
//
//		// by adjusting the scale, you can force the contents to be printed one page for
//		// example
//		double localScale = printInfo.getScale();
//
//		node.getTransforms().add(new Scale(localScale, localScale));
//		// move to 0,0
//		node.getTransforms().add(new Translate(-printRectX, -printRectY));
//
//		// the transform that moves the node to fit the current printed page in the grid
//		Translate gridTransform = new Translate();
//		node.getTransforms().add(gridTransform);
//		// for each page, move the node into position by adjusting the transform
//		// and call the print page method of the PrinterJob
//
//		System.out.printf(" : %d\n", rows);
////		for (int row = 0; row < rows; row++) {
////		    System.out.printf("Printing page %d\n", row);
////		    for (int col = 0; col < columns; col++) {
////			System.out.printf("Printing column %d\n", col);
////			gridTransform.setX(-col * pageWidth / localScale);
////			gridTransform.setY(-row * pageHeight / localScale);
////			success &= job.printPage(pageLayout, node);
////		    }
////		}
////		gridTransform.setX(-col * pageWidth / localScale);
////		gridTransform.setY(-row * pageHeight / localScale);
//		success &= job.printPage(pageLayout, node);
//		// restore the original transformation and clip values
//		node.getTransforms().clear();
//		node.getTransforms().addAll(oldTransforms);
//		node.setClip(oldClip);
	    }
	    if (success == true) {
		System.out.println("Success: true");
	    } else {
		System.out.println("Success: false");
		success = true;
	    }
	    return success;
	}
	return false;
    }

    /**
     * Returns a scale factor to apply for printing. A value of <code>0.72</code>
     * makes <code>96</code> units in the world coordinate system appear exactly one
     * inch long. The default value is <code>1.0</code>.
     */
    public double getScale() {
	return scale;
    }

    /**
     * Sets a scale factor to apply for printing. A value of <code>0.72</code> makes
     * <code>96</code> units in the world coordinate system appear exactly one inch
     * long. The default value is <code>1.0</code>.
     */
    public void setScale(final double scale) {
	this.scale = scale;
    }

    /**
     * Returns the rectangle that will be printed. This rectangle determines the
     * portion of the node to print in the world coordinate system.
     * 
     * @return a rectangle in the world coordinate system that defines the area of
     *         the contents of the node to print.
     */
    public Rectangle getPrintRectangle() {
	return printRectangle;
    }

    /**
     * Sets the rectangle that will be printed. This rectangle determines the
     * portion of the node to print in the world coordinate system.
     * 
     * @param printRectangle a rectangle in the world coordinate system that defines
     *                       the area of the contents of the node to print.
     */
    public void setPrintRectangle(final Rectangle printRectangle) {
	this.printRectangle = printRectangle;
    }

    /**
     * Determines the scale and the number of rows and columns needed to print the
     * determined contents of the component
     * 
     * @param pageLayout the {@link javafx.print.PageLayout} that defines the
     *                   printable area of a page.
     * @return a PrintInfo instance that encapsulates the computed values for scale,
     *         number of rows and columns.
     */
    public PrintInfo getPrintInfo(final PageLayout pageLayout) {

	double contentWidth = pageLayout.getPrintableWidth();
	double contentHeight = pageLayout.getPrintableHeight();

	double localScale = getScale() * SCREEN_TO_PRINT_DPI;

	final Rectangle printRect = getPrintRectangle();
	final double width = printRect.getWidth() * localScale;
	final double height = printRect.getHeight() * localScale;

	// calculate how many pages we need dependent on the size of the content and the
	// page.
	int cCount = (int) Math.ceil((width) / contentWidth);
	int rCount = (int) Math.ceil((height) / contentHeight);
	return new PrintInfo(localScale, rCount, cCount);
    }

    /**
     * Encapsulates information for printing with a specific
     * {@link javafx.print.PageLayout}, i.e. the scale dependent on the screen DPI
     * as well as the number of rows and columns for poster printing.
     */
    public static class PrintInfo {
	final double scale;
	final int rowCount;
	final int columnCount;

	/**
	 * Constructs a new PrintInfo instance.
	 * 
	 * @param scale       The scale of the content.
	 * @param rowCount    The number of rows that are needed to print the content
	 *                    completely with the {@link javafx.print.PageLayout}.
	 * @param columnCount The number of columns that are needed to print the content
	 *                    completely with the {@link javafx.print.PageLayout}.
	 */
	public PrintInfo(final double scale, final int rowCount, final int columnCount) {
	    this.scale = scale;
	    this.rowCount = rowCount;
	    this.columnCount = columnCount;
	}

	public double getScale() {
	    return scale;
	}

	public int getRowCount() {
	    return rowCount;
	}

	public int getColumnCount() {
	    return columnCount;
	}
    }
}