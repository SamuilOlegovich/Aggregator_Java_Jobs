package aggregator.view;

import aggregator.Controller;
import aggregator.vo.Vacancy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.File;

public class HtmlView implements View {
    private Controller controller;
        // Путь должен быть относительно корня проекта.
        // Формируем путь динамически используя this.getClass().getPackage() и разделитель "/".
        // Подсказка: путь должен начинаться с "./".
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName()
            .replace('.', '/') + "/vacancies.html";


    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) {
        Document document = null;
        try {
                // Получаем распарсеную страницу с помощью метода getDocument()
            document = getDocument();

                // Получаем элемент, у которого есть класс template. Сделаем копию этого объекта,
                //      удаляем из нее атрибут "style" и класс "template".
            Element templateOriginal = document.getElementsByClass("template").first();
            Element copyTemplate = templateOriginal.clone();
            copyTemplate.removeAttr("style");
            copyTemplate.removeClass("template");

                // Удаляем из страницы все добавленные ранее вакансии с классом "vacancy".
                //      Элемент с классом "vacancy template" должен остаться.
            document.select("tr[class=vacancy]").remove().not("tr[class=vacancy template");

                // Перед объектом template для каждой вакансии добавляем на страницу отдельный html-элемент,
                //      используя копию template.
            for (Vacancy vacancy : vacancyList) {
                Element localClone = copyTemplate.clone();

                    // Для каждой вакансии должен быть корректно заполнен элемент с классом "city".
                localClone.getElementsByClass("city").first().text(vacancy.getCity());

                    // Для каждой вакансии должен быть корректно заполнен элемент с классом "companyName".
                localClone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());

                    // Для каждой вакансии должен быть корректно заполнен элемент с классом "salary".
                localClone.getElementsByClass("salary").first().text(vacancy.getSalary());

                    // Для каждой вакансии должен быть корректно заполнен элемент-ссылка
                    //      с названием вакансии(title) и http-ссылкой на нее(href).
                Element link = localClone.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateOriginal.before(localClone.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
                // В случае возникновения исключения, выведи его стек-трейс и верни строку
                //      "Some exception occurred".
            return "Some exception occurred";
        }
            // Возвращаем html-код всей страницы в качестве результата работы метода.
        return document.html();
    }

        // распарсивает файл vacancies.html используя Jsoup.
        // Кодировка файла "UTF-8", используй поле filePath
    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath),"UTF-8");
    }

        // Он принимает тело файла в виде строки.
        // Нужно его записать в файл, который находится по пути filePath.
    private void updateFile(String string) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        // сформируй новое тело файла vacancies.html, которое будет содержать вакансии,
        // запиши в файл vacancies.html его обновленное тело,
        // Все исключения должны обрабатываться в этом методе - выведи стек-трейс, если возникнет исключение.
    public void update(List<Vacancy> vacancies) {
//        System.out.println(vacancies.size());
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
