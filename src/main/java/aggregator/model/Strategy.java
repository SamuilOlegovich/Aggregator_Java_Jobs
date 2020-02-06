package aggregator.model;


import aggregator.vo.Vacancy;

import java.util.List;

// отвечает за получение данных с сайта
public interface Strategy {
        List<Vacancy> getVacancies(String searchString);
}
