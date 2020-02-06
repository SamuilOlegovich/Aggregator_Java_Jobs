package aggregator;


import aggregator.model.Model;

public class Controller {
//    private Provider[] providers;
    private Model model;

    public Controller(Model model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

//    public Controller(Provider... providers) {
//        if (providers == null || providers.length == 0) {
//            throw new IllegalArgumentException();
//        }
//        this.providers = providers;
//    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

//    public void scan() {
//        List<Vacancy> totalVacList = new ArrayList<>();
//        try{
//            for (Provider provider : providers) {
//                List<Vacancy> listVac = provider.getJavaVacancies("Moscow");
//                totalVacList.addAll(listVac);
//            }
//            System.out.println(totalVacList.size());
//        } catch (NullPointerException e){
//            System.out.println("Null_Pointer_Exception");
//        }
//    }

//    @Override
//    public String toString() {
//        return "Controller{" + "providers=" + Arrays.toString(providers) + '}';
//    }
}
