package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "article_details")
public class ArticleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String area;
    @Column(name = "sub_area", nullable = false)
    private String subArea;
    @Column(nullable = false)
    private String period;
    @Column(name = "ods", nullable = false)
    private String ods;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String advisor;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String resume;
    @Column(nullable = false)
    private String keywords;
    @Column(nullable = false)
    private String curso;
    @Column(nullable = false)
    private String profesor;

    public ArticleDetail() {
        this.area = "";
        this.subArea = "";
        this.period = "";
        this.ods = "";
        this.title = "";
        this.author = "";
        this.advisor = "";
        this.resume = "";
        this.keywords = "";
        this.curso = "";
        this.profesor = "";
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

    public String getOds() {
        return ods;
    }

    public void setOds(String ods) {
        this.ods = ods;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ArticleDetail that = (ArticleDetail) object;
        return Objects.equals(id, that.id) &&
                Objects.equals(area, that.area) &&
                Objects.equals(subArea, that.subArea) &&
                Objects.equals(period, that.period) &&
                Objects.equals(ods, that.ods) &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(advisor, that.advisor) &&
                Objects.equals(resume, that.resume) &&
                Objects.equals(keywords, that.keywords) &&
                Objects.equals(curso, that.curso) &&
                Objects.equals(profesor, that.profesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, subArea, period, ods, title, author, advisor, resume, keywords, curso, profesor);
    }
}
