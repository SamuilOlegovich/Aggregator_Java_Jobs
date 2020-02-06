package aggregator.model;

import aggregator.vo.Vacancy;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Почти полная аналогия класса HHStrategy только для другого сайта
public class MoikrugStrategy implements Strategy {

private static final String GOOGLE_RU = "http://google.ru";
private static final String SITE_NAME = "https://moikrug.ru";
private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36(KHTML, like Gecko) " +
    "Chrome/73.0.3683.103 Safari/537.36";


protected Document getDocument(String searchString, int page) throws IOException {
return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
        .userAgent(USER_AGENT).referrer(GOOGLE_RU).get();
}

//@Override
public List<Vacancy> getVacancies(String searchString) {
List<Vacancy> listVacancy = new ArrayList<Vacancy>();
Document doc = null;
int pageNumber = 0;

try {
    while (true) {
        doc = getDocument(searchString, pageNumber++);
        Elements elements = doc.getElementsByAttributeValue("class", "job");
        elements.addAll( doc.getElementsByAttributeValue("class", "job marked"));

        if (elements.size() == 0) break;

        for (Element element : elements) {
            Vacancy vacancy = new Vacancy();
            vacancy.setTitle(element.getElementsByClass("title").first().text().trim());
            vacancy.setSalary(element.getElementsByClass("salary").first().text().trim());
            vacancy.setCity(element.select("span[class=location]").text().trim());
            vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
            vacancy.setSiteName(SITE_NAME);
            vacancy.setUrl(SITE_NAME + element.select("div[class=title]").first()
                    .getElementsByTag("a").attr("href").trim());
            listVacancy.add(vacancy);
        }
    }
} catch (IOException e) { e.printStackTrace(); }
return listVacancy;
}
}
