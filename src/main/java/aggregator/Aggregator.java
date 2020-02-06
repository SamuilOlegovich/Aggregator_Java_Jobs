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
//        SwingView app = new SwingView();
//        app.setVisible(true);

        View view = new HtmlView();
        Model model = new Model(view, new Provider(new HHStrategy()), new Provider(new MoikrugStrategy()));
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView)view).userCitySelectEmulationMethod();

    }
}