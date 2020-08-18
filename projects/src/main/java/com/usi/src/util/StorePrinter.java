/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class StorePrinter implements Printable, ActionListener {
    @Override

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

	if (page > 0) { /* We have only one page, and 'page' is zero-based */
	    return NO_SUCH_PAGE;
	}

	/*
	 * User (0,0) is typically outside the imageable area, so we must translate by
	 * the X and Y values in the PageFormat to avoid clipping
	 */
	Graphics2D g2d = (Graphics2D) g;
	g2d.translate(pf.getImageableX(), pf.getImageableY());
	Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

	/* Now we perform our rendering */
	Font newFont = new Font(Font.SANS_SERIF, Font.BOLD, 16).deriveFont(fontAttributes);
	g.setFont(newFont);
	String storeName = StoreConstants.STORE_NAME;
	g.drawString(storeName, 50, 30);

	Date curDate = new Date();
	String todaysDate = DateFormat.getDateTimeInstance().format(curDate);
	String datePurchased = String.format("Date: %s", todaysDate);
	newFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	g.setFont(newFont);
	g.drawString(datePurchased, 50, 50);

	double total = 0.00;
	for (int i = 1; i < 10; i++) {
	    String prod = String.format("Product: %s, price: %.2f\n", "pr" + i, (i * (i * .25)));
	    total += (i * (i * .25));
	    g.drawString(prod, 50, ((50) + i * 20));
	}
	double totalAmount = total;
	String tt = String.format("Your total today is $%.2f", totalAmount);
	g.drawString(tt, 50, ((50) + 11 * 20));
	/* tell the caller that this page is part of the printed document */
	return PAGE_EXISTS;
    }

    public void actionPerformed1(ActionEvent e) {
	PrinterJob job = PrinterJob.getPrinterJob();
	job.setPrintable(this);
	boolean ok = job.printDialog();
	if (ok) {
	    try {
		job.print();
	    } catch (PrinterException ex) {
		System.out.println("The job did not successfully print");
	    }
	}
    }

    public void PrintDialog() {

	UIManager.put("swing.boldMetal", Boolean.FALSE);
	JFrame f = new JFrame("Super Store Printer");
	f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});
	JButton printButton = new JButton("Print Reciept");
	printButton.addActionListener(new StorePrinter());
	f.add("Center", printButton);
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	f.setBounds(size.width - 400, 0, 500, 400);
	f.setLocation(250, 250);
	// f.pack();
	f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }

}
