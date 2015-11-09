package org.m4.bgw.domain.translate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.Language;

@Entity
@Table(name = "external_link")
public class ExternalLink {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("boardgameId", "languageCode").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "external_link_id")
    private Integer externalLinkId;

	public Integer getExternalLinkId() {
        return this.externalLinkId;
    }

	public void setExternalLinkId(Integer id) {
        this.externalLinkId = id;
    }

	@ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false)
    private Boardgame boardgameId;

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false)
    private Language languageCode;

	@Column(name = "name", length = 100)
    @NotNull
    private String name;

	@Column(name = "is_video")
    @NotNull
    private boolean isVideo;

	public Boardgame getBoardgameId() {
        return boardgameId;
    }

	public void setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }

	public Language getLanguageCode() {
        return languageCode;
    }

	public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public boolean isIsVideo() {
        return isVideo;
    }

	public void setIsVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }
}
