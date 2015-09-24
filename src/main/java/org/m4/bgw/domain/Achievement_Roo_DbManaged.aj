// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.Achievement;
import org.m4.bgw.domain.AchievementTranslation;

privileged aspect Achievement_Roo_DbManaged {
    
    @OneToMany(mappedBy = "achievementId")
    private Set<Achieved> Achievement.achieveds;
    
    @OneToMany(mappedBy = "achievementId")
    private Set<AchievementTranslation> Achievement.achievementTranslations;
    
    @Column(name = "points")
    @NotNull
    private Short Achievement.points;
    
    @Column(name = "name", length = 50, unique = true)
    @NotNull
    private String Achievement.name;
    
    public Set<Achieved> Achievement.getAchieveds() {
        return achieveds;
    }
    
    public void Achievement.setAchieveds(Set<Achieved> achieveds) {
        this.achieveds = achieveds;
    }
    
    public Set<AchievementTranslation> Achievement.getAchievementTranslations() {
        return achievementTranslations;
    }
    
    public void Achievement.setAchievementTranslations(Set<AchievementTranslation> achievementTranslations) {
        this.achievementTranslations = achievementTranslations;
    }
    
    public Short Achievement.getPoints() {
        return points;
    }
    
    public void Achievement.setPoints(Short points) {
        this.points = points;
    }
    
    public String Achievement.getName() {
        return name;
    }
    
    public void Achievement.setName(String name) {
        this.name = name;
    }
    
}
