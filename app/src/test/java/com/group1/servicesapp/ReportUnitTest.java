package com.group1.servicesapp;

import com.group1.servicesapp.logic.IReportLogic;
import com.group1.servicesapp.logic.ReportLogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ReportUnitTest {

    private IReportLogic report;

    @Before
    public void setUp(){
        report = new ReportLogic();
    }

    @Test
    public void testReportPass(){
        boolean output;

        output = report.reportPass(null);
        Assert.assertFalse(output);

        output = report.reportPass("");
        Assert.assertFalse(output);

        output = report.reportPass(" ");
        Assert.assertFalse(output);

        output = report.reportPass("                   ");
        Assert.assertFalse(output);

        output = report.reportPass("   hjkdfdsj    ");
        Assert.assertTrue(output);

        output = report.reportPass("$%%^&^&%&%$$##$^&*&*");
        Assert.assertTrue(output);

        output = report.reportPass("4573281432");
        Assert.assertTrue(output);

        output = report.reportPass("hey");
        Assert.assertTrue(output);

        output = report.reportPass("\\");
        Assert.assertTrue(output);
    }
}
