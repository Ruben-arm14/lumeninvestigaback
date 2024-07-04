package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "article_details"
)
public class ArticleDetail {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(nullable = false)
    private String area;
    @Column(name = "sub_area", nullable = false)
    private String subArea;
    @Column(nullable = false)
    private String period;
    @Column(name = "ods", nullable = false)
    private String ODS;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String advisor;
    @Column(nullable = false ,columnDefinition = "TEXT")
    private String resume;
    @Column(nullable = false)
    private String keywords;

    public ArticleDetail() {
        this.area = "";
        this.subArea = "";
        this.period = "";
        this.ODS = "";
        this.title = "";
        this.author = "";
        this.advisor = "";
        this.resume = "";
        this.keywords = "";
    }

    public Long getId() {
        return id;
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

    public String getODS() {
        return ODS;
    }

    public void setODS(String ODS) {
        this.ODS = ODS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ArticleDetail that = (ArticleDetail) object;
        return Objects.equals(id, that.id) && Objects.equals(area, that.area) && Objects.equals(subArea, that.subArea) && Objects.equals(period, that.period) && Objects.equals(ODS, that.ODS) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(advisor, that.advisor) && Objects.equals(resume, that.resume) && Objects.equals(keywords, that.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, subArea, period, ODS, title, author, advisor, resume, keywords);
    }
}
