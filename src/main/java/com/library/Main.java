package com.library;

import com.library.dao.ScenarioDao;
import com.library.ui.ScenarioMenu;

public class Main {

    public static void main(String[] args) {

        ScenarioDao dao = new ScenarioDao();

        ScenarioMenu menu = new ScenarioMenu(dao);

        menu.start();
    }
}