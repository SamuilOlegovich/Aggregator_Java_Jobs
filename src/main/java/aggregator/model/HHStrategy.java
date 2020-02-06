package aggregator.model;


import aggregator.vo.Vacancy;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер
//      (http://hh.ua/ и http://hh.ru/).
public class HHStrategy implements Strategy {

private static final String GOOGLE_RU = "http://google.ru";
private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36" +
    "(KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36";

private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
// "http://hh.ru/search/vacancy?text=java+Kiev&page=3"

private String URL = String.format(URL_FORMAT, "Kiev", 3);

protected Document getDocument(String searchString, int page) throws IOException {
return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
        .userAgent(USER_AGENT).referrer(GOOGLE_RU).get();
}

// Чтобы Хэдхантер знал, кто к нему коннектится, проставим Request Headers в наш запрос.
//
// Для разработчиков созданы инструменты, которые показывают различную информацию про запросы.
// Я расскажу тебе про два инструмента.
//
// ***Chrome****
// 1. В браузере Хром пойди в Меню - Инструменты - Инструменты разработчика, или нажми Ctrl+Shift+I
// 2. В браузерной строке набери URL http://hh.ua/search/vacancy?text=java+киев
// 3. Перейди на табу Network(Сеть), запрашиваемый URL должен быть в самом верху.
// У него метод отправки данных GET (еще есть POST), статус 200(успешно)
// 4. Выбери его, откроется информация о страничке.
// 5. Найди Request Headers(Заголовки запроса)
//
// ***FireFox***
// 1. Для FireFox есть плагин FireBug. Ставь FireBug (Меню - Дополнения - Поиск - FireBug, установить).
// 2. Правой клавишей мыши - Инспектировать элемент с помощью FireBug. Перейди на табу Net(Сеть), подменю HTML.
// 3. В браузерной строке набери URL http://hh.ua/search/vacancy?text=java+киев
// 4. В подменю HTML появился список запросов включая набранный в браузерной строке.
//      Нажми на нем и зайди в меню "Заголовки"
// 5. Найди Request Headers(Заголовки запроса)
//
// Добавь в коннекшен к урлу Хедхантера userAgent и referrer.
//
// Требования:
// 1. В методе getVacancies класса HHStrategy после создания коннекшена добавь заголовок userAgent.
// 2. В методе getVacancies класса HHStrategy после создания коннекшена добавь заголовок referrer.

// Требования:
// 1. В классе HHStrategy создай protected метод getDocument(String searchString, int page).
//      Перенеси туда логику по получению объекта html-страницы Document.
// 2. Метод getVacancies класса HHStrategy должен получать содержимое страниц с помощью метода getDocument.
//      Начни с 0 страницы.
// 3. Из объекта Document получи список html-элементов с атрибутом "vacancy-serp__vacancy".
//      Для каждого элемента создай объект вакансии и добавь его в возвращающий методом список.
// 4. Нужно последовательно обработать все страницы результатов поиска.
//      Как только страницы с вакансиями закончатся, прерви цикл и верни список найденных вакансий.
// 5. У каждой вакансии должно быть заполнено поле title полученными из html-элемента данными о названии вакансии.
// 6. У каждой вакансии должно быть заполнено поле url полученной из html-элемента ссылкой на вакансию.
// 7. У каждой вакансии должно быть заполнено поле city полученными из html-элемента данными о городе.
// 8. У каждой вакансии должно быть заполнено поле companyName полученными из html-элемента данными о компании.
// 9. У каждой вакансии должно быть заполнено поле siteName значением сайта, на котором вакансия была найдена.
// 10. Поле salary у вакансии должно быть заполнено, если в html-элементе присутствовал тег с зарплатой.
//      Иначе поле должно быть инициализировано пустой строкой.
// 11. Если ты менял значение поля URL_FORMAT, не забудь вернуть его обратно.
//@Override
public List<Vacancy> getVacancies(String searchString) {
List<Vacancy> listVacancy = new ArrayList<Vacancy>();
int pageNumber = 0;
try {
    while (true) {
        Document doc = getDocument(searchString, pageNumber);
        Elements elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

        if (elements.size() == 0) break;

        for (Element element : elements) {
            Vacancy vacancy = new Vacancy();

            vacancy.setTitle(element.getElementsByAttributeValueContaining
                    ("data-qa", "vacancy-serp__vacancy-title").text().trim());
            vacancy.setCity(element.getElementsByAttributeValueContaining
                    ("data-qa", "vacancy-serp__vacancy-address").text().trim());
            vacancy.setCompanyName(element.getElementsByAttributeValueContaining
                    ("data-qa", "vacancy-serp__vacancy-employer").text().trim());
            vacancy.setUrl(element.getElementsByAttributeValueContaining
                    ("data-qa", "vacancy-serp__vacancy-title").attr("href").trim());
            vacancy.setSalary(element.getElementsByAttributeValueContaining
                    ("data-qa", "vacancy-serp__vacancy-compensation").text().trim());
            vacancy.setSiteName(String.format(URL_FORMAT, searchString, pageNumber));

            listVacancy.add(vacancy);
        }
        pageNumber++;
    }
} catch (IOException e) {
    e.printStackTrace();
}
return listVacancy;
}
}
