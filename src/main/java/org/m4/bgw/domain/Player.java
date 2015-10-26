package org.m4.bgw.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "username", length = 20)
    private String username;


    @Column(name = "email", length = 90, unique = true)
    private String email;

    @Column(name = "password", length = 20)
    @NotNull
    private String password;

    @Column(name = "registration_dtm")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar registrationDtm;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date birthDate;

    @Column(name = "motto")
    private String motto;


    @ManyToOne
    @JoinColumn(name = "sex", referencedColumnName = "iso_code", nullable = false)
    private Gender sex;

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "level_id", nullable = false)
    private UserLevel levelId;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "iso_alpha_2", nullable = false)
    private Country country;

    
	@OneToMany(mappedBy = "username")
    private Set<Achieved> achieveds;

	@OneToMany(mappedBy = "username")
    private Set<Played> playeds;



    public String getUsername() {
        return this.username;
    }

    public void setUsername(String id) {
        this.username = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getRegistrationDtm() {
        return registrationDtm;
    }

    public void setRegistrationDtm(Calendar registrationDtm) {
        this.registrationDtm = registrationDtm;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }


    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public UserLevel getLevelId() {
        return levelId;
    }

    public void setLevelId(UserLevel levelId) {
        this.levelId = levelId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    
	public Set<Achieved> getAchieveds() {
        return achieveds;
    }

	public void setAchieveds(Set<Achieved> achieveds) {
        this.achieveds = achieveds;
    }

	public Set<Played> getPlayeds() {
        return playeds;
    }

	public void setPlayeds(Set<Played> playeds) {
        this.playeds = playeds;
    }


	@Override
	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames(
                        "achieveds", "playeds", "sex", "levelId", "country"
                ).toString();
    }
}
