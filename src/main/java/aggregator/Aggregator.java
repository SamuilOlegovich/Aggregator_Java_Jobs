package aggregator;


import aggregator.model.MoikrugStrategy;
import aggregator.model.HHStrategy;
import aggregator.view.SwingView;
import aggregator.model.Provider;
import aggregator.view.HtmlView;
import aggregator.model.Model;
import aggregator.view.View;

public class Aggregator {

    public static void main(String[] args) {
        View view = new HtmlView();
        View swingView = new SwingView();
        Model model = new Model(view, new Provider(new HHStrategy()), new Provider(new MoikrugStrategy()));
        model.setSwingVew(swingView);
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView)view).userCitySelectEmulationMethod();
//        ((SwingView)view).userCitySelectEmulationMethod();
    }
}

// Планы
//1. добавить еще 100500 других сайтов для агрегирования вакансий.
//      Нужно всего лишь создать стратегию, а потом добавить в модель провайдер с этой стратегией.
//2. отсортировать все вакансии, например, по дате создания (придется распарсить дату в полученном html).
//3. создать свою вью, например, на свинге. Подменить в main HtmlView на SwingView.
//4. собрать приложение в war-ник, развернуть Томкат, задеплоить приложение туда. Сделать, чтоб запрос приходил с браузера.
//

//Твои достижения:
//1. разобрался с паттерном Strategy,
//2. разобрался с самым популярным паттерном MVC (его очень часто спрашивают на собеседовании),
//3. научился парсить HTML,
//4. получил опыт работы с библиотекой Jsoup,
//5. научился подключать внешние либы в IDEA,
//6. получил опыт работы с внешними библиотеками,
//7. написал крутую архитектуру,
//8. стал больше знать и уметь,
//9. увидел, как раскладывать задачу на подзадачи,
//10. продвинулся на шаг ближе к работе джава программистом.
