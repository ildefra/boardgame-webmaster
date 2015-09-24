// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.m4.bgw.domain.AvgGameLength;
import org.m4.bgw.domain.AvgGameLengthDataOnDemand;
import org.m4.bgw.domain.AvgGameLengthIntegrationTest;
import org.m4.bgw.domain.AvgGameLengthPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AvgGameLengthIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AvgGameLengthIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AvgGameLengthIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: AvgGameLengthIntegrationTest: @Transactional;
    
    @Autowired
    AvgGameLengthDataOnDemand AvgGameLengthIntegrationTest.dod;
    
    @Test
    public void AvgGameLengthIntegrationTest.testCountAvgGameLengths() {
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to initialize correctly", dod.getRandomAvgGameLength());
        long count = AvgGameLength.countAvgGameLengths();
        Assert.assertTrue("Counter for 'AvgGameLength' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AvgGameLengthIntegrationTest.testFindAvgGameLength() {
        AvgGameLength obj = dod.getRandomAvgGameLength();
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to initialize correctly", obj);
        AvgGameLengthPK id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to provide an identifier", id);
        obj = AvgGameLength.findAvgGameLength(id);
        Assert.assertNotNull("Find method for 'AvgGameLength' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AvgGameLength' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AvgGameLengthIntegrationTest.testFindAllAvgGameLengths() {
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to initialize correctly", dod.getRandomAvgGameLength());
        long count = AvgGameLength.countAvgGameLengths();
        Assert.assertTrue("Too expensive to perform a find all test for 'AvgGameLength', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AvgGameLength> result = AvgGameLength.findAllAvgGameLengths();
        Assert.assertNotNull("Find all method for 'AvgGameLength' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AvgGameLength' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AvgGameLengthIntegrationTest.testFindAvgGameLengthEntries() {
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to initialize correctly", dod.getRandomAvgGameLength());
        long count = AvgGameLength.countAvgGameLengths();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AvgGameLength> result = AvgGameLength.findAvgGameLengthEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AvgGameLength' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AvgGameLength' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AvgGameLengthIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to initialize correctly", dod.getRandomAvgGameLength());
        AvgGameLength obj = dod.getNewTransientAvgGameLength(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to provide a new transient entity", obj);
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'AvgGameLength' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AvgGameLengthIntegrationTest.testRemove() {
        AvgGameLength obj = dod.getRandomAvgGameLength();
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to initialize correctly", obj);
        AvgGameLengthPK id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AvgGameLength' failed to provide an identifier", id);
        obj = AvgGameLength.findAvgGameLength(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'AvgGameLength' with identifier '" + id + "'", AvgGameLength.findAvgGameLength(id));
    }
    
}
