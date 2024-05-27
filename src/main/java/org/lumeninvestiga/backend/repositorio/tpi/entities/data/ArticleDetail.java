package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "article_details"
)
public class ArticleDetail {
    private String area;
    private String subArea;
    private String period;
    private ODS_GOALS ODS;
    private Set<String> keywords;
    @OneToOne
    @JoinColumn(
            name = "article_id"
    )
    private Article article;

    public ArticleDetail() {
        this.area = "";
        this.subArea = "";
        this.period = "";
        this.keywords = new HashSet<>();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ODS_GOALS getODS() {
        return ODS;
    }

    public void setODS(ODS_GOALS ODS) {
        this.ODS = ODS;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
