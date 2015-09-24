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
import org.m4.bgw.domain.GameDesigner;
import org.m4.bgw.domain.GameDesignerDataOnDemand;
import org.m4.bgw.domain.GameDesignerIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GameDesignerIntegrationTest_Roo_IntegrationTest {
    
    declare @type: GameDesignerIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: GameDesignerIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: GameDesignerIntegrationTest: @Transactional;
    
    @Autowired
    GameDesignerDataOnDemand GameDesignerIntegrationTest.dod;
    
    @Test
    public void GameDesignerIntegrationTest.testCountGameDesigners() {
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to initialize correctly", dod.getRandomGameDesigner());
        long count = GameDesigner.countGameDesigners();
        Assert.assertTrue("Counter for 'GameDesigner' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void GameDesignerIntegrationTest.testFindGameDesigner() {
        GameDesigner obj = dod.getRandomGameDesigner();
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to initialize correctly", obj);
        Integer id = obj.getGameDesignerId();
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to provide an identifier", id);
        obj = GameDesigner.findGameDesigner(id);
        Assert.assertNotNull("Find method for 'GameDesigner' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'GameDesigner' returned the incorrect identifier", id, obj.getGameDesignerId());
    }
    
    @Test
    public void GameDesignerIntegrationTest.testFindAllGameDesigners() {
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to initialize correctly", dod.getRandomGameDesigner());
        long count = GameDesigner.countGameDesigners();
        Assert.assertTrue("Too expensive to perform a find all test for 'GameDesigner', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<GameDesigner> result = GameDesigner.findAllGameDesigners();
        Assert.assertNotNull("Find all method for 'GameDesigner' illegally returned null", result);
        Assert.assertTrue("Find all method for 'GameDesigner' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void GameDesignerIntegrationTest.testFindGameDesignerEntries() {
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to initialize correctly", dod.getRandomGameDesigner());
        long count = GameDesigner.countGameDesigners();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<GameDesigner> result = GameDesigner.findGameDesignerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'GameDesigner' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'GameDesigner' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void GameDesignerIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to initialize correctly", dod.getRandomGameDesigner());
        GameDesigner obj = dod.getNewTransientGameDesigner(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'GameDesigner' identifier to be null", obj.getGameDesignerId());
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
        Assert.assertNotNull("Expected 'GameDesigner' identifier to no longer be null", obj.getGameDesignerId());
    }
    
    @Test
    public void GameDesignerIntegrationTest.testRemove() {
        GameDesigner obj = dod.getRandomGameDesigner();
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to initialize correctly", obj);
        Integer id = obj.getGameDesignerId();
        Assert.assertNotNull("Data on demand for 'GameDesigner' failed to provide an identifier", id);
        obj = GameDesigner.findGameDesigner(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'GameDesigner' with identifier '" + id + "'", GameDesigner.findGameDesigner(id));
    }
    
}
