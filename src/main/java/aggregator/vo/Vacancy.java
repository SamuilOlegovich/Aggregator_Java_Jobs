package aggregator.vo;


import java.util.Objects;

public class Vacancy {
    private String url;
    private String city;
    private String title;
    private String salary;
    private String siteName;
    private String companyName;

    public String getUrl() {
        return url;
    }

    public String getCity() {
        return city;
    }

    public String getTitle() {
        return title;
    }

    public String getSalary() {
        return salary;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return getUrl().equals(vacancy.getUrl()) &&
                getCity().equals(vacancy.getCity()) &&
                getTitle().equals(vacancy.getTitle()) &&
                getSalary().equals(vacancy.getSalary()) &&
                getSiteName().equals(vacancy.getSiteName()) &&
                getCompanyName().equals(vacancy.getCompanyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getCity(), getTitle(), getSalary(), getSiteName(), getCompanyName());
    }
}
