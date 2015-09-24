// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.m4.bgw.Boardgame;
import org.m4.bgw.Country;
import org.m4.bgw.Language;
import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.Gender;
import org.m4.bgw.domain.Played;
import org.m4.bgw.domain.Player;
import org.m4.bgw.domain.UserLevel;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Player_Roo_DbManaged {
    
    @ManyToMany
    @JoinTable(name = "friendship", joinColumns = { @JoinColumn(name = "username1", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "username2", nullable = false) })
    private Set<Player> Player.players1;
    
    @ManyToMany(mappedBy = "players")
    private Set<Language> Player.languages;
    
    @ManyToMany(mappedBy = "players1")
    private Set<Player> Player.players2;
    
    @OneToMany(mappedBy = "username")
    private Set<Achieved> Player.achieveds;
    
    @OneToMany(mappedBy = "developedBy")
    private Set<Boardgame> Player.boardgames;
    
    @OneToMany(mappedBy = "username")
    private Set<Played> Player.playeds;
    
    @ManyToOne
    @JoinColumn(name = "sex", referencedColumnName = "gender_id", nullable = false)
    private Gender Player.sex;
    
    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "level_id", nullable = false)
    private UserLevel Player.levelId;
    
    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "iso_alpha_2", nullable = false)
    private Country Player.country;
    
    @Column(name = "email", length = 90, unique = true)
    private String Player.email;
    
    @Column(name = "password", length = 20)
    @NotNull
    private String Player.password;
    
    @Column(name = "registration_dtm")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date Player.registrationDtm;
    
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date Player.birthDate;
    
    @Column(name = "bio")
    private String Player.bio;
    
    @Column(name = "is_premium")
    @NotNull
    private boolean Player.isPremium;
    
    public Set<Player> Player.getPlayers1() {
        return players1;
    }
    
    public void Player.setPlayers1(Set<Player> players1) {
        this.players1 = players1;
    }
    
    public Set<Language> Player.getLanguages() {
        return languages;
    }
    
    public void Player.setLanguages(Set<Language> languages) {
        this.languages = languages;
    }
    
    public Set<Player> Player.getPlayers2() {
        return players2;
    }
    
    public void Player.setPlayers2(Set<Player> players2) {
        this.players2 = players2;
    }
    
    public Set<Achieved> Player.getAchieveds() {
        return achieveds;
    }
    
    public void Player.setAchieveds(Set<Achieved> achieveds) {
        this.achieveds = achieveds;
    }
    
    public Set<Boardgame> Player.getBoardgames() {
        return boardgames;
    }
    
    public void Player.setBoardgames(Set<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }
    
    public Set<Played> Player.getPlayeds() {
        return playeds;
    }
    
    public void Player.setPlayeds(Set<Played> playeds) {
        this.playeds = playeds;
    }
    
    public Gender Player.getSex() {
        return sex;
    }
    
    public void Player.setSex(Gender sex) {
        this.sex = sex;
    }
    
    public UserLevel Player.getLevelId() {
        return levelId;
    }
    
    public void Player.setLevelId(UserLevel levelId) {
        this.levelId = levelId;
    }
    
    public Country Player.getCountry() {
        return country;
    }
    
    public void Player.setCountry(Country country) {
        this.country = country;
    }
    
    public String Player.getEmail() {
        return email;
    }
    
    public void Player.setEmail(String email) {
        this.email = email;
    }
    
    public String Player.getPassword() {
        return password;
    }
    
    public void Player.setPassword(String password) {
        this.password = password;
    }
    
    public Date Player.getRegistrationDtm() {
        return registrationDtm;
    }
    
    public void Player.setRegistrationDtm(Date registrationDtm) {
        this.registrationDtm = registrationDtm;
    }
    
    public Date Player.getBirthDate() {
        return birthDate;
    }
    
    public void Player.setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String Player.getBio() {
        return bio;
    }
    
    public void Player.setBio(String bio) {
        this.bio = bio;
    }
    
    public boolean Player.isIsPremium() {
        return isPremium;
    }
    
    public void Player.setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }
    
}
