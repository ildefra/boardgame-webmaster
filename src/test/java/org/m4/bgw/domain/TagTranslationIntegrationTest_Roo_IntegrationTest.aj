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
import org.m4.bgw.domain.TagTranslation;
import org.m4.bgw.domain.TagTranslationDataOnDemand;
import org.m4.bgw.domain.TagTranslationIntegrationTest;
import org.m4.bgw.domain.TagTranslationPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TagTranslationIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TagTranslationIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TagTranslationIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: TagTranslationIntegrationTest: @Transactional;
    
    @Autowired
    TagTranslationDataOnDemand TagTranslationIntegrationTest.dod;
    
    @Test
    public void TagTranslationIntegrationTest.testCountTagTranslations() {
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to initialize correctly", dod.getRandomTagTranslation());
        long count = TagTranslation.countTagTranslations();
        Assert.assertTrue("Counter for 'TagTranslation' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TagTranslationIntegrationTest.testFindTagTranslation() {
        TagTranslation obj = dod.getRandomTagTranslation();
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to initialize correctly", obj);
        TagTranslationPK id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to provide an identifier", id);
        obj = TagTranslation.findTagTranslation(id);
        Assert.assertNotNull("Find method for 'TagTranslation' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'TagTranslation' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TagTranslationIntegrationTest.testFindAllTagTranslations() {
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to initialize correctly", dod.getRandomTagTranslation());
        long count = TagTranslation.countTagTranslations();
        Assert.assertTrue("Too expensive to perform a find all test for 'TagTranslation', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<TagTranslation> result = TagTranslation.findAllTagTranslations();
        Assert.assertNotNull("Find all method for 'TagTranslation' illegally returned null", result);
        Assert.assertTrue("Find all method for 'TagTranslation' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TagTranslationIntegrationTest.testFindTagTranslationEntries() {
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to initialize correctly", dod.getRandomTagTranslation());
        long count = TagTranslation.countTagTranslations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<TagTranslation> result = TagTranslation.findTagTranslationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'TagTranslation' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'TagTranslation' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TagTranslationIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to initialize correctly", dod.getRandomTagTranslation());
        TagTranslation obj = dod.getNewTransientTagTranslation(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to provide a new transient entity", obj);
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
        Assert.assertNotNull("Expected 'TagTranslation' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TagTranslationIntegrationTest.testRemove() {
        TagTranslation obj = dod.getRandomTagTranslation();
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to initialize correctly", obj);
        TagTranslationPK id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TagTranslation' failed to provide an identifier", id);
        obj = TagTranslation.findTagTranslation(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'TagTranslation' with identifier '" + id + "'", TagTranslation.findTagTranslation(id));
    }
    
}
