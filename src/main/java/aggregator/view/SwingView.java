package aggregator.view;


import aggregator.Controller;
import aggregator.vo.Vacancy;

import java.util.List;
import javax.swing.*;


public class SwingView extends JFrame implements View {
    private Controller controller;

    protected void showAllJobs(final List<Vacancy> list) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();

                for (Vacancy vacancy : list) {
                    stringBuilder.append(vacancy.getTitle()).append("\n");
                    stringBuilder.append(vacancy.getCity()).append("\n");
                    stringBuilder.append(vacancy.getCompanyName()).append("\n");
                    stringBuilder.append(vacancy.getSalary()).append("\n");
                    stringBuilder.append(vacancy.getUrl()).append("\n");
                    stringBuilder.append(vacancy.getSiteName()).append("\n");
                    stringBuilder.append("\n");
                    stringBuilder.append("\n");
                }

                JTextArea textArea = new JTextArea(stringBuilder.toString());
                JFrame frame = new JFrame("Aggregator JAVA jobs hh...");
                frame.add(new JScrollPane(textArea));
                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    public void update(List<Vacancy> vacancies) {
        showAllJobs(vacancies);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
}

