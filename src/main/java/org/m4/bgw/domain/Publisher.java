package org.m4.bgw.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@Entity
@Table(name = "publisher")
@RooJavaBean
@RooJpaEntity(versionField = "", table = "publisher")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "boardgames" })
public class Publisher {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("boardgames").toString();
    }

	@OneToMany(mappedBy = "publisher")
    private Set<Boardgame> boardgames;

	@Column(name = "website", length = 255, unique = true)
    @NotNull
    private String website;

	@Column(name = "logo")
    @NotNull
    private byte[] logo;

	public Set<Boardgame> getBoardgames() {
        return boardgames;
    }

	public void setBoardgames(Set<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }

	public String getWebsite() {
        return website;
    }

	public void setWebsite(String website) {
        this.website = website;
    }

	public byte[] getLogo() {
        return logo;
    }

	public void setLogo(byte[] logo) {
        this.logo = logo;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "name", length = 50)
    private String name;

	public String getName() {
        return this.name;
    }

	public void setName(String id) {
        this.name = id;
    }
}
