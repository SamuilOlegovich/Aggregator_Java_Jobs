package aggregator.model;


import aggregator.view.View;
import aggregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private View swingVew;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

        // получить вакансии с каждого провайдера,
        // обновить вью списком вакансий из п.5.1.
        public void selectCity(String city) {
            List<Vacancy> vacancyList = new ArrayList<Vacancy>();
            for (Provider provider : providers)
                vacancyList.addAll(provider.getJavaVacancies(city));
            swingVew.update(vacancyList);
            view.update(vacancyList);
        }

    public void setSwingVew(View swingVew) {
        this.swingVew = swingVew;
    }
}
