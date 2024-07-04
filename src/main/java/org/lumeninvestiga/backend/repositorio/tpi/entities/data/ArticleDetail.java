package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.*;

import java.util.HashSet;
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
    @Column(nullable = false)
    //TODO: Contemplar la existencia de más de un ODS.
    //@Enumerated(EnumType.STRING)
    private ODS_GOALS ODS;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String advisor;
    @Column(name = "resume", columnDefinition = "TEXT")
    private String resume;
    @Column(nullable = false)
    private Set<String> keywords;

    public ArticleDetail() {
        this.area = "";
        this.subArea = "";
        this.period = "";
        this.title = "";
        this.author = "";
        this.advisor = "";
        this.resume = "";
        this.keywords = new HashSet<>();
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

    public ODS_GOALS getODS() {
        return ODS;
    }

    public void setODS(ODS_GOALS ODS) {
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

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
