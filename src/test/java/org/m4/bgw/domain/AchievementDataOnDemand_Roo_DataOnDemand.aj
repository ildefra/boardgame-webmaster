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
import org.m4.bgw.domain.Achievement;
import org.m4.bgw.domain.AchievementDataOnDemand;
import org.springframework.stereotype.Component;

privileged aspect AchievementDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AchievementDataOnDemand: @Component;
    
    private Random AchievementDataOnDemand.rnd = new SecureRandom();
    
    private List<Achievement> AchievementDataOnDemand.data;
    
    public Achievement AchievementDataOnDemand.getNewTransientAchievement(int index) {
        Achievement obj = new Achievement();
        setName(obj, index);
        setPoints(obj, index);
        return obj;
    }
    
    public void AchievementDataOnDemand.setName(Achievement obj, int index) {
        String name = "name_" + index;
        if (name.length() > 50) {
            name = new Random().nextInt(10) + name.substring(1, 50);
        }
        obj.setName(name);
    }
    
    public void AchievementDataOnDemand.setPoints(Achievement obj, int index) {
        Short points = new Integer(index).shortValue();
        obj.setPoints(points);
    }
    
    public Achievement AchievementDataOnDemand.getSpecificAchievement(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Achievement obj = data.get(index);
        Short id = obj.getAchievementId();
        return Achievement.findAchievement(id);
    }
    
    public Achievement AchievementDataOnDemand.getRandomAchievement() {
        init();
        Achievement obj = data.get(rnd.nextInt(data.size()));
        Short id = obj.getAchievementId();
        return Achievement.findAchievement(id);
    }
    
    public boolean AchievementDataOnDemand.modifyAchievement(Achievement obj) {
        return false;
    }
    
    public void AchievementDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Achievement.findAchievementEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Achievement' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Achievement>();
        for (int i = 0; i < 10; i++) {
            Achievement obj = getNewTransientAchievement(i);
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
