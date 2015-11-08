package org.m4.bgw.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

    @Column(name = "password_md5", length = 32)
    @NotNull
    private String passwordMd5;

    @Column(name = "registration_dtm")
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

    
	@OneToMany(mappedBy = "player")
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

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
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

	
	@PrePersist
	public void prePersist() {
	    hashPassword();
	    setRegistrationDtm(new GregorianCalendar());
	}
	
	@PreUpdate
	public void hashPassword() {
        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        digester.update(getPasswordMd5().getBytes());
        byte[] hash = digester.digest();
        String hashString = "";
        for (int i = 0; i < hash.length; i++) {
            hashString +=
                    ((0xff & hash[i]) < 0x10 ? "0" : "") + Integer.toHexString(0xFF & hash[i]);
        }
        setPasswordMd5(hashString);	    
	}
	

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        if (username == null) {
            if (other.username != null) return false;
        } else if (!username.equals(other.username)) return false;
        return true;
    }

    
    @Override
	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames(
                        "achieveds", "playeds", "sex", "levelId", "country"
                ).toString();
    }
}
