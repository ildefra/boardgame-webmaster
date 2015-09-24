// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.m4.bgw.Boardgame;
import org.m4.bgw.Language;
import org.m4.bgw.domain.ExternalLink;
import org.m4.bgw.domain.ExternalLinkDataOnDemand;
import org.springframework.stereotype.Component;

privileged aspect ExternalLinkDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ExternalLinkDataOnDemand: @Component;
    
    private Random ExternalLinkDataOnDemand.rnd = new SecureRandom();
    
    private List<ExternalLink> ExternalLinkDataOnDemand.data;
    
    public ExternalLink ExternalLinkDataOnDemand.getNewTransientExternalLink(int index) {
        ExternalLink obj = new ExternalLink();
        setBoardgameId(obj, index);
        setIsVideo(obj, index);
        setLanguageCode(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void ExternalLinkDataOnDemand.setBoardgameId(ExternalLink obj, int index) {
        Boardgame boardgameId = null;
        obj.setBoardgameId(boardgameId);
    }
    
    public void ExternalLinkDataOnDemand.setIsVideo(ExternalLink obj, int index) {
        Boolean isVideo = true;
        obj.setIsVideo(isVideo);
    }
    
    public void ExternalLinkDataOnDemand.setLanguageCode(ExternalLink obj, int index) {
        Language languageCode = null;
        obj.setLanguageCode(languageCode);
    }
    
    public void ExternalLinkDataOnDemand.setName(ExternalLink obj, int index) {
        String name = "name_" + index;
        if (name.length() > 100) {
            name = name.substring(0, 100);
        }
        obj.setName(name);
    }
    
    public ExternalLink ExternalLinkDataOnDemand.getSpecificExternalLink(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ExternalLink obj = data.get(index);
        Integer id = obj.getExternalLinkId();
        return ExternalLink.findExternalLink(id);
    }
    
    public ExternalLink ExternalLinkDataOnDemand.getRandomExternalLink() {
        init();
        ExternalLink obj = data.get(rnd.nextInt(data.size()));
        Integer id = obj.getExternalLinkId();
        return ExternalLink.findExternalLink(id);
    }
    
    public boolean ExternalLinkDataOnDemand.modifyExternalLink(ExternalLink obj) {
        return false;
    }
    
    public void ExternalLinkDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = ExternalLink.findExternalLinkEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'ExternalLink' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ExternalLink>();
        for (int i = 0; i < 10; i++) {
            ExternalLink obj = getNewTransientExternalLink(i);
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
            data.add(obj);
        }
    }
    
}
